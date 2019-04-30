package inf112.skeleton.app.Round;

import inf112.skeleton.app.Cards.ProgramCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Phase {
    private ArrayList<ProgramCard> movesToPerform;
    private int movesLeftOnCurrentCard;
    private Boolean phaseComplete;

    public Phase(ArrayList<ProgramCard> movesToPerform) {
        this.movesToPerform = movesToPerform;
        Collections.sort(this.movesToPerform, Collections.reverseOrder());
        phaseComplete = false;
        movesLeftOnCurrentCard = -1;
    }

    public Boolean getPhaseComplete() {
        return phaseComplete;
    }

    /**
     * sørger for at kort som flytter flere tiles blir hentet ut riktig antall ganger
     * kortet fjernes når ikke flere bevegelser igjen på kortet
     * @return ProgramCard
     */
    public ProgramCard nextMovement(){
        ProgramCard currentCard = movesToPerform.get(0);
        if(currentCard.getProgramType().isMoveCard()){
            if(movesLeftOnCurrentCard == -1){
                movesLeftOnCurrentCard = currentCard.getProgramType().nSteps();
            }
            movesLeftOnCurrentCard--;
            if(movesLeftOnCurrentCard == 0){
                movesToPerform.remove(0);
                movesLeftOnCurrentCard = -1;
            }
        }
        else {
            movesToPerform.remove(0);
        }
        if(movesToPerform.size() == 0){
            phaseComplete = true;
        }
        return currentCard;
    }

    public void addMovementToPhase(ProgramCard programCard) {
        movesToPerform.add(programCard);
    }
}
