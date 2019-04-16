package inf112.skeleton.app.Cards;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Player deck for programming cards
 */
public class PlayerDeck {

    public static final int MAX_NUMBER_CARDS_ON_HAND = 5;
    public static final int MAX_NUMBER_CARDS_IN_DECK = 9;

    /**
     * Deck of cards for the player to choose from
     */
    private ArrayList<ProgramCard> deck;

    /**
     * Cards on the players hand
     */
    private ArrayList<ProgramCard> hand;

    public PlayerDeck() {
        deck = new ArrayList<>(MAX_NUMBER_CARDS_IN_DECK);
        hand = new ArrayList<>(MAX_NUMBER_CARDS_ON_HAND);
    }

    /**
     * Takes card from deck at given index and puts it in players hand. Removes it from a players Deck
     * Throws exception if cardIndex is out of bounds or hand is already full (size: 5)
     *
     * @param cardInDeckNumber
     */
    public void selectCardForHand(int cardInDeckNumber) {
        if (cardInDeckNumber < 0 || cardInDeckNumber > deckSize())
            throw new IndexOutOfBoundsException("You only have " + MAX_NUMBER_CARDS_IN_DECK +
                    " cards to choose from. No such " + cardInDeckNumber + "th programCard");

        if (handSize() >= MAX_NUMBER_CARDS_ON_HAND)
            throw new IndexOutOfBoundsException("The players hand is already full (size: " + MAX_NUMBER_CARDS_ON_HAND + ")");

        ProgramCard programCard = deck.get(cardInDeckNumber);
        deck.remove(cardInDeckNumber);
        hand.add(programCard);
    }

<<<<<<< HEAD
    /**'
     * Takes a card from the hand at index, and puts it back in deck. Removes from hand
     * @param cardInHandNumber
     */
    public void removeCardFromHand(int cardInHandNumber) {
        if(cardInHandNumber < 0  || cardInHandNumber > handSize())
            throw new IndexOutOfBoundsException("You can only have 5 cards in your hand! You cannot remove a cardindex larger than 5");
        ProgramCard programCard = hand.get(cardInHandNumber);
        hand.remove(cardInHandNumber);
        deck.add(programCard);
    }
=======
>>>>>>> master
    /**
     * Get the next card form hand
     * If no cards left on hand then throw NoSuchElementException
     *
     * @return card
     */
    public ProgramCard getCardFromHand() {
        if (handSize() < 1)
            throw new NoSuchElementException("No cards in the deck");

        ProgramCard programCard = hand.get(0);
        hand.remove(0);
        return programCard;
    }

    /**
     * Set the deck of a player
     *
     * @param newDeck
     */
    public void setDeck(ArrayList<ProgramCard> newDeck) {
        if (newDeck.size() > MAX_NUMBER_CARDS_IN_DECK)
            throw new IllegalArgumentException("The deck needs to be size 9. Size was: " + newDeck.size());

        this.deck = newDeck;
    }

    /**
     * @return true if player deck is empty
     */
    public boolean deckIsEmpty() {
        return deckSize() == 0;
    }

    /**
     * @return true if hand is empty
     */
    public boolean handIsEmpty() {
        return handSize() == 0;
    }

    /**
     * @return number of cards left in the player deck
     */
    public int deckSize() {
        return deck.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck, hand);
    }

    /**
     * @return number of cards currently in hand
     */
    public int handSize() {
        return hand.size();
    }

<<<<<<< HEAD
    /**
     *
     * @param index
     * @return the card in the deck at given index
     */
    public ProgramCard get(int index) {
        return deck.get(index);
    }

    public ArrayList<ProgramCard> getHand() {
        return hand;
    }
=======
    public void setPlayerHand(ArrayList<ProgramCard> hand) {
        this.hand = hand;
    }

    public ProgramCard getCard(int i) {
        return deck.get(i);
    }


>>>>>>> master
}
