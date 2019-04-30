package inf112.skeleton.app.Cards;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * PlayerLayerObject deck for programming cards
 */
public class PlayerDeck {

    private final static int NUMBER_CARDS_ON_HAND = 5;
    public static int NUMBER_OF_LOCKED_CARDS;
    public static int NUMBER_OF_NEW_CARDS_TO_HAND;
    public static int NUMBER_OF_NEW_CARDS_TO_DECK;
    private static int MAX_NUMBER_CARDS_IN_DECK = 9;

    /**
     * Deck of cards for the player to choose from
     */
    private ArrayList<ProgramCard> deck;

    private ArrayList<ProgramCard> handFromLastRound;

    /**
     * Cards on the players hand
     */
    private ArrayList<ProgramCard> hand;

    public PlayerDeck() {

        deck = new ArrayList<>();
        hand = new ArrayList<>();
        handFromLastRound = new ArrayList<>();
        NUMBER_OF_NEW_CARDS_TO_DECK = MAX_NUMBER_CARDS_IN_DECK;
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

        if (handSize() >= NUMBER_CARDS_ON_HAND)
            throw new IndexOutOfBoundsException("The players hand is already full (size: " + NUMBER_CARDS_ON_HAND + ")");

        ProgramCard programCard = deck.get(cardInDeckNumber);
        deck.remove(cardInDeckNumber);
        hand.add(programCard);
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
            NUMBER_OF_NEW_CARDS_TO_HAND = NUMBER_CARDS_ON_HAND;
        }
        if (hP <= 5 && hP >= 1) {
            NUMBER_OF_LOCKED_CARDS = (6 - hP);
            NUMBER_OF_NEW_CARDS_TO_HAND = NUMBER_CARDS_ON_HAND - NUMBER_OF_LOCKED_CARDS;
    }
        if (hP < 1) {
            NUMBER_OF_NEW_CARDS_TO_HAND = 0;
            NUMBER_OF_LOCKED_CARDS = 5;
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
        if (newDeck.size() > NUMBER_OF_NEW_CARDS_TO_DECK)
            throw new IllegalArgumentException("The deck needs to be size " + NUMBER_OF_NEW_CARDS_TO_DECK + ". Size was: " + newDeck.size());
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
        ArrayList<ProgramCard> handFromLastRound = getLockedCardsFromLastRound();
        if (handFromLastRound.isEmpty()) {
            this.hand = hand;
            return;
        }
        for (int i = 0; i < handFromLastRound.size(); i++) {
            this.hand.set(i, handFromLastRound.get(i));
        }
        for (int i = handFromLastRound.size(); i<NUMBER_CARDS_ON_HAND; i++) {
            this.hand.set(i, hand.get(i));
        }
    }

    private ArrayList<ProgramCard> getLockedCardsFromLastRound() {
        ArrayList<ProgramCard> lockedCardsFromLastRound = new ArrayList<>();
        for (int i=0; i<this.getNumberOfLockedCards(); i++) {
            lockedCardsFromLastRound.add(this.handFromLastRound.get(i));
        }
        return  lockedCardsFromLastRound;
     }

    public ProgramCard getCard(int i) {
        return deck.get(i);
    }

    public int getNumberCardsOnHand(){
        return NUMBER_CARDS_ON_HAND;
    }

    public int getNumberOfNewCardsToDeck() {
        return NUMBER_OF_NEW_CARDS_TO_DECK;
    }

    public int getNumberOfLockedCards() {
        return  NUMBER_OF_LOCKED_CARDS;
    }
}
