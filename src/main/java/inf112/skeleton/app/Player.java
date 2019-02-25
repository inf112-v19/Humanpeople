package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;
import inf112.skeleton.app.GameObjects.GameObjects;
import inf112.skeleton.app.GameObjects.PlayerTile;

/**
 * Player class
 */
public class Player {

    private final int FULL_HEALTH = 9;
    private final int MAX_LIFE = 3;

    private String name;
    private int health;
    private int lives;
    private PlayerDeck playerDeck;
    private PlayerTile playerTile;

    public Player(String name, PlayerTile playerTile) {
        this.name = name;
        health = FULL_HEALTH;
        lives = MAX_LIFE;
        playerDeck = new PlayerDeck();
        this.playerTile = playerTile;
    }

    public void takeDamage() {
        health--;
        if (health == 0) {
            lives--;
            health = FULL_HEALTH;
            if (lives == 0)
                System.out.println(name + " fucking died");
            else
                System.out.println(name + " returned to respawn point");
        }

    }

    public void move(Direction direction, GameMap gameMap) {
        // Get the required variables for moving the player on the gameMap
        TiledMapTileLayer playerLayer = gameMap.getPlayerLayer();
        TiledMapTileLayer.Cell playerCell = gameMap.getPlayerCell();
        Grid grid = gameMap.getGrid();

        if (grid.AllowedToMoveInDirection(direction, playerTile.getPosition())) {
            Position playerPosition = playerTile.getPosition();
            switch (direction) {
                case NORTH: {

                    playerCell.setTile(playerTile.getNorthAvatar());

                    playerLayer.setCell(playerPosition.getX(), playerPosition.getY(), null);
                    playerLayer.setCell(playerPosition.getX(), playerPosition.getY() + 1, playerCell);

                    playerTile.setPosition(playerPosition.North());
                    break;
                }

                case SOUTH: {

                    playerCell.setTile(playerTile.getSouthAvatar());

                    playerLayer.setCell(playerPosition.getX(),playerPosition.getY(),null);
                    playerLayer.setCell(playerPosition.getX(), playerPosition.getY() - 1, playerCell);

                    playerTile.setPosition(playerPosition.South());
                    break;
                }
                case WEST: {

                    playerCell.setTile(playerTile.getWestAvatar());

                    playerLayer.setCell(playerPosition.getX(), playerPosition.getY(), null);
                    playerLayer.setCell(playerPosition.getX() - 1, playerPosition.getY(), playerCell);

                    playerTile.setPosition(playerPosition.West());
                    break;
                }
                case EAST: {

                    playerCell.setTile(playerTile.getEastAvatar());

                    playerLayer.setCell(playerPosition.getX(), playerPosition.getY(), null);
                    playerLayer.setCell(playerPosition.getX() + 1, playerPosition.getY(), playerCell);

                    playerTile.setPosition(playerPosition.East());
                    break;
                }

            }
        }
        System.out.println(playerTile.getPosition().getX() + "  " + playerTile.getPosition().getY());
    }

    public PlayerTile getPlayerTile() {
        return playerTile;
    }

    public int getHealth() {
        return health;
    }

    public int getLives() {
        return lives;
    }

}
