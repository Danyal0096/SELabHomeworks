# JaCoCo coverage and PIT mutation evaluation

> **Unmeasured:** waiting for the real starter project, build system, and executable tests. No percentages below are asserted.

## Reproduction environment

- Project revision / branch: TODO
- Java and Maven/Gradle version: TODO
- JUnit / JaCoCo / PIT versions: TODO
- Target classes (at least `ShoppingCart`): TODO
- Test selection and excluded generated/infrastructure code: TODO
- Coverage/mutation commands: TODO (extract from actual plugin configuration)
- Raw report and terminal transcript paths: TODO

## Baseline vs final (same target class and comparable configurations)

| Measure | Baseline | Final | Notes / report |
| --- | --- | --- | --- |
| JaCoCo line coverage — ShoppingCart | 16/19 (84.21%) | Not measured | `evidence/baseline/jacoco.xml` |
| JaCoCo branch coverage — ShoppingCart | 4/6 (66.67%) | Not measured | `evidence/baseline/jacoco.xml` |
| JaCoCo method coverage — ShoppingCart | 6/7 (85.71%) | Not measured | `evidence/baseline/jacoco.xml` |
| PIT mutation score — ShoppingCart | 9/11 (81.82%) | Not measured | `evidence/baseline/mutations.xml` |
| PIT killed / survived / no coverage | 9 / 0 / 2 | Not measured | 11 mutations generated |

Verify the denominator/target-scope remains comparable. Different sets of executable classes/mutants across versions can limit direct score interpretation. If PIT baseline fails, record exact failure rather than setting it to 0%.

## Coverage gaps

| Source line or branch | Baseline uncovered? | Final uncovered? | Test added or reason not covered |
| --- | --- | --- | --- |
| TODO | TODO | TODO | TODO |

Discuss **which actual test** drove the largest useful increase; do not infer that from a screenshot alone if attribution is uncertain.

## Surviving mutants (actual PIT records only)

| Mutation / location | Why mutant survived | Missing behavioral assertion OR equivalence rationale | Action / test / outcome |
| --- | --- | --- | --- |
| TODO | TODO | TODO | TODO |

**Interpretation:** coverage indicates executed code; killing mutants provides some evidence that assertions detect behavior changes. High coverage alone does **not** demonstrate good fault detection. Mutation score is also limited by mutant selection, equivalent mutants, and build/test compatibility. Explain actual important survivors rather than chasing a target percentage the handout does not specify.

## Verification

- [ ] Capture baseline raw JaCoCo report and command or explain actual blocker.
- [ ] Capture baseline PIT report and command or explain actual blocker.
- [ ] Capture final line/branch/method metrics with correct target class.
- [ ] Capture final PIT score and inspect important surviving mutants.
- [ ] Record reproducible commands and versions.
