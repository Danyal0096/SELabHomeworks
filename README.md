# Software Engineering Lab — Completed Experiments

**Danial Farahani · Student ID: 97105725**

This repository contains the implementations, tests, reports, and development evidence for **all five completed Software Engineering Lab experiments**. Each experiment has its own README with the relevant instructions and results. The work is maintained together in this repository, with completed experiment changes integrated into `main` through GitHub pull requests.

## Experiments

| Experiment | Topic | What was completed | Documentation |
| --- | --- | --- | --- |
| **01** | Git and Continuous Deployment | Built a responsive course website; practiced feature branches, pull requests, manual merge-conflict resolution, branch protection, and GitHub Pages deployment. | [Experiment 1](exp1/README.md) · [Live website](https://danyal0096.github.io/SELabHomeworks/) |
| **02** | Object-Oriented Design | Analyzed the five SOLID principles, created and used a repository-local Codex Skill, refactored a Python checkout application, added cash payment to the original and refactored versions, and compared their changes and output. | [Experiment 2](exp2/README.md) |
| **03** | Test-Driven Development | Investigated and fixed three shopping-cart defects; implemented price updates and configurable capacity; added regression and advanced tests; recorded baseline/final JaCoCo and PIT results and 12 Codex interactions. | [Experiment 3](exp3/README.md) · [Persian final report](exp3/docs/report-fa.md) |
| **04** | Behavior-Driven Development | Implemented a Java calculator with Cucumber/Gherkin scenarios, fixed an undefined step for negative operands, and verified **21 passing scenarios**. | [Experiment 4](exp4/README.md) |
| **05** | Docker Fundamentals | Built and ran separate Python server/client containers using Docker Compose; verified five successful client requests, HTTP 200 from the host, and server-container inspection. | [Experiment 5](exp5/README.md) · [Report](exp5/report/REPORT.md) |

## Selected results

- **Experiment 1:** The [course website](https://danyal0096.github.io/SELabHomeworks/) is published with GitHub Actions and GitHub Pages. Its README links to the conflict-resolution commits, pull requests, deployment run, and seven theoretical answers.
- **Experiment 2:** The original and refactored checkout demonstrations produced identical final output, including cash payment; the README documents the feature-only comparison. This experiment does not claim an automated test suite.
- **Experiment 3:** A clean-checkout verification recorded **43 passing tests**, **100% line/branch/method coverage for the `ShoppingCart` class**, and **28/28 generated PIT mutations killed**. These percentages apply to the target class and the recorded configuration, not every class or possible behavior. The completed experiment was merged via [PR #21](https://github.com/Danyal0096/SELabHomeworks/pull/21).
- **Experiment 4:** `mvn clean test` completed successfully with **21 scenarios**, zero failures, zero errors, and zero skipped tests, as recorded in its README.
- **Experiment 5:** Both images built and both services started. The host endpoint is `http://localhost:8300`; the client reached the server over the Compose network. The report and screenshots document the local execution.

## Using this repository

Open an experiment's linked README for its prerequisites, commands, scope, results, and supporting evidence. The experiments use different toolchains: Git/GitHub Actions for Experiment 1, Python for Experiment 2, Java and Maven for Experiments 3–4, and Docker Desktop / Docker Compose for Experiment 5. No single build command applies to all five.
