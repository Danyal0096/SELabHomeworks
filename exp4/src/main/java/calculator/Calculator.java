package calculator;

public class Calculator {

    public int add(int first, int second) {
        return first + second;
    }

    public int multiply(int first, int second) {
        return first * second;
    }

    public double divide(int first, int second) {
        if (second == 0) {
            throw new ArithmeticException("Division by zero");
        }

        return (double) first / second;
    }

    public double power(int first, int second) {
        return Math.pow(first, second);
    }
}