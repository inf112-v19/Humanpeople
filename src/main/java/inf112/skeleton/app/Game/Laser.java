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
 * All methods for to control laser for the game of RoboRally
 */
public class Laser {

    private GameMap gameMap;
    private Grid grid;
    private List<Player> players;

    private TiledMapTileSet tiles;
    private final TiledMapTileLayer playerLayer;
    private final TiledMapTileLayer laserLayer;
    /**
     * Variable to toggle laser. If false, then do not fire laser
     */
    private boolean laserFire = true;

    public Laser(GameMap gameMap, Grid grid, TiledMap map) {
        this.gameMap = gameMap;
        this.grid = grid;
        this.players = gameMap.getPlayers();

        this.tiles = map.getTileSets().getTileSet("testTileset");
        this.playerLayer = (TiledMapTileLayer) map.getLayers().get(8);
        this.laserLayer = (TiledMapTileLayer) map.getLayers().get(9);
    }

    public void resetHitByBoardLaser() {
        for (Player player : players)
            player.setHitByBoardLaser(false);
    }

    /**
     * Fire both player and board lasers
     */
    public void fireLasers() {
        if(!laserFire)
            return;

        for (Player player : players) {
            if (!player.isActive() || !player.isAlive() || player.isDestroyed())
                continue;
            Position startPos = player.getPosition();
            Direction dir = player.getDirection();
            int distance = getTargetDistance(startPos, dir);
            TiledMapTileLayer.Cell laserAvatar = new TiledMapTileLayer.Cell();
            laserAvatar.setTile(player.getLaserAvatar());
            drawLaser(distance, startPos, dir, laserAvatar);
        }
    }

    public void toggleLaser() {
        laserFire = !laserFire;
        System.out.println("Laserfire = " + laserFire);
    }

    /**
     * Checks if any players are hit by the board lasers.
     * Deals 1 damage if they are.
     */
    public void checkBoardLasers(int playerId) {
        Player player = players.get(playerId);
//        for (Player player : players) {
        Position pos = player.getPosition();
            /*if (grid.isLaser(pos) && player.isHitByBoardLaser() == false) {
                System.out.println("DAMAGE BOARD LASER " + player.getPlayerTile().getColor());*/
        if (grid.isLaser(pos) && !player.isHitByBoardLaser()) {
//                System.out.println("DAMAGE BOARD LASER " + player.getPlayerTile().getColor());
            player.damagePlayer(1);
            player.setHitByBoardLaser(true);
        }
    }

    /**
     * Draw laser tiles from startPos as far as distance
     *
     * @param distance the laser can travel unhindered
     * @param startPos of the laser
     * @param dir      of the laser
     */
    public void drawLaser(int distance, Position startPos, Direction dir, TiledMapTileLayer.Cell laserAvatar) {
//        System.out.println();
//        System.out.println("DISTANCE: " + distance);
//        System.out.println();
        TiledMapTileLayer.Cell laser = new TiledMapTileLayer.Cell();
        TiledMapTileLayer.Cell crossLaser = new TiledMapTileLayer.Cell();
        crossLaser.setTile(tiles.getTile(83));
        playerLayer.setCell(startPos.getX(), startPos.getY(), laserAvatar);
        switch (dir) {
            case NORTH:
                for (int i = 1; i < distance + 1; i++) {

                    laser.setTile(tiles.getTile(82));

                    if (laserLayer.getCell(startPos.getX(), startPos.getY() + i) != null &&
                            laserLayer.getCell(startPos.getX(), startPos.getY() + i).getTile().getId() == 81) {
                        laserLayer.setCell(startPos.getX(), startPos.getY() + i, crossLaser);
                        continue;
                    }

                    laserLayer.setCell(startPos.getX(), startPos.getY() + i, laser);
                }
                break;
            case SOUTH:
                for (int i = 1; i < distance + 1; i++) {
                    laser.setTile(tiles.getTile(82));

                    if (laserLayer.getCell(startPos.getX(), startPos.getY() - i) != null &&
                            laserLayer.getCell(startPos.getX(), startPos.getY() - i).getTile().getId() == 81) {
                        laserLayer.setCell(startPos.getX(), startPos.getY() - i, crossLaser);
                        continue;
                    }
                    laserLayer.setCell(startPos.getX(), startPos.getY() - i, laser);
                }
                break;
            case EAST:
                laser.setTile(tiles.getTile(81));
                for (int i = 1; i < distance + 1; i++) {

                    if (laserLayer.getCell(startPos.getX() + i, startPos.getY()) != null &&
                            laserLayer.getCell(startPos.getX() + i, startPos.getY()).getTile().getId() == 82) {
                        laserLayer.setCell(startPos.getX() + i, startPos.getY(), crossLaser);
                        continue;
                    }
                    laserLayer.setCell(startPos.getX() + i, startPos.getY(), laser);
                }
                break;
            case WEST:
                laser.setTile(tiles.getTile(81));
                for (int i = 1; i < distance + 1; i++) {

                    if (laserLayer.getCell(startPos.getX() - i, startPos.getY()) != null &&
                            laserLayer.getCell(startPos.getX() - i, startPos.getY()).getTile().getId() == 82) {
                        laserLayer.setCell(startPos.getX() - i, startPos.getY(), crossLaser);
                        continue;
                    }
                    laserLayer.setCell(startPos.getX() - i, startPos.getY(), laser);
                }
                break;
        }
    }

    /**
     * Remove laser tiles after fire. Ignore board lasers.
     */
    public void cleanLasers() {
        for (int x = 0; x < laserLayer.getWidth(); x++)
            for (int y = 0; y < laserLayer.getHeight(); y++) {

                if (grid.isBoardLaser(x, y)) {
                    TiledMapTileLayer.Cell laser = new TiledMapTileLayer.Cell();
                    laser.setTile(tiles.getTile(grid.getBoardLaserId(x, y)));
                    laserLayer.setCell(x, y, laser);
                    continue;
                }
                laserLayer.setCell(x, y, null);
            }
        gameMap.drawPlayers();
    }

    /**
     * The distance the laser has to be drawn. Returns as soon as it has found a wall or a player.
     * If player is found, deal damage.
     *
     * @param pos
     * @param dir
     * @return distance to target
     */
    public int getTargetDistance(Position pos, Direction dir) {
        int distance = 0;

        while (grid.canFire(pos, dir)) {
            switch (dir) {
                case NORTH:
                    pos = pos.north();
                    break;
                case SOUTH:
                    pos = pos.south();
                    break;
                case EAST:
                    pos = pos.east();
                    break;
                case WEST:
                    pos = pos.west();
                    break;
            }

            if (grid.hasPlayer(pos)) {
//                System.out.println("TRYING TO HIT PLAYER");
                try {
                    getPlayerFromPosition(pos).damagePlayer(1);
//                    System.out.println("PLAYER " + getPlayerFromPosition(pos).getPlayerTile().getColor() + " GOT BLASTED");
                } catch (IllegalArgumentException e) {
//                    System.out.println("THE PLAYER IS DESTROYED");
                }
                return distance;
            }
            distance++;
        }
//        System.out.println("WALL");
        return distance;
    }

    public Player getPlayerFromPosition(Position pos) {
        if (grid.hasPlayer(pos))
            for (Player player : players) {
                if (player.getPosition().equals(pos))
                    return player;
            }
        throw new IllegalArgumentException("Position must have a player");
    }

}
