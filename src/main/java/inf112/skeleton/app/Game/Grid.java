package inf112.skeleton.app.Game;

import com.badlogic.gdx.maps.tiled.*;
import inf112.skeleton.app.GameObjects.*;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Directions.Position;

import java.util.ArrayList;

public class Grid {

    private int width, height;
    private ArrayList gameLogicGrid[][];
    private TiledMapTileLayer groundLayer;
    private TiledMapTileLayer specialLayer;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer backupLayer;
    private TiledMapTileSet tiles;

    private ArrayList<PlayerLayerObject> listOfPlayerTilesToMove;
    private final int groundIndex;
    private final int specialIndex;
    private final int playerIndex;
    private final int flagIndex;
    private final int holeIndex;
    private final int backupIndex;

    public Grid(TiledMap map) {
        width = (Integer) map.getProperties().get("width");
        height = (Integer) map.getProperties().get("height");

        tiles = map.getTileSets().getTileSet("testTileset");
        gameLogicGrid = new ArrayList[width][height];

        groundIndex = 0;
        specialIndex = 1;
        flagIndex = 2;
        holeIndex = 3;
        backupIndex = 4;
        playerIndex = 5;

        groundLayer = (TiledMapTileLayer) map.getLayers().get(groundIndex);
        specialLayer = (TiledMapTileLayer) map.getLayers().get(specialIndex);
        flagLayer = (TiledMapTileLayer) map.getLayers().get(flagIndex);
        holeLayer = (TiledMapTileLayer) map.getLayers().get(holeIndex);
        backupLayer = (TiledMapTileLayer) map.getLayers().get(backupIndex);

        listOfPlayerTilesToMove = new ArrayList<>();
        fillGridWithArrayListsAndGameObjects();
    }


