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
}