package inf112.skeleton.app.Game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.GameObjects.*;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Directions.Position;

import java.util.ArrayList;

public class Grid {

    private int width, height;
    private ArrayList gameLogicGrid[][];
    private TiledMapTileLayer groundLayer;
    private TiledMapTileLayer specialLayer;

    private ArrayList<PlayerLayerObject> listOfPlayerTilesToMove;
    private final int groundIndex;
    private final int specialIndex;
    private final int playerIndex;

    public Grid(TiledMap map) {
        width = (Integer) map.getProperties().get("width");
        height = (Integer) map.getProperties().get("height");

        gameLogicGrid = new ArrayList[width][height];

        groundLayer = (TiledMapTileLayer) map.getLayers().get(0);
        specialLayer = (TiledMapTileLayer) map.getLayers().get(1);
        listOfPlayerTilesToMove = new ArrayList<>();
        fillGridWithArrayListsAndGameObjects();

        groundIndex = 0;
        specialIndex = 1;
        playerIndex = 2;
    }


    private void fillGridWithArrayListsAndGameObjects() {
        int id;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                gameLogicGrid[x][y] = new ArrayList<GameObject>();

                //Game objects on ground layer
                id = groundLayer.getCell(x, y).getTile().getId();
                gameLogicGrid[x][y].add(new GroundLayerObject(id));

                gameLogicGrid[x][y].add(new NothingSpecial());
                //gameLogicGrid[x][y].add(new NothingSpecial());
                gameLogicGrid[x][y].add(new NotAPlayer());
            }
        }
    }

    public void setPlayerPosition(PlayerLayerObject playerLayerObject) {
        gameLogicGrid[playerLayerObject.getPosition().getX()][playerLayerObject.getPosition().getY()].remove(playerIndex);
        gameLogicGrid[playerLayerObject.getPosition().getX()][playerLayerObject.getPosition().getY()].add(playerIndex, playerLayerObject);
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
        gameLogicGrid[x][y].remove(specialIndex);
        gameLogicGrid[x][y].add(specialIndex, backupTile);
    }

    public void removeBackupPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        gameLogicGrid[x][y].remove(specialIndex);
        gameLogicGrid[x][y].add(specialIndex, new NothingSpecial());
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
        if (groundLayerObject.canGo(dir)) {
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
     * Checks if tile at given position is a hole
     * @param position
     * @return
     */
    public boolean isHole(Position position) {
        int holeId = 6;
        return isSpecialItem(position, holeId);
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
        return isSpecialItem(position, flag1Id) || isSpecialItem(position, flag2Id) || isSpecialItem(position, flag3Id);
    }

    private boolean isSpecialItem(Position position, int specialItemId) {
        int x = position.getX();
        int y = position.getY();
        if (specialLayer.getCell(x,y) != null) {
            TiledMapTile tileAtPosition = specialLayer.getCell(x, y).getTile();
            return tileAtPosition.getId() == specialItemId;
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
