public class AdditionExpression extends BinaryExpression {
    public AdditionExpression(MathematicalExpression left, MathematicalExpression right) {
        super(left, right, "+");
    }

    @Override
    public double evaluate() {
        return left.evaluate() + right.evaluate();
    }

}