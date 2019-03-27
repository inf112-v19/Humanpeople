package inf112.skeleton.app.Player;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import inf112.skeleton.app.Cards.PlayerDeck;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Directions.Position;
import inf112.skeleton.app.GameObjects.PlayerLayerObject;

public class Player {

    public static final int MAX_DAMAGE_TOKENS = 9;
    public static final int MAX_LIFE_TOKENS = 3;

    private Position backup;
    private PlayerLayerObject playerTile;
    private int lifeTokens = MAX_LIFE_TOKENS;
    private int damageTokens = MAX_DAMAGE_TOKENS;
    private int id;
    private PlayerDeck playerDeck;
    private boolean isAlive = true;


    public Player(TiledMapTileSet tiles, int id) {
        this.id = id;
        this.playerTile = new PlayerLayerObject(tiles, id);
        this.playerDeck = new PlayerDeck();
        this.backup = new Position(id, id);
    }

    public int getId() {
        return id;
    }

    /**
     * Select the 5 first cards form player deck
     */
    public void select5FirstCards() {
        for (int i = 4; i >= 0; i--)
            playerDeck.selectCardForHand(i);
    }

    /**
     * Restores the amount of damageTokens to be the max amount of damage tokens
     */
    public void restoreDamageTokens() {
        isAlive = true;
        damageTokens = MAX_DAMAGE_TOKENS;
    }

    /**
     * If all damageTokens are lost then reduce lifeTokens with 1 and restore damageTokens
     * @return
     */
    public boolean lostAllDamageTokens() {
        if (damageTokens < 1) {
           lifeTokens--; // TODO Check if lifeTokes == 0 before doing anyting
            isAlive = false;
           restoreDamageTokens();
           return true;
        }
        return false;
    }

    public void revive() {
        isAlive = true;
    }

    public void kill() {
        lifeTokens--;
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Position getPosition() {
        return playerTile.getPosition();
    }

    public void setPosition(Position position) {
        playerTile.setPosition(position);
    }

    public Direction getDirection() {
        return playerTile.getDirection();
    }

    public PlayerLayerObject getPlayerTile() {
        return playerTile;
    }

    public Position getBackup() {
        return backup;
    }

    public void setBackup(Position pos) {
        backup = pos;
    }

    public TiledMapTile getAvatar() {
        return playerTile.getAvatar();
    }

    public TiledMapTile getBackupAvatar() {
        return playerTile.getBackup().getAvatar();
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }

}
