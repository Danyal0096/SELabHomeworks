# Actual RED/GREEN history

All three behavioral contracts below were approved by the project owner before their regression tests. They are additional requirements; the four original active tests did not establish them. Each RED and GREEN has a distinct commit on `exp3-bug-discovery`. No separate refactor commit or post-refactor run appears in the inspected history, so the Refactor stage is recorded as **not performed** for all three cycles. The current documentation and whitespace cleanup are not retroactively part of those cycles.

The `evidence/bugs/` files are saved Surefire test-class reports, not command transcripts. They establish the class-level test outcomes below. The owner supplied the full-suite GREEN totals of 5, 10, and 13 passes; the reports do not independently prove those aggregate counts or identify the exact Maven commands and process exit codes. The owner's proposed hash `06b6cd4` for BUG-01 RED is not in Git history; the actual commit is `06eb6c4`.

## BUG-01 — decimal precision

- **Base:** `c07242d`, before the RED commit; the earlier verified baseline is `f074fb2`.
- **RED, 2026-09-22 17:31 +03:30:** `06eb6c4` added `Test/ShoppingCartPrecisionTest.java` with `decimalPricesShouldProduceExactCentTotal`, plus `evidence/bugs/bug-01-red.txt`. JUnit reported 1 test, 1 assertion failure, 0 errors, 0 skipped: `expected: <0.3> but was: <0.30000000000000004>`. The exact invocation and exit code were not retained in the saved report.
- **GREEN, 2026-09-22 17:34 +03:30:** `b616ac1` replaced `double` accumulation in `getTotal()` with `BigDecimal.valueOf(price)` addition and converted the final value to `double`. `evidence/bugs/bug-01-green.txt` reports the new test passing (1/1). Owner-verified full suite: **5 passed, 0 failures, 0 errors**; no aggregate transcript was saved in `evidence/bugs/`.
- **REFACTOR:** Not performed or evidenced as a distinct stage. No refactor suite result or commit is claimed.
- **Regression scope:** The original four tests and the decimal regression were included in the reported full-suite count. Fractional-cent rounding remains unspecified. See `docs/bug-analysis.md` and interaction `CX-01`.

## BUG-02 — invalid prices

- **Base:** `b616ac1`, the preceding GREEN commit.
- **RED, 2026-09-22 17:41 +03:30:** `237d033` added five methods in `Test/ShoppingCartPriceValidationTest.java`, plus `evidence/bugs/bug-02-red.txt` and `bug-02-red-nan.txt`. The full class report records 5 tests, 5 assertion failures, 0 errors, 0 skipped. For each case, JUnit reported `Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.` The separate NaN report shows 1 failing test. The exact invocation and exit code were not retained.
- **GREEN, 2026-09-22 17:42 +03:30:** `c9bb841` added a finite, nonnegative price check before `items.put`. `evidence/bugs/bug-02-green.txt` reports all five price tests passing. Owner-verified full suite: **10 passed, 0 failures, 0 errors**; no aggregate transcript was saved in `evidence/bugs/`.
- **REFACTOR:** Not performed or evidenced as a distinct stage. No refactor suite result or commit is claimed.
- **Regression scope:** The tests cover NaN, both infinities, a negative new price, and invalid overwrite; the tested rejection paths leave count and total unchanged. Price-update behavior remains separate. See `docs/bug-analysis.md` and interaction `CX-01`.

## BUG-03 — invalid item names

- **Base:** `c9bb841`, the preceding GREEN commit.
- **RED, 2026-09-22 17:46 +03:30:** `c201787` added three methods in `Test/ShoppingCartNameValidationTest.java`, plus `evidence/bugs/bug-03-red.txt` and `bug-03-red-null.txt`. The full class report records 3 tests, 3 assertion failures, 0 errors, 0 skipped, each reporting `Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.` The separate null-name report shows 1 failing test. The exact invocation and exit code were not retained.
- **GREEN, 2026-09-22 17:50 +03:30:** `a080e28` added `name == null || name.isBlank()` validation before changing cart state. `evidence/bugs/bug-03-green.txt` reports all three name tests passing. Owner-verified full suite: **13 passed, 0 failures, 0 errors**; no aggregate transcript was saved in `evidence/bugs/`.
- **REFACTOR:** Not performed or evidenced as a distinct stage. No refactor suite result or commit is claimed.
- **Regression scope:** The tests cover null, empty, and ASCII-space-only names and verify unchanged count and total after rejection. See `docs/bug-analysis.md` and interaction `CX-01`.

