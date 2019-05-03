package inf112.skeleton.app.Cards;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class PlayerDeck {
    public static final int MAX_NUMBER_CARDS_ON_HAND = 5;
    public static final int MAX_NUMBER_CARDS_IN_DECK = 9;
    public int numberOfLockedCards;
    public int numberOfNewCardsToDeck;
    public int numberOfNewCardsToHand;
    private ArrayList<ProgramCard> deck;
    private ArrayList<ProgramCard> hand;
    private ArrayList<ProgramCard> handFromLastRound;

    public PlayerDeck() {
        numberOfNewCardsToDeck = MAX_NUMBER_CARDS_IN_DECK;
        numberOfNewCardsToHand = MAX_NUMBER_CARDS_ON_HAND;
        deck = new ArrayList<>();
        hand = new ArrayList<>();
        handFromLastRound = new ArrayList<>();
    }

    public void selectCardsForHand() {
        if (deck.size() < 0)
            throw new IndexOutOfBoundsException("The deck is too small, size is: " + deck.size() + " should be: " + numberOfNewCardsToDeck);
        ArrayList<ProgramCard> newHand = new ArrayList<>();
        if(numberOfLockedCards > 0)
            for (int i = 0; i < numberOfLockedCards; i++)
                if (handFromLastRound.isEmpty()) {
                    newHand.add(ProgramCardDeck.getProgramCardDeckSingleton().takeRandomCard());
                } else {
                    newHand.add(handFromLastRound.get(i));
                }
        for (int i = 0; i < numberOfNewCardsToHand; i++) {
            newHand.add(deck.get(0));
            deck.remove(0);
        }
        setPlayerHandForAi(newHand);
    }

    private void setPlayerHandForAi(ArrayList<ProgramCard> newHand) {
        if (!(newHand.isEmpty()))
        handFromLastRound = new ArrayList<>(newHand);
        this.hand = new ArrayList<>(newHand);
    }

    public ProgramCard getCardFromHand(int i) {
        if (handSize() < 1)
            throw new NoSuchElementException("No cards in the hand: " + handSize());
        ProgramCard programCard = hand.get(i);
        return programCard;
    }
    public ProgramCard getCardFromHand() {
        if (handSize() < 1)
            throw new NoSuchElementException("No cards in the hand: " + handSize());
        ProgramCard programCard = hand.get(0);
        hand.remove(0);
        return programCard;
    }

    public void setDeck(ArrayList<ProgramCard> newDeck) {
        if (newDeck.size() > numberOfNewCardsToDeck)
            throw new IllegalArgumentException("The deck needs to be size 9. Size was: " + newDeck.size());
        this.deck = new ArrayList<>(newDeck);
    }

    public void changedHealth(int health) {
        if (health > 9) {
            numberOfNewCardsToDeck = MAX_NUMBER_CARDS_IN_DECK;
        }
        if (health <= 9 && health > 0) {
            numberOfNewCardsToDeck = health-1;
        }
        burnCardsToHand(health);
    }

    private void burnCardsToHand(int hP) {
        if (hP > 5) {
            numberOfLockedCards = 0;
            numberOfNewCardsToHand = MAX_NUMBER_CARDS_ON_HAND;
        }
        if (hP <= 5 && hP >= 1) {
            numberOfLockedCards = (6 - hP);
            numberOfNewCardsToHand = MAX_NUMBER_CARDS_ON_HAND - numberOfLockedCards;
        }
        if (hP < 1) {
            numberOfNewCardsToHand = 0;
            numberOfLockedCards = 5;
        }
    }

    public boolean deckIsEmpty() {
        return deckSize() == 0;
    }

    public boolean handIsEmpty() {
        return handSize() == 0;
    }

    public int deckSize() {
        return deck.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck, hand);
    }

    public int handSize() {
        return hand.size();
    }

    public void setPlayerHand(ArrayList<ProgramCard> hand) {
        if(hand.isEmpty())
            return;
        this.hand = new ArrayList<>(hand);
        handFromLastRound = new ArrayList<>(this.hand);
    }

    public ArrayList<ProgramCard> getHandFromLastRound() {
        return handFromLastRound;
    }

    public ProgramCard getCard(int i) {
        return deck.get(i);
    }
}
