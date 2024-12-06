package blackjack;
import java.util.Random;

// The DeckManager class handles the deck (shuffling, dealing)
public class DeckManager {
    
    // Array to hold the deck of 52 cards
    private final Card[] deck;
    private int cardIndex;

    // Constructor initializes the deck with all 52 cards and shuffles them
    public DeckManager(int numberOfDecks) {
        deck = new Card[numberOfDecks * 52];
        cardIndex = 0;
        // Fill the deck with cards (all suits and ranks)
        for (int d = 0; d < numberOfDecks; d++) {
            int i = 0;
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    deck[52*d + i++] = new Card(suit, rank);
                }
            }
        }
        shuffle();
    }

    // Shuffle the deck with an implementation of the Fisher-Yates algorithm
    private void shuffle() {
        // Instantiate a random number generator
        Random r = new Random();
        // Shuffle the deck by swapping each card with a randomly selected card. "i" is the index and "j" is the new value for i.
        for (int i = 0; i < deck.length - 2; i++) {
            int j = r.nextInt(deck.length-i) + i; // Random card index between i and deck length.
            Card temp = deck[i]; // Temporary variable to hold the card
            deck[i] = deck[j]; // Swap the first card with a random card
            deck[j] = temp; // Complete the swap
        }
    }

    // Method to deal one card from the deck
    public Card dealCard() throws Exception {
        // If there are still cards left, return the next card
        if (cardIndex < deck.length) {
            return deck[cardIndex++]; // Return current card and increment the pointer
        } else {
            // If all cards have been dealt, throw an exception
            throw new Exception("All cards have been dealt.");
        }
    }

    // Method to deal two cards from the deck as an array with two elements.
    public Card[] dealHand() throws Exception {
        try {
            Card[] hand = new Card[2];
            hand[0] = dealCard();
            hand[1] = dealCard();
            return hand;
        } catch (Exception e) {
            throw e;
        }
    }
}