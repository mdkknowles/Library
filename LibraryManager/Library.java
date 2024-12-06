public class Library {
    private LibraryNode root;
    private int totalBooks;

    public Library() {
        root = null;
        totalBooks = 0;
    }
    //Adds a book, if it does not exist.
    public boolean addBook(String title, String author, String isbn) {
        if (findBook(title) != null) { return false; }
        Book book = new Book(title, author, isbn);
        root = addBookRecursive(root, book);
        totalBooks++;
        return true;
    }
    //Helper function to add the book as a LibraryNode recursively in the BST.
    private LibraryNode addBookRecursive(LibraryNode current, Book book) {
        //Base case.
        if (current == null) {
            return new LibraryNode(book);
        }
        //Compare new book to current book title
        int comparison = book.getTitle().compareToIgnoreCase(current.getBook().getTitle());
        //If less than current, traverse left.  If more, traverse right.  If equals, do nothing.
        if (comparison < 0) {
            current.setLeft(addBookRecursive(current.getLeft(), book));
        } else if (comparison > 0) {
            current.setRight(addBookRecursive(current.getRight(), book));
        }
        current.setBook(book);
        return current;
    }
    //Deletes the book recursively.
    public boolean deleteBook(String title) {
        if (findBook(title) == null) { return false; }
        root = deleteBookRecursive(root, title);
        return true;
    }
    //Helper function that actually does the recursing.
    private LibraryNode deleteBookRecursive(LibraryNode current, String title) {
        // Case 1: Null root.  Return null.
        if (current == null) return null;
        int comparison = title.compareToIgnoreCase(current.getBook().getTitle());
        if (comparison < 0) {
            current.setLeft(deleteBookRecursive(current.getLeft(), title));
        } else if (comparison > 0) {
            current.setRight(deleteBookRecursive(current.getRight(), title));
        } else {
            if (current.getLeft() == null && current.getRight() == null) {
                totalBooks--;
                return null;
            }
            if (current.getLeft() == null) {
                totalBooks--;
                return current.getRight();
            }
            if (current.getRight() == null) {
                totalBooks--;
                return current.getLeft();
            }
            //Finds the smallest child on right side of tree and sets it as new right.  Then the popped
            //node is deleted recursively.
            Book smallestBook = findSmallestBook(current.getRight());
            current.setBook(smallestBook);
            current.setRight(deleteBookRecursive(current.getRight(), smallestBook.getTitle()));
        }
        return current;
    }
    //Helper function finds the smallest value in a given tree.
    private Book findSmallestBook(LibraryNode node) {
        if (node.getLeft() == null) {
            return node.getBook();
        }
        return findSmallestBook(node.getLeft());
    }

    public Book findBook(String title) {
        LibraryNode node = findBookRecursive(root, title);
        return (node != null) ? node.getBook() : null;
    }

    private LibraryNode findBookRecursive(LibraryNode current, String title) {
        if (current == null) {
            return null;
        }

        int comparison = title.compareToIgnoreCase(current.getBook().getTitle());
        if (comparison == 0) {
            return current;
        }

        if (comparison < 0) {
            return findBookRecursive(current.getLeft(), title);
        } else {
            return findBookRecursive(current.getRight(), title);
        }
    }

    public boolean checkOutBook(String title) {
        Book book = findBook(title);
        if (book != null && !book.isCheckedOut()) {
            book.setCheckedOut(true);
            return true;
        }
        return false;
    }

    public boolean returnBook(String title) {
        Book book = findBook(title);
        if (book != null && book.isCheckedOut()) {
            book.setCheckedOut(false);
            return true;
        }
        return false;
    }
    //Prints the entire library using the printAlphabetically method.
    public void printLibrary() {
        System.out.println("Library Catalog (Alphabetical Order):");
        System.out.println("Total books: " + totalBooks);
        System.out.println(countBooksAvailable(root) + " available");
        System.out.println("-------------------------------------");
        printAlphabetically(root);
    }
    //Walks the tree and prints every node in order.
    private void printAlphabetically(LibraryNode node) {
        if (node != null) {
            printAlphabetically(node.getLeft());
            System.out.println(node.getBook());
            printAlphabetically(node.getRight());
        }
    }
    //Function counts the number of books recursively.
    private int countBooksAvailable(LibraryNode current) {
        if (current == null) { return 0; }
        int count = current.getBook().isCheckedOut() ? 0 : 1;
        return count + countBooksAvailable(current.getLeft()) + countBooksAvailable(current.getRight());
    }
}