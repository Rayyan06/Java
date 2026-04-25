package MathematicalExpressions;

public class ExponentExpression extends BinaryExpression {
    public ExponentExpression(MathematicalExpression left, MathematicalExpression right) {
        super(left, right);
    }

    @Override
    public double evaluate() {
        return Math.pow(evaluateLeft(), evaluateRight());
    }
}
