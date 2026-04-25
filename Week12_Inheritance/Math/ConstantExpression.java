public class ConstantExpression implements MathematicalExpression {
    private double value;
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