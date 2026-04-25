package MathematicalExpressions;

public class ConstantExpression implements MathematicalExpression {
    private final double value;

    public ConstantExpression(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

}