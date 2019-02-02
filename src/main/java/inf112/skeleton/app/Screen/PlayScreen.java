package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.Scenes.Hud;

public class PlayScreen implements Screen {
    RoboRally game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    public PlayScreen(RoboRally game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(RoboRally.width,RoboRally.height,gamecam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("assets/map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2,(gamePort.getWorldHeight()/2),0);

    }
    //experimenting with update and handleInput :-D
    public void update(){
        handleInput();
        gamecam.update();
    }


    public void handleInput(){
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get("wallS");
        TiledMapTileLayer.Cell cell = null;
        TiledMapTileLayer layerll = new TiledMapTileLayer(12,12,16,16);
        /*
        for(int x = 0;x<1000;x++){
            for(int y = 0; y<1000;y++){
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

            //layer.setCell(1,1,cell);
            System.out.println(layer.getHeight());
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
