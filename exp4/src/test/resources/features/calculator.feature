Feature: Calculator
  As a user of the calculator
  I want to add two numbers
  So that I receive their sum

  Scenario: add two numbers
    Given Two input values, 1 and 2
    When I add the two values
    Then I expect the result 3

Scenario Outline: add two numbers with examples
  Given Two input values, <first> and <second>
  When I add the two values
  Then I expect the result <result>

  Examples:
    | first | second | result |
    | 1     | 12     | 13     |
    | -1    | 6      | 5      |
    | 2     | 2      | 4      |

  Scenario: multiply two numbers
    Given Two input values, 3 and 4
    When I calculate using *
    Then I expect the result 12

  Scenario: divide two numbers
    Given Two input values, 8 and 2
    When I calculate using /
    Then I expect the result 4

  Scenario: exponentiation using double asterisk
    Given Two input values, 2 and 3
    When I calculate using **
    Then I expect the result 8

  Scenario Outline: calculate using different operations
    Given Two input values, <first> and <second>
    When I calculate using <opt>
    Then I expect the result <result>

    Examples:
      | first | second | opt | result |
      | 6     | 2      | *   | 12     |
      | 6     | 2      | /   | 3      |
      | 6     | 2      | ^   | 36     |

    Scenario Outline: handle arithmetic edge cases
    Given Two input values, <first> and <second>
    When I calculate using <opt>
    Then I expect the result <result>

    Examples:
      | first | second | opt | result |
      | -3    | 4      | *   | -12    |
      | -3    | -4     | *   | 12     |
      | 0     | 7      | *   | 0      |
      | -8    | 2      | /   | -4     |
      | 7     | 2      | /   | 3.5    |
      | 0     | 5      | /   | 0      |
      | -2    | 3      | ^   | -8     |
      | 2     | 0      | ^   | 1      |
      | 2     | -2     | ^   | 0.25   |
      | 0     | 3      | ^   | 0      |

    Scenario: reject division by zero
      Given Two input values, 6 and 0
      When I attempt to divide the two values
      Then division by zero is rejected