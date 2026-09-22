# Software Engineering Lab — Experiment 3: Test-Driven Development

> **Status: scaffold only / waiting for TA access to the prescribed ShoppingCart base project.**
> No application source, original tests, build configuration, test output, metrics, or Codex responses have been supplied or invented.

## Purpose

Develop and test the instructor-provided Java ShoppingCart using **Red → Green → Refactor**. The handout requires baseline analysis; **at least three independent hidden defects** proved by failing tests and fixed; `updateItemPrice(String itemName, double newPrice)` implemented with **at least eight meaningful tests written before implementation**; a second business capability developed with TDD; advanced testing; baseline and final JaCoCo line, branch, and method coverage; baseline and final PIT mutation scores; **at least 12 documented meaningful Codex interactions**; a Persian final report; and traceable Git history and public Hamgit submission.

## What exists in this ZIP

- `docs/` — plans, living analysis/report templates, decision and interaction logs, metrics tables, commit plan, and acceptance checklist.
- `src/main/java/` and `src/test/java/` — **empty placeholders only**. Preserve the base project's actual layout once obtained; these folders can be discarded if it uses a different structure.
- `evidence/` — directories and naming guidance for *real* screenshots, console outputs, and reports.
- `AGENTS.md` — bounded Codex collaboration rules aligned with the assignment.
- `.gitignore` — standard Java build-output exclusions, without ignoring intentionally collected evidence.

**Not included:** `pom.xml`, `build.gradle`, application classes, tests, JUnit/JaCoCo/PIT configurations, screenshots, coverage percentages, mutation scores, the professor's source code, or a copied handout. All depend on information or work that does not exist yet.

## Safe installation in our existing repository

1. Extract this archive **inside the existing `SELabHomeworks/` root** so it creates `SELabHomeworks/exp3/`. Do not replace the existing root `README.md`, `exp1/`, or `exp2/`.
2. Once the TA provides access, get the prescribed base project: https://hamgit.ir/sqrlab-public/base-project-for-tdd-shoppingcart . Place the *actual contents* in `exp3/` while retaining its license, original tests, file layout, and build files. Inspect conflicts before copying; do not overwrite this documentation blindly.
3. Record source URL, revision/commit, date acquired, Java/build versions, and unchanged original tests in `docs/baseline.md`.
4. Establish the baseline **before writing new tests or changing production code**. Follow `docs/roadmap.md` and `docs/commit-plan.md`.
5. Verify requirements and submission expectations with the TA. Our GitHub course repo is https://github.com/Danyal0096/SELabHomeworks ; the handout separately requires a **public Hamgit** submission. Confirm whether a whole-repo mirror or an exp3-only repository is accepted. Do not claim GitHub alone meets that requirement.

## Tracking and documentation

| Need | File |
| --- | --- |
| Phases and gates | `docs/roadmap.md` |
| Initial test/architecture baseline | `docs/baseline.md` |
| Bug discovery and fixes | `docs/bug-analysis.md` |
| Behavioral test inventory | `docs/test-plan.md` |
| Feature contracts and unresolved policies | `docs/feature-contracts.md` |
| Genuine TDD cycles | `docs/tdd-log.md` |
| JaCoCo and PIT findings | `docs/coverage-mutation.md` |
| Codex: question, answer, critique, decision | `docs/codex-interactions.md` |
| Real commit sequence | `docs/commit-plan.md` |
| Persian final report template | `docs/report-fa.md` |
| Submission and clean-checkout audit | `docs/submission-checklist.md` |
| Screenshot/log conventions | `evidence/README.md` |

**Important:** An empty placeholder or a planned test is not execution evidence. Replace `TODO` with verified facts as the work proceeds; keep known failures and uncertainty visible.
