package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;
import inf112.skeleton.app.GameObjects.PlayerTile;

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


        player = new Player("Sondre", new PlayerTile(map));
        playerCell = new TiledMapTileLayer.Cell();
        playerLayer = (TiledMapTileLayer) map.getLayers().get(2);

        initPlayer(player.getPlayerTile());

    }

    public void initPlayer(PlayerTile player) {
        playerCell.setTile(player.getNorthAvatar());
        playerLayer.setCell(getPlayer().getPosition().getX(), getPlayer().getPosition().getY(), playerCell);
    }



    /*public boolean AllowedToMove(Direction direction){
        return grid.AllowedToMoveInDirection(direction,player.getPosition());
    }*/

    public void movePlayer(Direction direction) {
        player.move(direction,this);
        PlayerTile playerTile = player.getPlayerTile();


    }


    public TiledMap getMap() {
        return map;
    }

    /**
     *
     * @return tile of Player
     */
    public PlayerTile getPlayer() {
        return player.getPlayerTile();
    }

    public Grid getGrid() {
        return grid;
    }

    public TiledMapTileLayer getPlayerLayer() {
        return playerLayer;
    }

    public TiledMapTileLayer.Cell getPlayerCell() {
        return playerCell;
    }
}
