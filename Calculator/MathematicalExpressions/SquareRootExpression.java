package MathematicalExpressions;

public class SquareRootExpression extends UnaryExpression {
    public SquareRootExpression(MathematicalExpression operand) {
        super(operand);
    }

    @Override
    public double evaluate() {
        double operandValue = operand.evaluate();
        
        if(operandValue < 0) {
            throw new ArithmeticException("Square Root of a Negative Number does not exist");
        }

        return Math.sqrt(operandValue);
    }
}