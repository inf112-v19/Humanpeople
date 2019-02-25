package inf112.skeleton.app.Cards;

import inf112.skeleton.app.GameObjects.Directions.Rotation;

public class RotationalCards extends Card {

    private Rotation rotation;
    public RotationalCards(Rotation rotation) {
        this.rotation = rotation;
    }

    public Rotation getRotation() {
        return rotation;
    }
}
