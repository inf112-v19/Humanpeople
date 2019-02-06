package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.GameObjects.*;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;

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

    private GameObjects[][] xy;


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

        xy = new GameObjects[width][height];

        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);

        for(int y = 0; y<height ; y++){
            for(int x = 0; x<width ; x++){
                int id = layer.getCell(x,y).getTile().getId();
                if(id == ground.getId()){
                    xy[x][y] = ground;

                } else if(id == westWall.getId()){
                    xy[x][y] = westWall;

                }
                else if(id == southWall.getId()){
                    xy[x][y] = southWall;

                }
                else if(id == northWall.getId()){
                    xy[x][y] = northWall;

                }
                else if(id == eastWall.getId()){
                    xy[x][y] = eastWall;

                }
                else if(id == neWall.getId()){
                    xy[x][y] = neWall;

                }
                else if(id == nwWall.getId()){
                    xy[x][y] = nwWall;

                }
                else if(id == swWall.getId()){
                    xy[x][y] = swWall;

                }
                else if(id == seWall.getId()){
                    xy[x][y] = seWall;

                }
            }
        }
        if(xy[1][1].getId() == southWall.getId()){
            System.out.println("SW");
        }
    }

    public boolean AllowedToMoveInDirection(Direction direction, Position position){

        GameObjects objects = xy[position.getX()][position.getY()];

        switch (direction){
            case NORTH: return objects.moveNorthFromAllowed();

            case SOUTH: return objects.moveSouthFromAllowed();

            case WEST: return  objects.moveEastFromAllowed();

            case EAST: return objects.moveWestFromAllowed();

        }
        return false;
    }

}
