package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.RoboRally;

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
        batch = new SpriteBatch();

        menuBackground = new Texture("assets/mainMenu/MRRCG.jpg");
        sprite = new Sprite(menuBackground);
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        playButtonTexture = new Texture("assets/mainMenu/playBtn.png");
        int playButtonPosX = (Gdx.graphics.getWidth()/2) - (playButtonTexture.getWidth()/2);
        int playButtonPosY = (Gdx.graphics.getHeight()/2) - (playButtonTexture.getHeight()/2);
        playButton = new Button(playButtonTexture, playButtonPosX, playButtonPosY, playButtonTexture.getWidth(), playButtonTexture.getHeight());

        testButtonTexture = new Texture("assets/mainMenu/testBtn.png");
        testButton = new Button(testButtonTexture,2,2,testButtonTexture.getWidth(),testButtonTexture.getHeight());
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

        sprite.draw(batch);
        playButton.draw(batch);
        testButton.draw(batch);
        batch.end();
    }

    private void handleInput(float deltaTime) {

        if (Gdx.input.isTouched()) {
            int inputX = Gdx.input.getX();
            int inputY = Gdx.input.getY();

            if (playButton.checkIfClicked(inputX, inputY)) {
                game.setScreen(playScreen);
            }
            else if(testButton.checkIfClicked(inputX,inputY)){
                System.out.println("pog");
            }
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
