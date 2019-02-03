package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GameMap;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.Scenes.Hud;

import java.util.Iterator;

public class PlayScreen implements Screen {
    private RoboRally game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private GameMap gameMap;


    public PlayScreen(RoboRally game){
        this.game = game;

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(RoboRally.width,RoboRally.height,gamecam);
        hud = new Hud(game.batch);


        gameMap = new GameMap("map1.tmx");
        map = gameMap.getMap();


        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2,(gamePort.getWorldHeight()/2),0);

    }

    //experimenting with update and handleInput :-D
    public void update(){
        handleInput();

    }

    public void handleInput(){
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get("wallS");
        TiledMapTileLayer.Cell cell = layer.getCell(9,0);
        TiledMapTile newTile = map.getTileSets().getTileSet("magecity").getTile(10);
        //System.out.println(layer.getCell(9,0).getTile().getId());

        Iterator iterator = map.getLayers().iterator();
/*
        for(int x = 0;x<1000;x++){
            for(int y = 0; y<1000;y++){
                cell = null;
                cell = layer.getCell(x,y);

                if(cell!=null){
                    System.out.println(x+" , "+y);
                }
            }
        }
*/
        //cell.setTile(map.getTileSets().getTileSet("0x72_16x16DungeonTilesetv1").getTile(4));
                                                    //change with actual tileset name should work ?
        if(Gdx.input.isTouched()){
           if(newTile!=null){
               cell.setTile(newTile);
           }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        renderer.setView(gamecam);
        renderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        //hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
