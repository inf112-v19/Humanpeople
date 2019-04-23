package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Cards.PlayerDeck;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Directions.Position;
import inf112.skeleton.app.Game.GameMap;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Player.Player;
import net.java.games.input.Component;
import net.java.games.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



/**
 * Play screen of RoboRally
 */
public class PlayScreen implements Screen {
    private RoboRally game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private GameMap gameMap;

    float tickTime = 0;

    private Stage stage;
    private float width;
    private float height;
    private UserInterface ui;


    public PlayScreen(RoboRally game) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(RoboRally.width * 2, RoboRally.height, gameCam);
        gameCam.translate(RoboRally.width, RoboRally.height / 2);
        width = gamePort.getWorldWidth();
        height = RoboRally.height;
        this.gameMap = new GameMap("assets/map3.tmx", 4);
        this.map = gameMap.getMap();
        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.ui = new UserInterface(width, height, gameMap.getPlayers().get(0));
    }

    public void update(float deltaTime) {
        handleInput();
        tickTime += deltaTime;
        if (gameMap.getCardsDealt()) {
            gameMap.setCardsDealt(false);
            ui.prepareNextRound();
            ui.initializeCardSelection();
        }
        if (gameMap.getPlayers().get(0).getHandChosen())
            gameMap.addPlayerHandToNewRound();

        updateMap();
        if (tickTime > 0.4) {
            tickTime = 0;
            gameMap.preformNextMovement();
            hasWon();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }
    }

    private void updateMap() {
        map = gameMap.getMap();
    }

    @Override
    public void show() {
        stage = ui.getStage();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(Gdx.graphics.getDeltaTime());
        renderer.setView(gameCam);
        renderer.render();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        stage.setViewport(gamePort);
    }

    public Image getLifeTokenOfPlayer() {
        ArrayList<Player> players = gameMap.getPlayers();
        Player player1 = players.get(0);
        int lifeTokens = player1.getLifeTokens();
        Texture lifeTokenTexture = null;
        switch (lifeTokens) {
            case 3: lifeTokenTexture = new Texture("assets/lifeTokens/lifeTokens3.png"); break;
            case 2: lifeTokenTexture = new Texture("assets/lifeTokens/lifeTokens2.png"); break;
            case 1: lifeTokenTexture = new Texture("assets/lifeTokens/lifeTokens1.png"); break;
            case 0: lifeTokenTexture = new Texture("assets/lifeTokens/lifeTokens0.png"); break;
            default: lifeTokenTexture = new Texture("assets/lifeTokens/lifeTokens0.png"); break;
        }
        Sprite lifeTokenSprite = new Sprite(lifeTokenTexture);
        Image lifeTokenImage = new Image(new SpriteDrawable(lifeTokenSprite));
        lifeTokenImage.setPosition(1,1);
        return lifeTokenImage;
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            game.setScreen(new MenuScreen(game));
        }
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

    /**
     * Checks if any players have won, i.e. has visited all flags
     */
    public void hasWon() {
        ArrayList<Player> players = gameMap.getPlayers();
        for (Player player : players) {
            hasWon(player);
        }
    }

    /**
     * Checks if the player has won, i.e. has visited all flags
     */
    public void hasWon(Player player) {
        int lastFlagVisited = player.getLastFlagVisited();

        if (lastFlagVisited == 1) {
            VictoryScreen victoryScreen = new VictoryScreen(player);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            stage.addActor(victoryScreen.getTable());
        }

    }
}
