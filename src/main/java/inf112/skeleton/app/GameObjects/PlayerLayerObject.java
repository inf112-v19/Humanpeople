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


    private String color;
    private Position pos;
    private Direction dir;


    public PlayerLayerObject(TiledMapTileSet tiles, int id) {
        this.id = id;
        pos = new Position(id, id);
        dir = Direction.NORTH;


        int tileId = (id*10)+30;
        northAvatar = tiles.getTile(tileId+1);
        eastAvatar = tiles.getTile(tileId+2);
        westAvatar = tiles.getTile(tileId+3);
        southAvatar = tiles.getTile(tileId+4);

        switch(id){
            case 0 : color = "Green"; break;
            case 1: color = "Dark blue"; break;
            case 2: color = "Light blue"; break;
            case 3: color = "Yellow"; break;
            default: color = "none"; break;
        }
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

    public void update(Direction direction){
        setDirection(direction);
        moveTileInDirection(direction);
    }
    public void moveTileInDirection(Direction direction){

        switch (direction) {
            case NORTH:
                setPosition(getPosition().North());
                break;
            case SOUTH:
                setPosition(getPosition().South());
                break;
            case WEST:
                setPosition(getPosition().West());
                break;
            case EAST:
                setPosition(getPosition().East());
        }
    }

    public String getColor(){
        return color;
    }
}


