package MathematicalExpressions;

public class SquareExpression extends UnaryExpression {
    public SquareExpression(MathematicalExpression operand) {
        super(operand);
    }

    @Override
    public double evaluate() {
        double operandValue = operand.evaluate();
        return operandValue * operandValue;
    }
}
