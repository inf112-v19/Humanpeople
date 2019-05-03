package inf112.skeleton.app.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.Screen.MenuScreen;

public class RoboRally extends Game {
    public SpriteBatch batch;
    public final static int width = 385;
    public final static int height = 385;

    @Override
    public void create() {
        batch = new SpriteBatch();
        MenuScreen menuScreen = new MenuScreen(this);
        menuScreen.enableMusic();
        setScreen(menuScreen);
    }

    @Override
    public void render(){
        super.render();
    }
}
