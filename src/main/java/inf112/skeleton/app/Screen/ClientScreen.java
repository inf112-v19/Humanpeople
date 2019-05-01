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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Networking.GameClient;
import inf112.skeleton.app.Networking.GameServer;

public class ClientScreen implements Screen {

    private RoboRally game;
    private Stage stage;

    private ImageButton selectButton;
    private Image menuBackground;
    private Skin skin;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private float width;
    private float height;

    public ClientScreen(RoboRally game) {
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

        final TextField portField = new TextField("25135", skin);
        portField.setPosition(100, 100);
        portField.setWidth(50);
        stage.addActor(portField);


        int buttonWidth = 150;
        int buttonHeight = 150*2;

        picture = new Sprite(new Texture("assets/mainMenu/selectButton.png"));
        selectButton = new ImageButton(new SpriteDrawable(picture));
        selectButton.setWidth(buttonWidth);
        selectButton.setHeight(buttonHeight);
        selectButton.setPosition((int)(width / 2.1), height / 4);
        selectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!portField.getText().equals(""))
                    new GameClient(game, Integer.parseInt(portField.getText()));
            }
        });

        stage.addActor(selectButton);
        //stage.addActor(clientField);
        //stage.addActor(hostField);
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
