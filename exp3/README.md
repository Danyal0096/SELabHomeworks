# Software Engineering Lab — Experiment 3: Test-Driven Development

**Status: Completed and merged into `main` via [PR #21](https://github.com/Danyal0096/SELabHomeworks/pull/21).**

This experiment develops a Java shopping cart through regression tests and documented RED/GREEN steps, then evaluates the test suite with JaCoCo and PIT. The [final Persian report](docs/report-fa.md) contains the detailed analysis and results.

## Implementation

The original four active tests were preserved. The three supplied `updateItemPrice` tests were uncommented, and additional tests were written in separate classes.

- **Three investigated defects:** decimal-total precision, acceptance of invalid prices, and acceptance of null or blank item names. The fixes use `BigDecimal` when summing prices and validate input before modifying cart state. The [bug analysis](docs/bug-analysis.md) links each defect to its RED/GREEN evidence and commits.
- **Price updates:** `updateItemPrice(String, double)` updates an existing item's price without changing the distinct-item count, returns `false` for an absent item, accepts zero, and rejects invalid names and negative or non-finite prices. Ten cases existed before implementation; later tests strengthened validation coverage.
- **Configurable capacity:** `ShoppingCart(int maxItems)` limits the number of distinct item names. A full cart rejects a new name but permits replacement of an existing item; removing an item frees a slot. The no-argument constructor retains the original practical default.
- **Advanced tests:** parameterized capacity cases, exception and invalid-input cases, and a multistep add/update/remove scenario. See the [TDD log](docs/tdd-log.md) and [feature contracts](docs/feature-contracts.md) for the actual development sequence.

**Discount-boundary note:** The original source and supplied test apply the 10% discount when the subtotal is **at least 100**. That original behavior was preserved rather than changing the supplied test; the [report](docs/report-fa.md) explains the difference from the handout's boundary wording.

## Run the project

Requirements: **JDK 17 or newer** and **Apache Maven**. The Maven compiler targets Java 17; the independently verified run used Temurin Java 25.0.4.1 and Maven 3.9.16 on Windows 11.

From `exp3/`:

```powershell
mvn -B clean verify
mvn -B org.pitest:pitest-maven:1.19.4:mutationCoverage
```

`pom.xml` configures the existing `src/` and `Test/` directories, JUnit Jupiter 5.8.1, JaCoCo 0.8.14, and PIT 1.19.4. Generated test reports appear under `target/surefire-reports/`, JaCoCo under `target/site/jacoco/`, and PIT under `target/pit-reports/`.

## Verified results

The committed [clean-checkout verification record](evidence/final-verification/clean-checkout-cx08.txt) reports that both commands succeeded on executable commit [`3d7f3bf`](https://github.com/Danyal0096/SELabHomeworks/commit/3d7f3bf9d6fccb937e77cb7b2a95a6862080fd3f): **43 tests passed, 0 failures, 0 errors, 0 skipped**; PIT killed **28/28 mutations generated for `ShoppingCart`**.

| `ShoppingCart` metric | Baseline | Final |
| --- | ---: | ---: |
| JaCoCo line coverage | 16/19 (84.21%) | 38/38 (100%) |
| JaCoCo branch coverage | 4/6 (66.67%) | 30/30 (100%) |
| JaCoCo method coverage | 6/7 (85.71%) | 8/8 (100%) |
| PIT mutations killed | 9/11 (81.82%) | 28/28 (100%) |

These are **class-scoped** measurements. The implementation and selected PIT tests changed between baseline and final runs, so the mutation percentages are not a like-for-like comparison of identical mutants. See the [coverage and mutation analysis](docs/coverage-mutation.md) and retained [baseline](evidence/baseline/) and [final](evidence/final-verification/) XML files.

## Report and development evidence

The [Persian final report](docs/report-fa.md) documents the baseline, three defect investigations, two features, edge cases, measurements, and reproduction instructions. The [Codex interaction register](docs/codex-interactions.md) records **12 genuine interactions**; where full historical transcripts or qualitative evaluations were unavailable, it identifies those limitations instead of inventing them. RED/GREEN commits and saved test-class results are indexed in the [TDD log](docs/tdd-log.md) and [evidence index](evidence/README.md).

The final verification and documentation were merged into the public repository through [PR #21 — Exp3 final verification](https://github.com/Danyal0096/SELabHomeworks/pull/21). The clean-checkout result applies to the specified executable commit; the later changes culminating in that PR finalized documentation and evidence.
