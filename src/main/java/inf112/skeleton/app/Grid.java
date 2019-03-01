package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.GameObjects.*;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;

import java.util.ArrayList;

public class Grid {

    private int width, height;
    private ArrayList gameLogicGrid[][];
    private TiledMapTileLayer groundLayer;
    private TiledMapTileLayer specialLayer;
    private TiledMapTileLayer playerLayer;


    public Grid(TiledMap map) {
        width = (Integer) map.getProperties().get("width");
        height = (Integer) map.getProperties().get("height");

        gameLogicGrid = new ArrayList[width][height];

        groundLayer = (TiledMapTileLayer) map.getLayers().get(0);
        specialLayer = (TiledMapTileLayer) map.getLayers().get(1);
        playerLayer = (TiledMapTileLayer) map.getLayers().get(2);

        fillGridWithArrayListsAndGameObjects();
    }


    private void fillGridWithArrayListsAndGameObjects() {
        int id;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //ArrayLists
                gameLogicGrid[x][y] = new ArrayList<GameObject>();

                //Game objects on ground layer
                id = groundLayer.getCell(x, y).getTile().getId();
                gameLogicGrid[x][y].add(new GroundLayerObject(id));

                //Game objects on special layer **(idè)**
                //id = specialLayer.getCell(x, y).getTile().getId();
                //gameLogicGrid[x][y].add(new SpecialObject(id));
            }
        }
    }
    public void setPlayerPosition(PlayerLayerObject playerLayerObject){
        gameLogicGrid[playerLayerObject.getPosition().getX()][playerLayerObject.getPosition().getY()].add(playerLayerObject);
    }
    public void removePlayerPosition(Position position){
        gameLogicGrid[position.getX()][position.getY()].remove(1);
    }
    public boolean AllowedToMoveInDirection(Direction dir, Position pos){
        GameObject groundLayerObject = (GameObject) gameLogicGrid[pos.getX()][pos.getY()].get(0);
        switch (dir){
            case NORTH:
                Position temp = pos.North();
                if (gameLogicGrid[temp.getX()][temp.getY()].size()>1)
                    return AllowedToMoveInDirection(dir,temp);
                break;
            case EAST:
                break;
            case WEST:
                break;
            case SOUTH:
                break;
        }
        return groundLayerObject.canGo(dir);
    }
    public boolean containsPlayer(Position position){
        if(gameLogicGrid[position.getX()][position.getY()].size()>1){
            return true;
        }
        return false;
    }
}
