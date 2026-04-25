public class Book {
    // Set instance variables private for proper encapsulation
    private String title;
    private int pageCount;

    public Book(String title, int pageCount) {
        this.title = title;
        this.pageCount = pageCount;
    }

    // Accessors
    public int getPageCount() {
        return pageCount;
    }

    public String toString() {
        return title;
    }
}