package inf112.skeleton.app.Screen.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Cards.ProgramType;
import inf112.skeleton.app.Game.GameMap;
import inf112.skeleton.app.Directions.Direction;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Game.RoboRally;

public class TestScreen implements Screen {

    private RoboRally game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private GameMap gameMap;
    private float time = 0;

    ProgramCard move1;
    ProgramCard move2;
    ProgramCard move3;
    ProgramCard right;
    ProgramCard left;
    ProgramCard backUp;
    ProgramCard uTurn;

    public TestScreen(RoboRally game) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        this.gamePort = new FitViewport(RoboRally.width, RoboRally.height, gameCam);
        this.gameMap = new GameMap("assets/map3.tmx", 4);
        this.map = gameMap.getMap();
        this.renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, (gamePort.getWorldHeight() / 2), 0);

        //Every program card type
        move1 = new ProgramCard(ProgramType.MOVE1, 0, "");
        move2 = new ProgramCard(ProgramType.MOVE2, 0, "");
        move3 = new ProgramCard(ProgramType.MOVE3, 0, "");
        right = new ProgramCard(ProgramType.ROTATERIGHT, 0, "");
        left = new ProgramCard(ProgramType.ROTATELEFT, 0, "");
        backUp = new ProgramCard(ProgramType.BACKUP, 0, "");
        uTurn = new ProgramCard(ProgramType.UTURN, 0, "");
    }

    public void update(float deltaTime) {
        handleInput(deltaTime);
        updateMap();
    }


    public void handleInput(float deltaTime) {
        time += deltaTime;

        //Test of movement according to program cards (using movePlayer() for testing)
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1) && time > 0.2) {
            time = 0;
            gameMap.movePlayer(0, move1);
            getInfo(move1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2) && time > 0.2) {
            time = 0;
            gameMap.movePlayer(0, move2);
            getInfo(move2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3) && time > 0.2) {
            time = 0;
            gameMap.movePlayer(0, move3);
            getInfo(move3);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R) && time > 0.2) {
            time = 0;
            gameMap.movePlayer(0, right);
            getInfo(right);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.L) && time > 0.2) {
            time = 0;
            gameMap.movePlayer(0, left);
            getInfo(left);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.U) && time > 0.2) {
            time = 0;
            gameMap.movePlayer(0, uTurn);
            getInfo(uTurn);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.B) && time > 0.2) {
            time = 0;
            gameMap.movePlayer(0, backUp);
            getInfo(backUp);
        }
    }

    private void getInfo(ProgramCard card) {
        //Get information from controlled tile:
        System.out.println("-------------------");
        System.out.println("CONTROLLED TILE: ");
        Player testPlayer = gameMap.getPlayers().get(0);
        int posX = testPlayer.getPosition().getX();
        int posY = testPlayer.getPosition().getY();
        Direction dir = testPlayer.getDirection();
        System.out.println("Card type: " + card.getProgramType());
        System.out.println("Color: " + testPlayer.getPlayerTile().getColor());
        System.out.println("Direction: " + dir);
        System.out.println("Position: (" + posX + "," + posY + ")" + "\n");
        System.out.println("Backup-pos: " + testPlayer.getPlayerTile().getBackup().getX() + "," + testPlayer.getPlayerTile().getBackup().getX());
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

    private void updateMap() {
        map = gameMap.getMap();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(Gdx.graphics.getDeltaTime());

        renderer.setView(gameCam);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

