// Assignment 1
// Michael Knowles
// November 6, 2024
// Class: CS&145
// Purpose:  This assignment is for a wordsearch puzzle generator.  This program asks the user for a
// number of words to place into the wordsearch.  It must be between 1 and 8 inclusive.  It then creates
// the wordsearch object.  It them gives the user the option to create a new one, print with solutions, print
// puzzle, or quit.

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        // The scanner, Wordsearch object and userIntent boolean are initialized.
        Scanner sc = new Scanner(System.in);
        Wordsearch ws = createWordsearch(sc, getNumberOfSolutions(sc));
        boolean userIntent = true;

        // This while loop operates until the user decides to quit.
        // The switch rule block acts on the users indicated intent.
        while (userIntent) {
            printInstructions();
            int userChoice = sc.nextInt();
            switch (userChoice) {
                case 1 -> ws = createWordsearch(sc, getNumberOfSolutions(sc));
                case 2 -> ws.print(true);
                case 3 -> ws.print(false);
                case 4 -> userIntent = false;
                default -> System.out.println("Invalid input");
            }
        }
    }
    // This method gets the desired number of solutions from the user.
    // The provided integer is checked to ensure it is within scope.
    private static int getNumberOfSolutions(Scanner sc) {
        System.out.println("How many solutions do you want in this crossword? (up to 8)");
        int n = sc.nextInt();
        while (n < 1 || n > 8) {
            if (n < 1) {
                System.out.println("Min number of solutions is 1. \n");
            } else if (n > 8) {
                System.out.println("Max number of solutions is 8. \n");
            }
            n = getNumberOfSolutions(sc);
        }
        return n;
    }
    // This method returns a filled wordsearch object.  It takes a scanner as input and the number of solutions.
    private static Wordsearch createWordsearch(Scanner sc, int n) {
        String[] solutions = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Please provide solution number "+ (i+1) + ".");
            solutions[i] = sc.next().toUpperCase();
        }
        return new Wordsearch(solutions);
    }
    // This method prints the instructions to the user.
    private static void printInstructions() {
        System.out.println("What would you like to do?");
        System.out.println("1. Create a crossword puzzle.");
        System.out.println("2. View crossword puzzle solutions.");
        System.out.println("3. View crossword puzzle.");
        System.out.println("4. Quit.");
    }
}
