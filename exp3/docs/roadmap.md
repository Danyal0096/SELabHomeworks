# Experiment 3 — staged execution roadmap

**Status:** planning only. Base project unavailable until TA grants access. Times are estimates, not observed durations. The order of the first two measurement tasks may depend on actual build/plugin feasibility.

| Gate | Work | Estimated time | Exit evidence |
| --- | --- | --- | --- |
| 1 | Acquire prescribed source, import, initial build, preserve original tests | 45–75 min | Source revision, build command/output, original tree |
| 2 | Baseline architecture/test inventory; initial JaCoCo and PIT if feasible | 1–2 h | `baseline.md`, initial metrics or precise blocker |
| 3 | Reveal + correct ≥3 independent real defects, one TDD cycle each | 2–4 h | Three failing tests, fixes, regression runs, commits |
| 4 | Uncomment 3 supplied tests; create ≥8 meaningful `updateItemPrice` tests **before implementation**; Red/Green/Refactor | 2–3 h | Test inventory, recorded Red, passing suite, separate commits |
| 5 | Define and implement second business feature with TDD | 1.5–2.5 h | Approved contract, Red/Green/Refactor evidence |
| 6 | Parameterized, exception, numerical, null, duplicate, and multi-step tests | 1–2 h | Tests linked to uncovered behavior; Mockito only if needed |
| 7 | JaCoCo + PIT final and initial/final comparison | 1.5–2.5 h | Line/branch/method and mutation scores, surviving mutant analysis |
| 8 | At least 12 real Codex exchanges, Persian report, evidence/commit review | 1.5–2.5 h | Actual interaction log and completed report |
| 9 | Fresh-checkout verification and TA-compliant public Hamgit submission | 30–60 min | Exact commands, passing run, publicly accessible link |

**Total planning estimate:** approximately 12–21 hours; actual effort is unknown.

## Workflow for *each* defect / feature

1. Specify one externally observable requirement and expected result.
2. Add tests first (or uncomment the three existing tests only where specifically directed).
3. **RED:** execute and preserve the exact expected failure, including whether it is compilation or assertion failure; commit.
4. **GREEN:** implement the minimum change; run the *entire* suite; capture output; commit.
5. **REFACTOR:** simplify production and/or new test code without altering behavior; rerun the suite; commit if changed.
6. Update `tdd-log.md`, `codex-interactions.md` (only for actual Codex use), `test-plan.md`, and metrics after appropriate milestones.

## Hard constraints

- **Never** quietly modify original base-project tests. Only uncomment the three explicitly supplied feature tests at the instructed phase.
- Baseline must precede behavioral modifications.
- The exact `> 100` discount threshold means **100 itself receives no 10% discount**.
- Discount calculations must not permanently alter the base subtotal.
- No invented output or fabricated before/after percentages, mutation scores, screenshots, responses, or hashes.
- GitHub organization does not replace the handout's public Hamgit submission requirement.
- Final report in Persian and consistent with actual behavior.

## Readiness gate — when TA grants access

- [ ] Confirm exact source URL / commit and license.
- [ ] Inspect `pom.xml` or `build.gradle` **if actually present**; install compatible Java/build tools.
- [ ] Identify test files and which three tests are commented (without editing them yet).
- [ ] Run the untouched project and capture output.
- [ ] Confirm whether PIT can generate a meaningful baseline for the provided tests.
- [ ] Ask TA only for ambiguities that materially affect compliance, notably Hamgit repository format.
