import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

// event handling
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Exceptions
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

// File reading
import java.util.Scanner;
import java.io.FileReader;

public class LibraryGui extends JFrame implements ActionListener {
    // Use composition here, inheritance would add confusion because a GUI does much more than the business logic of managing books
    private Library library;
    private JOptionPane genreSelector;
    private JButton readBooksFromFile;
    private JButton addBookButton;
    private JButton addShelfButton;
    private DefaultComboBoxModel<Shelf> shelfComboBoxModel;
    private JComboBox<Shelf> shelveDropdown;
    private JButton calculateAveragePageCount;
    private JButton displayBooksOnShelf;

    public LibraryGui(String libraryFilename) {
        super("Library Manager"); // super must be FIRST

        // Create our library and try to load the books from our library file
        try {
            library = Library.loadFromLibraryFile(libraryFilename);
            System.out.printf("Library with %d shelves successfully created\n", library.getShelfCount());
        } catch(InputMismatchException ime) {
            System.out.println(ime.getMessage());
            return;
        } catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            return;
        } catch (FileNotFoundException fnf) {
            System.out.println("Error, library file not found: " + fnf.getMessage()); // convey additional details to the user
            return;
        }

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create our components
        readBooksFromFile = new JButton("Load Books From File");
        addShelfButton = new JButton("Add a shelf");
        addBookButton = new JButton("Add a book");

        // It's better to use a JComboBoxModel for this!
        // the problem: JComboBox requires arrays 
        shelfComboBoxModel= new DefaultComboBoxModel<>(library.getShelves().toArray(new Shelf[0]));
        shelveDropdown = new JComboBox<>(shelfComboBoxModel);

        calculateAveragePageCount = new JButton("Calculate average page count");
        displayBooksOnShelf = new JButton("View books in selected shelf");

        // align components (TODO)

        // add components to layout
        // add(genreSelector, this);
        add(readBooksFromFile, this);
        add(addShelfButton, this);
        add(addBookButton, this);
        add(shelveDropdown, this);
        add(calculateAveragePageCount, this);
        add(displayBooksOnShelf, this);

        // connect our event listeners
        readBooksFromFile.addActionListener(this);
        readBooksFromFile.setActionCommand("load");

        addShelfButton.addActionListener(this);
        addShelfButton.setActionCommand("addshelf");

        addBookButton.addActionListener(this);
        addBookButton.setActionCommand("addbook");
        

        // Configuration
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // layout

        // center the window on the screen
        setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "load":
                String bookListFilename = JOptionPane.showInputDialog("Enter a filename");

                if(bookListFilename == null) {
                    // the user hit cancel or didn't type anything
                    break;
                }

                try (
                    Scanner bookListFileInput = new Scanner(new FileReader(bookListFilename.strip()));
                ) {

                    int numBooksReadSuccessfully = library.importBooks(bookListFileInput);
                    JOptionPane.showMessageDialog(this, String.format("%d books were successfully read from %s", numBooksReadSuccessfully, bookListFilename), "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (FileNotFoundException fnf) {
                    JOptionPane.showMessageDialog(this, "File not found", "Error", JOptionPane.ERROR_MESSAGE); // convey additional details to the user
                }

                break;

            case "addshelf":
                String genreName = JOptionPane.showInputDialog("Enter a Genre");
                if(genreName == null) {
                    break;
                }
                
                Shelf newShelf = new Shelf(genreName);

                if(!library.addShelf(newShelf)) {
                    JOptionPane.showMessageDialog(this, "Shelf of genre \"" + genreName + "\" already exists.", "Warning", JOptionPane.ERROR_MESSAGE);
                }

                // update the dropdown 
                shelfComboBoxModel.addElement(newShelf);
            
                break;

            case "addbook":
                JTextField bookTitleField = new JTextField();
                JTextField bookGenreField = new JTextField();

                // We don't want a maximum limit to pagecount, but we can't have negative page counts!
                SpinnerNumberModel snModel = new SpinnerNumberModel(0, 0, null, 1);
                JSpinner bookPageCountField = new JSpinner(snModel);

                Object[] createBookForm = {
                    "Book Title:", bookTitleField,
                    "Book Genre", bookGenreField,
                    "Book Page Count", bookPageCountField
                };

                int option = JOptionPane.showConfirmDialog(this, createBookForm, "Create a book", JOptionPane.YES_NO_OPTION);
                
                if(option == JOptionPane.YES_OPTION) {
                    String bookTitle = bookTitleField.getText();
                    String bookGenre = bookGenreField.getText();
                    int bookPageCount = (int) bookPageCountField.getValue();

                    if(!library.addBook(bookTitle, bookGenre, bookPageCount)) {
                        JOptionPane.showMessageDialog(this, String.format("No shelf of genre %s was found for %s", bookGenre, bookTitle), "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    JOptionPane.showMessageDialog(this, String.format("%s (%d pages) was successfully added the %s shelf", bookTitle, bookPageCount, bookGenre), "Success", JOptionPane.INFORMATION_MESSAGE);
                } 

                break;
            case "viewbooks":
                break;
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Must specify exactly one argument (library file name)");
            return;
        }
        LibraryGui window = new LibraryGui(args[0]);
        window.setVisible(true);
    }

}