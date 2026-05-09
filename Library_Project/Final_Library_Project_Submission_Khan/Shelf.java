import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Shelf {
    private List<Book> books;
    private String genre;


    public Shelf(String genre) {
        books = new ArrayList<>();
        this.genre = genre;
    }
    // accessors
    public String getGenre() {
        return genre;
    }

    // very easy with collections!
    public void addBook(Book book) {
        books.add(book);
    }

    /*
    Computes average page count, note: rounds to the nearest integer
    */
    public int computeAveragePageCount() {
        // no books on shelf, average is 0
        if(books.isEmpty()) {
            return 0;
        }

        // declare as double to ensure truncation does not occur
        double totalPageCount = 0;

        // Can use foreach loop now that we have collections!
        for(Book book : books) {
            totalPageCount += book.getPageCount();
        }

        return (int) Math.round(totalPageCount / books.size());
    }

    // // returns a string of all the books on the shelf
    // public String listBooks() {
    //     StringBuilder s = new StringBuilder();

    //     for(Book book : books) {
    //         s.append(System.lineSeparator());
    //         s.append(book);
    //     }

    //     return s.toString();
    // }


    public List<Book> listBooks() {
        return Collections.unmodifiableList(books);
    }

    // returns true if shelf is empty
    public boolean isEmpty() {
        return books.isEmpty();
    }

    public String toString() {
		return String.format("%s: %d book%s", genre, books.size(), (books.size() == 1) ? "": "s");
	}
}