// Lab Authors: Abdul Rahman Said, Michael Knowles, Harman Dhillon
// Assignment:  Lab 1 - Guessing Game Reloaded
// Date:        October 01, 2024
// Class:       CS&145
// Purpose:This program allows the user to play a guessing game where the computer selects a random number
// between 1 and 100. The user is prompted to guess a number. The program will notify them of
// whether the number is higher or lower than the generated value. After each game, the user can choose to play
// again or stop. The program keeps track of the total number of games played, total number of guesses
// made, average number of guesses per game, and the best game (fewest guesses). This program provides
// practice with while loops, pseudorandom numbers, and user input handling.
//
// For extra credit, we are implementing a save state mechanic.  On user exit, the program will prompt
// the user to save their game.  A save file will be created if they indicate they want to save.
// On program start, if a file exists, the program will ask if the user wants to load their previous save.

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.Thread;

public class Guess {
  public static void main(String[] args) {
    // Initialize a zeroed array that represents the game state.
    // {gameIndex, gameGuessCount, bestGameIndex, bestGameGuessCount, totalGuessCount}
    int[] gameState = initializeGameState();

    char userIntent = 'y';

    // Run game until user decides to quit.
    while (userIntent == 'y') {
      // Increment the game index by 1. Is essential the game number or id.
      gameState[0] += 1;
      // New game state is set based on the outcome of the playthrough. The state is passed into the game function.
      gameState = playGame(gameState);
      // The best game stats are updated if the latest playthrough is the best, or if this is the first playthrough.
      if (gameState[3] > gameState[1] || gameState[3] == 0) {
        gameState[3] = gameState[1];
        gameState[2] = gameState[0];
      }
      //The total guess count is recalculated based on the latest game.
      gameState[4] += gameState[1];
      //User is prompted if they want to play again.
      System.out.println("Would you like to play another game? [Y/N]");
      userIntent = getUserIntent();
      System.out.println();
    }
    //Results are printed to console.
    reportResults(gameState);
    System.out.println();
    //User is prompted whether they want to save.
    System.out.println("Would you like to save your game? [Y/N]");
    userIntent = getUserIntent();
    // If user intent is to save progress and quit, save state to file.
    if (userIntent == 'y') {
      System.out.println();
      saveGameState(gameState);
    }
  }
  
  // This method initializes the game state and overwrites if the user both wants to load a save and a save file exists.
  private static int[] initializeGameState() {
    // Initialize empty gameState array.
    int[] gameState = new int[5];
    // Print instructions to console and ask if user wants to load from save.
    System.out.println("Welcome to the Guessing Game!");
    System.out.println("Your goal is to guess a random number between 1 and 100. (Inclusive)");
    System.out.println("After every guess, you will be provided a hint.");
    System.out.println("Good luck!");
    System.out.println("");
    System.out.println("Would you like to load a previous save? [Y/N]");
    // Get user input with input scanner and extract the first char of token.
    char userIntent = getUserIntent();
    // If user intent is to load a save, call loadSave method.
    if (userIntent == 'y') {
      gameState = loadState(gameState);
    }
    //Print empty line and return the game state.
    System.out.println();
    return gameState;
  }
  // Get user intent method is called whenever the user is prompted for a yes or no question.
  private static char getUserIntent() {
    Scanner input = new Scanner(System.in);
    // The first token of user input is forced lower case.
    // Then the first char is set to output.
    char output = input.next().toLowerCase().charAt(0);
    return output;
  }
  // This method is used to obtain user guesses.
  private static int getUserGuess() {
    Scanner input = new Scanner(System.in);
    int output = input.nextInt();
    return output;
  }
  // This method loads the state from a file called state and returns it if the file exists.
  // If the file does not exist, the input gameState is returned unmodified.
  private static int[] loadState(int[] gameState) {
    // Try loading save from file "state".  If file does not exist, gracefully return zeroed array. 
    try {
      File f = new File("state");
      Scanner reader = new Scanner(f);
      for (int i = 0; i < gameState.length; i++) {
        gameState[i] = reader.nextInt(); 
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println();
      System.out.println("No save file found.");
      System.out.println("Resetting...");
    }
    return gameState;
  }
  // Dedicated method to generate the target integer.
  private static int newTarget() {
    Random r = new Random();
    return r.nextInt(99) + 1;
  }
  // This method is the game loop.
  private static int[] playGame(int[] gameState) {
    // New target is set and the initial guess and guess counter are zero'ed.
    int target = newTarget();
    int guess = 0;
    gameState[1] = 0;
    System.out.println("I have chosen a number.");
    // While loop runs while the guess is not equal to target. Depending on relative value, a different hint is provided.
    // Every guess increments the guess counter by 1.
    while ( target != guess) {
      System.out.println("What is your guess?");
      guess = getUserGuess();
      System.out.println();
      if (guess < target) {
        System.out.println("My number is higher!");
        gameState[1]++;
      } else if (guess > target) {
        System.out.println("My number is lower!");
        gameState[1]++;
      }
    }
    System.out.println("You got it right in " + gameState[1] + " guess" + (gameState[1] == 1 ? "" : "es"));
    return gameState;
  }
  // User results are printed to console.
  private static void reportResults(int[] gameState) {
    System.out.println("Your stats:");
    System.out.println("\tTotal Games Played:\t" + gameState[0]);
    System.out.println("\tTotal Guesses:\t\t" + gameState[4]);
    System.out.printf("\tAverage guesses per game:\t%.1f%n", (double) gameState[4] / gameState[0]);
    System.out.println();
    System.out.println("\tYour best game was game number " + gameState[2] + ",");
    System.out.println("\twhich required " + gameState[3] + " guess" + (gameState[3] == 1 ? "" : "es") + ".");
  }
  // This method takes the gameState and saves it into a file with a format that can be tokenized by a Scanner.
  private static void saveGameState(int[] gameState) {
    try {
      PrintWriter output = new PrintWriter("state");
      for (int i = 0; i < gameState.length; i++) {
        output.printf("%d ", gameState[i]);
      }
      output.close();
      System.out.println("Save was successful!");
    } catch (FileNotFoundException e) {
      System.out.println("Could not save game!");
    }
  }
}
