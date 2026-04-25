package MathematicalExpressions;

public abstract class UnaryExpression implements MathematicalExpression {
    protected MathematicalExpression operand;

    public UnaryExpression(MathematicalExpression operand) {
        this.operand = operand;
    }
}