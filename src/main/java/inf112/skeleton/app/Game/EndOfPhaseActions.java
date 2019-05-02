package inf112.skeleton.app.Game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Directions.Position;
import inf112.skeleton.app.GameObjects.FlagLayerObject;
import inf112.skeleton.app.GameObjects.PlayerLayerObject;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Round.Phase;

import java.util.ArrayList;
import java.util.List;

/**
 * All actions for Players at the end of a phase
 */
public class EndOfPhaseActions {

    private GameMap gameMap;
    private Grid grid;
    private List<Player> players;
    private List<Player> beltList;

    private TiledMapTileSet tiles;
    private final TiledMapTileLayer playerLayer;
    private final TiledMapTileLayer specialLayer;
    private final TiledMapTileLayer flagLayer;
    private final TiledMapTileLayer backupLayer1;
    private final TiledMapTileLayer backupLayer2;
    private final TiledMapTileLayer backupLayer3;
    private final TiledMapTileLayer backupLayer4;
    private final TiledMapTileLayer laserLayer;

    /**
     * Final flag to visit for players to win the game
     */
    private int finalFlagNumber = 1;

    public EndOfPhaseActions(GameMap gameMap, Grid grid, TiledMap map) {
        this.gameMap = gameMap;
        this.grid = grid;
        this.players = gameMap.getPlayers();
        this.beltList = new ArrayList<>();

        this.tiles = map.getTileSets().getTileSet("testTileset");
        this.playerLayer = (TiledMapTileLayer) map.getLayers().get(8);
        this.specialLayer = (TiledMapTileLayer) map.getLayers().get(1);
        this.flagLayer = (TiledMapTileLayer) map.getLayers().get(2);
        this.backupLayer1 = (TiledMapTileLayer) map.getLayers().get(4);
        this.backupLayer2 = (TiledMapTileLayer) map.getLayers().get(5);
        this.backupLayer3 = (TiledMapTileLayer) map.getLayers().get(6);
        this.backupLayer4 = (TiledMapTileLayer) map.getLayers().get(7);
        this.laserLayer = (TiledMapTileLayer) map.getLayers().get(9);
    }

    /**
     * Performs all end of phase checks in rule order
     */
    public void performAllChecks() {
        movePlayersOnConveyorBelts();
        steppedOnFlag();
        steppedOnWrench();
        hasWon();
        removeDeadPlayers();
    }

    /**
     * If player is standing on flag and another player does not have a backup there already, then set backup
     * If player is standing on a flag which it CAN visit, then update lastFlagVisited for player
     */
    public void steppedOnFlag() {
        for (Player player : players) {
            if (playerSteppedOnFlag(player)) {
                Position pos = player.getPosition();
                int flagId = flagLayer.getCell(pos.getX(), pos.getY()).getTile().getId();
                FlagLayerObject flag = new FlagLayerObject(flagId);
                if (flag.canVisit(player)) {
                    player.visitFlag();
                }
                gameMap.setBackup(player);
            }
        }
    }

    /**
     * Checks if player is stepping on a flag
     *
     * @param player
     * @return true if player is in flag
     */
    public boolean playerSteppedOnFlag(Player player) {
        Position currentPosition = player.getPosition();
        return grid.isFlag(currentPosition);
    }

    /**
     * Checks for all players if they are standing on a wrench
     */
    public void steppedOnWrench() {
        for (Player player : players) {
            steppedOnWrench(player);
        }
    }

    /**
     * Checks if player has stepped on a wrench.
     * If player stepped on wrench, then add one health to the player
     *
     * @param player
     * @return true if player is standing on a wrench tile
     */
    public boolean steppedOnWrench(Player player) {
        Position currentPosition = player.getPosition();
        if (grid.isWrench(currentPosition)) {
            player.incrementHealth();
            return true;
        }
        return false;
    }

    /**
     * Checks if any players have won, i.e. has visited all flags
     */
    public void hasWon() {
        for (Player player : players) {
            hasWon(player);
        }
    }

    /**
     * Checks if the player has won, i.e. has visited all flags
     */
    public void hasWon(Player player) {
        int lastFlagVisited = player.getLastFlagVisited();

        if (lastFlagVisited == finalFlagNumber) {
            gameMap.setWinner(player);
            //Needed to play on!
            //gameMap.getPlayers().remove(player);
        }
    }

    /**
     * If a player is alive, he is removed from the game
     */
    public void removeDeadPlayers() {
        for (Player player : players) {
            if (!player.isAlive() && !player.hasBeenRemoveFromBoard()) {
                Position playerPosition = player.getPosition();
                int x = playerPosition.getX();
                int y = playerPosition.getY();

                grid.removePlayerPosition(playerPosition);
                playerLayer.setCell(x, y, null);
                player.removeFromBoard();
            }
        }
    }

