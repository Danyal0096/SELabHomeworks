# Baseline — original ShoppingCart before defect and feature changes

The unmodified-source baseline was captured before the first defect RED commit `06eb6c4` and retained in commit `f074fb2`. The source provenance and original file hashes are in `source-audit.md`. The original ZIP's upstream commit and license remain unverified; the GitHub snapshot at `81fa448bd36f4fc9f2fd4fe4eac71539ea257f37` is a separately compared copy.

## Executed baseline

- Environment: Windows 11, Eclipse Adoptium Java 25.0.4.1, Maven 3.9.16; see `evidence/baseline/java-version.txt` and `maven-version.txt`.
- `mvn -B clean verify` exited 0. Its retained console log records **4 tests, 0 failures, 0 errors, 0 skipped**, and `BUILD SUCCESS`; see `evidence/baseline/maven-clean-verify.txt` and `exit-codes.txt`.
- `mvn -B org.pitest:pitest-maven:1.19.4:mutationCoverage` exited 0; see `evidence/baseline/pitest.txt` and `exit-codes.txt`.
- The four active tests were `testAddItem`, `testRemoveItem`, `testDiscountAtBoundary_WRONG`, and `testDiscountAboveThreshold`. The three supplied `updateItemPrice` tests were still commented out at baseline and were uncommented later in `a35395e`.

| Metric for `ShoppingCart` | Baseline | Retained source |
| --- | ---: | --- |
| JaCoCo lines | 16/19 (84.21%) | `evidence/baseline/jacoco.xml` |
| JaCoCo branches | 4/6 (66.67%) | `evidence/baseline/jacoco.xml` |
| JaCoCo methods | 6/7 (85.71%) | `evidence/baseline/jacoco.xml` |
| PIT mutations | 9 killed / 11 generated (81.82%); 2 no coverage, 0 survived | `evidence/baseline/mutations.xml` |

PIT targeted `ShoppingCart` and selected the original `ShoppingCartTest*` class. Its two no-coverage mutants were the absent-item return in `removeItem` and the undiscounted return in `getTotalWithDiscount`. See `docs/coverage-mutation.md` for the final comparison and changed test selection.

## Original behavior and later decision

The baseline cart used a `HashMap<String,Double>`: duplicate names replaced prices, the count was distinct names, and removing an absent name returned `false`. It accumulated totals with `double` and contained an empty `updateItemPrice(String,int)` stub. Its discount applied at `total >= 100`; the original boundary test expected 90 for a total of 100. The handout excludes exactly 100, but the owner explicitly chose to preserve the original source and supplied test behavior. This is a documented handout deviation, not one of the three investigated defects.
