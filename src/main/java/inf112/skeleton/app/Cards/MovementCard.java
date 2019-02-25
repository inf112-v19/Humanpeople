package inf112.skeleton.app.Cards;

public class MovementCard extends Card {

    private int movement;
    public MovementCard(int priority, int movement) {
        super(priority);
        if(movement < 0 && movement > 3) {
            throw new IllegalArgumentException("movement must be between 1 and 3!");
        }

        this.movement = movement;
    }

    public MovementCard(int priority,boolean backwards) {
        super(priority);
        if (backwards == true) {
            this.movement = 1;
        }
    }

    public int getMovement() {
        return movement;
    }
}




