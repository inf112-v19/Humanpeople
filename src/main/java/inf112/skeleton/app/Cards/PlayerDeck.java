package inf112.skeleton.app.Cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * PlayerLayerObject deck for programming cards
 */
public class PlayerDeck {

    public static final int MAX_NUMBER_CARDS_ON_HAND = 5;
    public static final int MAX_NUMBER_CARDS_IN_DECK = 9;
    public int NUMBER_OF_LOCKED_CARDS;
    public int NUMBER_OF_NEW_CARDS_TO_DECK;
    public int NUMBER_OF_NEW_CARDS_TO_HAND;

    /**
     * Deck of cards for the player to choose from
     */
    private ArrayList<ProgramCard> deck;

    /**
     * Cards on the players hand
     */
    private ArrayList<ProgramCard> hand;

    private ArrayList<ProgramCard> handFromLastRound;

    public PlayerDeck() {
        NUMBER_OF_NEW_CARDS_TO_DECK = MAX_NUMBER_CARDS_IN_DECK;
        deck = new ArrayList<>();
        hand = new ArrayList<>();
        handFromLastRound = new ArrayList<>();
    }

    /**
     * Takes card from deck at given index and puts it in players hand.
     * Throws exception if cardIndex is out of bounds or hand is already full (size: 5)
     *
     * @param cardInDeckNumber
     */
    public void selectCardForHand(int cardInDeckNumber) {
        if (cardInDeckNumber < 0 || cardInDeckNumber > deckSize())
            throw new IndexOutOfBoundsException("You only have " + NUMBER_OF_NEW_CARDS_TO_DECK +
                    " cards to choose from. No such " + cardInDeckNumber + "th programCard");

        if (handSize() >= MAX_NUMBER_CARDS_ON_HAND)
            throw new IndexOutOfBoundsException("The players hand is already full (size: " + MAX_NUMBER_CARDS_ON_HAND + ")");

        ProgramCard programCard = deck.get(cardInDeckNumber);
        deck.remove(cardInDeckNumber);
        hand.add(programCard);
    }

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
        if (newDeck.size() > NUMBER_OF_NEW_CARDS_TO_DECK)
            throw new IllegalArgumentException("The deck needs to be size 9. Size was: " + newDeck.size());
        this.deck = newDeck;
    }

    public void changedHealth(int hP) {
        if (hP > 9) {
            NUMBER_OF_NEW_CARDS_TO_DECK = MAX_NUMBER_CARDS_IN_DECK;
        }
        if (hP <= 9 && hP > 0) {
            NUMBER_OF_NEW_CARDS_TO_DECK = hP-1;
        }
        if (hP == 0)
            NUMBER_OF_NEW_CARDS_TO_DECK = 0;
        burnCardsToHand(hP);
    }

    private void burnCardsToHand(int hP) {
        if (hP > 6) {
            NUMBER_OF_LOCKED_CARDS = 0;
            NUMBER_OF_NEW_CARDS_TO_HAND = MAX_NUMBER_CARDS_ON_HAND;
        }
        if (hP <= 5 && hP >= 1) {
            NUMBER_OF_LOCKED_CARDS = (6 - hP);
            NUMBER_OF_NEW_CARDS_TO_HAND = MAX_NUMBER_CARDS_ON_HAND - NUMBER_OF_LOCKED_CARDS;
        }
        if (hP < 1) {
            NUMBER_OF_NEW_CARDS_TO_HAND = 0;
            NUMBER_OF_LOCKED_CARDS = 5;
        }
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

    /**
     * Trenger nye kort om det ikke finnes kort fra forrige runde
     * @param hand
     */
    public void setPlayerHand(ArrayList<ProgramCard> hand) {
        handFromLastRound = new ArrayList<>(hand);
        this.hand = hand;
    }

    public ArrayList<ProgramCard> getHandFromLastRound() {
        return handFromLastRound;
    }

    public ProgramCard getCard(int i) {
        return deck.get(i);
    }


}
