package inf112.skeleton.app.Player;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import inf112.skeleton.app.Cards.PlayerDeck;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Directions.Position;
import inf112.skeleton.app.GameObjects.PlayerLayerObject;

public class Player {

    public static final int MAX_HEALTH = 9;
    public static final int MAX_LIFE_TOKENS = 3;

    private Position backup;
    private PlayerLayerObject playerTile;
    private int lifeTokens = MAX_LIFE_TOKENS;
    private int health = MAX_HEALTH;
    private int id;
    private PlayerDeck playerDeck;
    private boolean isDestroyed;
    private boolean isAlive;
    private boolean active;
    private boolean handChosen;

    public Player(int id) {
        this.id = id;
        this.playerTile = new PlayerLayerObject(id);
        this.playerDeck = new PlayerDeck();
        this.backup = new Position(id, id);

        handChosen = false;
        isAlive = true;
        isDestroyed = false;
        active = true;
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
     * Restores the amount of health to be the max amount of damage tokens -2 and set isDestroyed to false
     */
    public void fix() {
        isDestroyed = false;
        health = MAX_HEALTH - 2;
    }

    public void restoreHealth() {
        health = MAX_HEALTH;
    }
    /**
     * If all health are lost then reduce lifeTokens with 1 and restore health
     * @return
     */
    public boolean lostAllHealth() {
        if (health < 1) {
           lifeTokens--;
           if (lifeTokens < 1)
                isAlive = false;
           else
                fix();
           return true;
        }
        return false;
    }

    public void revive() {
        isAlive = true;
    }

    /**
     * Removes one life token, sets isDestroyed to true and sets health = 0
     * If all life tokens are lost then set isAlive to false
     */
    public void destroy() {
        lifeTokens--;
        if (lifeTokens < 1)
            isAlive = false;
        health = 0;
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isActive() {
        return active;
    }

    public void powerDown() {
        active = false;
    }

    /**
     * A powered down player is now active
     */
    public void activate() {
        active = true;
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

        health = health - howMuchDamage;
    }

    public int getHealth() {
        return health;
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }

    public void setHandChosen(Boolean handChosen){
        this.handChosen = handChosen;
    }
    public boolean getHandChosen(){
        return handChosen;
    }


}
