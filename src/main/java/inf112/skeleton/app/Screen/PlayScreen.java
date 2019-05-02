package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Game.GameMap;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Player.Player;

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

    private int myID;

    private boolean isMultiPlayer;


    public PlayScreen(RoboRally game, int nPlayers, boolean isMultiPlayer) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(RoboRally.width * 2, RoboRally.height, gameCam);
        gameCam.translate(RoboRally.width, RoboRally.height / 2);
        width = gamePort.getWorldWidth();
        height = gamePort.getWorldHeight();
        if (isMultiPlayer)
            this.gameMap = new GameMap("assets/map3.tmx", nPlayers, true);
        else
            this.gameMap = new GameMap("assets/map3.tmx", nPlayers, false);
        this.map = gameMap.getMap();
        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.isMultiPlayer = isMultiPlayer;
    }

    public void initializeUI(int myID) {
        this.ui = new UserInterface(width, height, gameMap.getPlayers().get(myID));
        ui.toggleCardSelection();
    }

    public void setMyID(int id ) {
        myID = id;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void update(float deltaTime) {
        // Display winner if there is a winner
        if (gameMap.getWinner() != null)
            displayWinner(gameMap.getWinner());

        handleInput();
        tickTime += deltaTime;
        if (gameMap.getCardsDealt()) {
            gameMap.setCardsDealt(false);
            ui.prepareNextRound();
            ui.initializeCardSelection();
        }
         // If game is multiplayer then wait for all players to choose cards
        if (isMultiPlayer) {
            if (gameMap.hasAllPlayersChosenHands()) {
                gameMap.addPlayerHandToNewRound();
                gameMap.getPlayers().get(myID).setHandChosen(false);
            }
        }
        // If game is singlePlayer wait for player 0 to choose cards
        else if (gameMap.getPlayers().get(0).getHandChosen()) {
            gameMap.selectCardsForBots();
            gameMap.addPlayerHandToNewRound();
            gameMap.getPlayers().get(0).setHandChosen(false);
        }

        updateMap();
        if (tickTime > 0.4) {
            tickTime = 0;
            gameMap.performNextMovement();
        }
        //Update ui
        if(tickTime > 0.2){
            // Update only if there is a change in the variables
            if (ui.getPlayer().getHealth() != ui.getSavedHealth()) {
                ui.getDamageTokenOfPlayer();
                ui.setSavedHealth(ui.getPlayer().getHealth());
            }
            if (ui.getPlayer().getLifeTokens() != ui.getSavedLifeTokens()) {
                ui.getLifeTokenOfPlayer();
                ui.setSavedLifeTokens(ui.getPlayer().getLifeTokens());
            }
            if (ui.getPlayer().getLastFlagVisited() != ui.getSavedFlag()) {
                ui.getFlagInfo();
                ui.setSavedFlag(ui.getPlayer().getLastFlagVisited());
            }
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

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
        }
    }

    /**
     * Displays text over the board congratulating the winner
     * The winner is removed from the board while the game continues in the background
     * @param winner
     */
    public void displayWinner(Player winner) {
        VictoryScreen victoryScreen = new VictoryScreen(winner);
        Table winScreen = victoryScreen.getTable();
        stage.addActor(winScreen);
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
