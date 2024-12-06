import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PhonebookManager SeattlePhonebook = new PhonebookManager();
        PhonebookManager BellinghamPhonebook = new PhonebookManager();
        int userIntent = 0;
        while (userIntent != 4) {
            printCurrentView();
            printInstructions();
            userIntent = getUserIntent(sc);
            switch (userIntent) {
                case 1:
                    System.out.println("Option 1 was selected.");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Option 2 was selected.");
                    break;
                case 3:
                    System.out.println("Option 3 was selected.");
                    break;
                case 4:
                    System.out.println("Option 4 was selected.");
                    break;
                default:
                    System.out.println("Invalid input. Try again.\n");
                    break;
            }
        }
    }

    private static void printCurrentView() {

    }
    private static void printInstructions() {
        System.out.println("Select one of the following options:");
        System.out.println("1. Create a new phonebook entry.");
        System.out.println("2. Delete current phonebook entry.");
        System.out.println("3. Move current phonebook entry.");
        System.out.println("4. Quit.");
    }

    private static int getUserIntent(Scanner sc) {
        return sc.nextInt();
    }
}
