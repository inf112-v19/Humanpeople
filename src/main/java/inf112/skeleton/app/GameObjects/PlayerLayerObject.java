package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;

/**
 * PlayerLayerObject object
 *
 * @author Stian
 */
public class PlayerLayerObject implements GameObject {

    private int id;

    private final TiledMapTile northAvatar;
    private final TiledMapTile southAvatar;
    private final TiledMapTile eastAvatar;
    private final TiledMapTile westAvatar;

    private Position pos;
    private Direction dir;

    public PlayerLayerObject(TiledMapTileSet tiles, int id) {
        this.id = id;
        pos = new Position(id, id);
        dir = Direction.NORTH;

        northAvatar = tiles.getTile(31);
        westAvatar = tiles.getTile(33);
        eastAvatar = tiles.getTile(32);
        southAvatar = tiles.getTile(34);
    }


    @Override
    public boolean canGo(Direction dir) {
        return false;
    }

    @Override
    public int getId() {
        return id;
    }

    public Position getPosition() {
        return pos;
    }

    public void setPosition(Position position) {
        this.pos = position;
    }

    public Direction getDirection() {
        return dir;
    }

    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    public TiledMapTile getAvatar() {
        switch (dir) {
            case NORTH:
                return northAvatar;

            case SOUTH:
                return southAvatar;

            case EAST:
                return eastAvatar;

            case WEST:
                return westAvatar;

        }
        return northAvatar;
    }
}
