# Evidence index

These are retained artifacts, not a claim that every full-suite command or exit code was captured. `docs/tdd-log.md` links each RED/GREEN stage to its commit; `docs/coverage-mutation.md` interprets the measured XML.

| Directory | Actual retained evidence |
| --- | --- |
| `baseline/` | `maven-clean-verify.txt`, `pitest.txt`, `exit-codes.txt`, Java/Maven versions, original-test hash, original Surefire XML, `jacoco.xml`, `mutations.xml`, and a separate earlier production-only compile observation. |
| `bugs/` | `bug-01-*`, `bug-02-*`, and `bug-03-*` RED/GREEN Surefire class reports, including focused NaN and null RED reports. These are class-level reports, not full Maven transcripts. |
| `feature-1-price-update/` | Committed supplied-test and additional-test RED/GREEN Surefire reports, plus `decimal-update-compile-red.txt` showing the Maven compilation failure before the `double` API existed. |
| `feature-2/` | `capacity-red.txt` for the selected failing behavior and `capacity-green.txt` for five passing capacity tests. |
| `final-verification/` | `jacoco.xml` and `mutations.xml` originally retained in `41e4e87` and refreshed after CX-07; `clean-checkout-cx08.txt` records detached-HEAD verification of `3d7f3bf` with commands, exit codes, results, and XML comparison. |

Local `target/surefire-reports/` currently totals 43 passing tests. The CX-08 record includes clean-checkout exit codes, but full Maven console output from that checkout is not retained. The empty placeholder directories under `evidence/` contain no additional measurements. Preserve the distinction between saved reports, local generated output, and owner-supplied observations.

The separate `decimal-update-red.txt` and `exp3-review.patch` artifacts are designated for a private archive outside this repository. They are not committed history or submission evidence and are intentionally omitted from the evidence inventory above. This documentation-only task does not claim that their local untracked copies have already been moved.
