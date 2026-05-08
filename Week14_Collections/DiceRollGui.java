import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceRollGui extends JFrame implements ActionListener {
    private JComboBox<Die> dropdown;
    private JButton rollButton;
    
    public DiceRollGui() {
        super("Dice Roller");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Die[] dice = new Die[] {
            new PolyhedralDie(4),
            new PolyhedralDie(6),
            new PolyhedralDie(8),
            new PolyhedralDie(12),
            new CritDie(),
            new PercentileDicePair()
        };

        dropdown = new JComboBox<>(dice);

        rollButton = new JButton("Roll Dice");

        // connect our event listeners
        rollButton.addActionListener(this);

        // Add components to layout
        add(dropdown, this);
        add(rollButton, this);

        // Configuration
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // layout

        // center the window on the screen
        setLocationRelativeTo(null);
    }

    // Event Handler
    
    public static void main(String[] args) {
        DiceRollGui window = new DiceRollGui();
        window.setVisible(true);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dropdown.getItemAt(dropdown.getSelectedIndex()).roll();
        dropdown.repaint();
    }

}
