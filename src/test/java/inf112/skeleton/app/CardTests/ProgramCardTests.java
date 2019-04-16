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
        card3 = new ProgramCard(ProgramType.UTURN, 52, "filename42");

    }

    @Test
    public void simpleTest() {
        assertNotEquals(card1, card2);
    }

    @Test
    public void priorityGetTest() {
        assertNotEquals(card1.getPriority(), card2.getPriority());
        assertEquals(card2.getPriority(), 69);
        assertEquals(card3.getPriority(), 52);

    }

    @Test
    public void programTypeGetTest() {
        assertNotEquals(card3.getProgramType(), card2.getProgramType());
        assertEquals(card1.getProgramType(), card2.getProgramType());
        assertEquals(card1.getProgramType(), ProgramType.MOVE1);
    }

    @Test
    public void filenameGetTest() {
        assertNotEquals(card1.getFilename(), card2.getFilename());
        assertEquals(card1.getFilename(), "filename420");
    }

    @Test
    public void setAndGetPlayerThatPlayedCardId() {
        card1.setPlayerThatPlayedTheCard(2);
        assertEquals(card1.getPlayerThatPlayedTheCard(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void playerThatPlayedCardNotSet() {
        card1.getPlayerThatPlayedTheCard();
    }

    @Test
    public void cardsAreComparableTest() {
        int lowerIdComparedToHigherId = card2.compareTo(card1);
        assertEquals(lowerIdComparedToHigherId, -1);

        int equalIdComparedToEqualId = card1.compareTo(card1);
        assertEquals(equalIdComparedToEqualId, 0);

        int higherIdComparedToLowerId = card1.compareTo(card2);
        assertEquals(higherIdComparedToLowerId, 1);
    }
}
