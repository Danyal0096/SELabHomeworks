# Actual Red → Green → Refactor evidence log

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

## Evidence still needed for a complete submission

The saved bug reports do not include exact RED/GREEN commands, process exit codes, or aggregate full-suite transcripts. No bug-specific JaCoCo or PIT comparison is documented. The next required feature cycles, need their own real RED, GREEN, and any actual REFACTOR evidence; no feature work is recorded here.
