package blackjack;
// The Card class represents an individual card in the deck
public class Card {

    // Enum for suits (Hearts, Diamonds, Clubs, Spades)
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    // Enum for ranks with values (e.g., Ace is 1, King/Queen/Jack are 10)
    public enum Rank {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

        // Value of the card in a game (e.g., Jack = 10 points)
        private final int value;

        Rank(int value) {
            this.value = value;
        }

        // Returns the value of the rank (e.g., Ace = 11)
        public int getValue() {
            return value;
        }
    }

    // Fields to store the suit and rank of the card
    private final Suit suit;
    private final Rank rank;

    // Constructor to initialize a card with a suit and rank
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Getter to return the suit of the card
    public Suit getSuit() {
        return suit;
    }

    // Getter to return the rank of the card
    public Rank getRank() {
        return rank;
    }

    // toString method to print the card as a string (e.g., "Ace of Spades")
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}