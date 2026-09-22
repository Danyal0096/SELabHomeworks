# Run from exp3 or any location. Captures real, not simulated, baseline outputs.
# Preserve the original four active tests and three commented feature tests.
param([switch]$WithMutation)
$ErrorActionPreference = 'Stop'
$project = (Resolve-Path (Join-Path $PSScriptRoot '..')).Path
Set-Location $project
$evidence = Join-Path $project 'evidence/baseline'
New-Item -ItemType Directory -Force -Path $evidence | Out-Null
if (-not (Get-Command mvn -ErrorAction SilentlyContinue)) {
    Write-Error 'Maven (mvn) not found. Install/configure it and rerun; no results were fabricated.'
    exit 127
}
if (-not (Get-Command java -ErrorAction SilentlyContinue)) {
    Write-Error 'Java not found.'
    exit 127
}
$src = Join-Path $project 'src/test/java/ShoppingCartTest.java'
Get-FileHash -Path $src -Algorithm SHA256 | Format-List | Out-File -Encoding utf8 (Join-Path $evidence 'original-test-sha256.txt')
& java -version 2>&1 | Out-File -Encoding utf8 (Join-Path $evidence 'java-version.txt')
& mvn -version 2>&1 | Out-File -Encoding utf8 (Join-Path $evidence 'maven-version.txt')
& mvn -B clean verify 2>&1 | Tee-Object -FilePath (Join-Path $evidence 'maven-clean-verify.txt')
$testExit = $LASTEXITCODE
"mvn -B clean verify exit code: $testExit" | Out-File -Encoding utf8 (Join-Path $evidence 'exit-codes.txt')
if ($testExit -eq 0) {
    $jacoco = Join-Path $project 'target/site/jacoco/jacoco.xml'
    if (Test-Path $jacoco) { Copy-Item $jacoco (Join-Path $evidence 'jacoco.xml') -Force }
    $surefire = Join-Path $project 'target/surefire-reports'
    if (Test-Path $surefire) {
        Get-ChildItem $surefire -Filter '*.xml' | Copy-Item -Destination $evidence -Force
    }
}
if ($WithMutation -and $testExit -eq 0) {
    & mvn -B org.pitest:pitest-maven:1.19.4:mutationCoverage 2>&1 | Tee-Object -FilePath (Join-Path $evidence 'pitest.txt')
    $pitExit = $LASTEXITCODE
    "PIT exit code: $pitExit" | Add-Content -Encoding utf8 (Join-Path $evidence 'exit-codes.txt')
    $mutations = Join-Path $project 'target/pit-reports/mutations.xml'
    if (Test-Path $mutations) { Copy-Item $mutations (Join-Path $evidence 'mutations.xml') -Force }
    if ($pitExit -ne 0) { exit $pitExit }
}
if ($testExit -ne 0) { exit $testExit }
Write-Host "Baseline outputs captured at: $evidence"
