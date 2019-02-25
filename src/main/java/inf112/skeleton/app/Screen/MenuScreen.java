package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.RoboRally;

import javax.swing.event.ChangeEvent;

/**
 * Menu screen for RoboRally
 * @author Stian
 *
 */
public class MenuScreen implements Screen {
    private PlayScreen playScreen;
    private RoboRally game;
    private SpriteBatch batch;
    private Texture menuBackground;
    private Button playButton;
    private Texture playButtonTexture;

    Stage stage;

    public MenuScreen(RoboRally game){
        this.game = game;
        batch = new SpriteBatch();
        menuBackground = new Texture("assets/mainMenu/MRRCG.jpg");
        playButtonTexture = new Texture("assets/mainMenu/playBtn.png");
        //playButton = new ImageButton(new TextureRegionDrawable(playButtonTexture));
        this.playScreen = new PlayScreen(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        handleInput(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(menuBackground,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // X and Y positions for drawing Play button
        int playButtonStartDrawWidth = (Gdx.graphics.getWidth()/2) - (int)(playButtonTexture.getWidth()/2);
        int playButtonStartDrawHeight = (Gdx.graphics.getHeight()/2) - (int)(playButtonTexture.getHeight()/2);
        int playButtonEndDrawWidth = (Gdx.graphics.getWidth()/2) - (int)(playButtonTexture.getWidth()*1.8);
        int playButtonEndDrawHeight = (Gdx.graphics.getHeight()/2) - (int)(playButtonTexture.getHeight()*1.8);
        batch.draw(playButtonTexture, playButtonStartDrawWidth, playButtonStartDrawHeight, playButtonEndDrawWidth, playButtonEndDrawHeight);
        batch.end();
    }

    private void handleInput(float deltaTime) {
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
