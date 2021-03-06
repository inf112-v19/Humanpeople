package inf112.skeleton.app.Cards;

public class ProgramCard implements ICard {
    private ProgramType programType;
    private int priority;
    private String filename;
    private int playerThatPlayedTheCard;
    private boolean marked;

    //Need empty constructor for it to be serializable. Used for networking
    public ProgramCard() {

    }

    public ProgramCard(ProgramType programType, int priority, String filename) {
        this.programType = programType;
        this.priority = priority;
        this.filename = filename;
        playerThatPlayedTheCard = -1;
        this.marked = false;
    }

    public ProgramType getProgramType() {
        return this.programType;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getFilename() {
        return this.filename;
    }


    public void setPlayerThatPlayedTheCard(int id) {
        playerThatPlayedTheCard = id;
    }

    public int getPlayerThatPlayedTheCard() {
        if (playerThatPlayedTheCard != -1) {
            return playerThatPlayedTheCard;
        }
        throw new IllegalArgumentException("PlayerThatPlayedTheCard not set");
    }

    @Override
    public boolean equals(Object obj) {
        return programType == ((ProgramCard) obj).getProgramType() && priority == ((ProgramCard) obj).getPriority();
    }

    @Override
    public int compareTo(Object obj) {
        if (priority > ((ProgramCard) obj).getPriority()) {
            return 1;
        }
        if (priority < ((ProgramCard) obj).getPriority()) {
            return -1;
        }
        return 0;
    }

    /**
     * Helper for graphical card selection
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * Helper for graphical card selection
     */
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        String string = "Type : " + programType + ", Priority:" + priority;
        return string;
    }
}