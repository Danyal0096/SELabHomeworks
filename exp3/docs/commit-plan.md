# Git strategy — preserve an honest TDD history

The course-wide canonical repository already exists: https://github.com/Danyal0096/SELabHomeworks . Keep this experiment isolated under `exp3/`; do not fabricate or modify Exp 1/2 contents. Public **Hamgit** submission is separately required; ask TA whether a mirror of the whole repository or a separate exp3-only repository is accepted.

## Planned order (example message names only; no commits have been created here)

| Stage | Illustrative commit message | Required state |
| --- | --- | --- |
| Scaffold | `docs(exp3): initialize TDD experiment workspace` | Only preparatory files; this ZIP itself is not a commit |
| Genuine imported baseline | `chore(exp3): import unmodified ShoppingCart starter` | Provenance recorded; existing tests untouched |
| Baseline analysis | `docs(exp3): record original tests and baseline metrics` | Actual outputs and plugin settings |
| Each bug Red | `test(exp3): expose <specific bug>` | Newly failing test + captured failure |
| Each bug Green | `fix(exp3): correct <specific bug>` | Full suite run with results |
| Each justified bug refactor | `refactor(exp3): simplify <specific area>` | No behavior changes; full suite run |
| Price-update Red | `test(exp3): specify price update behavior` | Three provided tests uncommented and ≥8 meaningful total tests **before implementation** |
| Price-update Green | `feat(exp3): implement item price update` | Minimum behavior; full suite passes |
| Price-update refactor | `refactor(exp3): improve price update design` | Full suite passes |
| Feature 2 Red / Green / refactor | `test/feat/refactor(exp3): ...` | Separate traceable phases |
| Advanced testing | `test(exp3): cover boundaries and multi-step behavior` | Real tests and measured results |
| Quality evidence | `docs(exp3): compare JaCoCo and PIT results` | Actual reports and survivor analysis |
| Submission | `docs(exp3): finalize Persian report and reproduction` | Checked report, links and commands |

Do not force a meaningless refactor for every microscopic change; document the decision when none is needed. Record actual hashes in `tdd-log.md` and `report-fa.md`. An intentionally failing Red commit is expected, but the final HEAD must pass. Never squash away the Red/Green/Refactor chronology in a submission that requires it.

## Git / TA questions

- Does the TA require the prescribed starter repository to remain its own Git history, or is copying source with provenance sufficient?
- Does the TA accept `SELabHomeworks` mirrored to Hamgit, or require a dedicated public repo?
- Is a GitHub PR useful for our own process, while the final authoritative submission is Hamgit? (Do not confuse these.)

These are questions to resolve when access/instructions are available; no external actions have been taken.
