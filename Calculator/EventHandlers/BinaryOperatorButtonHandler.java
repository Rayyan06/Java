package EventHandlers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import MathematicalExpressions.BinaryExpression;
import MathematicalExpressions.AdditionExpression;
import MathematicalExpressions.ConstantExpression;
import MathematicalExpressions.DivisionExpression;
import MathematicalExpressions.ExponentExpression;
import MathematicalExpressions.MathematicalExpression;
import MathematicalExpressions.SubtractionExpression;
import main.Calculator;
import MathematicalExpressions.MultiplicationExpression;
import MathematicalExpressions.SquareRootExpression;


/*
Handles clicking a math operation button
*/
public class BinaryOperatorButtonHandler extends OperatorButtonHandler {
    
    public BinaryOperatorButtonHandler(Calculator calculator) {
        super(calculator);
    }

    @Override
    public void calculate(String command) {
        double leftBoxValue = calculator.getLeftBoxValue();
        double rightBoxValue = calculator.getRightBoxValue();

        MathematicalExpression leftBoxExpression = new ConstantExpression(leftBoxValue);
        MathematicalExpression rightBoxExpression = new ConstantExpression(rightBoxValue);

        BinaryExpression calculatedExpression = null;

        // command selector
        switch (command) {
            case "add":
                calculatedExpression = new AdditionExpression(leftBoxExpression, rightBoxExpression);
                break;
            case "subtract":
                calculatedExpression = new SubtractionExpression(leftBoxExpression, rightBoxExpression);
                break;
            case "multiply":
                calculatedExpression = new MultiplicationExpression(leftBoxExpression, rightBoxExpression);
                break;
            case "divide":
                calculatedExpression = new DivisionExpression(leftBoxExpression, rightBoxExpression);
                break;
            case "exponent":
                calculatedExpression = new ExponentExpression(leftBoxExpression, rightBoxExpression);
                break;
            default:
                throw new IllegalArgumentException("Unknown Command");
        }

        // clear the second box
        calculator.setRightBoxValue(0);
        // set the value of the left box (results box) to the calculated expression
        calculator.setLeftBoxValue(calculatedExpression.evaluate());
    }
}

