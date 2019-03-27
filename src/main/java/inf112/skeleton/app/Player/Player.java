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
    private boolean isDestroyed;
    private boolean isAlive;
    private boolean handChosen;

    public Player(int id) {
        this.id = id;
        this.playerTile = new PlayerLayerObject(id);
        this.playerDeck = new PlayerDeck();
        this.backup = new Position(id, id);

        handChosen = false;
        isAlive = true;
        isDestroyed = false;
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
        isDestroyed = false;
        damageTokens = MAX_DAMAGE_TOKENS;
    }

    /**
     * If all damageTokens are lost then reduce lifeTokens with 1 and restore damageTokens
     * @return
     */
    public boolean lostAllDamageTokens() {
        if (damageTokens < 1) {
           lifeTokens--;
           if (lifeTokens < 1)
                isAlive = false;
           else
                restoreDamageTokens();
           return true;
        }
        return false;
    }

    public void revive() {
        isDestroyed = false;
    }

    public void destroy() {
        lifeTokens--;
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
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

    public void damagePlayer(int howMuchDamage) {
        if(howMuchDamage < 1)
            throw new IllegalArgumentException("Damage much be greater than 0");

        damageTokens = damageTokens - howMuchDamage;
    }

    public int getDamageTokens() {
        return damageTokens;
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }

    public void setHandChosen(Boolean handChosen){
        this.handChosen = handChosen;
    }
    public boolean getHandChoosen(){
        return handChosen;
    }


}
