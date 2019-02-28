package inf112.skeleton.app.Cards;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ProgramCard {
	private ProgramType programType;
	private int priority;
	private String filename;

	public ProgramCard(ProgramType programType, int priority, String filename) {
		this.programType = programType;
		this.priority = priority;
		this.filename = filename;
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
}