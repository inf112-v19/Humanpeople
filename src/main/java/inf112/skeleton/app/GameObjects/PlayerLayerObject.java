package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Directions.Position;

/**
 * PlayerLayerObject object
 *
 * @author Stian
 */
public class PlayerLayerObject implements GameObject {

    private int id;

    private TiledMapTileSet tiles;
    private  TiledMapTile northAvatar;
    private  TiledMapTile southAvatar;
    private  TiledMapTile eastAvatar;
    private  TiledMapTile westAvatar;

    private SpecialLayerObject backup;

    private String color;
    private Position pos;
    private Direction dir;


    public PlayerLayerObject(int id) {
        this.id = id;
        pos = new Position(id, id);
        dir = Direction.NORTH;
<<<<<<< HEAD


        int tileId = (id*10)+30;
        northAvatar = tiles.getTile(tileId+1);
        eastAvatar = tiles.getTile(tileId+2);
        westAvatar = tiles.getTile(tileId+3);
        southAvatar = tiles.getTile(tileId+4);

=======
>>>>>>> master
        switch(id){
            case 0 : color = "Green"; break;
            case 1: color = "Dark blue"; break;
            case 2: color = "Light blue"; break;
            case 3: color = "Yellow"; break;
            default: color = "none"; break;
        }
    }

    public void setSprite(TiledMapTileSet tiles) {
        this.tiles = tiles;
        int tileId = (id*10)+30;
        northAvatar = tiles.getTile(tileId+1);
        eastAvatar = tiles.getTile(tileId+2);
        westAvatar = tiles.getTile(tileId+3);
        southAvatar = tiles.getTile(tileId+4);
        backup = new SpecialLayerObject(tiles, tileId+5);
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
        return this.id == obj.getId();
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

    public SpecialLayerObject getBackup() {
        return backup;
    }

    public void moveTileInDirection(Direction direction){

        switch (direction) {
            case NORTH:
                setPosition(getPosition().north());
                break;
            case SOUTH:
                setPosition(getPosition().south());
                break;
            case WEST:
                setPosition(getPosition().west());
                break;
            case EAST:
                setPosition(getPosition().east());
        }
    }

    public String getColor(){
        return color;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}


