# Behavior-oriented test plan

**Status:** source/test inventory verified; JUnit result, behavioral choices, and three independently proven bugs still pending. The handout specifies `(String,double)`, whereas starter contains an empty `(String,int)` stub.

> **Source inspection update (2026-09-22):** Four active original tests: `testAddItem`, `testRemoveItem`, `testDiscountAtBoundary_WRONG`, `testDiscountAboveThreshold`. Three commented tests: `testUpdateItemPrice_ShouldChangePrice`, `testUpdateItemPrice_ShouldNotChangeCount`, `testUpdateItemPrice_ItemNotFound_ShouldDoNothing`. The boundary test contradicts the handout; its expected result is 90 at exactly 100. See `source-audit.md` and `ta-clarifications.md`. JUnit results remain unmeasured.

## Original tests (must be preserved)

| Original class/method | Behavior | Baseline result | Notes |
| --- | --- | --- | --- |
| `testAddItem` | one Book (50) → count 1 and total 50 | Not run | duplicates/invalid values |
| `testRemoveItem` | one Pen (5) removed → true, count 0, total 0 | Not run | absent or repeated removal |
| `testDiscountAtBoundary_WRONG` | 40+60 → **expects 90** | Not run | contradicts handout (expects 100) |
| `testDiscountAboveThreshold` | 120 → 108 | Not run | decimals, repeat calls |

## Three independent bug regression tests

| Test ID | Defect / public behavior | Red evidence | Green / regression evidence |
| --- | --- | --- | --- |
| BUG-01 | TBD after reproduction | TODO | TODO |
| BUG-02 | TBD after reproduction | TODO | TODO |
| BUG-03 | TBD after reproduction | TODO | TODO |

## `updateItemPrice` — proposed scenarios, at least 8 written preimplementation

These are candidate behaviors; exact expectations for error handling and duplicates require verification/decision first. **Do not count duplicate tests or tests written after the implementation toward the preimplementation requirement.**

| ID | Setup / action | Intended observation | Actual test / Red output |
| --- | --- | --- | --- |
| UP-01 | Existing item → change price | Price/total update; count unchanged | TODO |
| UP-02 | Missing item → attempted update | Explicit unsuccessful result; cart unchanged | TODO |
| UP-03 | Null name | Defined invalid-name handling; atomic | TODO |
| UP-04 | Empty name | Defined invalid-name handling; atomic | TODO |
| UP-05 | New price is zero | Explicit decided zero-price policy | TODO |
| UP-06 | New price is negative | Explicit decided negative-price policy | TODO |
| UP-07 | Update raises subtotal from ≤100 to >100 | Exactly 10% discount begins to apply | TODO |
| UP-08 | Update changes subtotal to exactly 100 | **No** discount at exactly 100 | TODO |
| UP-09 | Fractional price update | Stable numeric result under agreed tolerance | TODO |
| UP-10 | Duplicate name / multi-item cart | Agreed duplicate-name update semantics; unrelated items unchanged | TODO |

Map the three original commented tests to IDs above after reading them. Distinct behavior matters more than test count. Add scenario(s) if original tests do not cover a row. Confirm that all at-least-eight tests exist before GREEN.

## Second feature: proposed capacity limit

| ID | Scenario | Observable behavior to establish |
| --- | --- | --- |
| CAP-01 | Add while below maximum | Accepted; count and total update |
| CAP-02 | Add reaching maximum exactly | Accepted |
| CAP-03 | Add one beyond maximum | Explicit rejection; complete observable state unchanged |
| CAP-04 | Remove then add after full | New addition accepted |
| CAP-05 | Capacity invalid / default policy | Decided behavior, no accidental regression |
| CAP-06 | Duplicate item names at boundary | Follows documented count semantics |

These are conditional on selecting the capacity feature; replace with the approved alternative as needed.

## Advanced scenarios and techniques

- JUnit parameterized tests where multiple values share one **genuine behavior** (e.g., values around 100, invalid names, decimal values), avoiding uninformative parameter sweeps.
- Exception assertions for invalid operations only after selecting the actual exception contract.
- Multi-step public-API sequences: add → update → remove → add; check subtotal and discount each step.
- Empty cart; repeated removal; non-existing removal; duplicate names; huge prices; numerical boundary values.
- No mocking merely for a checkbox. Introduce Mockito only for a real external dependency (e.g., clock, gateway) if the actual project has one.
- Prefer public outputs and observable state over reflection/private-field assertions.

## Traceability / non-flakiness

| Test name | Requirement or real bug | Why existing tests miss it | Coverage branch / mutant relevance | Result / evidence |
| --- | --- | --- | --- | --- |
| TODO | TODO | TODO | TODO | TODO |

Use deterministic fixtures, fresh cart per test, no order-dependent shared state; rerun the full suite after each fix.