## FEATURE-01 — update item price

- **RED `a35395e`:** Uncommented exactly the three supplied tests in `Test/ShoppingCartTest.java` and added six cases in `Test/ShoppingCartUpdatePriceTest.java`, before changing the empty `updateItemPrice(String,int)` stub. `evidence/feature-1-price-update/provided-tests-red.txt` records 7 original-class tests with 1 assertion failure (`80.0` expected, `50.0` actual). `additional-tests-red.txt` records 6 new tests with 6 assertion failures. Two supplied tests passed despite the stub; their distinct count and absent-item assertions remain useful but do not alone prove an update occurred.
- **Further RED `614d792`:** Added `shouldSupportDecimalPriceUpdates`, the seventh additional preimplementation case. `decimal-update-compile-red.txt` records a Maven test-compilation failure: a `double` argument could not be converted to the stub's `int`. Thus **3 supplied + 7 additional = 10 cases** preceded implementation. An untracked `decimal-update-red.txt` exists locally but is not part of the committed RED history.
- **GREEN `635ee30`:** Replaced the stub with `boolean updateItemPrice(String,double)`: validate name and price, return `false` for absence, update an existing entry and return `true`. The same commit added two return-value tests; these are **post-RED additions** and are not counted toward the ten preimplementation cases. `provided-tests-green.txt` records 7/7 supplied-class tests passing; `additional-tests-green.txt` records 9/9 update-class tests passing. These saved reports are class-level results, not an aggregate suite transcript.
- **REFACTOR:** No separate refactor commit, structural change, or post-refactor run is evidenced. Do not label the two GREEN-stage tests as refactoring.
- **Later validation tests:** CX-07 added seven postimplementation cases in `Test/ShoppingCartUpdateValidationTest.java` for invalid names, non-finite prices, and validation before absent-item lookup. They are not part of the ten preimplementation cases or a refactor stage.

## FEATURE-02 — configurable capacity

- **RED `85f8d35`:** Added five methods in `Test/ShoppingCartCapacityTest.java` and a positive-capacity constructor/API scaffold in production so the tests could compile. `evidence/feature-2/capacity-red.txt` records one selected test failing because a full cart accepted a new name. This was a behavioral RED, but the commit was **not test-only**.
- **GREEN `da8cce7`:** Added the capacity check before inserting a new name. `capacity-green.txt` records all 5 capacity-class tests passing. The tests cover full-cart rejection, replacement at capacity, removal freeing space, the default constructor, and invalid capacities.
- **Later tests:** `349b4f7` added parameterized limits and a multistep lifecycle test; `41e4e87` added zero-price and absent-removal cases and retained final metric XML. These are postimplementation tests, not part of the capacity RED count.
- **REFACTOR:** No separate refactor step or verification is evidenced.

## Final verification and evidence limits

After the CX-07 validation tests, local generated Surefire XML on `exp3-final-verification` totals **43 tests, 0 failures, 0 errors, 0 skipped**. The refreshed `evidence/final-verification/mutations.xml` records 28/28 killed mutations; JaCoCo XML is retained beside it. CX-08 independently repeated both commands from a detached clean checkout of `3d7f3bf`, with exit code 0 for each and the same results; `evidence/final-verification/clean-checkout-cx08.txt` records the details without full console output. The older bug and feature reports above establish their named class-level outcomes; do not infer unrecorded historical full-suite counts, exit codes, or refactor stages from them. See `coverage-mutation.md` for baseline/final scope differences.
