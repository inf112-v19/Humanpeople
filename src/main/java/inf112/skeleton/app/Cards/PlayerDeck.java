package inf112.skeleton.app.Cards;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * PlayerLayerObject deck for programming cards
 */
public class PlayerDeck {

    public static int MAX_NUMBER_CARDS_ON_HAND;
    public static int MAX_NUMBER_CARDS_IN_DECK;
    /**
     * Deck of cards for the player to choose from
     */
    private ArrayList<ProgramCard> deck;

    /**
     * Cards on the players hand
     */
    private ArrayList<ProgramCard> hand;

    public PlayerDeck() {
        deck = new ArrayList<>();
        hand = new ArrayList<>();
        MAX_NUMBER_CARDS_ON_HAND = 5;
        MAX_NUMBER_CARDS_IN_DECK = 9;
    }

    /**
     * Takes card from deck at given index and puts it in players hand.
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

    public void changedHealth(int hP) {
        if (hP >= 9) {
            MAX_NUMBER_CARDS_ON_HAND = 5;
            MAX_NUMBER_CARDS_IN_DECK = 9;
        }
        if (hP < 9) {
            MAX_NUMBER_CARDS_IN_DECK = hP;
        }
        if (hP <= 5) {
            MAX_NUMBER_CARDS_ON_HAND = hP;
        }
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
        if (newDeck.size() > MAX_NUMBER_CARDS_IN_DECK)
            throw new IllegalArgumentException("The deck needs to be size 9. Size was: " + newDeck.size());
        this.deck = newDeck;
    }


    /**
     * puts card in discardpile (inactivecarddeck)
     * @param pc
     */
    private void discardCard(ProgramCard pc) {
        ArrayList<ProgramCard> aL = new ArrayList<>();
        aL.add(pc);
        discardCards(aL);
    }

    /**
     * Put cards in discardpile (inactivecarddeck)
     * @param unusedCards
     */
    private void discardCards(ArrayList<ProgramCard> unusedCards) {
        ProgramCardDeck programCardDeck = ProgramCardDeck.getProgramCardDeckSingleton();
        for (int i=0; i<unusedCards.size(); i++) {
            programCardDeck.addToInactiveCardDeck(unusedCards.get(i));
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

    public void setPlayerHand(ArrayList<ProgramCard> hand) {
        this.hand = hand;
    }

    public ProgramCard getCard(int i) {
        return deck.get(i);
    }


}
