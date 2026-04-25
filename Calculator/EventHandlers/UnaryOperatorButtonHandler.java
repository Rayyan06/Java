package EventHandlers;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import MathematicalExpressions.ConstantExpression;
import MathematicalExpressions.MathematicalExpression;
import MathematicalExpressions.SquareExpression;
import MathematicalExpressions.SquareRootExpression;
import MathematicalExpressions.UnaryExpression;
import main.Calculator;

public class UnaryOperatorButtonHandler extends OperatorButtonHandler {
    public UnaryOperatorButtonHandler(Calculator calculator) {
        super(calculator);
    }
    @Override
    public void calculate(String command) {
        double leftBoxValue = calculator.getLeftBoxValue();

        MathematicalExpression leftBoxExpression = new ConstantExpression(leftBoxValue);

        UnaryExpression calculatedExpression = null;

        // command selector
        switch (command) {   
            case "squareroot":
                calculatedExpression = new SquareRootExpression(leftBoxExpression);
                break;
            case "square":
                calculatedExpression = new SquareExpression(leftBoxExpression);
                break;
            default:
                throw new IllegalArgumentException("Unknown Command");
        }

        // set the value of the left box (results box) to the calculated expression
        calculator.setLeftBoxValue(calculatedExpression.evaluate());
    }
}


