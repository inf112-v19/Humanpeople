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
    private TiledMapTileLayer backupLayer1;
    private TiledMapTileLayer backupLayer2;
    private TiledMapTileLayer backupLayer3;
    private TiledMapTileLayer backupLayer4;
    private TiledMapTileLayer laserLayer;
    private TiledMapTileSet tiles;

    private ArrayList<PlayerLayerObject> listOfPlayerTilesToMove;
    private final int groundIndex;
    private final int specialIndex;
    private final int playerIndex;
    private final int flagIndex;
    private final int holeIndex;
    private final int backup1Index;
    private final int backup2Index;
    private final int backup3Index;
    private final int backup4Index;
    private final int laserIndex;


    public Grid(TiledMap map) {
        width = (Integer) map.getProperties().get("width");
        height = (Integer) map.getProperties().get("height");

        tiles = map.getTileSets().getTileSet("testTileset");
        gameLogicGrid = new ArrayList[width][height];

        groundIndex = 0;
        specialIndex = 1;
        flagIndex = 2;
        holeIndex = 3;
        backup1Index = 4;
        backup2Index = 5;
        backup3Index = 6;
        backup4Index = 7;
        playerIndex = 8;
        laserIndex = 9;

        groundLayer = (TiledMapTileLayer) map.getLayers().get(groundIndex);
        specialLayer = (TiledMapTileLayer) map.getLayers().get(specialIndex);
        flagLayer = (TiledMapTileLayer) map.getLayers().get(flagIndex);
        holeLayer = (TiledMapTileLayer) map.getLayers().get(holeIndex);
        backupLayer1 = (TiledMapTileLayer) map.getLayers().get(backup1Index);
        backupLayer2 = (TiledMapTileLayer) map.getLayers().get(backup2Index);
        backupLayer3 = (TiledMapTileLayer) map.getLayers().get(backup3Index);
        backupLayer4 = (TiledMapTileLayer) map.getLayers().get(backup4Index);
        laserLayer = (TiledMapTileLayer) map.getLayers().get(laserIndex);

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

                // BackupLayer 1
                gameLogicGrid[x][y].add(backup1Index, new NothingSpecial());

                // BackupLayer 2
                gameLogicGrid[x][y].add(backup2Index, new NothingSpecial());

                // BackupLayer 3
                gameLogicGrid[x][y].add(backup3Index, new NothingSpecial());

                // BackupLayer 4
                gameLogicGrid[x][y].add(backup4Index, new NothingSpecial());

                // PlayerLayer
                gameLogicGrid[x][y].add(playerIndex, new NotAPlayer());

                // Laser layer
                gameLogicGrid[x][y].add(laserIndex, new NothingSpecial());
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

    public void setBackupPosition(PlayerLayerObject player) {
        int playerId = player.getId();
        Position currentPosition = player.getPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        TiledMapTile backupTile = player.getBackup().getAvatar();
        int backupLayerIndex = backup1Index + playerId;

        gameLogicGrid[x][y].remove(backupLayerIndex);
        gameLogicGrid[x][y].add(backupLayerIndex, backupTile);
    }

    public void removeBackupPosition(Position position, int playerId) {
        int x = position.getX();
        int y = position.getY();
        int backupLayerIndex = backup1Index + playerId;
        gameLogicGrid[x][y].remove(backup1Index);
        gameLogicGrid[x][y].add(backup1Index, new NothingSpecial());
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
        if (backupLayer1.getCell(x, y) != null) {
            TiledMapTile tileAtPosition = backupLayer1.getCell(x, y).getTile();
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

    public boolean isNorthBelt(Position position) {
        int northBeltId1 = 21;
        return isConveyorBelt(position, northBeltId1);
    }

    public boolean isEastBelt(Position position) {
        int eastBeltId1 = 24;
        int eastBeltId2 = 25;
        return isConveyorBelt(position, eastBeltId1) || isConveyorBelt(position, eastBeltId2);
    }

    public boolean isSouthBelt(Position position) {
        int southBeltId1 = 22;
        int southBeltId2 = 28;
        int southBeltId3 = 27;
        return isConveyorBelt(position, southBeltId1) || isConveyorBelt(position, southBeltId2) || isConveyorBelt(position, southBeltId3);
    }

    public boolean isWestBelt(Position position) {
        int westBeltId1 = 23;
        int westBeltId2 = 26;
        return isConveyorBelt(position, westBeltId1) || isConveyorBelt(position, westBeltId2);
    }

    public boolean isRightGyro(Position position) {
        int rightGyroId = 71;
        return isConveyorBelt(position, rightGyroId);
    }

    public boolean isLeftGyro(Position position) {
        int leftGyroId = 72;
        return isConveyorBelt(position, leftGyroId);
    }

    public boolean isDoubleWestBelt(Position position) {
        int doubleWestBeltId = 30;
        return isConveyorBelt(position, doubleWestBeltId);
    }

    public boolean isDoubleEastBelt(Position position) {
        int doubleEastBeltId = 29;
        return isConveyorBelt(position, doubleEastBeltId);
    }

    public boolean isDoubleSouthBelt(Position position) {
        int doubleSouthBeltId = 19;
        return isConveyorBelt(position, doubleSouthBeltId);
    }

    public boolean isDoubleNorthBelt(Position position) {
        int doubleNorthBeltId = 18;
        return isConveyorBelt(position, doubleNorthBeltId);
    }

    public boolean isWrench(Position position) {
        int wrenchTileId = 73;
        int x = position.getX();
        int y = position.getY();
        if (specialLayer.getCell(x,y) != null) {
            TiledMapTile tileAtPosition = specialLayer.getCell(x, y).getTile();
            return tileAtPosition.getId() == wrenchTileId;
        }
        return false;
    }

    /**
     *
     * @param position
     * @param conveyorId
     * @return true if the given conveyor belt id matches the tile at the given position
     */
    public boolean isConveyorBelt(Position position, int conveyorId) {
        int x = position.getX();
        int y = position.getY();
        if (specialLayer.getCell(x,y) != null) {
            TiledMapTile tileAtPosition = specialLayer.getCell(x, y).getTile();
            return tileAtPosition.getId() == conveyorId;
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
