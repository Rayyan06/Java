import java.lang.StringBuilder;

public class Shelf {
    private Book[] books;
    private int capacity;    // the maximum space on the shelf
    private String genre;


    public Shelf(String genre, int capacity) {
        if(capacity <= 0) {
            throw new IllegalArgumentException("Capacity of shelf must be a whole positive number");
        }
        books = new Book[capacity];

        this.genre = genre;
    }
    // accessors
    public String getGenre() {
        return genre;
    }

    // returns true if a book was successfully added, false if the shelf was full
    public boolean addBook(Book book) {
        // Check if shelf is full
        if(capacity == books.length) {
            return false;
        }

        // BOOK SUCCESSFULLY ADDED

        books[capacity] = book;
        capacity++;

        return true;
    }

    /*
    Computes average page count, note: rounds to the nearest integer
    */
    public int computeAveragePageCount() {
        // no books on shelf, average is 0
        if(capacity == 0) {
            return 0;
        }

        // declare as double to ensure truncation does not occur
        double totalPageCount = 0;

        // Cannot use foreach loop because there are null slots
        for(int i = 0; i < capacity; i++) {
            totalPageCount += books[i].getPageCount();
        }

        return (int) Math.round(totalPageCount / capacity);
    }

    // returns a string of all the books on the shelf
    public String listBooks() {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < capacity; i++) {
            s.append("\n");
            s.append(books[i]);
        }

        return s.toString();
    }

    // returns true if shelf is empty
    public boolean isEmpty() {
        return (capacity == 0);
    }

    public String toString() {
		return String.format("%s: %d book%s", genre, capacity, (capacity == 1) ? "": "s");
	}
}