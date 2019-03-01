package inf112.skeleton.app.Cards;

public enum ProgramType {
	MOVE1, MOVE2, MOVE3, BACKUP, ROTATELEFT, ROTATERIGHT, UTURN;

	public  boolean isMoveCard() {
		return this == MOVE1 || this == MOVE2 || this == MOVE3;
	}

	/**
	 * How many steps the program type says to take
	 * @return
	 */
	public int nSteps() {
		switch (this) {
			case MOVE1: return 1;
			case MOVE2: return 2;
			case MOVE3: return 3;
			default: return 0;
		}
	}
}
