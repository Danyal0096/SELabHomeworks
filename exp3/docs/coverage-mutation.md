# JaCoCo coverage and PIT mutation evaluation

The measurements below come from retained XML, scoped to `ShoppingCart`. Baseline was captured before the defect changes (`f074fb2`); final XML was first committed in `41e4e87` and refreshed after the CX-07 validation tests on `exp3-final-verification`. The original and final code and test sets differ, so percentages describe each revision rather than a fixed set of source lines or mutants.

| `ShoppingCart` measure | Baseline XML | Final XML |
| --- | ---: | ---: |
| JaCoCo lines | 16/19 (84.21%) | 38/38 (100%) |
| JaCoCo branches | 4/6 (66.67%) | 30/30 (100%) |
| JaCoCo methods | 6/7 (85.71%) | 8/8 (100%) |
| PIT killed / generated | 9/11 (81.82%) | 28/28 (100%) |
| PIT no coverage / survived | 2 / 0 | 0 / 0 |

Sources: `evidence/baseline/jacoco.xml`, `evidence/baseline/mutations.xml`, `evidence/final-verification/jacoco.xml`, and `evidence/final-verification/mutations.xml`. The refreshed final XML files match the local generated `target` copies byte-for-byte. The latest local Surefire XML totals **43 tests, 0 failures, 0 errors, 0 skipped**; a clean-checkout console transcript is not retained.

## Scope and interpretation

- Both PIT runs mutated `ShoppingCart`, but the class grew from 19 to 38 JaCoCo-counted lines and from 6 to 30 branches. The generated mutant denominator therefore changed from 11 to 28.
- The baseline POM selected `ShoppingCartTest*` for PIT and the log says one test class was sent to the minion. Commit `41e4e87` changed the pattern to `ShoppingCart*Test`; the latest PIT run sent eight test classes to its minion. The 81.82% and 100% mutation scores are **not** a like-for-like comparison of the same mutants under the same selected tests.
- JaCoCo's whole-project totals include untested `Item` and `Main`: baseline lines 16/30 and final lines 38/49. Do not label the class-scoped percentages above as whole-project coverage.
- Baseline PIT's two `NO_COVERAGE` mutants were on the absent-item return in `removeItem` and the undiscounted return in `getTotalWithDiscount`. The final XML has no surviving or no-coverage mutants among those generated; 100% does not mean every input path is tested.

The CX-07 tests directly exercise null, empty, and whitespace-only update names; NaN and both infinities as update prices; and invalid-price validation before an absent-name lookup. The latest JaCoCo XML has no missed `ShoppingCart` lines, branches, or methods. Full class coverage and a 100% score for the generated PIT mutants do not establish every possible input contract. Production code did not change between the prior final report and this run; these seven cases account for the newly exercised update-validation paths.

## Reproduction evidence

The baseline retained `mvn -B clean verify` and `mvn -B org.pitest:pitest-maven:1.19.4:mutationCoverage` console logs, both with exit code 0 in `evidence/baseline/exit-codes.txt`. That environment used Java 25.0.4.1 and Maven 3.9.16; `pom.xml` configures JUnit 5.8.1, JaCoCo 0.8.14, and PIT 1.19.4. In CX-07, an initial sandboxed `clean verify` stopped at plugin resolution because network access was denied; its network-enabled rerun and the PIT command both exited 0. These latest console logs are in the interaction, not retained as committed evidence or a clean-checkout record. Retain such a record before making final reproducibility claims.