    /**
     * Moves all players standing on conveyor belts
     */
    public void movePlayersOnConveyorBelts() {
        beltList.clear();
        for (Player player : players) {
            addConveyorBeltMovement(player);
        }
        if (!beltList.isEmpty())
            moveConveyorBelts();
    }

    /**
     * Checks if player is standing on conveyor belt
     * and adds the player to the beltList for later movement
     * @param player
     */
    public void addConveyorBeltMovement(Player player) {
        ConveyorBelt conveyorBelt = steppedOnConveyorBelt(player);
        if (conveyorBelt != null) {
            player.setCurrentConveyorBelt(conveyorBelt);
            beltList.add(player);
        }
    }

    /**
     * Checks if player is standing on a conveyor belt or a gyro
     * @param player
     * @return enum of the belt/gyro the player is standing on
     * return null if not standing on belt/gyro
     */
    public ConveyorBelt steppedOnConveyorBelt(Player player) {
        Position position = player.getPosition();
        if (grid.isEastBelt(position)) {
            return ConveyorBelt.EAST;
        } else if (grid.isNorthBelt(position)) {
            return ConveyorBelt.NORTH;
        } else if (grid.isSouthBelt(position)) {
            return ConveyorBelt.SOUTH;
        }else if (grid.isWestBelt(position)) {
            return ConveyorBelt.WEST;
        }else if (grid.isLeftGyro(position)) {
            return ConveyorBelt.COUNTER_CLOCK_WISE;
        }else if (grid.isRightGyro(position)) {
            return ConveyorBelt.CLOCK_WISE;
        }else if (grid.isDoubleNorthBelt(position)) {
            return ConveyorBelt.DOUBLE_NORTH;
        }else if (grid.isDoubleEastBelt(position)) {
            return ConveyorBelt.DOUBLE_EAST;
        } else if (grid.isDoubleSouthBelt(position)) {
            return ConveyorBelt.DOUBLE_SOUTH;
        } else if (grid.isDoubleWestBelt(position)) {
            return ConveyorBelt.DOUBLE_WEST;
        } else {
            return null;
        }
    }

    public void moveConveyorBelts() {
        int i = 0;
        while (beltList.size() > 0) {
            i++;
            if (i > 16) {
                break;
            }
            Player player = beltList.get(0);
            ConveyorBelt conveyorBelt = player.getCurrentConveyorBelt();

            if (conveyorBelt == ConveyorBelt.NORTH) {
                moveBeltInDirection(Direction.NORTH, player);
            }
            else if (conveyorBelt == ConveyorBelt.WEST) {
                moveBeltInDirection(Direction.WEST, player);
            }
            else if (conveyorBelt == ConveyorBelt.SOUTH) {
                moveBeltInDirection(Direction.SOUTH, player);
            }
            else if (conveyorBelt == ConveyorBelt.EAST) {
                moveBeltInDirection(Direction.EAST, player);
            }
            else if (conveyorBelt == ConveyorBelt.DOUBLE_EAST) {
                moveBeltInDirection(Direction.EAST, player);
                moveBeltInDirection(Direction.EAST, player);
            }
            else if (conveyorBelt == ConveyorBelt.DOUBLE_WEST) {
                moveBeltInDirection(Direction.WEST, player);
                moveBeltInDirection(Direction.WEST, player);
            }
            else if (conveyorBelt == ConveyorBelt.DOUBLE_NORTH) {
                moveBeltInDirection(Direction.NORTH, player);
                moveBeltInDirection(Direction.NORTH, player);
            }
            else if (conveyorBelt == ConveyorBelt.DOUBLE_SOUTH) {
                moveBeltInDirection(Direction.SOUTH, player);
                moveBeltInDirection(Direction.SOUTH, player);
            }
            else if (conveyorBelt == ConveyorBelt.CLOCK_WISE) {
                Direction currentDirection = player.getDirection();
                Direction newDirection = Direction.rotate(currentDirection, 1);
                player.setDirection(newDirection);
                beltList.remove(0);
            }
            else if (conveyorBelt == ConveyorBelt.COUNTER_CLOCK_WISE) {
                Direction currentDirection = player.getDirection();
                Direction newDirection = Direction.rotate(currentDirection, -1);
                player.setDirection(newDirection);
                beltList.remove(0);
            }
        }
    }

    /**
     *
     * @param dir
     * @param player
     */
    public void moveBeltInDirection(Direction dir, Player player) {
        ArrayList<PlayerLayerObject> playerTiles = gameMap.getPlayerTiles();
        Position currentPosition = player.getPosition();
        if (gameMap.canGo(dir, currentPosition)) {
            if (playerTiles.size() == 1) {
                int x = currentPosition.getX();
                int y = currentPosition.getY();
                playerLayer.setCell(x, y, null);
                gameMap.movePlayerTilesInList(dir);
                player.setCurrentConveyorBelt(null);
                beltList.remove(player);
                gameMap.drawPlayer(player);

            } else {
                beltList.remove(player);
                beltList.add(player);
            }
        } else {
            beltList.remove(player);
        }
    }

}
