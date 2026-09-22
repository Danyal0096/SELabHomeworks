# Undefined Step Analysis

## Experiment 4 — Behavior-Driven Development

### 1. Problem

The original Scenario Outline contained three examples.
The example with inputs -1 and 6 produced an undefined
Given step:

`Two input values, -1 and 6`

The initial Maven test run executed four scenarios and
reported one error.

### 2. Root Cause

The original Given step definition used the regular
expression:

`^Two input values, (\d+) and (\d+)$`

The `\d+` pattern matches digits but does not accept
a minus sign. Consequently, Cucumber could not match
the step containing -1.

### 3. Solution

We updated the Given expression to:

`^Two input values, (-?\d+) and (-?\d+)$`

The optional minus sign allows both positive and
negative integers.

We also updated the Then expression to support
negative expected results.

### 4. Verification

After applying the correction, we executed:

`mvn test`

Results:
- Tests executed: 4
- Failures: 0
- Errors: 0
- Skipped: 0
- Build status: SUCCESS

All original addition scenarios now pass.