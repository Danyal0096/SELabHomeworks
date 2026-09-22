# Actual Red → Green → Refactor evidence log

> **No TDD cycles have been performed yet.** Record actions as they occur, with real commit hashes and paths. A planning entry is not evidence.

## Cycle template — duplicate once for each independent bug and each feature increment

- Cycle ID / behavioral requirement: TODO
- Why this matters / original contract: TODO
- Base commit and environment: TODO
- **RED** test file and test method: TODO
- **RED** command, exit code and *actual* failure excerpt: TODO
- Type of Red: compilation failure / assertion failure / exception / other: TODO
- RED evidence file: TODO
- RED commit hash: TODO
- **GREEN** minimum production changes: TODO
- GREEN full-suite command and actual pass/fail/skip counts: TODO
- GREEN evidence file: TODO
- GREEN commit hash: TODO
- **REFACTOR** exact structural change (or explain why none is justified): TODO
- REFACTOR full-suite results and evidence: TODO
- REFACTOR commit hash (if changed): TODO
- Regression / side effects checked: TODO
- Related Codex interaction IDs (actual only): TODO

### Suggested cycle IDs

`BUG-01`, `BUG-02`, `BUG-03`, `UP-01` … `UP-N`, `FEATURE2-01` … `FEATURE2-N`.

**Do not rewrite history:** save and commit the genuine Red test state even if it cannot compile. Before the final submission, the current tip must build and pass normally; intermediary Red commits are intentionally failing by design.
