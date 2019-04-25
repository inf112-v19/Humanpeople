package inf112.skeleton.app.CardTests;

import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramCardDeck;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.Player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProgramCardDeckTests {
    private ProgramCardDeck deck;

    @Before
    public void setUp() {
        deck = ProgramCardDeck.getProgramCardDeckSingleton();
    }

    @Test
    public void rightAmountOfSpecificTypeCardsTest() {
        deck.reConstructProgramCardDeck();
        ArrayList<ProgramType> types = new ArrayList<>();
        ProgramType type;

        int move1 = 0;
        int move2 = 0;
        int move3 = 0;
        int backUp = 0;
        int rotateLeft = 0;
        int rotateRight = 0;
        int uTurn = 0;
        int errors = 0;

        for (int i = 0; i < deck.getSizeOfDeck(); i++) {
            type = deck.getDeck().get(i).getProgramType();
            if (!(types.contains(type))) {
                types.add(type);
            }
            if (type == ProgramType.MOVE1) {
                move1++;
            } else if (type == ProgramType.MOVE2) {
                move2++;
            } else if (type == ProgramType.MOVE3) {
                move3++;
            } else if (type == ProgramType.BACKWARD) {
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
        assertEquals(7, types.size());
        assertEquals(18, move1);
        assertEquals(12, move2);
        assertEquals(6, move3);
        assertEquals(6, backUp);
        assertEquals(18, rotateLeft);
        assertEquals(18, rotateRight);
        assertEquals(6, uTurn);
        assertEquals(0, errors);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void cannotTakeCardFromThatDontExist() {
        deck.takeCard(deck.getSizeOfDeck() + 1);
    }

    @Test
    public void giveOutCardsToAllPlayersTest() {
        Player player1 = new Player(0);
        Player player2 = new Player(1);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        assertEquals(player1.getPlayerDeck().deckSize(), 0);
        assertEquals(player2.getPlayerDeck().deckSize(), 0);
        deck.giveOutCardsToAllPlayers(players);
        assertEquals(player1.getPlayerDeck().deckSize(), 9);
        assertEquals(player2.getPlayerDeck().deckSize(), 9);

    }

    @Test
    public void giveOutCardToSinglePlayerTest() {
        Player player = new Player(0);
        assertEquals(player.getPlayerDeck().deckSize(), 0);
        deck.giveOutCardsToPlayer(player);
        assertEquals(player.getPlayerDeck().deckSize(), 9);
    }


    @Test
    public void deckTest() {
        ArrayList<ProgramCard> deck2 = deck.getDeck();
        assertEquals(deck2, deck.getDeck());
    }

    @Test
    public void standardDeckSizeTest() {
        assertEquals(deck.getSizeOfDeck(), 84);
    }

    @Test
    public void removingCardsFromDeckReducesSizeTest() {
        int i = 15;
        int newdeckSize = deck.getSizeOfDeck() - i;

        for (int j = 0; j < i; j++) {
            deck.takeTopCard();
        }

        assertEquals(deck.getSizeOfDeck(), newdeckSize);

    }

//    @Test
//    public void someCardsNotEqualTest() {
//        for (int i = 1; i < deck.getSizeOfDeck(); i++) {
//            assertNotEquals(deck.getDeck().get(0), deck.getDeck().get(i));
//        }
//        for (int j = 2; j < deck.getSizeOfDeck(); j++) {
//            assertNotEquals(deck.getDeck().get(1), deck.getDeck().get(j));
//        }
//        for (int k = 0; k < 50; k++) {
//            assertNotEquals(deck.getDeck().get(50), deck.getDeck().get(k));
//        }
//        for (int l = 51; l < deck.getSizeOfDeck(); l++) {
//            assertNotEquals(deck.getDeck().get(50), deck.getDeck().get(l));
//        }
//    }


//    @Test
//    public void changedByShuffleTest() {
//        for (int i = 0; i < 84; i++) {
//            assertEquals(deck.getDeck().get(i).getPriority(), deck2.getDeck().get(i).getPriority());
//        }
//        boolean shuffled = false;
//        deck.shuffleDeck();
//
//        for (int i = 0; i < 84; i++) {
//            if (!(deck.getDeck().get(i).getPriority() == deck2.getDeck().get(i).getPriority())) {
//                shuffled = true;
//                break;
//            }
//        }
//        assertTrue(shuffled);
//    }

    @Test
    public void uniquePrioritiesTest() {
        ArrayList<Integer> priorities = new ArrayList<Integer>();
        int pri;
        for (int i = 0; i < deck.getSizeOfDeck(); i++) {
            pri = deck.getDeck().get(i).getPriority();
            if (!(priorities.contains(pri))) {
                priorities.add(pri);
            }
        }
        assertEquals(priorities.size(), deck.getSizeOfDeck());
    }

    @Test
    public void uniqueFilenameTest() {
        ArrayList<String> filenames = new ArrayList<String>();
        String name;
        for (int i = 0; i < deck.getSizeOfDeck(); i++) {
            name = deck.getDeck().get(i).getFilename();
            if (!(filenames.contains(name))) {
                filenames.add(name);
            }
        }
        assertEquals(filenames.size(), deck.getSizeOfDeck());
    }


}
