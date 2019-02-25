package inf112.skeleton.app;

import inf112.skeleton.app.Card;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class PlayerDeck {

    private final int MAX_NUMBER_CARDS_IN_DECK = 5;
    private ArrayList<Card> deck;

    public PlayerDeck() {
        deck = new ArrayList<Card>(MAX_NUMBER_CARDS_IN_DECK);
    }

    /**
     * Get the next card in the deck
     * If no cards left in the deck then throw NoSuchElementException
     * @return
     */
    public Card getCard() {
        if (size() < 1)
            throw new NoSuchElementException("No cards in the deck");
        Card card = deck.get(size()-1);
        return card;
    }

    /**
     * Set the deck of a player
     * @param newDeck
     */
    public void setDeck(ArrayList<Card> newDeck) {
        if (newDeck.size() != 5)
            throw new IllegalArgumentException("The deck needs to be size 5, not " + newDeck.size());
        this.deck = newDeck;
    }

    public int size() {
        return deck.size();
    }
}
