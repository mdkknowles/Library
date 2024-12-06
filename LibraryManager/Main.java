/* CS 145
 * Assignment 3
 * Michael Knowles
 * This program is a Library catalogue manager. It incorporates a binary tree structure to organize
 * a series of "LibraryNodes", which are objects that contain a book object.  The program asks for a user
 * prompt on whether to conduct a crud operation to add, delete, update, or print the books.  There is also
 * a check out mechanism to allows a book to changed to an "checked out" or "available" state.
 * The library summary print out also lets the user know the total number of books in the library and how
 * many are available.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        int userIntent = 0;

        while (userIntent != 7) {
            printInstructions();
            userIntent = scanner.nextInt();
            scanner.nextLine();

            switch (userIntent) {
                case 1:
                    addLibraryBook(library, scanner);
                    break;
                case 2:
                    updateLibraryBook(library, scanner);
                    break;
                case 3:
                    deleteLibraryBook(library, scanner);
                    break;
                case 4:
                    checkOutLibraryBook(library, scanner);
                    break;
                case 5:
                    returnLibraryBook(library, scanner);
                    break;
                case 6:
                    library.printLibrary();
                    break;
                case 7:
                    System.out.println("Thanks for using this program! exiting...");
                    return;
                default:
                    System.out.println("Invalid selection.\n\n");
                    break;
            }
        }
    }

    private static void printInstructions() {
        System.out.println("Welcome to the Library. Please select an option.");
        System.out.println("1. Add a book\n2. Update a book\n3. Delete a book");
        System.out.println("4. Check out a book\n5. Return a book");
        System.out.println("6. Print Library Contents\n7. Quit");
    }

    //This method collects user input for the book atributes and calls the appropriate library object method.
    private static void addLibraryBook(Library library, Scanner scanner) {
        System.out.println("What is the title?");
        String title = scanner.nextLine();
        System.out.println("Who is the author?");
        String author = scanner.nextLine();
        System.out.println("What is the ISBN?");
        String isbn = scanner.nextLine();
        //Method call embedded to provide easier error reporting.
        if (!library.addBook(title, author, isbn)) {
            System.out.println("\"" + title + "\" already exists!");
        }
    }
    //Ran out of time, so hacked a solution that first deletes the exhisting entry and adds a whole new one.
    private static void updateLibraryBook(Library library, Scanner scanner) {
        System.out.println("Title of the book you want to update:");
        String title = scanner.nextLine();
        if (library.findBook(title) == null) {
            System.out.println("\"" + title + "\" was not found in the Library.");
        } else {
            library.deleteBook(title);
            System.out.println("What is the updated title?");
            title = scanner.nextLine();
            System.out.println("Who is the updated author?");
            String author = scanner.nextLine();
            System.out.println("What is the updated ISBN?");
            String isbn = scanner.nextLine();
            library.addBook(title, author, isbn);
        }
    }
    //
    private static void deleteLibraryBook(Library library, Scanner scanner) {
        System.out.println("Title of the book you want to delete:");
        String title = scanner.nextLine();
        if (!library.deleteBook(title)) {
            System.out.println("\"" + title + "\" was not found in the Library.");
        }
    }
    //Finds a book and changes the bool "isCheckedOut" to true using the setter.
    //If already checked out, provides error in console.
    private static void checkOutLibraryBook(Library library, Scanner scanner) {
        System.out.println("Title of the book you want to check out:");
        String title = scanner.nextLine();
        if (library.findBook(title) == null) {
            System.out.println("\"" + title + "\" was not found in the Library.");
        } else if (library.checkOutBook(title)) {
            System.out.println("You checked out \"" + title + "\"");
        } else {
            System.out.println("\"" + title + "\" was already checked out");
        }
    }
    //Same as the checkOutLibraryBook method, except the opposite.
    private static void returnLibraryBook(Library library, Scanner scanner) {
        System.out.println("Title of the book you want to return:");
        String title = scanner.nextLine();
        if (library.findBook(title) == null) {
            System.out.println("\"" + title + "\" was not found in the Library.");
        } else if (library.returnBook(title)) {
            System.out.println("Thank you for returning \"" + title + "\"");
        } else {
            System.out.println("\"" + title + "\" was not checked out");
        }
    }
}