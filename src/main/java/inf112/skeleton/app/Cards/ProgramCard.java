package inf112.skeleton.app.Cards;

public class ProgramCard implements ICard {
    private ProgramType programType;
    private int priority;
    private String filename;

    private int playerThatPlayedTheCard;

    private boolean marked;


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
     * helper for Graphical card selection
     */
    public boolean isMarked() {
        return marked;
    }
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public static ProgramCard move1Card() {
        return new ProgramCard(ProgramType.MOVE1, 490,
                "assets/cards/ProgramCards/Move1/move1p" + 490 + ".png");
    }

    public static ProgramCard move2Card() {
        return new ProgramCard(ProgramType.MOVE2, 670,
                "assets/cards/ProgramCards/Move2/move2p" + 670 + ".png");
    }

    public static ProgramCard move3Card() {
        return new ProgramCard(ProgramType.MOVE3, 790,
                "assets/cards/ProgramCards/Move3/move3p" + 790 + ".png");
    }

    public static ProgramCard moveLeftCard() {
        return new ProgramCard(ProgramType.ROTATELEFT, 70,
                "assets/cards/ProgramCards/rotateLeft/rotateLeftp" + 70 + ".png");
    }

    public static ProgramCard moveRightCard() {
        return new ProgramCard(ProgramType.ROTATERIGHT, 80,
                "assets/cards/ProgramCards/rotateRight/rotateRightp" + 80 + ".png");
    }

    public static ProgramCard uTurnCard() {
        return new ProgramCard(ProgramType.UTURN, 10,
                "assets/cards/ProgramCards/uTurn/uTurnp" + 10 + ".png");
    }

    public static ProgramCard moveBackCard() {
        return new ProgramCard(ProgramType.BACKWARD, 430,
                "assets/cards/ProgramCards/backUp/backUp1p" + 430 + ".png");
    }

}