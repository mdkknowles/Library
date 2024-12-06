// Contributors:    Abdul Rahman, Jasia Ernest, and Michael Knowles
// Assignment:      Lab 2 - Blackjack
// Date:            October 8, 2024
// Class:           CS&145
// Abstract:        We set out to create a blackjack CLI program that is feature complete, with the exception of side bets. It
//                  utilizes a Card and DeckManager class to define an object and implement some constructor, getter and setter methods.
//
//                  For extra credit, we included a betting mechanic that allows the user to bet on the rounds they play.
//                  If the user runs out of money, the game ends.  Also, we implemented the ability to choose the number of decks to play with.
//                  In the real world, casinos often deter card counters with multi-deck shoes, which we have mimiced here.

package blackjack;
import java.util.Scanner;

// The Test class contains the main method and handles the game logic
public class Test {
    // Minimum bet size per hand.
    public final int MINIMUM_BET = 5;
    public static void main(String[] args) {
        // Scanner to handle user input
        Scanner scanner = new Scanner(System.in);
        
        // Ask the user how many decks they want to play with.
        System.out.println("How many decks of cards do you want to play with?");
        int numberOfDecks = scanner.nextInt();
        System.out.println();
        // Create a DeckManager object to manage the deck
        DeckManager deckManager = new DeckManager(numberOfDecks);


        // Player's initial wallet value.
        int playerWallet = 100;

        // Introduce game rules and flow.
        intro();

        // Flag to keep track of whether the player wants to keep playing
        boolean keepPlaying = true;

        // Game loop: Keep playing until the player busts, wins, or quits
        while (keepPlaying) {
            try {
                // Player is prompted for a bet
                // Bet validity is initialized to false.
                boolean betValidity = false;
                int playerBet = 0; // Default to zero.
                int playerAction = 0; // Default to zero.

                System.out.println("Your wallet currently has: " + playerWallet + "\n");

                while (betValidity == false) {
                    System.out.println("Place a bet!");
                    playerBet = scanner.nextInt();
                    System.out.println();
                    betValidity = isValidBet(playerBet, playerWallet); //If bet is invalid, betValidity remains false and while loop continues.
                }

                // Hands are dealt from deck.
                Card[] dealerHand = deckManager.dealHand();
                Card[] playerHand = deckManager.dealHand();
                

                while (handTotal(playerHand) < 22 && playerAction != 1) {
                    // Print dealer face card.
                    System.out.println("The dealer's face up card is:");
                    System.out.println("\t" + dealerHand[0]);
                    System.out.println();
                
                    // Print the players hand.
                    System.out.println("Your hand:");
                    printHand(playerHand);
                    System.out.println();
                    
                    System.out.println("Do you want to stand(1) or hit(2)");
                    playerAction = scanner.nextInt();
                    System.out.println();
                    switch (playerAction) {
                        case 1:
                            break;
                        case 2:
                            playerHand = hit(playerHand, deckManager);
                            break;
                        default:
                            System.out.println("Invalid input.");
                            System.out.println();
                    }
                }

                if (handTotal(playerHand) > 21) {
                    System.out.println("You busted!");
                } else if (handTotal(dealerHand) < 17) {
                    while (handTotal(dealerHand) < 17) {
                        System.out.println("The dealer hits...");
                        System.out.println();
                        dealerHand = hit(dealerHand, deckManager);
                    }
                } else if (handTotal(playerHand) > handTotal(dealerHand)) {
                    while (handTotal(playerHand) > handTotal(dealerHand)) {
                        System.out.println("The dealer hits...");
                        System.out.println();
                        dealerHand = hit(dealerHand, deckManager);
                    }
                }

                // Print players entire hand.
                System.out.println("Your hand is:");
                printHand(playerHand);
                System.out.println();

                // Print dealers entire hand.
                System.out.println("The dealer's hand is:");
                printHand(dealerHand);
                System.out.println();

                // Evaluate win conditions and update wallet total.
                playerWallet = evaluateRound(playerHand, dealerHand, playerWallet, playerBet);
                
                // Ask player if they want to continue playing.
                System.out.println("Another round? (1)");
                playerAction = scanner.nextInt();
                switch (playerAction) {
                    case 1:
                        // Player chooses to keep playing.
                        break;
                    default:
                        keepPlaying = false;
                        break;
                }

                // Check if wallet funds are sufficient for the minimum
                if (playerWallet < 5) {
                    System.out.println("Sorry you do not have enough money to play. :(");
                    keepPlaying = false;
                }

            } catch (Exception e) {
                // Catch the exception if no cards are left in the deck
                System.out.println(e.getMessage());
                keepPlaying = false; // End the game
            }
        }

        // Display the player's final score after the game ends
        System.out.println("You ended the game with " + playerWallet + " in your wallet.");
        scanner.close(); // Close the scanner
    }

