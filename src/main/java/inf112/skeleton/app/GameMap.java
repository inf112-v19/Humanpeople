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

    public GameMap(String name) {
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(name);
        grid = new Grid(map);

        player = new Player(map);
        playerCell = new TiledMapTileLayer.Cell();
        playerLayer = (TiledMapTileLayer) map.getLayers().get(2);

        drawPlayer(player);

    }

    public void drawPlayer(Player player) {
        playerCell.setTile(player.getCurrentAvatar());
        playerLayer.setCell(player.getPosition().getX(), player.getPosition().getY(), playerCell);
    }



    public void movePlayer(Direction direction) {
        if (grid.AllowedToMoveInDirection(direction, player.getPosition())) {

            //removes player from map
            Position playerPosition = player.getPosition();
            playerLayer.setCell(playerPosition.getX(), playerPosition.getY(), null);

            player.move(direction);

            drawPlayer(player);

        }
        //for testing
        System.out.println(player.getPosition().getX() + "  " + player.getPosition().getY());

    }


    public TiledMap getMap() {
        return map;
    }

}
