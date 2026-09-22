# Three independent defect investigations

> **No bugs have been discovered yet.** These are evidence forms, NOT descriptions of existing defects. Choose defects only after inspecting and reproducing real original-project behavior.

## Independence rule

Each defect must have a distinguishable root cause and scenario. Three tests proving the same underlying bug are not three independent defects. Do not mislabel intended behavior or a test-design mistake as a production bug.

## Bug 01 — TODO: descriptive title

- Source revision / baseline commit: TODO
- Intended observable contract and its source: TODO
- Input, state setup, actions, expected result: TODO
- Actual original behavior: TODO
- Why existing tests missed it: TODO
- Newly added test name/file (do not alter original tests): TODO
- **RED** command, result, exact failure excerpt and evidence path: TODO
- Technical root cause, referenced lines: TODO
- **GREEN** smallest code fix, associated commit: TODO
- Complete-suite command and pass/fail counts: TODO
- **REFACTOR** if justified, associated commit and unchanged results: TODO
- Regression effects / other behaviors checked: TODO
- Codex interaction IDs, critique and decisions: TODO (if used)
- Before/after coverage or mutants, if attributable: TODO

## Bug 02 — TODO: descriptive title

- Source revision / baseline commit: TODO
- Intended observable contract and its source: TODO
- Input, state setup, actions, expected result: TODO
- Actual original behavior: TODO
- Why existing tests missed it: TODO
- Newly added test name/file: TODO
- **RED** command, result, exact failure excerpt and evidence path: TODO
- Technical root cause, referenced lines: TODO
- **GREEN** smallest code fix, associated commit: TODO
- Complete-suite command and pass/fail counts: TODO
- **REFACTOR** if justified, associated commit and unchanged results: TODO
- Regression effects / other behaviors checked: TODO
- Codex interaction IDs, critique and decisions: TODO (if used)
- Before/after coverage or mutants, if attributable: TODO

## Bug 03 — TODO: descriptive title

- Source revision / baseline commit: TODO
- Intended observable contract and its source: TODO
- Input, state setup, actions, expected result: TODO
- Actual original behavior: TODO
- Why existing tests missed it: TODO
- Newly added test name/file: TODO
- **RED** command, result, exact failure excerpt and evidence path: TODO
- Technical root cause, referenced lines: TODO
- **GREEN** smallest code fix, associated commit: TODO
- Complete-suite command and pass/fail counts: TODO
- **REFACTOR** if justified, associated commit and unchanged results: TODO
- Regression effects / other behaviors checked: TODO
- Codex interaction IDs, critique and decisions: TODO (if used)
- Before/after coverage or mutants, if attributable: TODO

## Candidate investigations (only hypotheses)

- Removing a nonexistent item or removing it repeatedly.
- Decimal subtotal and discounts; a subtotal of exactly 100 vs one greater than 100.
- Very large numerical prices.
- Duplicate names and consistency of item counts.
- A discount calculation mutating the underlying subtotal.

Reject a candidate if real code and actual tests show it is correct. Do not claim a known bug merely because the handout mentions an example.
