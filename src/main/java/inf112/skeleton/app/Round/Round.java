package inf112.skeleton.app.Round;

import inf112.skeleton.app.Cards.ProgramCard;

import java.util.ArrayList;

public class Round {
    private final int maxPhasesInOneRound = 5;

    private ArrayList<Phase> phasesInTheRound;
    private int currentPhase;

    public Round() {
        this.phasesInTheRound = new ArrayList<>();
        currentPhase = 0;
    }
    
    public void addPhases(Phase phase){
        if(phasesInTheRound.size() < maxPhasesInOneRound){
            phasesInTheRound.add(phase);
        }
    }

    public boolean allPhasesAddedToRound(){
        return phasesInTheRound.size() == 5;
    }

    public Boolean isCompleted(){
        if(currentPhase >= maxPhasesInOneRound){
            phasesInTheRound.clear();
            return true;
        }
        return false;
    }

    public void nextPhase(){
        currentPhase++;
    }

    public Phase getCurrentPhase(){
        return phasesInTheRound.get(currentPhase);
    }

    public int getCurrentPhaseNumber(){
        return currentPhase;
    }

    public ProgramCard getNextMovementCard(){
        return getCurrentPhase().nextMovement();
    }


}

