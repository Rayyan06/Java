package MathematicalExpressions;

public class DivisionExpression extends BinaryExpression {
    public DivisionExpression(MathematicalExpression left, MathematicalExpression right) {
        super(left, right);
    }

    @Override
    public double evaluate() {

        double rightValue = evaluateRight();
        // check for zero division here!
        if(rightValue == 0.0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }

        return evaluateLeft() / rightValue;
    }
}