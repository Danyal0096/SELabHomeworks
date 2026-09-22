# Final acceptance and handoff checklist

**Nothing is complete yet:** tick only after actual verification. Keep evidence reproducible and don't treat a successful IDE screenshot as a substitute for clean-checkout instructions.

## Source and behavior

- [ ] Exact original project, source revision, license/provenance identified.
- [ ] Original tests unchanged except the three explicitly instructed uncomment operations.
- [ ] Empty cart, add, remove, total, duplicate policy and discount threshold behavior covered.
- [ ] Three **independent** hidden bugs each have test-fails-before-fix evidence and root-cause analysis.
- [ ] `updateItemPrice` has ≥8 meaningful tests written before implementation and a genuine Red/Green/Refactor history.
- [ ] Second business capability has specified contract and independent TDD cycle.
- [ ] Boundary, exception, numerical, duplicate and multi-step tests address actual gaps.
- [ ] No brittle implementation-dependent tests or unjustified mocks.

## Quality and process

- [ ] Initial and final JaCoCo line, branch and method coverage for `ShoppingCart` recorded.
- [ ] Initial and final PIT mutation scores recorded, or initial technical impossibility clearly evidenced and transparently described.
- [ ] Important surviving mutants examined and addressed/explained.
- [ ] ≥12 **real** Codex interactions each have prompt, response, critique and final decision.
- [ ] Git commits reveal original baseline, Red, Green, Refactor and both features in proper chronology.
- [ ] Complete suite rerun after fixes/refactors, outcomes retained.

## Report and delivery

- [ ] Persian final report completed; correct RTL when rendered/exported.
- [ ] Report contains actual bug explanations, failure/success outputs, edge cases, test rationale, metrics, mutation analysis, Codex critique, meaningful commit explanations and exact reproduction commands.
- [ ] Remove all `TODO` placeholders or explicitly identify genuine unresolved limitations; no fictitious metrics or screenshots.
- [ ] Preserve actual output/transcript files or screenshots in `evidence/`, with source revision and clear labels.
- [ ] Clean checkout tested with recorded Java/build versions and exact commands.
- [ ] Final project HEAD builds and all required tests pass (or explicitly disclose remaining failures).
- [ ] Public Hamgit link supplied and accessible; submission format confirmed with TA.

## Clean checkout verification log

- Revision / URL: TODO
- Machine and Java/build versions: TODO
- Commands and environment: TODO
- Actual exit status / counts: TODO
- JaCoCo command/report: TODO
- PIT command/report: TODO
- Public Hamgit submission URL: TODO
- Known remaining limitations: TODO