    private void fillGridWithArrayListsAndGameObjects() {
        int id;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                gameLogicGrid[x][y] = new ArrayList<GameObject>();

                //Game objects on ground layer
                id = groundLayer.getCell(x, y).getTile().getId();
                gameLogicGrid[x][y].add(groundIndex, new GroundLayerObject(id));

                // SpecialLayer
                gameLogicGrid[x][y].add(specialIndex, new NothingSpecial());

                // Flag layer
                TiledMapTileLayer.Cell flagCell = flagLayer.getCell(x,y);
                if (flagCell == null)
                    gameLogicGrid[x][y].add(flagIndex, new NothingSpecial());
                else {
                    int flagLayerId = flagCell.getTile().getId();
                    gameLogicGrid[x][y].add(flagIndex, new SpecialLayerObject(tiles, flagLayerId));
                }

                // Hole layer
                TiledMapTileLayer.Cell holeCell = holeLayer.getCell(x,y);
                if (holeCell == null)
                    gameLogicGrid[x][y].add(holeIndex, new NothingSpecial());
                else {
                    int holeLayerId = holeCell.getTile().getId();
                    gameLogicGrid[x][y].add(holeIndex, new SpecialLayerObject(tiles, holeLayerId));
                }

                // BackupLayer
                gameLogicGrid[x][y].add(backupIndex, new NothingSpecial());

                // PlayerLayer
                gameLogicGrid[x][y].add(playerIndex, new NotAPlayer());
            }
        }
    }

    public void setPlayerPosition(PlayerLayerObject playerLayerObject) {
        Position currentPosition = playerLayerObject.getPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        gameLogicGrid[x][y].remove(playerIndex);
        gameLogicGrid[x][y].add(playerIndex, playerLayerObject);
    }

    public void removePlayerPosition(Position position) {
        gameLogicGrid[position.getX()][position.getY()].remove(playerIndex);
        gameLogicGrid[position.getX()][position.getY()].add(playerIndex, new NotAPlayer());
    }

    public void setBackupPosition(PlayerLayerObject playerLayerObject) {
        Position currentPosition = playerLayerObject.getPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        TiledMapTile backupTile = playerLayerObject.getBackup().getAvatar();
        gameLogicGrid[x][y].remove(backupIndex);
        gameLogicGrid[x][y].add(backupIndex, backupTile);

    }

    public void removeBackupPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        gameLogicGrid[x][y].remove(backupIndex);
        gameLogicGrid[x][y].add(backupIndex, new NothingSpecial());
    }


    /**
     * Skjekker om det er lov å flytte i Direction fra Position, og legger
     * spillern til i en liste over spillere som skal flyttes.
     * Dersom spilleren som skal flyttes koliderer med en annen spiller skjekker man også om
     * denne spilleren kan flytte seg i samme reting,
     * dersom den kan legges denne også til listen over spillere som skal flyttes,
     * hvis ikke tømmes listen, for da har ingen lov å flytte
     *
     * @param dir
     * @param pos
     */
    public void AllowedToMoveInDirection(Direction dir, Position pos) {
        GameObject groundLayerObject = (GameObject) gameLogicGrid[pos.getX()][pos.getY()].get(groundIndex);
        if (groundLayerObject.canGo(dir) ) {
            listOfPlayerTilesToMove.add((PlayerLayerObject) gameLogicGrid[pos.getX()][pos.getY()].get(playerIndex));
            Position positionInDir;
            switch (dir) {
                case NORTH:
                    positionInDir = pos.north();
                    if (gameLogicGrid[positionInDir.getX()][positionInDir.getY()].get(playerIndex) instanceof PlayerLayerObject) {
                        AllowedToMoveInDirection(Direction.NORTH, positionInDir);
                    }
                    break;
                case EAST:
                    positionInDir = pos.east();
                    if (gameLogicGrid[positionInDir.getX()][positionInDir.getY()].get(playerIndex) instanceof PlayerLayerObject) {
                        AllowedToMoveInDirection(Direction.EAST, positionInDir);
                    }
                    break;
                case WEST:
                    positionInDir = pos.west();
                    if (gameLogicGrid[positionInDir.getX()][positionInDir.getY()].get(playerIndex) instanceof PlayerLayerObject) {
                        AllowedToMoveInDirection(Direction.WEST, positionInDir);
                    }
                    break;
                case SOUTH:
                    positionInDir = pos.south();
                    if (gameLogicGrid[positionInDir.getX()][positionInDir.getY()].get(playerIndex) instanceof PlayerLayerObject) {
                        AllowedToMoveInDirection(Direction.SOUTH, positionInDir);
                    }
                    break;
            }
        } else {
            listOfPlayerTilesToMove.clear();
            return;
        }
    }

    /**
     * @param direction
     * @param position
     * @return en liste over spillere som skal flyttes, dersom den er tom har ingen spillere lov å flytte
     */
    public ArrayList<PlayerLayerObject> numberOfPlayersToMove(Direction direction, Position position) {
        listOfPlayerTilesToMove.clear();
        AllowedToMoveInDirection(direction, position);

        return listOfPlayerTilesToMove;
    }

    /**
     * Checks if tile at given position is a backup
     * @param position
     * @return true if tile has backup
     */
    public boolean isBackup(Position position) {
        int backupId0 = 35;
        int backupId1 = 45;
        int backupId2 = 55;
        int backupId3 = 65;

        return isBackupItem(position, backupId0) || isBackupItem(position, backupId1) || isBackupItem(position, backupId2) || isBackupItem(position, backupId3);
    }

    /**
     * Checks if given backup object is located at given position
     * @param position
     * @param backupObjectId
     * @return
     */
    private boolean isBackupItem(Position position, int backupObjectId) {
        int x = position.getX();
        int y = position.getY();
        if (backupLayer.getCell(x, y) != null) {
            TiledMapTile tileAtPosition = backupLayer.getCell(x, y).getTile();
            return tileAtPosition.getId() == backupObjectId;
        }
        return false;
    }

    /**
     * Checks if tile at given position is a hole
     * @param position
     * @return
     */
    public boolean isHole(Position position) {
        int holeId = 6;
        int x = position.getX();
        int y = position.getY();
        if (holeLayer.getCell(x,y) != null) {
            TiledMapTile tileAtPosition = holeLayer.getCell(x, y).getTile();
            return tileAtPosition.getId() == holeId;
        }
        return false;
    }

    /**
     * Checks if tile at given position is a flag
     * @param position
     * @return
     */
    public boolean isFlag(Position position) {
        int flag1Id = 15;
        int flag2Id = 16;
        int flag3Id = 17;
        return isFlagItem(position, flag1Id) || isFlagItem(position, flag2Id) || isFlagItem(position, flag3Id);
    }

    /**
     * Checks if given flag object is located at given position
     * @param position
     * @param flagObjectId
     * @return
     */
    private boolean isFlagItem(Position position, int flagObjectId) {
        int x = position.getX();
        int y = position.getY();
        if (flagLayer.getCell(x,y) != null) {
            TiledMapTile tileAtPosition = flagLayer.getCell(x, y).getTile();
            return tileAtPosition.getId() == flagObjectId;
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
