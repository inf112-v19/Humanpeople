package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import inf112.skeleton.app.Slave;

public class clientScreen implements Screen {
    Game game;
    Slave slave;
    public clientScreen(Game game) {
        this.game= game;
        slave = new Slave();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

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