    public static void intro() {
        // Welcome message
        System.out.println("Welcome to blackjack! The goal is to reach 21 without going over. (No side rules)");
        System.out.println("At the start of each hand, place a bet.");
        System.out.println("The dealer then deals.");
        System.out.println("All cards, except for one of the dealer's cards (the hole), are dealt face up."); 
        System.out.println("The player can then choose to \"stand\" (take no cards) or \"hit\" (take one more card from deck).");
        System.out.println("After player concludes their turn, the dealer takes theirs.");
        System.out.println();
    }

    // This method returns whether the bet meets the minimum and can be pledged by the playerWallet.
    public static boolean isValidBet(int b, int w) {
        if (b < 5) {
            System.out.println("The minimum bet size is 5.\n");
            return false;
        } else if (b > w) {
            System.out.println("Insufficient funds for a bet of that size.\n");
            return false;
        } else {
            return true;
        }
    }

    // This method takes a given hand and outputs the best numeric value.  It respects the dual value of aces.
    public static int handTotal(Card[] hand) {
        int total = 0;
        int aceQuantity = numberOfAces(hand);
        // First calculate total assuming all aces are one.
        for (int i = 0; i < hand.length; i++) {
            if (hand[i].getRank().getValue() != 1) {
                total += hand[i].getRank().getValue();
            }
        }
        // Then, for the total quantity of aces, add 10 until bust.  That max total is returned.
        while (total < 12 && aceQuantity > 0) {
            total += 10;
            aceQuantity--;
        }
        return total;
    }

    // This method returns an integer number of aces in a hand.
    public static int numberOfAces(Card[] hand) {
        int total = 0;
        for (int i = 0; i < hand.length; i++) {
            if (hand[i].getRank().getValue() == 1) {
                total += 1;
            }
        }
        return total;
    }

    // This method takes the existing hand as an input and outputs a hand array that is one larger.
    public static Card[] hit(Card[] input, DeckManager deckManager) throws Exception {
        Card[] output = new Card[input.length + 1];
        System.arraycopy(input, 0, output, 0, input.length);
        try {
            output[input.length] = deckManager.dealCard();
        } catch (Exception e) {
            throw e;
        }
        return output;
    }

    // This method prints all cards in a hand.
    public static void printHand(Card[] input) {
        for (int i=0; i < input.length; i++) {
            System.out.println("\t" + input[i]);
        }
    }

    // This method determines whether the player lost, won, or drawed and outputs the correct console text and returns the updated player wallet total.
    public static int evaluateRound(Card[] playerHand, Card[] dealerHand, int playerWallet, int playerBet) {
        if (handTotal(playerHand) > 21) {
            playerWallet -= playerBet;
            System.out.println("You lost and forfeit your bet.");
            System.out.println("");
            System.out.println("Your wallet now has a value of " + playerWallet);
            System.out.println("");
        } else if (handTotal(dealerHand) > 21) {
            playerWallet += playerBet;
            System.out.println("You won!");
            System.out.println();
            System.out.println("Your wallet now has a value of " + playerWallet);
            System.out.println("");
        } else if (handTotal(playerHand) < handTotal(dealerHand)) {
            playerWallet -= playerBet;
            System.out.println("You lost and forfeit your bet.");
            System.out.println("");
            System.out.println("Your wallet now has a value of " + playerWallet);
            System.out.println("");
        } else if (handTotal(playerHand) > handTotal(dealerHand)) {
            playerWallet += playerBet;
            System.out.println("You won!");
            System.out.println();
            System.out.println("Your wallet now has a value of " + playerWallet);
            System.out.println("");
        } else if (handTotal(playerHand) == handTotal(dealerHand)) {
            System.out.println("Round resulted in a push.");
            System.out.println();
            System.out.println("Your wallet still has a value of " + playerWallet);
            System.out.println("");
        }
        return playerWallet;
    }
}