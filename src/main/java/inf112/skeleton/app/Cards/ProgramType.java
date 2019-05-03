package inf112.skeleton.app.Cards;

public enum ProgramType {
	MOVE1, MOVE2, MOVE3, BACKWARD, ROTATELEFT, ROTATERIGHT, UTURN;

	public  boolean isMoveCard() {
		return this == MOVE1 || this == MOVE2 || this == MOVE3 || this == BACKWARD;
	}

	public int nSteps() {
		switch (this) {
			case MOVE1: return 1;
			case MOVE2: return 2;
			case MOVE3: return 3;
			case BACKWARD: return 1;
			default: return 0;
		}
	}
}
