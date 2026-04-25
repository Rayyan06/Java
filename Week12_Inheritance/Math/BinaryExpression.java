public abstract class BinaryExpression implements MathematicalExpression {

    private final String operator;
    protected MathematicalExpression left; // not final, we can swap
    protected MathematicalExpression right;
    
    public BinaryExpression(MathematicalExpression left, MathematicalExpression right, String operator) {
        this.right = right;
        this.left = left;
    }

    @Override
    public String toString() {
        return "(" + right + " " + operator + " " + left + ")";
    }

    public void swapOperands() {
        MathematicalExpression temp = left;
        left = right;
        right = temp;
    }


    protected void setLeft(MathematicalExpression left) {
        this.left = left;
    }

    protected void setRight(MathematicalExpression right) {
        this.right = right;
    }

    public double evaluateLeft() {
        return left.evaluate();
    }

    public double evaluateRight() {
        return right.evaluate();
    }
}