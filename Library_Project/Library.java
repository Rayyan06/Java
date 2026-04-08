import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Library {

    private Shelf[] shelves;

    public Library(int shelveCount) {
        shelves = new Shelf[shelveCount];
    }

    // methods should be private by default, for encapsulation

    private addShelf(String genre, int shelf ) {

    }

    private loadFromLibraryFile(String libraryFilename) {
    }

    // Error Code: 0 if success, 1 if failure
    public static boolean import(Scanner bookListFileInput blfi) {
        // create the library file
        // use try-with-resources block
        try (
            Scanner libraryFileInput = new Scanner(new FileReader(libraryFilename))
        ) {
            // Read the first line of the library file
            int shelveCount = libraryFileInput.nextLine();

            while(libraryFileInput.hasNextLine()) {
                String shelfGenre = libraryFileInput.next();
                int shelfCapacity = libraryFileInput.nextLine();

                // Attempt to create the shelf
                addShelf();
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("Error, file not found: ");
            System.out.println(fnf.getMessage()); // convey additional details to the user
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Must specify exactly one argument (library file name)");
            return;
        }

        // Load from the library file with the first argument being the filename
        loadFromLibraryFile(args[0]);

      

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Type \"import\", \"average\", \"shelves\", \"books\", \"list\", or \"exit\": ");
            // IMPORTANT: We must read the newline as well!
            String userChoice = scan.nextLine();

            switch(userChoice.toLowerCase()) {
                case "import":
                    
                    // When the user enters the import command, ask them for a filename for a book list file. 
                    System.out.println("Enter a filename for a book list file: ");
                    String bookListFileName = scan.next(); // only grab until the whitespace

                    //  try (FileWriter library = new FileWriter(libraryFilename)) {
                    //     } catch (FileNotFoundException fnf) {
                    //         System.out.println("Error, file not found: ");
                    //         System.out.println(fnf.getMessage()); // convey additional details to the user
                    //     } catch (IOException ioe) {
                    //         ioe.printStackTrace();
                    //     }
                    /*
                    The book list file will contain a description of a book on each line, consisting of a genre, a page count, and a title, separated by spaces. 
                    The genre will be a single word, the page count will be a number, and the title will be a string that may contain spaces. 
                    Attempt to open the book list file; if you cannot open it, print an error message but continue the program.
                    */
                    while(bookListFile.hasNextLine()) {
                        /*
                        Read all books from the file and place them in the appropriate shelf based on their genre. 
                        If a line is malformed and doesn't follow the format, print an error message and skip that line. 
                        If there is no space for a book (either because there is no shelf matching its genre or because the matching shelf is full), 
                        print an error message and skip that line. 
                        At the end, print out the number of books that were successfully read.
                        */
                    }
                    
                    break;
                case "average":
                    /*
                    When the user enters the average command, ask them to choose a shelf, and then output the average page count of all books on that shelf.
                    */
                    System.out.println("Choose a shelf:");
                    break;

                case "shelves":
                    /* 
                    When the user enters the shelves command, diplay a list of all the shelves
                    */
                    break;
                
                case "books":
                    /* 
                    When the user enters the books command, diplay a list of all the books
                    */
                    break;
                case "list":
                    /* When the user enters the list command, ask them to choose a shelf, and then output a list of all of the titles of books on that shelf. */
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