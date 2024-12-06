public class LibraryNode {
    private Book book;
    private LibraryNode left;
    private LibraryNode right;

    public LibraryNode(Book book) {
        this.book = book;
        this.left = null;
        this.right = null;
    }

    public Book getBook() { return book; }
    public LibraryNode getLeft() { return left; }
    public LibraryNode getRight() { return right; }

    public void setBook(Book book) { this.book = book; }
    public void setLeft(LibraryNode left) { this.left = left; }
    public void setRight(LibraryNode right) { this.right = right; }
}