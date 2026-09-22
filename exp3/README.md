# Software Engineering Lab — Experiment 3: TDD

**Status (2026-09-22): base project acquired, files compared to GitHub `main`, original production classes compiled in an independent Java 21 environment; Maven/JUnit/JaCoCo/PIT baseline not yet run.** This README documents initialization, not completed assignment results.

## Provenance and preservation

- Required course base: https://hamgit.ir/sqrlab-public/base-project-for-tdd-shoppingcart
- Uploaded source snapshot: `base-project-for-tdd-shoppingcart-main.zip` supplied by student (upstream commit SHA **not supplied**).
- At GitHub `main` commit `81fa448bd36f4fc9f2fd4fe4eac71539ea257f37`, the four moved Java files match the uploaded source **byte-for-byte**; see `docs/source-audit.md`.
- Original layout: `src/{Item,Main,ShoppingCart}.java` and `Test/ShoppingCartTest.java` as an IntelliJ module (JDK 17, JUnit 5.8.1). Current repository layout: `src/main/java/` and `src/test/java/` respectively, which preserves file contents and supports Maven.
- Do **not** edit the original four active tests. Only uncomment the three existing feature tests at the prescribed TDD step; write all other tests separately.

## Run on Windows / VS Code

From the existing `SELabHomeworks/` checkout:

```powershell
cd exp3
java -version
mvn -version
mvn -B clean verify
mvn -B org.pitest:pitest-maven:1.19.4:mutationCoverage
```

`mvn -B clean verify` runs JUnit and produces JaCoCo reports at `target/site/jacoco/` (HTML + `jacoco.xml`); PIT writes `target/pit-reports/`. **These commands are configured, not yet verified in this environment.** A separate optional evidence-capture script is provided:

```powershell
pwsh -File .\scripts\capture-baseline.ps1 -WithMutation
# For Windows PowerShell, use: powershell -File .\scripts\capture-baseline.ps1 -WithMutation
```

JDK 17+ and Maven are required. The provided IntelliJ module targets 17 and the POM compiles with `--release 17`; running Maven on JDK 25 is intended to be supported. JaCoCo 0.8.14 officially supports Java 25. If PIT or Maven shows an error, preserve the **actual** log before adjusting configuration. Java / JUnit / Maven / PIT success cannot be inferred from compiling production classes alone.

## Read before changing code

1. `docs/source-audit.md` — the actual source, original tests, code-level observations, and GitHub comparison.
2. `docs/ta-clarifications.md` — the **blocking** contradiction: test expects a 10% discount at exactly 100, while the handout explicitly forbids it. Do not secretly change/remove/disable that test.
3. `docs/baseline.md` — verified observations and still-pending test/coverage/mutation measurements.
4. `docs/test-plan.md` + `docs/feature-contracts.md` — proposed tests vs. decisions requiring explicit contract choices.
5. `docs/tdd-log.md`, `docs/codex-interactions.md`, `docs/bug-analysis.md` — record genuine Red/Green/Refactor stages and at least 12 *real* Codex interactions, never invent evidence.
6. `docs/submission-checklist.md` — handout requires a **public Hamgit** repository; the GitHub course repository alone is not the required submission.

## TDD order

Establish and commit original baseline → capture original JaCoCo and PIT → characterize and test three **genuinely independent** defects → prove each Red, implement minimal Green, refactor → uncomment 3 supplied update tests + write at least 5 more meaningful tests before implementing the feature → implement second feature through TDD → rerun metrics → Persian report + Hamgit submission.

**Known inconsistency:** `updateItemPrice(String,int)` already exists as an empty stub, while the handout specifies `updateItemPrice(String,double)`; the three commented calls use integer literals and are therefore compilable with the stub. Their observed failure needs an actual JUnit run and should not be described as a missing-method compilation error.
