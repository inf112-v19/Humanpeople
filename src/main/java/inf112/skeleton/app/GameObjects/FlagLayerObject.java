package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Player.Player;

public class FlagLayerObject implements GameObject {

    private int id;
    private int flagNumber;

    public FlagLayerObject(int id) {
        if (id != 15 && id != 16 && id != 17)
            throw new IllegalArgumentException("No flag with id: " + id);

        this.id = id;
        this.flagNumber = id - 14;
    }

    /**
     * Checks if the last flag the player visited was the flag just before this one
     * @param player
     * @return true if previous flag visited was this.flagNumber -1
     */
    public boolean canVisit(Player player) {
        int lastFlagVisited = player.getLastFlagVisited();
        return lastFlagVisited == flagNumber - 1;
    }

    @Override
    public boolean canGo(Direction dir) {
        return true;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(GameObject obj) {
        return id == obj.getId();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
