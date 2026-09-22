# Software Engineering Lab — Experiment 3

The cart and tests are under `src/` and `Test/`; `pom.xml` configures both directories. The current implementation includes three owner-approved defect corrections, `updateItemPrice(String,double)`, and a configurable item-capacity limit. The final local Surefire reports total **36 passing tests**. Retained final PIT XML records **28 killed of 28 generated mutations** for `ShoppingCart`. These observations do not replace a retained clean-checkout command transcript.

## Build and evidence

From `exp3/` with JDK 17+ and Maven:

```powershell
mvn -B clean verify
mvn -B org.pitest:pitest-maven:1.19.4:mutationCoverage
```

The POM targets Java 17, uses JUnit 5.8.1, JaCoCo 0.8.14, and PIT 1.19.4. Maven writes Surefire reports under `target/surefire-reports/`, JaCoCo under `target/site/jacoco/`, and PIT under `target/pit-reports/`. Retained baseline logs and XML are in `evidence/baseline/`; the final JaCoCo and PIT XML are in `evidence/final-verification/`. See `docs/coverage-mutation.md` for scope, denominators, and test-selection differences.

The original ZIP contained three production classes and one test class. `docs/source-audit.md` records its provenance and an earlier Maven-style Git snapshot. The present checkout uses the original `src/` and `Test/` layout through explicit POM settings. The original four active tests remain; only the three supplied update-price tests were uncommented in `Test/ShoppingCartTest.java`. New tests are separate classes.

## Current behavior and history

- `ShoppingCart` rejects null/blank names and negative/non-finite prices, accumulates totals with `BigDecimal`, and retains the original discount rule of 10% when subtotal is **at least** 100.
- `updateItemPrice(String,double)` returns `true` for an existing name and `false` for an absent name; it validates input before changing cart state.
- `ShoppingCart(int maxItems)` sets a positive limit on distinct names. A full cart rejects a new name but permits replacement of an existing name; the no-argument constructor preserves the original practical default.
- `docs/bug-analysis.md` and `docs/tdd-log.md` identify the actual RED/GREEN commits and saved test-class reports. Ten update-price cases existed before implementation; two return-value tests were added with GREEN. No separate refactor stage is claimed.

The original experiment handout excludes a subtotal of exactly 100 from the discount; the supplied test and original source discount at 100. The owner explicitly chose to preserve the original behavior, so this is a **known handout deviation**. The handout also requires public Hamgit submission; the owner chose the existing [GitHub repository](https://github.com/Danyal0096/SELabHomeworks) instead. A GitHub submission must **not** be described as handout-compliant on that point.

## Submission status

`docs/report-fa.md` is still an unfinished Persian RTL report; it needs to reflect the actual defect, feature, TDD, coverage, mutation, and reproduction evidence. `docs/codex-interactions.md` records the real exchanges available so far; the handout requires at least 12. Before submission, retain a clean-checkout verification record with the revision, Java/Maven versions, exact commands, exit codes, and test/metric results, plus the actual delivery link. No such final transcript or delivery evidence is retained yet; do not infer it from local generated reports or this README.
