package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Game.GameMap;
import inf112.skeleton.app.Game.RoboRally;


/**
 * Play screen of RoboRally
 */
public class PlayScreen implements Screen {
    private RoboRally game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private GameMap gameMap;
    float time = 0;


    public PlayScreen(RoboRally game) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        this.gamePort = new FitViewport(RoboRally.width, RoboRally.height, gameCam);
        this.gameMap = new GameMap("assets/map3.tmx", 4);
        this.map = gameMap.getMap();
        this.renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, (gamePort.getWorldHeight() / 2), 0);
    }

    public void update(float deltaTime) {
        handleInput(deltaTime);
        updateMap();
    }


    public void handleInput(float deltaTime) {
        time += deltaTime;
        //Does a full phase for all players
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && time > 0.2) {
            time = 0;
            gameMap.movePlayers();
        }
    }

    private void updateMap() {
        map = gameMap.getMap();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(Gdx.graphics.getDeltaTime());
        renderer.setView(gameCam);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
