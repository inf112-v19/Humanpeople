package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.Directions.Direction;

/**
 * Placeholder for player layer so that positions never are null
 */
public class NotAPlayer implements GameObject {

    private int id;

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

    @Override
    public boolean equals(GameObject obj) {
        return this.id == obj.getId();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
