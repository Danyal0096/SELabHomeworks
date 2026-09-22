# Three independent defect investigations

These defects are measured against three behavioral contracts explicitly approved by the project owner before regression testing. The four original active tests did **not** establish decimal-cent accuracy or rejection of invalid prices and names. The discount-at-100 behavior, duplicate-name semantics, and the empty `updateItemPrice` stub are outside these investigations.

The saved `evidence/bugs/` files are JUnit test-class reports. The GREEN full-suite totals below are the owner's verified milestones; those class reports alone do not establish aggregate suite totals or the exact Maven commands and exit codes. No separate refactor commit or post-refactor test run is present in the inspected history. The reported decimal RED hash `06b6cd4` does not resolve here; Git history identifies `06eb6c4`.

## Independence rule

Each defect must have a distinguishable root cause and scenario. Three tests proving the same underlying bug are not three independent defects. Do not mislabel intended behavior or a test-design mistake as a production bug.

## Bug 01 — Decimal precision in cart totals

- **Approved contract:** A cart total for prices `0.10` and `0.20` must equal `0.30` to the cent. This requirement came from the project owner's approval, not the original tests.
- **Original source and root cause:** `src/ShoppingCart.java` stored `double` prices and `getTotal()` used `double total = 0.0` followed by `total += price`. Binary floating-point accumulation produced a visible decimal artifact.
- **Reproduction and observed result:** Add `("A", 0.10)` and `("B", 0.20)`, then call `getTotal()`. The RED report records `expected: <0.3> but was: <0.30000000000000004>`.
- **Why the original tests missed it:** Their active add, remove, and discount cases use integer-valued prices.
- **Regression test:** `Test/ShoppingCartPrecisionTest.java`, method `decimalPricesShouldProduceExactCentTotal`. The original test file was preserved.
- **RED:** `06eb6c4` added the test and `evidence/bugs/bug-01-red.txt`; the saved report records 1 test, 1 assertion failure, 0 errors, 0 skipped. The exact command and process exit code are not in that report.
- **GREEN:** `b616ac1` changed `getTotal()` to accumulate with `BigDecimal.valueOf(price)` and return through the existing `double` API. `evidence/bugs/bug-01-green.txt` records the new test passing: 1 test, 0 failures/errors/skips. The owner verified the full suite at **5 passed, 0 failures, 0 errors**.
- **REFACTOR:** No distinct refactor step, commit, or suite result is recorded.
- **Regression scope and limits:** The verified suite includes the four original active tests and this regression test. This test establishes the `0.10 + 0.20` case; it does not define fractional-cent rounding, arbitrary monetary precision, or discount arithmetic beyond the original behavior.
- **Codex context:** `CX-01` identified precision as a candidate requiring contract approval; it did not claim an independently confirmed bug from the original tests.

## Bug 02 — Invalid prices accepted by `addItem`

- **Approved contract:** Reject `NaN`, positive and negative infinity, and negative prices with `IllegalArgumentException`, leaving cart state unchanged. The project owner approved this rule before testing; the original tests did not specify it. A zero price is not rejected by this contract.
- **Original source and root cause:** `src/ShoppingCart.java` called `items.put(name, price)` without price validation. Invalid values could be stored or could overwrite an existing item's price.
- **Reproduction and observed result:** Start with `("Book", 20.0)`, then call `addItem("Invalid", Double.NaN)`. The RED test expected `IllegalArgumentException`; the report says `Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.` Four further tests cover positive infinity, negative infinity, `-10.0`, and an attempted overwrite of `"Book"` with `-5.0`.
- **Why the original tests missed it:** They add only finite, nonnegative prices and never assert rejection or atomicity after invalid input.
- **Regression tests:** Five methods in `Test/ShoppingCartPriceValidationTest.java`; the NaN and overwrite cases also check the original count and total after rejection. The original test file was preserved.
- **RED:** `237d033` added the tests and reports. `evidence/bugs/bug-02-red.txt` records 5 tests, 5 assertion failures, 0 errors, 0 skipped. `bug-02-red-nan.txt` separately records the single NaN failure. Exact Maven invocation and exit code are not retained in these reports.
- **GREEN:** `c9bb841` added `!Double.isFinite(price) || price < 0` validation before `items.put`. `evidence/bugs/bug-02-green.txt` records all 5 price tests passing, with 0 failures/errors/skips. The owner verified the full suite at **10 passed, 0 failures, 0 errors**.
- **REFACTOR:** No distinct refactor step, commit, or suite result is recorded.
- **Regression scope and limits:** The tests check rejection of the five specified cases and unchanged cart state for the tested invalid operations. Existing callers that used negative prices as adjustments would now receive an exception; such an adjustment contract was not established. `updateItemPrice` is still a separate feature and is not covered by these tests.
- **Codex context:** `CX-01` proposed numeric validity as a policy choice; the owner subsequently approved the precise rejection contract.

## Bug 03 — Null and blank item names accepted by `addItem`

- **Approved contract:** Reject null, empty, and whitespace-only item names with `IllegalArgumentException` before modifying the cart. The project owner approved this rule before testing; the original tests did not specify it.
- **Original source and root cause:** `src/ShoppingCart.java` originally passed `name` directly to `items.put(name, price)`. `HashMap` accepts null and the code also allowed empty and whitespace-only strings.
- **Reproduction and observed result:** Start with `("Book", 20.0)`, then call `addItem(null, 30.0)`. The RED test expected `IllegalArgumentException`; the report says `Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.` Separate tests use `""` and `"   "`.
- **Why the original tests missed it:** Every original item name is nonblank, and no original test checks invalid-name rejection or unchanged state after rejection.
- **Regression tests:** Three methods in `Test/ShoppingCartNameValidationTest.java`; each checks the original count and total after the expected rejection. The original test file was preserved.
- **RED:** `c201787` added the tests and reports. `evidence/bugs/bug-03-red.txt` records 3 tests, 3 assertion failures, 0 errors, 0 skipped. `bug-03-red-null.txt` separately records the single null-name failure. Exact Maven invocation and exit code are not retained in these reports.
- **GREEN:** `a080e28` added `name == null || name.isBlank()` validation before the price check and before `items.put`. `evidence/bugs/bug-03-green.txt` records all 3 name tests passing, with 0 failures/errors/skips. The owner verified the full suite at **13 passed, 0 failures, 0 errors**.
- **REFACTOR:** No distinct refactor step, commit, or suite result is recorded.
- **Regression scope and limits:** Rejected inputs cannot alter cart count or total in the tested cases. The change also rejects names containing only Unicode whitespace as defined by Java's `String.isBlank()`; the three tests exercise null, empty, and ASCII-space names only. Removal behavior and duplicate-name semantics were not changed by this fix.
- **Codex context:** `CX-01` identified item identity as a contract question; the owner subsequently approved this validation rule.

## Independence and remaining evidence gaps

The three cases have distinct triggers and corrections: arithmetic representation in `getTotal()`, price validation in `addItem`, and name validation in `addItem`. No behavior at the discount threshold, duplicate-name insertion, or the empty price-update stub is counted here. The saved `evidence/bugs/` files show class-level RED and GREEN JUnit results, but do not preserve the exact commands, process exit codes, or aggregate GREEN suite transcripts. No bug-specific before/after JaCoCo or PIT measurement is recorded here.
