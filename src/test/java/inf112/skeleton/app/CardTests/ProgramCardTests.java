package inf112.skeleton.app.CardTests;

import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramCardDeck;
import inf112.skeleton.app.Cards.ProgramType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProgramCardTests {
    ProgramCardDeck deck;
    ProgramCard card1;
    ProgramCard card2;
    ProgramCard card3;

    @Before
    public void setUp() {
        deck = new ProgramCardDeck();
        card1 = new ProgramCard(ProgramType.MOVE1, 420, "filename420");
        card2 = new ProgramCard(ProgramType.MOVE1, 69, "filename2");

    }

    @Test
    public void simpleTest() {
        assertNotEquals(card1, card2);
    }
}
