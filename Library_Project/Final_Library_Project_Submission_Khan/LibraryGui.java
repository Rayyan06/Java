import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// UI:
import java.awt.Dimension;
import javax.swing.BoxLayout;

// For Book creation form
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

// For shelf selection
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

// For Dialog box to view books
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;

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
            library = new Library();
        } catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            library = new Library();

        } catch (FileNotFoundException fnf) {
            System.out.println("Error, library file not found: " + fnf.getMessage()); // convey additional details to the user
            library = new Library();
        }

        // if there's a problem reading

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create our components
        readBooksFromFile = new JButton("Load Books From File");
        addShelfButton = new JButton("Add a shelf");
        addBookButton = new JButton("Add a book");

        // It's better to use a JComboBoxModel for this!
        // the problem: JComboBox requires arrays 
        shelfComboBoxModel = new DefaultComboBoxModel<>();
        // add all the current shelves to the model:
        shelfComboBoxModel.addAll(library.getShelves());
        shelveDropdown = new JComboBox<>(shelfComboBoxModel);

        calculateAveragePageCount = new JButton("Calculate average page count");
        displayBooksOnShelf = new JButton("View books in selected shelf");

        // prevent elements from stretching too much
        Dimension maxSize = new Dimension(250, 300);

        shelveDropdown.setMaximumSize(maxSize);
        readBooksFromFile.setMaximumSize(maxSize);
        addShelfButton.setMaximumSize(maxSize);
        calculateAveragePageCount.setMaximumSize(maxSize);
        displayBooksOnShelf.setMaximumSize(maxSize);
        addBookButton.setMaximumSize(maxSize);

        // align components (TODO)
        readBooksFromFile.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        addShelfButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        addBookButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        shelveDropdown.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        calculateAveragePageCount.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        displayBooksOnShelf.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

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

        displayBooksOnShelf.addActionListener(this);
        displayBooksOnShelf.setActionCommand("viewbooks");

        calculateAveragePageCount.addActionListener(this);
        calculateAveragePageCount.setActionCommand("average");
        

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
            {
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
                    shelveDropdown.repaint();

                } catch (FileNotFoundException fnf) {
                    JOptionPane.showMessageDialog(this, "File not found", "Error", JOptionPane.ERROR_MESSAGE); // convey additional details to the user
                }


                break;
            }
            case "addshelf":
            {
                String genreName = JOptionPane.showInputDialog("Enter a Genre");
                
                if(genreName == null) {
                    break;
                } else if (genreName.isBlank()) {
                    JOptionPane.showMessageDialog(this, "Shelf cannot have a blank genre name.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                Shelf newShelf = new Shelf(genreName);

                if(!library.addShelf(newShelf)) {
                    JOptionPane.showMessageDialog(this, "Shelf of genre \"" + genreName + "\" already exists.", "Warning", JOptionPane.ERROR_MESSAGE);
                }

                // update the dropdown 
                shelfComboBoxModel.addElement(newShelf);
                JOptionPane.showMessageDialog(this, String.format("%s shelf was successfully added the library", newShelf), "Success", JOptionPane.INFORMATION_MESSAGE);

                shelveDropdown.repaint();

                break;
            }
            case "addbook":
            {
                JTextField bookTitleField = new JTextField();

                JComboBox bookGenreField = new JComboBox<>(shelfComboBoxModel);

                // We don't want a maximum limit to pagecount, but we can't have negative page counts!
                SpinnerNumberModel snModel = new SpinnerNumberModel(0, 0, null, 1);
                JSpinner bookPageCountField = new JSpinner(snModel);

                Object[] createBookForm = {
                    "Book Title:", bookTitleField,
                    "Book Genre", bookGenreField,
                    "Book Page Count", bookPageCountField
                };
                
                boolean bookAdded = false;

                while(!bookAdded) {
                    int option = JOptionPane.showConfirmDialog(this, createBookForm, "Create a book", JOptionPane.YES_NO_OPTION);
                
                    // If the user hits cancel or closes the window, exit early
                    if (option != JOptionPane.YES_OPTION) {
                        break;
                    }

                    // The user chose to submit the form
                    // get the form values
                    String bookTitle = bookTitleField.getText();
                    Shelf selectedShelf = (Shelf) bookGenreField.getModel().getSelectedItem();
                    int bookPageCount = (int) bookPageCountField.getValue();


                    // Error handling
                    if (bookTitle.isBlank()) {
                        JOptionPane.showMessageDialog(this, "Book cannot have a blank title.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (selectedShelf == null) {
                        JOptionPane.showMessageDialog(this, "No shelf selected in dropdown", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String bookGenre = selectedShelf.getGenre();

                        if(!library.addBook(bookTitle, bookGenre, bookPageCount)) {
                            JOptionPane.showMessageDialog(this, String.format("No shelf of genre %s was found for %s", bookGenre, bookTitle), "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, String.format("%s (%d pages) was successfully added the %s shelf", bookTitle, bookPageCount, bookGenre), "Success", JOptionPane.INFORMATION_MESSAGE);
                            bookAdded = true;
                        }
                    }
                }

                break;
            }
            case "viewbooks":
            {
                /* Display the titles of all books in the selected shelf in a dialog box */
                // grab our selected shelf
                Shelf selectedShelf = (Shelf) shelveDropdown.getModel().getSelectedItem();

                if(selectedShelf == null) {
                    JOptionPane.showMessageDialog(this, "No shelf selected in dropdown", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }

                DefaultListModel<Book> bookListViewModel = new DefaultListModel<>();

                // add all the books to the shelf
                bookListViewModel.addAll(selectedShelf.listBooks());

                JList<Book> bookListView = new JList<>(bookListViewModel);

                JOptionPane.showMessageDialog(
                    this, 
                    bookListView, 
                    String.format("Books of genre %s", selectedShelf.getGenre()), 
                    JOptionPane.OK_CANCEL_OPTION
                );

                break;
            }

            case "average":
            {
                Shelf selectedShelf = (Shelf) shelveDropdown.getModel().getSelectedItem();
                if(selectedShelf == null) {
                    JOptionPane.showMessageDialog(this, "No shelf selected in dropdown", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                JOptionPane.showMessageDialog(
                    this, 
                    String.format(
                        "The average page count for books on the %s shelf is %d pages", 
                        selectedShelf.getGenre(), 
                        selectedShelf.computeAveragePageCount()
                    ),
                    "Average",
                    JOptionPane.INFORMATION_MESSAGE
                );
                break;
            }

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