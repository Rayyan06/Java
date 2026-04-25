public class MultiplicationExpression extends BinaryExpression {

    public MultiplicationExpression(MathematicalExpression left, MathematicalExpression right) {
        super(left, right, "*");
    }

    @Override
    public double evaluate() {
        return evaluateLeft() + evaluateRight();
    }
}