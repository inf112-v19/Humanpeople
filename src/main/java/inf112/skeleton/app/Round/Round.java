package inf112.skeleton.app.Round;

import inf112.skeleton.app.Exception.TooManyPhasesException;

import java.util.ArrayList;

public class Round {
    private final int maxPhasesInOneRound = 5;

    private ArrayList<Phase> phasesInTheRound;
    private Boolean roundComplete;


    private int currentPhase;
    public Round() {
        this.phasesInTheRound = new ArrayList<>();

        roundComplete = false;
        currentPhase = 0;
    }
    public void addPhases(Phase phase){
        if(phasesInTheRound.size() < maxPhasesInOneRound){
            phasesInTheRound.add(phase);
        }
        else {
            //TODO
            //            throw new TooManyPhasesException("Trying to add more than 5 phases");
        }
    }
    public Boolean roundSet(){
        if(phasesInTheRound.size() == 5){
            return true;
        }
        return false;
    }

}

