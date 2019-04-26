package inf112.skeleton.app.Player;

import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Directions.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player;
    private int id;
    @Before
    public void setup() {
        Random r = new Random();
        id = r.nextInt();
        player = new Player(id);
    }

    @Test
    public void getId() {
        assertEquals(player.getId(), id);
    }

//    @Test
//    public void select5FirstCards() {
//        ProgramCard programCard0 = new ProgramCard(ProgramType.MOVE1, 20, "MOVE1");
//        ProgramCard programCard1 = new ProgramCard(ProgramType.MOVE2, 40, "MOVE2");
//        ProgramCard programCard2 = new ProgramCard(ProgramType.MOVE3, 60, "MOVE3");
//        ProgramCard programCard3 = new ProgramCard(ProgramType.UTURN, 80, "UTURN");
//        ProgramCard programCard4 = new ProgramCard(ProgramType.ROTATELEFT, 90, "ROTATELEFT");
//        ProgramCard programCard5 = new ProgramCard(ProgramType.MOVE1, 0, "");
//        ProgramCard programCard6 = new ProgramCard(ProgramType.MOVE1, 0, "");
//        ProgramCard programCard7 = new ProgramCard(ProgramType.MOVE1, 0, "");
//        ProgramCard programCard8 = new ProgramCard(ProgramType.MOVE1, 0, "");
//
//        ArrayList<ProgramCard> deck = new ArrayList<>();
//        deck.add(programCard0);
//        deck.add(programCard1);
//        deck.add(programCard2);
//        deck.add(programCard3);
//        deck.add(programCard4);
//        deck.add(programCard5);
//        deck.add(programCard6);
//        deck.add(programCard7);
//        deck.add(programCard8);
//
//        player.getPlayerDeck().setDeck(deck);
//        player.select5FirstCards();
//
//        assertEquals(player.getPlayerDeck().handSize(), 5);
//        assertEquals(programCard4, player.getPlayerDeck().getCardFromHand());
//        assertEquals(programCard3, player.getPlayerDeck().getCardFromHand());
//        assertEquals(programCard2, player.getPlayerDeck().getCardFromHand());
//        assertEquals(programCard1, player.getPlayerDeck().getCardFromHand());
//        assertEquals(programCard0, player.getPlayerDeck().getCardFromHand());
//    }

    @Test
    public void restoreDamageTokens() {
        assertEquals(player.getHealth(), 9);
        player.damagePlayer(4);
        assertEquals(player.getHealth(), 5);
        player.fix();
        assertEquals(player.getHealth(), 9);
    }

    @Test
    public void damagePlayerTest() {
        assertEquals(player.getHealth(), 9);
        player.damagePlayer(2);
        assertEquals(player.getHealth(), 7);
        player.damagePlayer(3);
        assertEquals(player.getHealth(), 4);
        player.damagePlayer(4);
        assertEquals(player.getHealth(), 0);
    }

    @Test
    public void lostAllDamageTokens() {
        assertFalse(player.lostAllHealth());
        player.damagePlayer(9);
        assertTrue(player.lostAllHealth());
    }

    @Test
    public void canPlayerDieFromDamaging() {
        Player player1 = new Player(2);
        assertTrue(player1.isAlive());

        player1.damagePlayer(9);
        player1.lostAllHealth();

        player1.damagePlayer(9);
        player1.lostAllHealth();

        player1.damagePlayer(9);
        player1.lostAllHealth();

        assertFalse(player1.isAlive());
    }

    @Test (expected = IllegalArgumentException.class)
    public void cannotDamagePlayerWithLessThanOneDamage() {
        player.damagePlayer(-4);
    }

    @Test
    public void getDirection() {
        assertNotEquals(player.getDirection(), Direction.SOUTH);
        player.getPlayerTile().setDirection(Direction.SOUTH);
        assertEquals(player.getDirection(), Direction.SOUTH);
    }


    @Test
    public void setAndGetBackup() {
        Position p = new Position(3, 5);
        player.setBackup(p);
        assertEquals(player.getBackup(), p);
    }

    @Test
    public void setAndGetPosition() {
        Position p = new Position(10, 3);
        player.setPosition(p);
        assertEquals(player.getPosition(), p);
    }

}
