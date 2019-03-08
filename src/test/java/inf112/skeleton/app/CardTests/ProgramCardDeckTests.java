package inf112.skeleton.app.CardTests;

import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramCardDeck;
import inf112.skeleton.app.Cards.ProgramType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProgramCardDeckTests {
    private ProgramCardDeck deck1;
    private ProgramCardDeck deck2;

    @Before
    public void setUp() {
        deck1 = new ProgramCardDeck();
        deck2 = new ProgramCardDeck();
    }


    @Test
    public void deckTest() {
        ArrayList<ProgramCard> deck2 = deck1.getDeck();
        assertEquals(deck2, deck1.getDeck());
    }

    @Test
    public void standardDeckSizeTest() {
        assertEquals(deck1.getSizeOfDeck(), 84);
    }

    @Test
    public void someCardsNotEqualTest() {
        for (int i = 1; i < 84; i++) {
            assertNotEquals(deck1.getDeck().get(0), deck1.getDeck().get(i));
        }
        for (int j = 2; j < 84; j++) {
            assertNotEquals(deck1.getDeck().get(1), deck1.getDeck().get(j));
        }
        for (int k = 0; k < 50; k++) {
            assertNotEquals(deck1.getDeck().get(50), deck1.getDeck().get(k));
        }
        for (int l = 51; l < 84; l++) {
            assertNotEquals(deck1.getDeck().get(50), deck1.getDeck().get(l));
        }
    }


    @Test
    public void changedByShuffleTest() {
        for (int i = 0; i < 84; i++) {
            assertEquals(deck1.getDeck().get(i).getPriority(), deck2.getDeck().get(i).getPriority());
        }
        boolean shuffled = false;
        deck1.shuffleDeck();
        for (int i = 0; i < 84; i++) {
            if (!(deck1.getDeck().get(i).getPriority() == deck2.getDeck().get(i).getPriority())) {
                shuffled = true;
                break;
            }
        }
        assertTrue(shuffled);
    }

    @Test
    public void uniquePrioritiesTest() {
        ArrayList<Integer> priorities = new ArrayList<Integer>();
        int pri;
        for (int i = 0; i < deck1.getSizeOfDeck(); i++) {
            pri = deck1.getDeck().get(i).getPriority();
            if (!(priorities.contains(pri))) {
                priorities.add(pri);
            }
        }
        assertEquals(priorities.size(), deck1.getSizeOfDeck());
    }

    @Test
    public void uniqueFilenameTest() {
        ArrayList<String> filenames = new ArrayList<String>();
        String name;
        for (int i = 0; i < deck1.getSizeOfDeck(); i++) {
            name = deck1.getDeck().get(i).getFilename();
            if (!(filenames.contains(name))) {
                filenames.add(name);
            }
        }
        assertEquals(filenames.size(), deck1.getSizeOfDeck());
    }

    @Test
    public void rightAmountOfSpecificTypeCardsTest() {
        ArrayList<ProgramType> types = new ArrayList<ProgramType>();
        ProgramType type;

        int move1 = 0;
        int move2 = 0;
        int move3 = 0;
        int backUp = 0;
        int rotateLeft = 0;
        int rotateRight = 0;
        int uTurn = 0;
        int errors = 0;

        for (int i = 0; i < deck1.getSizeOfDeck(); i++) {
            type = deck1.getDeck().get(i).getProgramType();
            if (!(types.contains(type))) {
                types.add(type);
            }
            if (type == ProgramType.MOVE1) {
                move1++;
            } else if (type == ProgramType.MOVE2) {
                move2++;
            } else if (type == ProgramType.MOVE3) {
                move3++;
            } else if (type == ProgramType.BACKUP) {
                backUp++;
            } else if (type == ProgramType.ROTATELEFT) {
                rotateLeft++;
            } else if (type == ProgramType.ROTATERIGHT) {
                rotateRight++;
            } else if (type == ProgramType.UTURN) {
                uTurn++;
            } else {
                errors++;
            }
        }
        assertEquals(types.size(), 7);
        assertEquals(move1, 18);
        assertEquals(move2, 12);
        assertEquals(move3, 6);
        assertEquals(backUp, 6);
        assertEquals(rotateLeft, 18);
        assertEquals(rotateRight, 18);
        assertEquals(uTurn, 6);
        assertEquals(errors, 0);
    }
}
