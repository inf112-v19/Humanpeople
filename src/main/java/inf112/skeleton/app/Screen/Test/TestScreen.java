package inf112.skeleton.app.Screen.Test;

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
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.Game.GameMap;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Screen.InfoScreen;
import inf112.skeleton.app.Screen.MenuScreen;
import inf112.skeleton.app.Screen.UserInterface;
import inf112.skeleton.app.Screen.DisplayMessageOnScreen;

import java.util.ArrayList;

public class TestScreen implements Screen {

    private RoboRally game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Stage stage;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private GameMap gameMap;
    private float time = 0;
    private float tickTime = 0;

    ProgramCard move1;
    ProgramCard move2;
    ProgramCard move3;
    ProgramCard right;
    ProgramCard left;
    ProgramCard backUp;
    ProgramCard uTurn;

    ArrayList<ProgramCard> cardsForPlayer;

    private boolean infoShowed;
    boolean pause = false;
    float width;
    float height;

    private UserInterface ui;
    private InfoScreen infoScreen;


    public TestScreen(RoboRally game) {
        cardsForPlayer = new ArrayList<>();
        this.game = game;
        this.gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(RoboRally.width * 2, RoboRally.height, gameCam);
        gameCam.translate(RoboRally.width, RoboRally.height / 2);
        width = gamePort.getWorldWidth();
        height = gamePort.getWorldHeight();
        this.gameMap = new GameMap("assets/testMap.tmx", 4, false);
        this.map = gameMap.getMap();
        this.renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(width / 2, (height / 2), 0);

        this.ui = new UserInterface(width, gameMap.getPlayers().get(0));
        this.infoScreen = new InfoScreen(gameMap.getPlayers(), width, height);

        //Every program card type
        move1 = new ProgramCard(ProgramType.MOVE1, 1, "assets/cards/programCards/Move1/move1p490.png");
        move2 = new ProgramCard(ProgramType.MOVE2, 2, "assets/cards/programCards/Move2/move2p670.png");
        move3 = new ProgramCard(ProgramType.MOVE3, 3, "assets/cards/programCards/Move3/move3p790.png");

        right = new ProgramCard(ProgramType.ROTATERIGHT, 4, "assets/cards/programCards/rotateRight/rotateRightp80.png");
        left = new ProgramCard(ProgramType.ROTATELEFT, 5, "assets/cards/programCards/rotateLeft/rotateLeftp70.png");
        backUp = new ProgramCard(ProgramType.BACKWARD, 6, "assets/cards/programCards/backup/backUp1p430.png");
        uTurn = new ProgramCard(ProgramType.UTURN, 7, "assets/cards/programCards/uturn/uTurnp10.png");

        // Setter id-en til 0 for å flytte spilleren med id 0
        move1.setPlayerThatPlayedTheCard(0);
        move2.setPlayerThatPlayedTheCard(0);
        move3.setPlayerThatPlayedTheCard(0);
        right.setPlayerThatPlayedTheCard(0);
        left.setPlayerThatPlayedTheCard(0);
        backUp.setPlayerThatPlayedTheCard(0);
        uTurn.setPlayerThatPlayedTheCard(0);

        infoShowed = false;
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
        if (gameMap.getPlayers().get(0).getHandChosen()) {
            givePlayersRotateHand();
            gameMap.addPlayerHandToNewRound();
        }

        updateMap();
        if (tickTime > 0.4) {
            tickTime = 0;
            gameMap.performNextMovement();
            infoScreen.update(gameMap.getPlayers(), 0);
        }
        //Update ui
        if (tickTime > 0.2) {
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

    public void displayWinner(Player winner) {
        String victoryMessage = "Congratulations! Player " + winner.getPlayerTile().getColor() + " has won!";
        DisplayMessageOnScreen victoryScreen = new DisplayMessageOnScreen(victoryMessage, 150, 150);
        Table victoryTable = victoryScreen.getTable();
        stage.addActor(victoryTable);
    }


    public void handleInput() {
        if (!infoShowed) {
            infoShowed = true;
            System.out.println("\nSelect 5:\n1 = move1\n2 = move2\n3 = move3\nr = rotateRight\nl = rotateLeft\nu = uTurn\nb = backup\n\nSpace = Confirm");
        }
        if (cardsForPlayer.size() != 5) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
                cardsForPlayer.add(move1);
                System.out.println("selected: " + cardsForPlayer.size() + "/" + 5);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
                cardsForPlayer.add(move2);
                System.out.println("selected: " + cardsForPlayer.size() + "/" + 5);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
                cardsForPlayer.add(move3);
                System.out.println("selected: " + cardsForPlayer.size() + "/" + 5);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                cardsForPlayer.add(right);
                System.out.println("selected: " + cardsForPlayer.size() + "/" + 5);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                cardsForPlayer.add(left);
                System.out.println("selected: " + cardsForPlayer.size() + "/" + 5);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
                cardsForPlayer.add(uTurn);
                System.out.println("selected: " + cardsForPlayer.size() + "/" + 5);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
                cardsForPlayer.add(backUp);
                System.out.println("selected: " + cardsForPlayer.size() + "/" + 5);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
                System.out.println("LASER");
                gameMap.getLaser().fireLasers();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
                for (Player player : gameMap.getPlayers()) {
                    player.printStatus();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                game.setScreen(new MenuScreen(game));
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
                gameMap.getLaser().toggleLaser();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
                ui.toggleCardSelection();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
                stage = infoScreen.getStage();
                stage.setViewport(gamePort);
            }
            else {
                stage = ui.getStage();
                stage.setViewport(gamePort);
            }
        }
        //Test of movement according to program cards (using movePlayer() for testing
        else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            gameMap.getPlayers().get(0).getPlayerDeck().setPlayerHand(cardsForPlayer);
            givePlayersRotateHand();
            gameMap.addPlayerHandToNewRound();
            cardsForPlayer.clear();
            infoShowed = false;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            getInfo();
        }
    }

    public void givePlayersRotateHand() {
        for (int i = 1; i < numberOfPlayers(); i++) {
            ArrayList<ProgramCard> temp = new ArrayList<>();
            ProgramCard card = new ProgramCard(ProgramType.ROTATERIGHT, i, "");
            card.setPlayerThatPlayedTheCard(i);
            for (int z = 0; z < 5; z++) {
                temp.add(card);
            }
            gameMap.getPlayers().get(i).getPlayerDeck().setPlayerHand(temp);
        }
    }

    private void getInfo() {
        //Get information from controlled tile:
        System.out.println("-------------------");
        System.out.println("CONTROLLED TILE: ");

        Player testPlayer = gameMap.getPlayers().get(0);
        int posX = testPlayer.getPosition().getX();
        int posY = testPlayer.getPosition().getY();
        Direction dir = testPlayer.getDirection();

        System.out.println("Color: " + testPlayer.getPlayerTile().getColor());
        System.out.println("Direction: " + dir);
        System.out.println("Position: (" + posX + "," + posY + ")" + "\n");
        System.out.println("-------------------");

        //Get information from the other tiles:
        System.out.println("OTHER TILES: ");
        for (Player player : gameMap.getPlayers()) {
            if (player.getPlayerTile().getColor() == "Green")
                continue;

            posX = player.getPosition().getX();
            posY = player.getPosition().getY();
            dir = player.getDirection();

            System.out.println("Color: " + player.getPlayerTile().getColor());
            System.out.println("Direction: " + dir);
            System.out.println("Position: (" + posX + "," + posY + ")" + "\n");
        }
    }

    public int numberOfPlayers() {
        return gameMap.getPlayers().size();
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            pause = !pause;
        }
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

