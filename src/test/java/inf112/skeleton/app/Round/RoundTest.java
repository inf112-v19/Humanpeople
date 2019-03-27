package inf112.skeleton.app.Round;

import inf112.skeleton.app.Cards.ProgramCard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoundTest {


    Round round;
    @Before
    public void setup() {
        round = new Round();
    }

    @Test
    public void cannotAddMoreThanFivePhases() {

    }

    @Test
    public void isSet() {
        assertFalse(round.isSet());
        ArrayList<ProgramCard> programCards0 = new ArrayList<>();
        Phase phase0 = new Phase(programCards0);
        ArrayList<ProgramCard> programCards1 = new ArrayList<>();
        Phase phase1 = new Phase(programCards1);
        ArrayList<ProgramCard> programCards2 = new ArrayList<>();
        Phase phase2 = new Phase(programCards2);
        ArrayList<ProgramCard> programCards3 = new ArrayList<>();
        Phase phase3 = new Phase(programCards3);
        ArrayList<ProgramCard> programCards4 = new ArrayList<>();
        Phase phase4 = new Phase(programCards4);

        round.addPhases(phase0);
        round.addPhases(phase1);
        round.addPhases(phase2);
        round.addPhases(phase3);
        round.addPhases(phase4);
        assertTrue(round.isSet());
    }

    @Test
    public void isCompleted() {
        ArrayList<ProgramCard> programCards0 = new ArrayList<>();
        Phase phase0 = new Phase(programCards0);
        round.addPhases(phase0);
        round.addPhases(phase0);
        round.addPhases(phase0);
        round.addPhases(phase0);
        round.addPhases(phase0);

        assertFalse(round.isCompleted());
        round.nextPhase();
        round.nextPhase();
        round.nextPhase();
        round.nextPhase();
        round.nextPhase();
        assertTrue(round.isCompleted());
    }

    @Test
    public void nextPhase() {
        assertEquals(round.getCurrentPhaseNumber(), 0);
        round.nextPhase();
        round.nextPhase();
        round.nextPhase();
        assertEquals(round.getCurrentPhaseNumber(), 3);
    }

    @Test
    public void getCurrentPhase() {
        Round round = new Round();

        ArrayList<ProgramCard> programCards0 = new ArrayList<>();
        Phase phase0 = new Phase(programCards0);
        ArrayList<ProgramCard> programCards1 = new ArrayList<>();
        Phase phase1 = new Phase(programCards1);
        ArrayList<ProgramCard> programCards2 = new ArrayList<>();
        Phase phase2 = new Phase(programCards2);
        ArrayList<ProgramCard> programCards3 = new ArrayList<>();
        Phase phase3 = new Phase(programCards3);
        ArrayList<ProgramCard> programCards4 = new ArrayList<>();
        Phase phase4 = new Phase(programCards4);

        round.addPhases(phase0);
        round.addPhases(phase1);
        round.addPhases(phase2);
        round.addPhases(phase3);
        round.addPhases(phase4);
        assertEquals(round.getCurrentPhase(), phase0);
        round.nextPhase();
        assertEquals(round.getCurrentPhase(), phase1);
        round.nextPhase();
        assertEquals(round.getCurrentPhase(), phase2);
        round.nextPhase();
        assertEquals(round.getCurrentPhase(), phase3);
        round.nextPhase();
        assertEquals(round.getCurrentPhase(), phase4);
    }

    @Test
    public void getCurrentPhaseNumber() {

        assertEquals(round.getCurrentPhaseNumber(), 0);
        round.nextPhase();
        assertEquals(round.getCurrentPhaseNumber(), 1);
        round.nextPhase();
        assertEquals(round.getCurrentPhaseNumber(), 2);
        round.nextPhase();
        assertEquals(round.getCurrentPhaseNumber(), 3);
        round.nextPhase();
        assertEquals(round.getCurrentPhaseNumber(), 4);
    }
}