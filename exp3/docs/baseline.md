# Baseline — source audit completed; executable JUnit/JaCoCo/PIT pending

**Source of truth:** uploaded `base-project-for-tdd-shoppingcart-main.zip`, compared against GitHub `main` commit `81fa448bd36f4fc9f2fd4fe4eac71539ea257f37` on 2026-09-22. See `source-audit.md` for exact file hashes, API architecture, all original test names, and commented tests.

## Environment and provenance

| Field | Current verified status |
| --- | --- |
| Required base URL | https://hamgit.ir/sqrlab-public/base-project-for-tdd-shoppingcart (from handout) |
| Student-supplied archive | Uploaded ZIP inspected on 2026-09-22 |
| Upstream Hamgit commit / license | Not supplied in ZIP; not verified |
| GitHub integration | Four moved Java files match ZIP byte-for-byte at `main` commit `81fa448bd36f4fc9f2fd4fe4eac71539ea257f37` |
| Original project build metadata | IntelliJ `.iml` + `.idea`; Java 17, JUnit 5.8.1 |
| Reproducible Maven build | Proposed `exp3/pom.xml` in initialization overlay; not yet executed |
| Local sandbox observation | OpenJDK 21.0.11, production `javac` passed; JUnit/Maven unavailable |
| Student environment | Previously used Java 25 / Maven; versions and results for **this experiment** not yet verified |

## Original tests (four active, three commented)

| Test | Tested behavior | JUnit result |
| --- | --- | --- |
| `testAddItem` | one item / count / subtotal | Not run |
| `testRemoveItem` | removal existing item | Not run |
| `testDiscountAtBoundary_WRONG` | discounts subtotal of exactly 100 | Not run; conflicts with handout |
| `testDiscountAboveThreshold` | 120 → 108 | Not run |

Three commented tests: `testUpdateItemPrice_ShouldChangePrice`, `testUpdateItemPrice_ShouldNotChangeCount`, `testUpdateItemPrice_ItemNotFound_ShouldDoNothing`. **Leave commented during baseline.**

## Observations and limitations

- Internal storage uses `HashMap<String,Double>`; adding an existing name overwrites its price. Item count is distinct keys, not quantity.
- `removeItem` already returns false on absent keys: not a demonstrated defect.
- `getTotalWithDiscount` uses `>= 100`, while handout requires `> 100`. Original test also expects the opposite; see `ta-clarifications.md`.
- `updateItemPrice(String,int)` is empty; handout requests `(String,double)`. Do not claim a missing method or unconditional compilation failure.
- `Item` exists but `ShoppingCart` does not use that class internally.
- `Main` contains IntelliJ-generated demo code, not a cart scenario.

## Execution gate — pending on student machine / CI

Run from `exp3/` **before any production or original-test edits**, preserving all logs and exit codes:

```powershell
mvn -B clean verify
mvn -B org.pitest:pitest-maven:1.19.4:mutationCoverage
```

Optional capture helper: `powershell -File .\scripts\capture-baseline.ps1 -WithMutation`. Inspect `target/surefire-reports/`, `target/site/jacoco/`, and `target/pit-reports/`; record exact numbers in `coverage-mutation.md` and observed results here.

| Required metric | Result |
| --- | --- |
| Original JUnit pass / fail count | **Not measured** |
| JaCoCo line coverage (ShoppingCart) | **Not measured** |
| JaCoCo branch coverage (ShoppingCart) | **Not measured** |
| JaCoCo method coverage (ShoppingCart) | **Not measured** |
| PIT mutation score (ShoppingCart) | **Not measured** |

This static audit must **not** be passed off as the baseline test execution. Keep original source unchanged until baseline evidence exists.
