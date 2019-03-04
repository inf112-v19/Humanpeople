package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.GameObjects.Directions.Direction;

public class NotAPlayer implements GameObject {
    public NotAPlayer() {
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
