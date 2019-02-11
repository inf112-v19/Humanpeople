package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import inf112.skeleton.app.GameObjects.Directions.Position;
import inf112.skeleton.app.Screen.PlayScreen;

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

    private Position position;

    public Player(TiledMap map){
        id = 6;
        position = new Position(1,1);

        northAvatar = map.getTileSets().getTileSet("testTileset").getTile(31);
        westAvatar = map.getTileSets().getTileSet("testTileset").getTile(33);
        eastAvatar = map.getTileSets().getTileSet("testTileset").getTile(32);
        southAvatar = map.getTileSets().getTileSet("testTileset").getTile(34);
    }
    @Override
    public boolean moveNorthFromAllowed() {
        return false;
    }

    @Override
    public boolean moveSouthFromAllowed() {
        return false;
    }

    @Override
    public boolean moveEastFromAllowed() {
        return false;
    }

    @Override
    public boolean moveWestFromAllowed() {
        return false;
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


    public TiledMapTile getNorthAvatar() {
        return northAvatar;
    }

    public TiledMapTile getSouthAvatar() {
        return southAvatar;
    }

    public TiledMapTile getEastAvatar() {
        return eastAvatar;
    }

    public TiledMapTile getWestAvatar() {
        return westAvatar;
    }
}
