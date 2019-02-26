package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;
import inf112.skeleton.app.GameObjects.PlayerLayerObject;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    private Position backup;
    private PlayerLayerObject playerTile;
    private int lifeTokens = 3;
    private int damageTokens = 9;
    private int id;
    private Direction dir;

//    public ArrayList<Cards> cardsDealt;
//    private ArrayList<Cards> cardsChosen;

    //Temporary for cards
    private ArrayList<Direction> chosen;

    public Player(TiledMapTileSet tiles, int id) {
        this.id = id;
        this.playerTile = new PlayerLayerObject(tiles, id);
        this.chosen = new ArrayList<>();

        //Temporary
        chosen.add(Direction.NORTH);
        chosen.add(Direction.SOUTH);
        chosen.add(Direction.EAST);
        chosen.add(Direction.WEST);
    }

    public int getId(){
        return id;
    }

    //Supposed to return next move from current cards
    public Direction getMove() {
        //Temporary random directions
        Random r = new Random();
        Direction dir = chosen.get(r.nextInt(4));
        System.out.println(dir);
        this.dir = dir;
        return dir;
    }

    //Updates the playerTile if move was legal
    public void update() {
        playerTile.setDirection(dir);
        switch (dir) {
            case NORTH:
                playerTile.setPosition(playerTile.getPosition().North());
                break;
            case SOUTH:
                playerTile.setPosition(playerTile.getPosition().South());
                break;
            case WEST:
                playerTile.setPosition(playerTile.getPosition().West());
                break;
            case EAST:
                playerTile.setPosition(playerTile.getPosition().East());
        }
    }

    public Position getPosition() {
        return playerTile.getPosition();
    }

    public Direction getDirection() {
        return playerTile.getDirection();
    }

    public void powerDown() {

    }

    public void setBackup(Position pos) {
        backup = pos;
    }

    public TiledMapTile getAvatar() {
        return playerTile.getAvatar();
    }
}
