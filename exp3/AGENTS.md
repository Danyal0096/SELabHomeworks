# Instructions for coding assistants (Experiment 3 only)

Work in `exp3/`. Maven compiles production files from `src/` and tests from `Test/`, as configured in `pom.xml`. `src/main/java/` and `src/test/java/` describe an earlier Git snapshot, not this branch's layout.

The baseline, three owner-approved defect investigations, `updateItemPrice(String,double)`, configurable capacity, and advanced tests are implemented. Read the actual code, tests, Git history, and retained evidence before changing claims or behavior. `docs/source-audit.md` is a historical source snapshot; `docs/bug-analysis.md`, `docs/tdd-log.md`, and `docs/coverage-mutation.md` hold measured results and their limits.

1. Do not invent contracts, test runs, coverage, mutation results, commits, or Codex exchanges. Distinguish saved reports from owner-supplied observations and interpretations.
2. Preserve the four original active tests. The only authorized change to the original test class was uncommenting its three supplied `updateItemPrice` tests; keep other tests in separate files.
3. The handout excludes a subtotal of exactly 100 from the discount, but the original source and supplied test discount at 100. The owner explicitly chose to preserve the original behavior. State this deviation accurately; do not silently change the test or implementation.
4. The handout requires a public Hamgit submission. The owner chose GitHub instead. Do not present that choice as handout-compliant or imply that a Hamgit submission exists.
5. Preserve the genuine RED/GREEN history. Ten update-price tests existed before implementation: three supplied tests, six additional tests in `a35395e`, and a decimal case in `614d792`. Two return-value tests were added with GREEN `635ee30` and are not preimplementation tests. Capacity RED `85f8d35` included an API scaffold before GREEN `da8cce7`. Do not invent refactoring stages.
6. Compare JaCoCo and PIT using the `ShoppingCart` class scope and each report's actual denominator. Baseline and final code and PIT test selection differ; a percentage change is not a like-for-like mutant comparison.
7. At least 12 genuine, documented Codex interactions are mandatory. Record the actual request, response, critique, and owner decision when known; mark pending evaluations as pending. Never fabricate a transcript or count a proposed interaction.
8. The final report must be Persian, correctly RTL, and consistent with the code and evidence. It is still a draft. Keep work scoped to `exp3/`; do not operate on other experiments without an explicit task-specific reason.
