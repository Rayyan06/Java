package main;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;
import java.util.function.UnaryOperator;

import javax.swing.text.NumberFormatter;

import EventHandlers.BinaryOperatorButtonHandler;
import EventHandlers.UnaryOperatorButtonHandler;


public class Calculator extends JFrame {
    // textfields
    private JTextField leftBox;
    private JTextField rightBox;

    // input validation
    // private NumberFormat inputFieldNumberFormat;
    // private NumberFormatter formatter;

    // textfield labels
    private JLabel leftBoxLabel;
    private JLabel rightBoxLabel;

    // buttons
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton squareRootButton;
    private JButton squareButton;
    private JButton exponentButton;

    // event listeners
    private BinaryOperatorButtonHandler bobh;
    private UnaryOperatorButtonHandler uobh;
    
    // layout stuff
    /*
    I decided on GridBagLayout due to the flexibility it offers us 
    in having components of various widths, while maintaining a grid structure
    */
    private GridBagLayout gbl;
    private GridBagConstraints gbc;

    public Calculator() {
        super("Calculator");
        
        gbl = new GridBagLayout();
        setLayout(gbl);

        // instantiate our components
        // NOTE: WE CANNOT USE NUMBER FORMAT OR JFormattedTextField due to the program spec
        // NumberFormat: https://docs.oracle.com/javase/8/docs/api/java/text/NumberFormat.html
        // inputFieldNumberFormat = NumberFormat.getNumberInstance();
        // inputFieldNumberFormat.setGroupingUsed(false); // disable the commas
        // formatter = new NumberFormatter(inputFieldNumberFormat);
        // formatter.setValueClass(Double.class);

        // formatter.setAllowsInvalid(false); // do NOT allow invalid input

        // leftBox = new JFormattedTextField(formatter);
        // leftBox.setColumns(10);
        leftBox = new JTextField(10);
        rightBox = new JTextField(10);

        leftBoxLabel = new JLabel("First Number x / Result");
        rightBoxLabel = new JLabel("Second Number y");

        addButton = new JButton("Add: x + y");
        subtractButton = new JButton("Subtract: x - y");
        multiplyButton = new JButton("Multiply: x * y");
        divideButton = new JButton("Divide: x / y");
        squareRootButton = new JButton("Square Root: sqrt(x)");
        squareButton = new JButton("Square: x^2");
        exponentButton = new JButton("Exponent: x^y");

        // configure out layout settings for components
        gbc = new GridBagConstraints();     

        // add components to layout

        gbc.weightx = 0.5; // stretch each sub-component to fill half of the width

        // first row
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // center within parent

        gbc.gridx = 0;
        add(leftBoxLabel, gbc);

        gbc.gridx = 1;
        add(rightBoxLabel, gbc);

        // second row
        gbc.fill = GridBagConstraints.BOTH; // maximum horizontal and vertical fill
        gbc.gridy++;
        gbc.weighty = 0.3;

        // add the left box
        gbc.gridx = 0;
        add(leftBox, gbc); // pass gbc as the second parameter

        // add the right box
        gbc.gridx = 1;
        add(rightBox, gbc);


        // buttons
        gbc.fill = GridBagConstraints.BOTH; // maximum horizontal and vertical fill

        gbc.gridx = 0; // reset to the first column
        gbc.gridwidth = 2; // tell the buttons to span both columns
        gbc.gridy++;
        gbc.weightx = 1.0; // fill the entire width

        add(addButton, gbc);

        gbc.gridy++;
        add(subtractButton, gbc);

        gbc.gridy++;
        add(multiplyButton, gbc);
        
        gbc.gridy++;
        add(divideButton, gbc);

        gbc.gridy++;
        add(squareRootButton, gbc);

        gbc.gridy++;
        add(squareButton, gbc);

        gbc.gridy++;
        add(exponentButton, gbc);


        // connect our event listeners
        // BINARY OPERATORS
        bobh = new BinaryOperatorButtonHandler(this);
        addButton.addActionListener(bobh);
        addButton.setActionCommand("add");

        subtractButton.addActionListener(bobh);
        subtractButton.setActionCommand("subtract");

        multiplyButton.addActionListener(bobh);
        multiplyButton.setActionCommand("multiply");

        divideButton.addActionListener(bobh);
        divideButton.setActionCommand("divide");
        
        exponentButton.addActionListener(bobh);
        exponentButton.setActionCommand("exponent");


        // UNARY OPERATORS
        uobh = new UnaryOperatorButtonHandler(this);
        squareRootButton.addActionListener(uobh);
        squareRootButton.setActionCommand("squareroot");

        squareButton.addActionListener(uobh);
        squareButton.setActionCommand("square");


        pack();
        
        // Window Configuration
        // center the window on the screen when it appears
        setLocationRelativeTo(null); 
        // end the program when we click the X button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // accessors
    public double getLeftBoxValue() throws NumberFormatException {
        return Double.parseDouble(leftBox.getText());
    }

    public double getRightBoxValue() throws NumberFormatException {
        return Double.parseDouble(rightBox.getText());
    }

    // mutators
    public void setLeftBoxValue(double number) {
        leftBox.setText(String.valueOf(number));
    }

    public void setRightBoxValue(double number) {
        rightBox.setText(String.valueOf(number));
    }

    public static void main(String[] args) {
        Calculator window = new Calculator();
        window.setVisible(true);
    }
}
