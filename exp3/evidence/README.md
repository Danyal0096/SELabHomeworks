# Evidence directory — genuine artifacts only

Save **actual** stdout/stderr captures, screenshots, coverage/PIT exports, and final verification output here. These directories currently contain only `.gitkeep` placeholders; no execution evidence has been produced.

## Naming convention

Use a sortable name like `2026-09-21_bug-01_red_test-name.txt` or `bug-01_red_<short-commit>.png`, replacing date, test name and revision with the actual observed values. Dates are illustrative, not timestamps of completed tasks. In text logs record the exact command, repository revision, environment and exit code. Prefer terminal text alongside screenshots, not images alone.

## Directories

- `baseline/` — first unmodified build/test result and initial JaCoCo/PIT outputs.
- `bugs/bug-01/` through `bug-03/` — real Red/Green/regression runs.
- `feature-1-price-update/` — three uncommented original tests, new tests and TDD evidence.
- `feature-2/` — behavior contract and real TDD outcomes.
- `coverage/` — selected initial/final JaCoCo HTML/XML reports or clear links to generated reports.
- `mutation/` — selected initial/final PIT outputs and survivor references.
- `final-verification/` — clean-checkout reproducibility run and final pass/fail evidence.

Do not commit binaries/secrets or enormous redundant generated directories indiscriminately; retain selected proof and instructions to regenerate full reports. Do not fabricate evidence, rewrite failing output, or claim a `TODO` file is a test artifact.
