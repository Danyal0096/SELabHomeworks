package calculator;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CalculatorSteps {

    private Calculator calculator;
    private int first;
    private int second;
    private double result;
    private ArithmeticException divisionError;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Given("^Two input values, (-?\\d+) and (-?\\d+)$")
    public void twoInputValues(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @When("^I add the two values$")
    public void addTheTwoValues() {
        result = calculator.add(first, second);
    }

    @When("^I calculate using (\\*\\*|\\*|/|\\^)$")
    public void calculateUsing(String operation) {

        switch (operation) {
            case "*":
                result = calculator.multiply(first, second);
                break;

            case "/":
                result = calculator.divide(first, second);
                break;

            case "^":
            case "**":
                result = calculator.power(first, second);
                break;

            default:
                throw new IllegalArgumentException(
                    "Unsupported operation: " + operation
                );
        }
    }

    @Then("^I expect the result (-?\\d+(?:\\.\\d+)?)$")
    public void expectTheResult(double expected) {
        Assert.assertEquals(expected, result, 1e-9);
    }

    @When("^I attempt to divide the two values$")
    public void attemptToDivide() {
        divisionError = null;

        try {
            calculator.divide(first, second);
        } catch (ArithmeticException exception) {
            divisionError = exception;
        }
    }

    @Then("^division by zero is rejected$")
    public void divisionByZeroIsRejected() {
        Assert.assertNotNull(
            "Expected an ArithmeticException",
            divisionError
        );

        Assert.assertEquals(
            "Division by zero",
            divisionError.getMessage()
        );
    }
}