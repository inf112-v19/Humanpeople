package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import inf112.skeleton.app.Directions.Direction;

public class SpecialLayerObject implements GameObject {

    private int id;
    private final TiledMapTile avatar;

    public SpecialLayerObject(TiledMapTileSet tiles, int id) {
        this.id = id;
        this.avatar = tiles.getTile(id);

        switch (id) {
            case 6: // hole
                break;
            case 15: // flag 1
                break;
            case 16: // flag 2
                break;
            case 17: // flag 3
                break;
            case 35: // backup 0
                break;
            case 45: // backup 1
                break;
            case 55: // backup 2
                break;
            case 65: // backup 3
                break;
            default: System.out.println("Not an implemented specialLayerObject");
        }
    }

    public TiledMapTile getAvatar() {
        return avatar;
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
