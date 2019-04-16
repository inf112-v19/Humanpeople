package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.Directions.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotAPlayerTest {
    NotAPlayer notAPlayer;
    NotAPlayer notAPlayer1;
    @Before
    public void  setup() {
         notAPlayer = new NotAPlayer();
        notAPlayer1 = new NotAPlayer();
    }
    @Test
    public void canGo() {
        assertFalse(notAPlayer.canGo(Direction.NORTH));
        assertFalse(notAPlayer.canGo(Direction.SOUTH));
        assertFalse(notAPlayer.canGo(Direction.EAST));
        assertFalse(notAPlayer.canGo(Direction.WEST));
    }

    @Test
    public void getId() {
        assertEquals(notAPlayer.getId(), 0);
    }

    @Test
    public void equals() {

        assertTrue(notAPlayer1.equals(notAPlayer));
    }

    @Test
    public void compareTo() {
        assertTrue(notAPlayer.compareTo(notAPlayer1)==0);
    }
}