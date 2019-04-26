package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Screen.Test.TestScreen;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

/**
 * Menu screen for RoboRally
 *
 * @author Stian
 */
public class MenuScreen implements Screen {
    private PlayScreen playScreen;
    private RoboRally game;
    private Stage stage;

    private ImageButton playButton;
    private ImageButton testButton;
    private Image menuBackground;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private float width;
    private float height;


    public MenuScreen(RoboRally game) {
        this.gameCam = new OrthographicCamera();
        this.gamePort = new StretchViewport(RoboRally.width * 2, RoboRally.height, gameCam);
        this.gameCam.translate(RoboRally.width, RoboRally.height / 2);
        this.width = gamePort.getWorldWidth();
        this.height = gamePort.getWorldHeight();
        this.game = game;
        this.playScreen = new PlayScreen(game);
    }


    @Override
    public void show() {


        try {
            Sequence sequence = null;
            sequence = MidiSystem.getSequence(new File("assets/music.mid"));
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.start();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }

        stage = new Stage();

        Sprite picture = new Sprite(new Texture("assets/mainMenu/MRRCG.jpg"));
        menuBackground = new Image(new SpriteDrawable(picture));
        menuBackground.setSize(width, height);
        menuBackground.setPosition(0, 0);
        stage.addActor(menuBackground);

        picture = new Sprite(new Texture("assets/mainMenu/playBtn.png"));
        playButton = new ImageButton(new SpriteDrawable(picture));
        playButton.setPosition(0, 0);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(playScreen);
            }
        });

        stage.addActor(playButton);

        picture = new Sprite(new Texture("assets/mainMenu/testBtn.png"));
        testButton = new ImageButton(new SpriteDrawable(picture));
        testButton.setPosition(width-testButton.getWidth(), 0);

        testButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new TestScreen(game));
            }
        });
        stage.addActor(testButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        gamePort.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.setViewport(gamePort);
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
        stage.dispose();
    }

}
