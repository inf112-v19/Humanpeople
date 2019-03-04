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
    private ArrayList<PlayerLayerObject> playerTiles;


    public Grid(TiledMap map) {
        width = (Integer) map.getProperties().get("width");
        height = (Integer) map.getProperties().get("height");

        gameLogicGrid = new ArrayList[width][height];

        groundLayer = (TiledMapTileLayer) map.getLayers().get(0);
        specialLayer = (TiledMapTileLayer) map.getLayers().get(1);
        playerLayer = (TiledMapTileLayer) map.getLayers().get(2);

        playerTiles = new ArrayList<PlayerLayerObject>();
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
                gameLogicGrid[x][y].add(new NotAPlayer());
                //Game objects on special layer **(idè)**
                //id = specialLayer.getCell(x, y).getTile().getId();
                //gameLogicGrid[x][y].add(new SpecialObject(id));
            }
        }
    }
    public void setPlayerPosition(PlayerLayerObject playerLayerObject){
        gameLogicGrid[playerLayerObject.getPosition().getX()][playerLayerObject.getPosition().getY()].remove(1);
        gameLogicGrid[playerLayerObject.getPosition().getX()][playerLayerObject.getPosition().getY()].add(playerLayerObject);
    }
    public void removePlayerPosition(Position position){
        gameLogicGrid[position.getX()][position.getY()].remove(1);
        gameLogicGrid[position.getX()][position.getY()].add(new NotAPlayer());
    }

    public void AllowedToMoveInDirection(Direction dir, Position pos){

        GameObject groundLayerObject = (GameObject) gameLogicGrid[pos.getX()][pos.getY()].get(0);
        if(groundLayerObject.canGo(dir)){
            playerTiles.add((PlayerLayerObject)gameLogicGrid[pos.getX()][pos.getY()].get(1));
            Position temp;
            switch (dir){
                case NORTH:
                    temp = pos.North();
                    if(gameLogicGrid[temp.getX()][temp.getY()].get(1) instanceof PlayerLayerObject){
                        AllowedToMoveInDirection(Direction.NORTH,temp);
                    }
                    break;
                case EAST:
                    temp = pos.East();
                    if(gameLogicGrid[temp.getX()][temp.getY()].get(1) instanceof PlayerLayerObject){
                        AllowedToMoveInDirection(Direction.EAST,temp);
                    }
                    break;
                case WEST:
                    temp = pos.West();
                    if(gameLogicGrid[temp.getX()][temp.getY()].get(1) instanceof PlayerLayerObject){
                        AllowedToMoveInDirection(Direction.WEST,temp);
                    }
                    break;
                case SOUTH:
                    temp = pos.South();
                    if(gameLogicGrid[temp.getX()][temp.getY()].get(1) instanceof PlayerLayerObject){
                        AllowedToMoveInDirection(Direction.SOUTH,temp);
                    }
                    break;
            }
        }
        else {
            playerTiles.clear();
        }


    }
    public ArrayList<PlayerLayerObject> numberOfPlayersToMove(Direction direction, Position position){
        playerTiles.clear();
        AllowedToMoveInDirection(direction,position);

        return playerTiles;
    }

}
