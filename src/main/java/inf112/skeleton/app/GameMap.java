package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;
import inf112.skeleton.app.GameObjects.Player;

public class GameMap {


    private TmxMapLoader mapLoader;
    private TiledMap map;

    private Grid grid;
    private Player player;

    private final TiledMapTileLayer playerLayer;
    private TiledMapTileLayer.Cell playerCell;

    public GameMap(String name){
         mapLoader = new TmxMapLoader();
         map = mapLoader.load(name);
         grid = new Grid(map);

         player = new Player(map);
         playerCell = new TiledMapTileLayer.Cell();
         playerLayer = (TiledMapTileLayer)map.getLayers().get(2);

         initPlayer(player);

    }
    public void initPlayer(Player player){
        playerCell.setTile(player.getNorthAvatar());
        playerLayer.setCell(getPlayer().getPosition().getX(),getPlayer().getPosition().getY(),playerCell);
    }



    /*public boolean AllowedToMove(Direction direction){
        return grid.AllowedToMoveInDirection(direction,player.getPosition());
    }*/

    public void movePlayer(Direction direction){
        if(grid.AllowedToMoveInDirection(direction,player.getPosition())){
            Position playerPosition = player.getPosition();
            switch (direction){
                case NORTH: {

                    playerCell.setTile(player.getNorthAvatar());

                    playerLayer.setCell(playerPosition.getX(),playerPosition.getY(),null);
                    playerLayer.setCell(playerPosition.getX(),playerPosition.getY()+1, playerCell);

                    player.setPosition(playerPosition.North());
                    break;
                }

                case SOUTH:
                {

                    playerCell.setTile(player.getSouthAvatar());

                    playerLayer.setCell(playerPosition.getX(),playerPosition.getY(),null);
                    playerLayer.setCell(playerPosition.getX(),playerPosition.getY()-1, playerCell);

                    player.setPosition(playerPosition.South());
                    break;
                }
                case WEST:
                {

                    playerCell.setTile(player.getWestAvatar());

                    playerLayer.setCell(playerPosition.getX(),playerPosition.getY(),null);
                    playerLayer.setCell(playerPosition.getX()-1,playerPosition.getY(), playerCell);

                    player.setPosition(playerPosition.West());
                    break;
                }
                case EAST:
                {

                    playerCell.setTile(player.getEastAvatar());

                    playerLayer.setCell(playerPosition.getX(),playerPosition.getY(),null);
                    playerLayer.setCell(playerPosition.getX()+1,playerPosition.getY(), playerCell);

                    player.setPosition(playerPosition.East());
                    break;
                }

            }
        }
        System.out.println(player.getPosition().getX() +"  "+player.getPosition().getY());

    }


    public TiledMap getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }
}
