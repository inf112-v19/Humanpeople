package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.Directions.Direction;

/**
 * Placeholder for special layer so that positions never are null
 */
public class NothingSpecial implements GameObject{
    public NothingSpecial() {
    }

    @Override
    public boolean canGo(Direction dir) {
        return false;
    }

    @Override
    public int getId() {
        return 0;
    }
}
