# Instructions for coding assistants (Experiment 3 only)

This is a documentation scaffold, not the ShoppingCart source. The TA has not yet provided access to the prescribed base project.

1. Do not invent the ShoppingCart implementation, its interfaces, existing tests, build tool, failures, or numerical results.
2. Never edit the base project's original tests, **except uncommenting the three feature tests expressly identified by the handout when the actual files are available**. Keep any additional tests in separate traceable files.
3. Before proposing fixes, inspect the real repository and capture a reproducible baseline; formulate behavioral contracts from the actual code and problem statement, identifying unresolved choices explicitly.
4. For each of three independent bugs: add a failing regression test → capture failure → minimal fix → run the full suite → record root cause, why old tests missed it, and possible regressions. Preserve separate meaningful commits.
5. For `updateItemPrice`, write at least eight meaningful tests before implementation (including the three provided uncommented tests, if valid). A missing method may produce a compilation failure during the first Red stage; later prove genuine assertion failures where feasible. Implement Green minimally, then Refactor with suite verification.
6. For a second business feature, agree a precise observable contract before code. The **proposed** capacity-limit feature in `docs/feature-contracts.md` is not yet approved or implemented.
7. Run JaCoCo line/branch/method and PIT baseline and final when technically possible; explain any failed/inapplicable baseline instead of inventing it. Analyze significant surviving mutants.
8. Codex is an assistant, not an authority. Document **at least 12 actual meaningful interactions** with the real prompt, actual response, critique, and adopted/rejected decision. Never fabricate a transcript or generate the whole application/tests in a single request.
9. Final report must be Persian, correct RTL, and consistent with real code, test results, commit history, and evidence. Never treat this scaffold as a completed submission.
10. Do not operate on `exp1/`, `exp2/`, or the existing repository root without an explicit task-specific reason.
