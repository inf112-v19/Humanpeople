package inf112.skeleton.app.Cards;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProgramTypeTest {

    @Test
    public void isMoveCard() {
        assertTrue(ProgramType.MOVE1.isMoveCard());
        assertTrue(ProgramType.MOVE2.isMoveCard());
        assertTrue(ProgramType.MOVE3.isMoveCard());
        assertTrue(ProgramType.BACKWARD.isMoveCard());
        assertFalse(ProgramType.UTURN.isMoveCard());
        assertFalse(ProgramType.ROTATELEFT.isMoveCard());
        assertFalse(ProgramType.ROTATERIGHT.isMoveCard());

    }

    @Test
    public void nSteps() {
        assertEquals(ProgramType.MOVE1.nSteps(), 1);
        assertEquals(ProgramType.MOVE2.nSteps(), 2);
        assertEquals(ProgramType.MOVE3.nSteps(), 3);
        assertEquals(ProgramType.BACKWARD.nSteps(), 1);
        assertEquals(ProgramType.UTURN.nSteps(), 0);
        assertEquals(ProgramType.ROTATERIGHT.nSteps(), 0);
        assertEquals(ProgramType.ROTATELEFT.nSteps(), 0);
    }
}