package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import inf112.skeleton.app.Directions.Direction;

public class BackupLayerObject implements GameObject {

    private final int id;
    private final TiledMapTile avatar;

    public BackupLayerObject(TiledMapTileSet tiles, int id) {
        this.id = id;
        this.avatar = tiles.getTile(id);
    }


    @Override
    public boolean canGo(Direction dir) {
        return false;
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
