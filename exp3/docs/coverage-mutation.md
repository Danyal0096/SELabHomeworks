# JaCoCo coverage and PIT mutation evaluation

The measurements below come from retained XML, scoped to `ShoppingCart`. Baseline was captured before the defect changes (`f074fb2`); final XML was committed in `41e4e87` on `exp3-final-verification`. The original and final code and test sets differ, so percentages describe each revision rather than a fixed set of source lines or mutants.

| `ShoppingCart` measure | Baseline XML | Final XML |
| --- | ---: | ---: |
| JaCoCo lines | 16/19 (84.21%) | 37/38 (97.37%) |
| JaCoCo branches | 4/6 (66.67%) | 27/30 (90.00%) |
| JaCoCo methods | 6/7 (85.71%) | 8/8 (100%) |
| PIT killed / generated | 9/11 (81.82%) | 28/28 (100%) |
| PIT no coverage / survived | 2 / 0 | 0 / 0 |

Sources: `evidence/baseline/jacoco.xml`, `evidence/baseline/mutations.xml`, `evidence/final-verification/jacoco.xml`, and `evidence/final-verification/mutations.xml`. The final XML files match the local generated `target` copies byte-for-byte. The final local Surefire XML totals **36 tests, 0 failures, 0 errors, 0 skipped**; a final clean-checkout console transcript and process exit codes are not retained.

## Scope and interpretation

- Both PIT runs mutated `ShoppingCart`, but the class grew from 19 to 38 JaCoCo-counted lines and from 6 to 30 branches. The generated mutant denominator therefore changed from 11 to 28.
- The baseline POM selected `ShoppingCartTest*` for PIT and the log says one test class was sent to the minion. Commit `41e4e87` changed the pattern to `ShoppingCart*Test`, admitting the seven current test classes. The 81.82% and 100% mutation scores are **not** a like-for-like comparison of the same mutants under the same selected tests.
- JaCoCo's whole-project totals include untested `Item` and `Main`: baseline lines 16/30 and final lines 37/49. Do not label the class-scoped percentages above as whole-project coverage.
- Baseline PIT's two `NO_COVERAGE` mutants were on the absent-item return in `removeItem` and the undiscounted return in `getTotalWithDiscount`. The final XML has no surviving or no-coverage mutants among those generated; 100% does not mean every input path is tested.

Final JaCoCo marks the exception body at `src/ShoppingCart.java:81` uncovered, with two missed branches at line 80 and one at line 86. Current update tests exercise a negative price, but not an invalid update name or non-finite update price. This is a direct test-evidence gap, not a demonstrated implementation failure. No per-test attribution of the overall coverage increase is claimed.

## Reproduction evidence

The baseline retained `mvn -B clean verify` and `mvn -B org.pitest:pitest-maven:1.19.4:mutationCoverage` console logs, both with exit code 0 in `evidence/baseline/exit-codes.txt`. That environment used Java 25.0.4.1 and Maven 3.9.16; `pom.xml` configures JUnit 5.8.1, JaCoCo 0.8.14, and PIT 1.19.4. The same Maven commands are configured for current verification, but no committed final command transcript or clean-checkout result establishes their latest exit codes. Retain one before making final reproducibility claims.
