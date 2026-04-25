
// EVENT HANDLING
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// COMPONENTS
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



// Often, we want our subclasses to be from JFrame
public class GuiExample extends JFrame implements ActionListener {
    // instance variables for our various components
    private JButton firstButton;
    private JButton secondButton;
    private JTextField textField;

    public GuiExample() {
        super("Example window program");
        setLayout(new FlowLayout());

        // instantiate the instance variables
        firstButton = new JButton("First Button");
        secondButton = new JButton("Second Button");
        textField = new JTextField(10);

        // connect our action listeners
        firstButton.addActionListener(this);
        firstButton.setActionCommand("addlabel");
        secondButton.addActionListener(this);
        secondButton.setActionCommand("somethingelse");

        // add our components to the JFrame
        add(new JLabel("Some text"));
        add(firstButton);
        add(secondButton);
        add(new JLabel("More text"));

        add(textField);

        // config
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("addlabel")) {
            String userInput = JOptionPane.showInputDialog("Enter some text");
            add(new JLabel(userInput));          
            revalidate();
            repaint();
        } else {
            int option = JOptionPane.showConfirmDialog(this,  "Choose an option","Dialog box", JOptionPane.YES_NO_CANCEL_OPTION);
            if(option == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Yes", "Chosen option", JOptionPane.INFORMATION_MESSAGE);
            } else if(option == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "No", "Chosen option", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cancel", "Chosen option", JOptionPane.WARNING_MESSAGE);
            }
        }
          
    }

    public static void main(String[] args) {
        GuiExample window = new GuiExample();
        window.setVisible(true);
    }
}