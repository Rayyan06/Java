import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class Library {
    private Shelf[] shelves;
    private int shelfIndex;

    public Library(int shelveCount) {
        // A library must have at least one shelf
        if(shelveCount <= 0) {
            throw new IllegalArgumentException("Shelf count must be positive.");
        }
        shelves = new Shelf[shelveCount];
        shelfIndex = 0;
    }
    // methods should be private by default, for encapsulation


    private void addShelf(String genre, int capacity) {
       // check to ensure we aren't adding shelves with duplicate genre
        for(int i = 0; i < shelfIndex; i++) {
            // Must use equals() method
            if(shelves[i].getGenre().equals(genre)) {
                System.out.println("Warning: Shelf of genre \"" + genre + "\" was already added. Skipped.");
                return;
            }
        }

        shelves[shelfIndex] = new Shelf(genre, capacity);
        shelfIndex++;
    }

    // finds a shelf of a particular genre and returns it
    // if it cannot find the matching shelf, returns null
    public Shelf findShelf(String genre) {
        for(int i = 0; i < shelfIndex; i++) {
            // check if the strings are equal
            if(shelves[i].getGenre().equals(genre)) {
                return shelves[i];
            }
        }

        return null;
    }

    // returns number of shelves in library
    public int getShelfCount() {
        return shelfIndex;
    }


    // lists all shelves in library
    public void listShelves() {
        if(shelfIndex == 0) System.out.println("No shelves in library. Check library file import");
        for(int i = 0; i < shelfIndex; i++) {
            System.out.println(shelves[i]);
        }
    }

    // Lists all books in library
    public void listBooks() {
        for(int i = 0; i < shelfIndex; i++) {
            String border = "--".repeat(shelves[i].getGenre().length()); // Just an aesthetic border
            System.out.println(border); 
            System.out.println(shelves[i]);
            System.out.println(border); // Just an aesthetic border
            System.out.println(shelves[i].listBooks());
        }
    }

    /*
    Loads books from library file
    Format:
    - First line contains number indicating how many shelves the library has
    - Each subsequent line contains a one-word genre, followed by a number indicating the size of the shelf

    */
    public static Library loadFromLibraryFile(String libraryFilename) throws FileNotFoundException {
        // use try-with-resources block
        Library myLibrary;

        try (
            Scanner libraryFileInput = new Scanner(new FileReader(libraryFilename));
        ) {

            if(!libraryFileInput.hasNextInt()) {
                throw new IllegalArgumentException("Error: Library file does not indicate how many shelves it has on the first line");
            }

            // Read the first line of the library file to obtain the number of shelves
            int shelveCount = libraryFileInput.nextInt();

            myLibrary = new Library(shelveCount);

            // Use For-Loop here instead of a while-loop. Iterating up to shelveCount?

            for(int i = 0; i < shelveCount; i++) {
                if(!libraryFileInput.hasNextLine()) {
                    System.out.println("Warning: File ended early, expected " + shelveCount + " shelves but only found " + i);
                    break;
                }

                libraryFileInput.nextLine(); // Consume the remainder of the current line

                String shelfGenre = libraryFileInput.next();

                if(!libraryFileInput.hasNextInt()) {
                    throw new IllegalArgumentException(String.format("Error: Shelf \"%s\" capacity must be a valid positive integer", shelfGenre));
                }

                int shelfCapacity = libraryFileInput.nextInt();

                // Create the shelf
                myLibrary.addShelf(shelfGenre, shelfCapacity);
            }

            // If there are extra lines
            if(libraryFileInput.hasNextLine()) {
                System.out.println("Warning: File has more shelves than specified " + shelveCount);
            }
        }
        return myLibrary;
    }


    // returns number of books read successfully from book list file
    private int importBooks(Scanner bookListFileInput)  {
        int numBooksReadSuccessfully = 0;

        // Read each line
        while(bookListFileInput.hasNextLine()) {
            // .next() reads until the next space
            String bookGenre = bookListFileInput.next();

            if(!bookListFileInput.hasNextInt()) {
                System.out.println("Warning: Malformed Line \"" + bookGenre + "\". No page count. Line skipped");
                bookListFileInput.nextLine(); // consume rest of malformed line
                continue;
            }

            int bookPageCount = bookListFileInput.nextInt();

            if(!bookListFileInput.hasNextLine()) {
                System.out.println("Warning: Malformed Line \"" + bookGenre + "\". No Title Provided. Line skipped");
                continue;
            }

            // The title may contain spaces, so we must read the rest of the line
            String bookTitle = bookListFileInput.nextLine().strip(); // remove initial space

            // Create the book object
            Book newBook = new Book(bookTitle, bookPageCount);

            /*
            Retrieve a shelf of a particular Genre and add the book
            */

            Shelf shelf = findShelf(bookGenre);

            if(shelf == null) {
                System.out.println("Warning: No shelf with genre \"" + bookGenre + "\" was found for \"" + bookTitle + "\". Line skipped");
                continue;
            }

            // add the book. if the book cannot be added, print an error
            if(!shelf.addBook(newBook)) {
                System.out.println("Warning: Shelf of genre \"" + shelf.getGenre() + "\" is full. Line skipped");
                continue;
            }

            numBooksReadSuccessfully++;
        }

        return numBooksReadSuccessfully;
    }


    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Must specify exactly one argument (library file name)");
            return;
        }
        // Create a new Library
        Library library = null;
    
        // Load from the library file with the first argument being the filename taken from the command line arguments
        try {
            library = loadFromLibraryFile(args[0]);
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

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Type \"import\", \"average\", \"shelves\", \"books\", \"list\", or \"exit\": ");
            // IMPORTANT: We must read the newline as well!
            String userChoice = scan.nextLine();

            switch(userChoice.strip().toLowerCase()) {
                case "import":
                    // When the user enters the import command, ask them for a filename for a book list file.
                    System.out.print("Enter a filename for a book list file: ");
                    String bookListFileName = scan.nextLine().strip();

                    try (
                        Scanner bookListFileInput = new Scanner(new FileReader(bookListFileName));
                    ) {
                        int numBooksReadSuccessfully = library.importBooks(bookListFileInput);
                        System.out.println("-".repeat(40));
                        System.out.printf("%d books were successfully read from %s\n", numBooksReadSuccessfully, bookListFileName);

                    } catch (FileNotFoundException fnf) {
                        System.out.println("Error, file not found: " + fnf.getMessage()); // convey additional details to the user
                    }

                    break;
                case "average":
                    /*
                    When the user enters the average command, ask them to choose a shelf, and then output the average page count of all books on that shelf.
                    */
                    System.out.print("Choose a shelf by entering its genre: ");
                    Shelf shelf = library.findShelf(scan.nextLine().strip());

                    if(shelf == null) {
                        System.out.println("Shelf of genre does not exist. Please try again");
                        break;
                    }

                    if(shelf.isEmpty()) {
                        System.out.println("No books on this shelf yet.");
                        break;
                    }
                    System.out.printf("The average page count for books on the %s shelf is %d pages\n", shelf.getGenre(), shelf.computeAveragePageCount());

                    break;
                case "shelves":
                    /*
                    When the user enters the shelves command, diplay a list of all the shelves in the library
                    */
                    System.out.println("Current shelves in library: ");
                    library.listShelves();
                    break;

                case "books":
                    /*
                    When the user enters the books command, display a list of all the books in the library
                    */
                    library.listBooks();

                    break;

                case "list":
                    /* When the user enters the list command, ask them to choose a shelf, and then output a list of all of the titles of books on that shelf. */
                    System.out.print("Choose a shelf by entering its genre: ");
                    
                    Shelf chosenShelf = library.findShelf(scan.nextLine().strip());

                    if(chosenShelf == null) {
                        System.out.println("Shelf of genre does not exist. Please try again");
                        break;
                    }
                    if(chosenShelf.isEmpty()) {
                        System.out.println("No books on this shelf yet.");
                        break;
                    }

                    System.out.println(chosenShelf.listBooks());

                    break;

                case "exit":
                case "done":
                    return; // terminate the program

                default:
                    System.out.println("That wasn't a valid choice. Try again. ");
            }
        }
    }
}