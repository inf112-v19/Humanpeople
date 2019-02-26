package inf112.skeleton.app;

import inf112.skeleton.app.Card;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Player deck for programming cards
 */
public class PlayerDeck {

    public final int MAX_NUMBER_CARDS_ON_HAND = 5;
    public final int MAX_NUMBER_CARDS_IN_DECK = 9;

    /**
     * Deck of cards for the player to choose from
     */
    private ArrayList<Card> deck;
    /**
     * Cards on the players hand
     */
    private ArrayList<Card> hand;

    public PlayerDeck() {
        deck = new ArrayList<>(MAX_NUMBER_CARDS_IN_DECK);
        hand = new ArrayList<>(MAX_NUMBER_CARDS_ON_HAND);
    }

    /**
     * Takes card from deck at given index and puts it in players hand.
     * Throws exception if cardIndex is out of bounds or hand is already full (size: 5)
     * @param cardInDeckNumber
     */
    public void selectCardForHand(int cardInDeckNumber) {
        if (cardInDeckNumber > 0 || cardInDeckNumber < MAX_NUMBER_CARDS_IN_DECK-1)
            throw new IndexOutOfBoundsException("You only have " + MAX_NUMBER_CARDS_IN_DECK +" cards to choose from. No such " + cardInDeckNumber + "th card");
        if (handSize() >= MAX_NUMBER_CARDS_ON_HAND)
            throw new IndexOutOfBoundsException("The players hand is already full (size: " + MAX_NUMBER_CARDS_ON_HAND+ ")");
        Card card = deck.get(cardInDeckNumber);
        deck.remove(cardInDeckNumber);
        hand.add(card);
    }
    /**
     * Get the next card form hand
     * If no cards left on hand then throw NoSuchElementException
     * @return card
     */
    public Card getCardFromHand() {
        if (handSize() < 1)
            throw new NoSuchElementException("No cards in the deck");
        Card card = hand.get(handSize()-1);
        return card;
    }

    /**
     * Set the deck of a player
     * @param newDeck
     */
    public void setDeck(ArrayList<Card> newDeck) {
        if (newDeck.size() != MAX_NUMBER_CARDS_IN_DECK)
            throw new IllegalArgumentException("The deck needs to be size 5. Size was: " + newDeck.size());
        this.deck = newDeck;
    }

    /**
     *
     * @return number of cards left in the player deck
     */
    public int deckSize() {
        return deck.size();
    }

    /**
     *
     * @return number of cards currently in hand
     */
    public int handSize() {
        return hand.size();
    }
}
