package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class GameMap {

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private Grid grid;

    public GameMap(String name){
         mapLoader = new TmxMapLoader();
         map = mapLoader.load(name);
         grid = new Grid(map);
    }

    public TiledMap getMap() {
        return map;
    }
}
