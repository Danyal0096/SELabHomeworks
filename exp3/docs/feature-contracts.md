# Feature behavior contracts — decisions pending source inspection

> **These are proposed questions and test scenarios, not established implementation facts.** Consult the actual three provided tests and source before fixing return values, exception types, duplicate-name semantics, or constructor shape.

## Feature 1: `updateItemPrice(String itemName, double newPrice)`

Handout-required core contract: update the price of an existing item while preserving item count and overall cart structure. A nonexistent item must have an explicit, testable failure with **no side effects**. Specify invalid null/empty names and zero/negative prices *before implementing*. Verify that totals and the rule of 10% off only when subtotal **strictly exceeds 100** respond correctly and that discount does not permanently change base subtotal.

| Contract decision | Choice and rationale | Evidence from supplied tests/code |
| --- | --- | --- |
| Item lookup and duplicate names | TODO | TODO |
| If name absent: exception/return value | TODO | TODO |
| `null` / empty name | TODO | TODO |
| New price zero / negative | TODO | TODO |
| Precision / rounding policy (`double` signature is prescribed) | TODO | TODO |
| Success return type / declared exceptions | TODO | TODO |
| Invariants for count, ordering, subtotal | TODO | TODO |

**Minimum preimplementation tests:** at least 8 meaningful cases total; identify which three originally commented tests are part of this count and add further tests separately. See `test-plan.md`.

## Feature 2: proposed cart item-capacity limit (handout option B)

**Proposal, not yet selected/implemented:** a maximum allowed number of items and an atomic rejection if an add would exceed it. A positive example is a capacity-3 cart accepting three items and rejecting a fourth without changing any observable cart state. The example `3` is illustrative, not an instructor-mandated constant.

| Contract question | Choice after inspection |
| --- | --- |
| How is capacity configured (constructor/setter/constant)? | TODO |
| Is capacity mandatory or opt-in? Does default retain original behavior? | TODO |
| Is capacity 0 allowed? Negative capacity? | TODO |
| Does one duplicate-name addition count as an additional item? | TODO |
| What exact public API reports rejection? | TODO |
| What happens after removing an item from a full cart? | TODO |
| Does rejected addition leave count, subtotal and discount unchanged? | TODO |
| What is the interaction with invalid prices/names? | TODO |

Only implement this proposal if it fits the actual starter project's design and course interpretation. The handout also permits alternative second business rules such as composable discounts or other validation policies.

## Source vs design decisions

- **Prescribed by handout:** discount applies if subtotal >100, exactly 100 is not discounted; preserve original tests; 3 confirmed bugs; ≥8 update tests; second TDD feature.
- **To infer or choose after access:** current public methods, duplicate behavior, precise exceptions, capacity API, rounding policy, actual defects.
- Document unresolved domain ambiguities rather than silently inventing 'correct' behavior.
