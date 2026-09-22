# Baseline analysis — fill from actual starter project

> **Not performed.** Awaiting TA access; all TODO fields below require direct verification. Do not turn suspected defects into findings without reproducing them.

## Provenance

| Field | Verified value |
| --- | --- |
| Base project URL | https://hamgit.ir/sqrlab-public/base-project-for-tdd-shoppingcart (handout; access pending) |
| Date obtained | TODO |
| Exact source revision / commit | TODO |
| License / attribution | TODO |
| Java version | TODO |
| Build tool and version | TODO |
| Test framework and version | TODO |
| Operating system / IDE | TODO |
| Original files preserved? / proof | TODO |

## Original build / test run (before ANY edits)

- Working directory: TODO
- Build command: TODO
- Test command: TODO
- Exit code / outcome: TODO
- Number of original tests run / passed / failed / skipped: TODO
- Actual output saved at: `evidence/baseline/TODO.txt`
- If initial build fails, classify environmental vs genuine source/test failure; preserve output.

## Architecture and observable contract inventory

| Class / API | Responsibility | External observable behavior | Source reference |
| --- | --- | --- | --- |
| `ShoppingCart` | To be inspected | TODO | TODO |
| Other actual classes | TODO | TODO | TODO |

Record actual item representation and storage, add/remove semantics, subtotal, discount threshold, precision policy, duplicate-name policy, invalid-input rules, and item-count semantics. **Do not guess.**

## Existing tests (unaltered)

| Original test ID | Expected behavior | Current result | Missing edge case? |
| --- | --- | --- | --- |
| TODO | TODO | TODO | TODO |

The handout says **three new-feature tests are commented out**; verify their names and location when available. Keep them commented through the *original* baseline stage.

## Suspected gaps, hypotheses, limitations

| Hypothesis (NOT confirmed defect) | Reason to investigate | Proposed observable test | Status |
| --- | --- | --- | --- |
| Removing absent item may change state or error improperly | Handout candidate | TODO | Unverified |
| Decimal subtotal/discount may drift | Handout candidate | TODO | Unverified |
| Very large prices may overflow/behave oddly | Handout candidate | TODO | Unverified |
| Count may diverge from storage semantics | Handout candidate | TODO | Unverified |

## Baseline JaCoCo — `ShoppingCart` only

| Metric | Observed value | Report / command |
| --- | --- | --- |
| Line coverage | TODO, not measured | TODO |
| Branch coverage | TODO, not measured | TODO |
| Method coverage | TODO, not measured | TODO |

If the actual build cannot produce one of these, record the exact tool limitation and remedy. Do not confuse line coverage with behavior verification.

## Baseline PIT

| Metric | Observed value | Command / report |
| --- | --- | --- |
| Mutation score | TODO, not measured | TODO |
| Mutants killed / survived / no coverage / other | TODO | TODO |

If initial PIT is not feasible, capture the actual error and note that a numerical baseline **cannot** be claimed. Avoid editing original tests to force a number.

## Baseline approval gate

- [ ] Original build/test result saved.
- [ ] Test inventory + code assumptions reviewed.
- [ ] JaCoCo original metric or documented genuine blocker.
- [ ] PIT original metric or documented genuine blocker.
- [ ] Baseline source snapshot committed, without production/test modifications.
