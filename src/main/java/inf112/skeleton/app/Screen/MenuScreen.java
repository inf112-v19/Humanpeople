package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Screen.Test.TestScreen;

/**
 * Menu screen for RoboRally
 * @author Stian
 *
 */
public class MenuScreen implements Screen {
    private PlayScreen playScreen;
    private RoboRally game;
    private SpriteBatch batch;

    private Button playButton;
    private Texture playButtonTexture;

    private Sprite sprite;
    private Texture menuBackground;

    private Button testButton;
    private Texture testButtonTexture;

    public MenuScreen(RoboRally game){
        this.game = game;
        this.playScreen = new PlayScreen(game);
        this.batch = new SpriteBatch();

        this.menuBackground = new Texture("assets/mainMenu/MRRCG.jpg");
        this.sprite = new Sprite(menuBackground);
        this.sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        this.playButtonTexture = new Texture("assets/mainMenu/playBtn.png");
        int playButtonPosX = (Gdx.graphics.getWidth()/2) - (playButtonTexture.getWidth()/2);
        int playButtonPosY = (Gdx.graphics.getHeight()/2) - (playButtonTexture.getHeight()/2);
        this.playButton = new Button(playButtonTexture, playButtonPosX, playButtonPosY, playButtonTexture.getWidth(), playButtonTexture.getHeight());
        this.testButtonTexture = new Texture("assets/mainMenu/testBtn.png");
        this.testButton = new Button(testButtonTexture,2,2,testButtonTexture.getWidth(),testButtonTexture.getHeight());
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

        sprite.draw(batch);
        playButton.draw(batch);
        testButton.draw(batch);
        batch.end();
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            int inputX = Gdx.input.getX();
            int inputY = Gdx.input.getY();
            if (playButton.checkIfClicked(inputX, inputY))
                game.setScreen(playScreen);

            else if(testButton.checkIfClicked(inputX,inputY))
                game.setScreen(new TestScreen(game));
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
