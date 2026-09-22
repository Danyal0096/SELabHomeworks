# Experiment 4 — Behavior-Driven Development

Software Engineering Laboratory

## Objective

This experiment demonstrates Behavior-Driven Development
(BDD) by translating calculator requirements into executable
Gherkin scenarios using Cucumber and JUnit.

## Technologies

- Java
- Apache Maven
- Cucumber
- JUnit
- Visual Studio Code

## Implemented Operations

The calculator supports:

- Addition (+)
- Multiplication (*)
- Division (/)
- Exponentiation (^ and **)

Division returns a floating-point result. Division by zero
throws an ArithmeticException.

## Running the Tests

Requirements:
- JDK 17 or newer
- Apache Maven

Open a terminal in the exp4 directory and execute:

    mvn clean test

Maven automatically downloads the project's dependencies.

## Test Scenarios

The project contains 21 executable BDD scenarios:

| Test category | Scenarios |
|---|---:|
| Original addition scenario | 1 |
| Addition Scenario Outline | 3 |
| Additional operation scenarios | 3 |
| Required operation examples | 3 |
| Arithmetic edge cases | 10 |
| Division by zero | 1 |
| **Total** | **21** |

The scenarios cover positive and negative operands,
zero, fractional division, negative exponents, and
division by zero.

## Undefined Step Investigation

The original addition Scenario Outline produced an
undefined step when the first operand was negative.

The original regular expression did not accept a
minus sign.

We corrected the step definition to support signed
integers and verified the correction by rerunning
the test suite.

See [Undefined Step Analysis](docs/undefined-step-analysis.md)
for the investigation and its results.

## Verification Results

The final execution of `mvn clean test` produced:

- Tests executed: 21
- Failures: 0
- Errors: 0
- Skipped: 0
- Build status: SUCCESS

These results were observed during local testing.

## Project Structure

- `src/main/java/calculator/` — Calculator implementation
- `src/test/java/calculator/` — Step definitions and test runner
- `src/test/resources/features/` — Gherkin feature file
- `docs/` — Written investigation
- `pom.xml` — Maven configuration