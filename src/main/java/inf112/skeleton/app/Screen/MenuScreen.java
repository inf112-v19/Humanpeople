package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.RoboRally;

public class MenuScreen implements Screen {
    private  PlayScreen playScreen;
    private RoboRally game;
    private SpriteBatch batch;
    private Texture texture;


    public MenuScreen(RoboRally game,PlayScreen playScreen){
        this.game = game;
        batch = new SpriteBatch();
        texture = new Texture("magecity.png");
        this.playScreen = playScreen;



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        handleInput();
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture,0,0);
        batch.end();
    }

    private void handleInput() {
        if(Gdx.input.isTouched()){
            game.setScreen(playScreen);
        }
    }

    @Override
    public void resize(int i, int i1) {

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
