package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import inf112.skeleton.app.GameObjects.Directions.Direction;
import inf112.skeleton.app.GameObjects.Directions.Position;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.Scenes.Hud;

public class PlayScreen implements Screen {
    private RoboRally game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private GameMap gameMap;
    float time = 0;


    public PlayScreen(RoboRally game){
        this.game = game;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(RoboRally.width,RoboRally.height, gameCam);
        hud = new Hud(game.batch);


        gameMap = new GameMap("map3.tmx");
        map = gameMap.getMap();


        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth()/2,(gamePort.getWorldHeight()/2),0);

    }

    //experimenting with update and handleInput :-D
    public void update(float deltaTime){
        handleInput(deltaTime);

    }

    public void handleInput(float deltaTime){
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(2);


        TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();

        //henter grafikk
        TiledMapTile northTile = map.getTileSets().getTileSet("testTileset").getTile(31);
        TiledMapTile southTile = map.getTileSets().getTileSet("testTileset").getTile(34);
        TiledMapTile eastTile = map.getTileSets().getTileSet("testTileset").getTile(33);
        TiledMapTile westTile = map.getTileSets().getTileSet("testTileset").getTile(32);

        //legger til grafikk i player cell
        //playerCell.setTile(northTile);

        //setter celle til playercell
        //layer.setCell(gameMap.getPlayer().getPosition().getX(),gameMap.getPlayer().getPosition().getY(),playerCell);
        time += deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.W) && time > 0.2){
            time = 0;
            if(gameMap.AllowedToMove(Direction.NORTH)){
                playerCell.setTile(northTile);
                layer.setCell(gameMap.getPlayer().getPosition().getX(),gameMap.getPlayer().getPosition().getY()+1,playerCell);
                layer.setCell(gameMap.getPlayer().getPosition().getX(),gameMap.getPlayer().getPosition().getY(),null);
                gameMap.getPlayer().setPosition(new Position(gameMap.getPlayer().getPosition().getX(),gameMap.getPlayer().getPosition().getY()+1));
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)&& time > 0.2) {
            time = 0;
            if(gameMap.AllowedToMove(Direction.SOUTH)){
                playerCell.setTile(southTile);
                layer.setCell(gameMap.getPlayer().getPosition().getX(),gameMap.getPlayer().getPosition().getY()-1,playerCell);
                layer.setCell(gameMap.getPlayer().getPosition().getX(),gameMap.getPlayer().getPosition().getY(),null);
                gameMap.getPlayer().setPosition(new Position(gameMap.getPlayer().getPosition().getX(),gameMap.getPlayer().getPosition().getY()-1));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)&& time > 0.2) {
            time = 0;
            if (gameMap.AllowedToMove(Direction.WEST)) {
                playerCell.setTile(westTile);
                layer.setCell(gameMap.getPlayer().getPosition().getX() + 1, gameMap.getPlayer().getPosition().getY(), playerCell);
                layer.setCell(gameMap.getPlayer().getPosition().getX(), gameMap.getPlayer().getPosition().getY(), null);
                gameMap.getPlayer().setPosition(new Position(gameMap.getPlayer().getPosition().getX() + 1, gameMap.getPlayer().getPosition().getY()));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)&& time > 0.2) {
            time = 0;
            if (gameMap.AllowedToMove(Direction.EAST)) {
                playerCell.setTile(eastTile);
                layer.setCell(gameMap.getPlayer().getPosition().getX() -1, gameMap.getPlayer().getPosition().getY(), playerCell);
                layer.setCell(gameMap.getPlayer().getPosition().getX(), gameMap.getPlayer().getPosition().getY(), null);
                gameMap.getPlayer().setPosition(new Position(gameMap.getPlayer().getPosition().getX() -1, gameMap.getPlayer().getPosition().getY()));
            }
        }
        if(Gdx.input.isTouched()){


               game.setScreen(new MenuScreen(game,this));
           }
        }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(Gdx.graphics.getDeltaTime());

        renderer.setView(gameCam);
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
