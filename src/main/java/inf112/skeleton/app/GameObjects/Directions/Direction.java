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

    public static Direction getDir(int i){
        switch (i) {
            case 0: return NORTH;
            case 1: return EAST;
            case 2: return SOUTH;
            case 3: return WEST;
        }
        return NORTH;
    }

    public static int getValue(Direction dir){
        switch(dir) {
            case NORTH: return 0;
            case EAST:  return 1;
            case SOUTH: return 2;
            case WEST:  return 3;
        }
        return 0;
    }

    /**
     *  Takes current direction and an int:
     *  1 for clockwise rotation
     *  -1 for counterclockwise rotation
     *
     * @param dir
     * @param i
     * @return
     */
    public static Direction rotate(Direction dir, int i){
        int currentValue = getValue(dir) + i;

        if(currentValue > 3)
            return getDir(0);

        if(currentValue < 0)
            return getDir(3);

        return getDir(currentValue);

    }
}


