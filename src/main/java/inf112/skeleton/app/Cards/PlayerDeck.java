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
        NUMBER_OF_NEW_CARDS_TO_HAND = MAX_NUMBER_CARDS_ON_HAND;
        deck = new ArrayList<>();
        hand = new ArrayList<>();
        handFromLastRound = new ArrayList<>();
    }

    /**
     * Takes card from deck at given index and puts it in players hand.
     * Throws exception if cardIndex is out of bounds or hand is already full (size: 5)
     *
     * @param
     */
    public void selectCardsForHand() {
        if (deck.size() < 0)
            throw new IndexOutOfBoundsException("The deck is too small, size is: " + deck.size() + " should be: " + NUMBER_OF_NEW_CARDS_TO_DECK);
        ArrayList<ProgramCard> newHand = new ArrayList<>();
        if(NUMBER_OF_LOCKED_CARDS > 0)
            for (int i = 0; i < NUMBER_OF_LOCKED_CARDS; i++)
                if (handFromLastRound.isEmpty()) {
                    newHand.add(ProgramCardDeck.getProgramCardDeckSingleton().takeRandomCard());
                } else {
                    newHand.add(handFromLastRound.get(i));
                }
        for (int i = 0; i < NUMBER_OF_NEW_CARDS_TO_HAND; i++) {
            newHand.add(deck.get(0));
            deck.remove(0);
        }
        setPlayerHandForAi(newHand);
    }

    private void setPlayerHandForAi(ArrayList<ProgramCard> newHand) {
        if (!(newHand.isEmpty()))
        handFromLastRound = new ArrayList<>(this.hand);
        this.hand = new ArrayList<>(newHand);
//        discardCardArrayList(deck);
//        discardOldHand();
    }

    /**
     * Get the next card form hand
     * If no cards left on hand then throw NoSuchElementException
     *
     * @return card
     */
    public ProgramCard getCardFromHand(int i) {
        if (handSize() < 1)
            throw new NoSuchElementException("No cards in the deck");

        ProgramCard programCard = hand.get(i);
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
        this.deck = new ArrayList<>(newDeck);
    }

    public void changedHealth(int health) {
        if (health > 9) {
            NUMBER_OF_NEW_CARDS_TO_DECK = MAX_NUMBER_CARDS_IN_DECK;
        }
        if (health <= 9 && health > 0) {
            NUMBER_OF_NEW_CARDS_TO_DECK = health-1;
        }
        burnCardsToHand(health);
    }

    private void burnCardsToHand(int hP) {
        if (hP > 5) {
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
        if(hand.isEmpty())
            return;

            this.hand = new ArrayList<>(hand);
            handFromLastRound = new ArrayList<>(this.hand);

//        discardCards();
    }

    public ArrayList<ProgramCard> getHandFromLastRound() {
        return handFromLastRound;
    }

    public ProgramCard getCard(int i) {
        return deck.get(i);
    }

    public void discardCards() {
        discardRestOfDeck();
        discardOldHand();
    }

    private void discardOldHand() {
        for (int i = NUMBER_OF_LOCKED_CARDS; i < hand.size(); i++) {
            discardCard(hand.get(i));
        }
    }

    public void discardRestOfDeck() {
        if (deck.isEmpty())
            return;
        ArrayList<ProgramCard> list = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            if (!(hand.contains(deck.get(i))))
                list.add(deck.get(i));
        }
        deck.clear();
        discardCardArrayList(list);
    }

    public void discardCardArrayList(ArrayList<ProgramCard> cards) {
        for (int i = 0; i < cards.size(); i++)
            discardCard(cards.get(i));
    }

    public void discardCard(ProgramCard card) {
        ProgramCardDeck pCD = ProgramCardDeck.getProgramCardDeckSingleton();
        pCD.addToInactiveCardDeck(card);
    }
}
