package inf112.skeleton.app.GameObjects.Directions;

import java.util.Random;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    public static Direction randomDirection() {
        Random r = new Random();
        int randomInteger = r.nextInt(3);
        switch (randomInteger) {
            case 0: return NORTH;
            case 1: return EAST;
            case 2: return SOUTH;
            case 3: return WEST;
        }
        return NORTH;
    }
}


