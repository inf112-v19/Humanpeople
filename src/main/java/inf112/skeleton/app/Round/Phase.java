package inf112.skeleton.app.Round;

import inf112.skeleton.app.Cards.ProgramCard;

import java.util.ArrayList;

public class Phase {
    private ArrayList<ProgramCard> movesToPreform;
    private int movesLeftOnCurrentCard;
    private Boolean phaseComplete;

    public Phase(ArrayList<ProgramCard> movesToPreform) {
        this.movesToPreform = movesToPreform;
        phaseComplete = false;
        movesLeftOnCurrentCard = -1;
    }

    public Boolean getPhaseComplete() {
        return phaseComplete;
    }
    public ProgramCard nextMovement(){
        ProgramCard currentCard = movesToPreform.get(0);
        if(currentCard.getProgramType().isMoveCard()){
            if(movesLeftOnCurrentCard == -1){
                movesLeftOnCurrentCard = currentCard.getProgramType().nSteps();
            }
            movesLeftOnCurrentCard--;
            if(movesLeftOnCurrentCard == 0){
                movesToPreform.remove(0);
                movesLeftOnCurrentCard = -1;
                if(movesToPreform.size() == 0){
                    phaseComplete = true;
                }
            }
        }
        else {
            movesToPreform.remove(0);
        }
        return currentCard;

    }
}
