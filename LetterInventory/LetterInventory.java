// Name: Jasia Ernest, Abdul Rahman, and Micheal Knowles
// Assignment: Lab 3 - Letter Inventory
// Date: October 11 2024
// Class: CS&145

public class LetterInventory {
    // Array to store counts for each letter of the alphabet (26 letters)
    private int[] letterCounts; 

/**
     * This constructor initializes an empty LetterInventory object.
     * The inventory ignores case and non-alphabetic characters.
     * 
     * The input string "input" constructs the initial inventory
     */
    public LetterInventory() {
        // Initialize the array to store letter counts
        letterCounts = new int[26];
    }

    /**
     * Returns the count of a specific letter in the inventory.
     * The method is case-insensitive and validates the input.
     * 
     * @param letter The letter whose count is to be returned
     * @return The count of the specified letter in the inventory
     * @throws IllegalArgumentException if the input is not a valid letter
     */
    public int get(char letter) {
        // Convert the letter to uppercase to ensure case insensitivity
        letter = Character.toUpperCase(letter);
        int index = letter - 'A';
        // Validate that the character is a letter
        if (0 > index && 25 < index) {
            throw new IllegalArgumentException("Invalid character.");
        }
        // Return the count for the letter
        return letterCounts[index];
    }

    /**
     * Sets the count for a specific letter in the inventory.
     * The letter is case-insensitive, and the input is validated.
     * 
     * @param letter The letter whose count is to be set
     * @param value The new count for the specified letter
     * @throws IllegalArgumentException if the letter is not alphabetic or value is negative
     */
    public void set(char letter, int value) {
        // Convert the letter to uppercase to ensure case insensitivity
        letter = Character.toUpperCase(letter);
        int index = letter - 'A';
        // Validate that the character is a letter
        if (0 > index && 25 < index) {
            throw new IllegalArgumentException("Invalid character.");
        }
        // Return the count for the letter
        letterCounts[index] = value;
    }

    /**
     * Checks if the inventory is empty, meaning all letter counts are zero.
     * 
     * @return True if the inventory is empty, false otherwise
     */
    public boolean isEmpty() {
        boolean b = true;
        for (int i : this.letterCounts) {
            if (letterCounts[i] != 0) {
                b = false;
                break;
            }
        }
        return b;
    }

    /**
     * Returns a string representation of the inventory.
     * The string contains all letters in lowercase, in sorted order, and enclosed in square brackets.
     * The number of occurrences of each letter matches its count in the inventory.
     * 
     * @return The string representation of the inventory
     */
    public String toString() {
        // StringBuilder to construct the result
        StringBuilder sb = new StringBuilder();
        sb.append("["); // Start with an opening bracket
        // Loop through each letter in the alphabet
        for (int i = 0; i < this.letterCounts.length; i++) {
            // Append the letter as many times as its count
            for (int j = 0; j < letterCounts[i]; j++) {
                sb.append((char) (i + 'a'));
            }
        }
        sb.append("]"); // End with a closing bracket
        return sb.toString();
    }

    /* This method is called on a LetterInventory object and adds the input string to the inventory.
     * It iterates over the string, which has been cast to upper case, using a for loop and extracts
     * the char with charAt(i) where i is the index. The char is subtracted by an offset of 65 to bring
     * the A-Z integer representation within the size of the array.  It is then saved to the array if within
     * the bounds of 0 and 25 inclusive.
    */
    public void add(String input) {
        input = input.toUpperCase();
        for ( int i = 0; i < input.length(); i++ ) {
            char ch = input.charAt(i);
            int index = ch - 'A';
            try {
                if ( 0 <= index && 25 >= index ) {
                    letterCounts[index]++;
                }
                if (0 > index && 25 < index) {
                    throw new IllegalArgumentException("Invalid character.");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /* This method operates in the same way as the add() method.
     * It instead decrements the array element using --.
     */
    public void subtract(String input) {
        input = input.toUpperCase();
        for ( int i = 0; i < input.length(); i++ ) {
            char ch = input.charAt(i);
            int index = ch - 'A';
            try {
                if ( 0 <= index && 25 >= index ) {
                    letterCounts[index]--;
                }
                if (0 > index && 25 < index) {
                    throw new IllegalArgumentException("Invalid character.");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
   }