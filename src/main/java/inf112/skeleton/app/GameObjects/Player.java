package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;

/**
 * Player object
 * @author Stian
 *
 */
public class Player implements GameObjects {

    private final int id;

    private final TiledMapTile northAvatar;
    private final TiledMapTile southAvatar;
    private final TiledMapTile eastAvatar;
    private final TiledMapTile westAvatar;

    private TiledMapTile currentAvatar;

    private Position position;

    public Player(TiledMap map){
        id = 6;
        position = new Position(1,1);

        northAvatar = map.getTileSets().getTileSet("testTileset").getTile(31);
        westAvatar = map.getTileSets().getTileSet("testTileset").getTile(33);
        eastAvatar = map.getTileSets().getTileSet("testTileset").getTile(32);
        southAvatar = map.getTileSets().getTileSet("testTileset").getTile(34);

        currentAvatar = northAvatar;
    }

    @Override
    public boolean canGo(Direction dir) {
        return true;
    }

    @Override
    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public void move(Direction direction) {
        switch (direction) {
            case NORTH: {
                currentAvatar = northAvatar;
                position = position.North();
                break;
            }

            case SOUTH: {
                currentAvatar = southAvatar;
                position = position.South();
                break;
            }
            case WEST: {
                currentAvatar = westAvatar;
                position = position.West();
                break;
            }
            case EAST: {
                currentAvatar = eastAvatar;
                position = position.East();
                break;
            }

        }
    }


    public TiledMapTile getCurrentAvatar() {
        return currentAvatar;
    }
}
