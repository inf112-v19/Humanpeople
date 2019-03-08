package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import inf112.skeleton.app.Cards.PlayerDeck;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;
import inf112.skeleton.app.GameObjects.PlayerLayerObject;

import java.util.ArrayList;
import java.util.Random;

import static inf112.skeleton.app.GameObjects.Directions.Direction.NORTH;

public class Player {

    public static final int MAX_DAMAGE_TOKENS = 9;
    public static final int MAX_LIFE_TOKENS = 3;

    private Position backup;
    private PlayerLayerObject playerTile;
    private int lifeTokens = MAX_LIFE_TOKENS;
    private int damageTokens = MAX_DAMAGE_TOKENS;
    private int id;
    private Direction dir;
    private PlayerDeck playerDeck;

//    public ArrayList<Cards> cardsDealt;
//    private ArrayList<Cards> cardsChosen;

    //Temporary for cards
    private ArrayList<Direction> chosen;

    public Player(TiledMapTileSet tiles, int id) {
        this.id = id;
        this.playerTile = new PlayerLayerObject(tiles, id);
        this.playerDeck = new PlayerDeck();
        this.chosen = new ArrayList<>();

        //Temporary
        chosen.add(NORTH);
        chosen.add(Direction.SOUTH);
        chosen.add(Direction.EAST);
        chosen.add(Direction.WEST);

        this.dir = Direction.randomDirection();
    }

    public int getId() {
        return id;
    }

    //Supposed to return next move from current cards
    public ProgramType getMove() {
        //Temporary random directions
        /*Random r = new Random();
        Direction dir = chosen.get(r.nextInt(4));
        System.out.println(dir);
        this.dir = dir;
        */
        ProgramCard card = playerDeck.getCardFromHand();
        ProgramType programType = card.getProgramType();
        Direction newDir = getDirectionFromCard(card);
        dir = newDir;
        playerTile.setDirection(newDir);
        return programType;
    }

    /**
     * Get the direction from program card based on the players current direction
     *
     * @return dir
     */
    private Direction getDirectionFromCard(ProgramCard card) {
        ProgramType programType = card.getProgramType();
        switch (dir) {
            case NORTH:
                switch (programType) {
                    case ROTATERIGHT:
                        return Direction.EAST;
                    case ROTATELEFT:
                        return Direction.WEST;
                    case UTURN:
                        return Direction.SOUTH;
                    default:
                        return dir;
                }
            case EAST:
                switch (programType) {
                    case ROTATERIGHT:
                        return Direction.SOUTH;
                    case ROTATELEFT:
                        return NORTH;
                    case UTURN:
                        return Direction.WEST;
                    default:
                        return dir;
                }
            case SOUTH:
                switch (programType) {
                    case ROTATERIGHT:
                        return Direction.WEST;
                    case ROTATELEFT:
                        return Direction.EAST;
                    case UTURN:
                        return NORTH;
                    default:
                        return dir;
                }
            case WEST:
                switch (programType) {
                    case ROTATERIGHT:
                        return NORTH;
                    case ROTATELEFT:
                        return Direction.SOUTH;
                    case UTURN:
                        return Direction.EAST;
                    default:
                        return dir;
                }
        }
        return dir;
    }

    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    public void movePlayerInDirection(Direction direction){
        playerTile.moveTileInDirection(direction);
    }

    /**
     * Select the 5 first cards form player deck
     */
    public void select5FirstCards() {
        for (int i = 4; i >= 0; i--) {
            playerDeck.selectCardForHand(i);
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
    public PlayerLayerObject getPlayerTile(){
        return playerTile;
    }

    public void setBackup(Position pos) {
        backup = pos;
    }

    public TiledMapTile getAvatar() {
        return playerTile.getAvatar();
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }
}
