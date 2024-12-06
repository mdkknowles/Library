public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isCheckedOut;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isCheckedOut = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isCheckedOut() { return isCheckedOut; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setCheckedOut(boolean status) { this.isCheckedOut = status; }

    @Override
    public String toString() {
        return "\""+title+"\" "+(isCheckedOut ? "[Checked Out]" : "[Available]")+"\n    By: "+author+"\n    ISBN: "+isbn;
    }
}
