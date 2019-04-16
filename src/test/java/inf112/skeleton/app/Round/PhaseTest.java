package inf112.skeleton.app.Round;

import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PhaseTest {

    Phase phase;
    @Before
    public void setup() {
        ArrayList<ProgramCard> programCards = new ArrayList<>();
        programCards.add(new ProgramCard(ProgramType.MOVE1, 620, ""));
        programCards.add(new ProgramCard(ProgramType.MOVE2, 300, ""));
        programCards.add(new ProgramCard(ProgramType.MOVE3, 200, ""));
        programCards.add(new ProgramCard(ProgramType.UTURN, 100, ""));
        programCards.add(new ProgramCard(ProgramType.MOVE2, 30, ""));
        phase = new Phase(programCards);
    }
    @Test
    public void getPhaseComplete() {
        assertFalse(phase.getPhaseComplete());

        phase.nextMovement();
        assertFalse(phase.getPhaseComplete());

        phase.nextMovement();
        phase.nextMovement();
        assertFalse(phase.getPhaseComplete());

        phase.nextMovement();
        phase.nextMovement();
        phase.nextMovement();
        assertFalse(phase.getPhaseComplete());

        phase.nextMovement();
        assertFalse(phase.getPhaseComplete());

        phase.nextMovement();
        phase.nextMovement();
        assertTrue(phase.getPhaseComplete());
    }

    @Test
    public void nextMovement() {
        assertEquals(phase.nextMovement(), new ProgramCard(ProgramType.MOVE1, 620, ""));
        assertEquals(phase.nextMovement(), new ProgramCard(ProgramType.MOVE2, 300, ""));
        phase.nextMovement();
        assertEquals(phase.nextMovement(), new ProgramCard(ProgramType.MOVE3, 200, ""));
        phase.nextMovement();
        phase.nextMovement();
        assertEquals(phase.nextMovement(), new ProgramCard(ProgramType.UTURN, 100, ""));

        assertEquals(phase.nextMovement(), new ProgramCard(ProgramType.MOVE2, 30, ""));
    }
}