package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameObjects.Directions.Rotation;

public class RotationalCards extends Card {

    private Rotation rotation;
    public RotationalCards(int priority, Rotation rotation) {
        super(priority);
        this.rotation = rotation;
    }

    public Rotation getRotation() {
        return rotation;
    }
}
