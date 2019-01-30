package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.Scenes.Hud;

public class PlayScreen implements Screen {
    RoboRally game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    public PlayScreen(RoboRally game){
        this.game = game;
        gamecam = new OrthographicCamera();
        texture = new Texture("assets/preview.jpg");
        gamePort = new FitViewport(RoboRally.width,RoboRally.height,gamecam);
        hud = new Hud(game.batch);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(texture,0,0);
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
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
