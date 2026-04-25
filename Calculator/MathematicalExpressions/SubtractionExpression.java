package MathematicalExpressions;

public class SubtractionExpression extends BinaryExpression {
    public SubtractionExpression(MathematicalExpression left, MathematicalExpression right) {
        super(left, right);
    }

    @Override
    public double evaluate() {
        return evaluateLeft() - evaluateRight();
    }
}