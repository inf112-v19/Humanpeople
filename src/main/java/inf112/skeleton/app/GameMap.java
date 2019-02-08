package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Player;

public class GameMap {

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private Grid grid;
    private Player player;

    public GameMap(String name){
         mapLoader = new TmxMapLoader();
         map = mapLoader.load(name);
         grid = new Grid(map);
         player = new Player();
    }
    
    public boolean AllowedToMove(Direction direction){
        return grid.AllowedToMoveInDirection(direction,player.getPosition());
    }


    public TiledMap getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }
}
