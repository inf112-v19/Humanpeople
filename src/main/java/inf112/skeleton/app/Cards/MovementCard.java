package inf112.skeleton.app.Cards;

public class MovementCard extends Card {

    private int movement;
    public MovementCard(int movement) {
        this.movement = movement;
    }

    public MovementCard(int movement, boolean backwards) {
        if (backwards == true) {
            this.movement = 1;
        }
    }

    public int getMovement() {
        return movement;
    }
}




