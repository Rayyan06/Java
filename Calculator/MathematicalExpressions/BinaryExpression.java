package MathematicalExpressions;

public abstract class BinaryExpression implements MathematicalExpression {

    private MathematicalExpression left; // not final, we can swap
    private MathematicalExpression right;
    
    public BinaryExpression(MathematicalExpression left, MathematicalExpression right) {
        this.right = right;
        this.left = left;
    }

    public double evaluateLeft() {
        return left.evaluate();
    }

    public double evaluateRight() {
        return right.evaluate();
    }
}