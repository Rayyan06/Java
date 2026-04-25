package EventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.Calculator;

public abstract class OperatorButtonHandler implements ActionListener{
    protected Calculator calculator;

    public OperatorButtonHandler(Calculator calculator) {
        this.calculator = calculator;
    }

    // Write our event handler once for both our unary and binary operators
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            calculate(e.getActionCommand());
            
        } catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(calculator, "Invalid Format: " + nfe.getLocalizedMessage(), "Number Format Error", JOptionPane.ERROR_MESSAGE);
        } catch(ArithmeticException ae) {
            JOptionPane.showMessageDialog(calculator, "Error: " + ae.getLocalizedMessage(), "Math Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(calculator, "Error: Illegal operator", "Program Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // implement the actual calculation logic seperately
    protected abstract void calculate(String command) throws NumberFormatException;
}
