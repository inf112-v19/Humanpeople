package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.GameObjects.*;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;

import java.util.ArrayList;

public class Grid {
    private SouthWall southWall;
    private WestWall westWall;
    private EastWall eastWall;
    private NorthWall northWall;
    private Ground ground;
    private swWall swWall;
    private seWall seWall;
    private nwWall nwWall;
    private neWall neWall;

    private int width, height;
    
    private ArrayList gameLogicGrid[][];


    private TiledMapTileLayer groundLayer;
    private TiledMapTileLayer specialLayer;
    private TiledMapTileLayer playerLayer;


    public Grid(TiledMap map){
        swWall = new swWall();
        seWall = new seWall();
        nwWall = new nwWall();
        neWall = new neWall();
        southWall = new SouthWall();
        westWall = new WestWall();
        northWall = new NorthWall();
        eastWall = new EastWall();
        ground = new Ground();

        width = (Integer)map.getProperties().get("width");
        height = (Integer)map.getProperties().get("height");


        gameLogicGrid = new ArrayList[width][height];


        groundLayer = (TiledMapTileLayer)map.getLayers().get(0);
        specialLayer = (TiledMapTileLayer)map.getLayers().get(1);
        playerLayer = (TiledMapTileLayer)map.getLayers().get(2);


        fillGridWithArrayLists();
        fillGridWithGroundObjectsFromGroundLayer();


    }

    private void fillGridWithGroundObjectsFromGroundLayer() {
        for(int y = 0; y<height ; y++){
            for(int x = 0; x<width ; x++){
                int id = groundLayer.getCell(x,y).getTile().getId();
                if(id == ground.getId()){
                    gameLogicGrid[x][y].add(ground);


                } else if(id == westWall.getId()){

                    gameLogicGrid[x][y].add(westWall);

                }
                else if(id == southWall.getId()){

                    gameLogicGrid[x][y].add(southWall);

                }
                else if(id == northWall.getId()){

                    gameLogicGrid[x][y].add(northWall);

                }
                else if(id == eastWall.getId()){

                    gameLogicGrid[x][y].add(eastWall);

                }
                else if(id == neWall.getId()){

                    gameLogicGrid[x][y].add(neWall);

                }
                else if(id == nwWall.getId()){

                    gameLogicGrid[x][y].add(nwWall);

                }
                else if(id == swWall.getId()){

                    gameLogicGrid[x][y].add(swWall);

                }
                else if(id == seWall.getId()){

                    gameLogicGrid[x][y].add(seWall);

                }
            }
        }
    }



    private void fillGridWithArrayLists(){
        for(int y = 0; y<height ; y++){
            for(int x = 0; x<width ; x++){
                gameLogicGrid[x][y] = new ArrayList<GameObjects>();
            }
        }
    }
    public boolean AllowedToMoveInDirection(Direction direction, Position position){
        GameObjects objects = (GameObjects) gameLogicGrid[position.getX()][position.getY()].get(0);

        switch (direction){
            case NORTH: return objects.moveNorthFromAllowed();

            case SOUTH: return objects.moveSouthFromAllowed();

            case WEST: return  objects.moveWestFromAllowed();

            case EAST: return objects.moveEastFromAllowed();

        }
        return false;
    }

}
