// LAYOUT & COMPONENTS
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Dimension;

public class TallyCounter extends JFrame {
    // state variable
    private int count;

    // layout
    // components
    private JFormattedTextField numberDisplay;
    private JButton incrementButton;
    private JButton resetButton;
    
    public TallyCounter() {
        super("Tally Counter");
        // padding 10 between components
        setLayout(new BorderLayout(10, 10));

        count = 0;

        // Instantiate our components
        numberDisplay = new JFormattedTextField(NumberFormat.getNumberInstance());
        numberDisplay.setValue(count);
        incrementButton = new JButton("Add 1");
        resetButton = new JButton("Reset Count");

        // connect our event listeners
        incrementButton.addActionListener(new IncrementButtonHandler());
        resetButton.addActionListener(new ResetButtonHandler());


        // configure sizes
        incrementButton.setPreferredSize(new Dimension(100, 20));
        resetButton.setPreferredSize(new Dimension(150, 20));

        // Let's create panels for holding our buttons and display
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(incrementButton);
        buttonPanel.add(resetButton);

        JPanel displayPanel = new JPanel();

        displayPanel.add(numberDisplay);
        // add components to layout
        add(displayPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    
        // Configuration
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // layout

        // center the window on the screen
        setLocationRelativeTo(null);
    }

    // EVENT HANDLERS

    private class IncrementButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            count++;
            numberDisplay.setValue(count);
        }
    }
    
    private class ResetButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            count = 0;
            numberDisplay.setValue(count);
        }
    }

    public static void main(String[] args) {
        TallyCounter window = new TallyCounter();
        window.setVisible(true); 
    }
}
