package inf112.skeleton.app.Cards;

public class ProgramCard implements ICard {
	private ProgramType programType;
	private int priority;
	private String filename;
	private int playerThatPlayedTheCard;

	public ProgramCard(ProgramType programType, int priority, String filename) {
		this.programType = programType;
		this.priority = priority;
		this.filename = filename;
		playerThatPlayedTheCard = -1;
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

	public void setPlayerThatPlayedTheCard(int id){
		playerThatPlayedTheCard = id;
	}
	public int getPlayerThatPlayedTheCard(){
		if(playerThatPlayedTheCard!=-1){
			return playerThatPlayedTheCard;
		}
		throw new IllegalArgumentException("PlayerThatPlayedTheCard not set");
	}
}