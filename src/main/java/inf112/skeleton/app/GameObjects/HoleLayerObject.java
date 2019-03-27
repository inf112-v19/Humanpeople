package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import inf112.skeleton.app.Directions.Direction;

public class HoleLayerObject implements GameObject {

    private final int id;
    private final TiledMapTile avatar;

    public HoleLayerObject(TiledMapTileSet tiles, int id) {
        if (id != 6)
            throw new IllegalArgumentException("HoleLayerObject can only have id 6");
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
