package inf112.skeleton.app.CardTests;

import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramCardDeck;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProgramCardDeckTests {
    ProgramCardDeck deck;
    @Before
    public void setUp() { deck = new ProgramCardDeck(); }


    @Test
    public void deckTest() {
        ArrayList<ProgramCard> deck2 = deck.getDeck();
        assertEquals(deck2, deck.getDeck());
    }

    @Test
    public void standardDeckSizeTest () {
        assertEquals(deck.getSizeOfDeck(), 84);
    }

    @Test
    public void someCardsTest() {
        for (int i = 1; i<84; i++) {
            assertNotEquals(deck.getDeck().get(0), deck.getDeck().get(i));
        }
        for (int j = 2; j<84; j++) {
            assertNotEquals(deck.getDeck().get(1), deck.getDeck().get(j));
        }
        for (int k = 0; k<50; k++) {
            assertNotEquals(deck.getDeck().get(50), deck.getDeck().get(k));
        }
        for (int l = 51; l<84; l++) {
            assertNotEquals(deck.getDeck().get(50), deck.getDeck().get(l));
        }
    }


    @Test
    public void shuffleTest() {
        ArrayList<ProgramCard> deck2 = deck.getDeck();
        deck.shuffleDeck();
        assertNotEquals(deck, deck2);
    }
}