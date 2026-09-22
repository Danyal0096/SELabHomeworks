# Evidence index

These are retained artifacts, not a claim that every full-suite command or exit code was captured. `docs/tdd-log.md` links each RED/GREEN stage to its commit; `docs/coverage-mutation.md` interprets the measured XML.

| Directory | Actual retained evidence |
| --- | --- |
| `baseline/` | `maven-clean-verify.txt`, `pitest.txt`, `exit-codes.txt`, Java/Maven versions, original-test hash, original Surefire XML, `jacoco.xml`, `mutations.xml`, and a separate earlier production-only compile observation. |
| `bugs/` | `bug-01-*`, `bug-02-*`, and `bug-03-*` RED/GREEN Surefire class reports, including focused NaN and null RED reports. These are class-level reports, not full Maven transcripts. |
| `feature-1-price-update/` | Supplied-test and additional-test RED/GREEN Surefire reports, plus `decimal-update-compile-red.txt` showing the Maven compilation failure before the `double` API existed. A local `decimal-update-red.txt` is currently untracked and is not committed history. |
| `feature-2/` | `capacity-red.txt` for the selected failing behavior and `capacity-green.txt` for five passing capacity tests. |
| `final-verification/` | `jacoco.xml` and `mutations.xml` retained in `41e4e87`; the latter records 28 killed of 28 generated `ShoppingCart` mutations. |

Local `target/surefire-reports/` currently totals 36 passing tests, but no committed final clean-checkout console log or exit-code file is present. The empty placeholder directories under `evidence/` contain no additional measurements. Preserve the distinction between saved reports, local generated output, and owner-supplied observations.
