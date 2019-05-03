package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Game.RoboRally;

public class ChooseMapScreen implements Screen {

    private RoboRally game;
    private Stage stage;

    private ImageButton map1Button;
    private ImageButton map2Button;
    private Image menuBackground;
    private Skin skin;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private float width;
    private float height;

    public ChooseMapScreen(RoboRally game) {
        this.gameCam = new OrthographicCamera();
        this.gamePort = new StretchViewport(RoboRally.width * 2, RoboRally.height, gameCam);
        this.gameCam.translate(RoboRally.width, RoboRally.height / 2);
        this.width = gamePort.getWorldWidth();
        this.height = gamePort.getWorldHeight();
        this.game = game;
        this.skin = new Skin(Gdx.files.internal("assets/skin/uiskin.json"));
    }

    @Override
    public void show() {
        stage = new Stage();

        Sprite picture = new Sprite(new Texture("assets/mainMenu/background.png"));
        menuBackground = new Image(new SpriteDrawable(picture));
        menuBackground.setSize(width, height);
        menuBackground.setPosition(0, 0);
        stage.addActor(menuBackground);

        int buttonWidth = 150;
        int buttonHeight = 150*2;

        picture = new Sprite(new Texture("assets/mainMenu/map1Button.png"));
        map1Button = new ImageButton(new SpriteDrawable(picture));
        map1Button.setWidth(buttonWidth);
        map1Button.setHeight(buttonHeight);
        map1Button.setPosition(0, 0);
        map1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayScreen playScreen = new PlayScreen(game, 4, false, "assets/map3.tmx");
                playScreen.initializeUI(0);
                game.setScreen(playScreen);
            }
        });

        stage.addActor(map1Button);

        picture = new Sprite(new Texture("assets/mainMenu/map2Button.png"));
        map2Button = new ImageButton(new SpriteDrawable(picture));
        map2Button.setWidth(buttonWidth);
        map2Button.setHeight(buttonHeight);
        map2Button.setPosition(width - map2Button.getWidth(), 0);

        map2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayScreen playScreen = new PlayScreen(game, 4, false, "assets/riskyExchange.tmx");
                playScreen.initializeUI(0);
                game.setScreen(playScreen);

            }
        });
        stage.addActor(map2Button);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        stage.draw();
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
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
