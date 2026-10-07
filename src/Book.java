public class Book {
    private int bookID;
    private String title;
    private String author;
    private int qunatity;

    public Book( String title, String author, int qunatity) {
        this.title = title;
        this.author = author;
        this.qunatity = qunatity;
    }
    public int getBookID() {
        return bookID;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getQunatity() {
        return qunatity;
    }
}
