import java.util.Scanner;

public class Dictionary {
  public static void main(String[] args) {
    final int NUM_OF_INTENTS = 6;

    Scanner sc = new Scanner(System.in);
    int userIntent = 0;
    BinarySearchTree bst = new BinarySearchTree();

    while (userIntent != NUM_OF_INTENTS) {
      printIntroduction();
      userIntent = getUserIntent(sc);
      switch (userIntent) {
        case 1 -> { addEntry(sc, bst); }
        case 2 -> { deleteEntry(sc, bst); }
        case 3 -> { modifyEntry(sc, bst); }
        case 4 -> { lookupEntry(sc, bst); }
        //case 5 -> { numberOfEntries(bst); }
        case 6 -> { break; }
        default -> System.out.println("Invalid input. Try again.");
      }
    }
    
  }

  private static void printIntroduction(){
    System.out.println("Select one of the following options.");
    System.out.println("1: Add an entry.");
    System.out.println("2: Delete an entry.");
    System.out.println("3: Modify an entry.");
    System.out.println("4: Lookup an entry.");
    System.out.println("5: List total number of entries.");
  }

  private static int getUserIntent(Scanner sc) {
    return sc.nextInt();
  }

  private static void addEntry(Scanner sc, BinarySearchTree bst) {
    int isCorrect = 2;
    while (isCorrect == 2) {
      System.out.print("Entry ID: ");
      int key = sc.nextInt();
      sc.nextLine();//To get rid of newline.
      System.out.print("First name: ");
      String firstName = sc.nextLine();
      System.out.print("Last name: ");
      String lastName = sc.nextLine();
      System.out.print("Address line 1: ");
      String address = sc.nextLine();
      System.out.print("City: ");
      String city = sc.nextLine();
      System.out.print("State: ");
      String state = sc.nextLine();
      System.out.print("Postal/zip code: ");
      String postal = sc.nextLine();
      System.out.print("Email address: ");
      String email = sc.nextLine();
      System.out.print("Phone number: ");
      String phone = sc.nextLine();

      System.out.println();
      System.out.println("Entry number " + key + ":");
      System.out.println("First name: " + firstName);
      System.out.println("Last name: " + lastName);
      System.out.println("Address: " + address + ", " + city + ", " + state + " " + postal);
      System.out.println("Email address: " + email);
      System.out.println("Phone number: " + phone);

      System.out.println("Is this correct?\n1. Yes 2. No");
      isCorrect = sc.nextInt();
      sc.nextLine();
      if (isCorrect == 1) {
        bst.add(key, firstName, lastName, address, city, state, postal, email, phone);
      }
    }
  }

  private static void deleteEntry(Scanner sc, BinarySearchTree bst) {
    Node entry = null;
    while (entry == null) {
      System.out.println("Which entry do you want to delete? (Provide its integer ID)");
      int entryID = sc.nextInt();
      sc.nextLine();
      entry = bst.search(entryID);
      if (entry == null) { System.out.println("Entry with ID "+entryID+" was not found."); }
    }
    System.out.println("Are you sure you want to delete " + entry.firstName + " "+ entry.lastName + "?");
    System.out.println("1. Yes 2. No");
    int confirmation = sc.nextInt();
    sc.nextLine();
    if (confirmation == 1) {
      bst.delete(entry.key);
    } else {
      System.out.println("Aborted.");
    }
  }

  private static void modifyEntry(Scanner sc, BinarySearchTree bst) {
    Node entry = null;
    Node tmpNode = null;
    int userIntent = 0;
    while (entry == null && tmpNode == null) {
      System.out.println("Which entry do you want to modify? (Integer ID)");
      int entryID = sc.nextInt();
      entry = bst.search(entryID);
      if (entry == null) { System.out.println("Entry with ID "+entryID+" was not found."); }
    }
    //Temporary node to stage changes.
    tmpNode = entry;
    while (userIntent != 5) {
      System.out.println(tmpNode);
      System.out.println("What do you want to modify?");
      System.out.println("1. Name 2. Address 3. Email 4. Phone 5. Done");
      switch (userIntent) {
        case 1 -> {
          System.out.print("New first name: ");
          tmpNode.firstName = sc.nextLine();
          System.out.print("New last name: ");
          tmpNode.lastName = sc.nextLine();
        }
        case 2 -> {
          System.out.print("New address line 1: ");
          tmpNode.address = sc.nextLine();
          System.out.print("New city: ");
          tmpNode.city = sc.nextLine();
          System.out.print("New postal/zip code: ");
          tmpNode.postal = sc.nextLine();
        }
        case 3 -> {
          System.out.print("New email: ");
          tmpNode.email = sc.nextLine();
        }
        case 4 -> {
          System.out.print("New phone: ");
          tmpNode.phone = sc.nextLine();
        }
        case 5 -> {
          break;
        }
        default -> System.out.println("Invalid option.");
      }
      System.out.println(tmpNode+"\n");
      System.out.println("Do you want to write your changes?");
      userIntent = sc.nextInt();
      if (userIntent == 1) {
        entry.setFirstName(tmpNode.firstName);
        entry.setLastName(tmpNode.lastName);
        entry.setAddress(tmpNode.address);
        entry.setCity(tmpNode.city);
        entry.setState(tmpNode.state);
        entry.setPostal(tmpNode.postal);
        entry.setEmail(tmpNode.email);
        entry.setPhone(tmpNode.phone);
      } else {
        System.out.println("Aborted.");
      }
    }

  }

  private static void lookupEntry(Scanner sc, BinarySearchTree bst) {
    Node entry = null;
    while (entry == null) {
      System.out.println("Which entry do you want to lookup? (Positive integer)");
      int entryID = sc.nextInt();
      entry = bst.search(entryID);
      if (entry == null) { System.out.println("Entry with ID "+entryID+" was not found."); }
    }
    System.out.println(entry);
  }
}
