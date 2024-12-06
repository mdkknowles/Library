// Name: Jasia Ernest, Abdul Rahman, and Micheal Knowles
// Assignment: Lab 3 - Letter Inventory
// Date: October 11 2024
// Class: CS&145

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int userChoice = 0;
        System.out.println("Please provide a sentence you want to put in the letter inventory");
        LetterInventory li = new LetterInventory();
        li.add(scan.nextLine());
        while (userChoice != 5) {
            char userInputChar;
            String userInputString = "";
            System.out.println("\nInventory:\n"+li.toString()+"\n");
            System.out.println("Do you want to:");
            System.out.println("1. add a string to the inventory?");
            System.out.println("2. substract a string from the inventory?");
            System.out.println("3. get the quantity of a letter?");
            System.out.println("4. manually set the quantity of a letter?");
            System.out.println("5. quit?");
            userChoice = scan.nextInt();
            switch (userChoice) {
                case 1:
                    System.out.println("Please enter the sentence you want added to the inventory");
                    userInputString = scan.nextLine();
                    li.add(userInputString);
                    break;
                case 2:
                    System.out.println("Please enter the sentence you want substracted from the inventory");
                    userInputString = scan.nextLine();
                    li.subtract(userInputString);
                    break;
                case 3:
                    System.out.println("What letter do you want to get?");
                    userInputChar = scan.nextLine().charAt(0);
                    System.out.println("The inventory has "+li.get(userInputChar)+" "+Character.toUpperCase(userInputChar)+"\'s");
                    break;
                case 4:
                    System.out.println("What letter do you want to set?");
                    userInputChar = scan.nextLine().charAt(0);
                    System.out.println("What quantity do you want to set for letter \""+userInputChar+"\"?");
                    try {
                        li.set(userInputChar, scan.nextInt());
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using our program!");
                    break;
                default:
                    System.out.println("Invalid input.");
                    System.out.println();
            }
        }
        scan.close();
    }

    public static void intro() {
        System.out.println("This program will keep track of an inventory of letters of the alphabet");
    }
}
