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

import java.util.ArrayList;

public class TestScreen implements Screen {

    private RoboRally game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

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

    private int numberOfPlayers;
    private boolean infoShowed;


    public TestScreen(RoboRally game) {
        cardsForPlayer = new ArrayList<>();
        numberOfPlayers = 4;
        this.game = game;
        this.gameCam = new OrthographicCamera();
        this.gamePort = new FitViewport(RoboRally.width, RoboRally.height, gameCam);
        this.gameMap = new GameMap("assets/map3.tmx", numberOfPlayers);
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


        //setter iden til 0 for å flytte spilleren med id 0
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
        handleInput(deltaTime);
        updateMap();

        tickTime += deltaTime;
        if(tickTime>0.2){
            tickTime=0;
            gameMap.preformNextMovement();
            //TODO få getInfo til å virke på en fornuftig måte
            //getInfo(backUp);
        }
    }


    public void handleInput(float deltaTime) {
        time += deltaTime;
        if(!infoShowed){
            infoShowed = true;
            System.out.println("\nSelect 5:\n1 = move1\n2 = move2\n3 = move3\nr = rotateRight\nl = rotateLeft\nu = uTurn\nb = backup\n\nSpace = Confirm");
        }
        if(time >0.1){

            if(cardsForPlayer.size()!=5){
                if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
                    time = 0;
                    cardsForPlayer.add(move1);
                    System.out.println("selected: "+cardsForPlayer.size()+"/"+5);
//            getInfo(move1);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
                    time = 0;
                    cardsForPlayer.add(move2);
                    System.out.println("selected: "+cardsForPlayer.size()+"/"+5);
//            getInfo(move2);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
                    time = 0;

                    cardsForPlayer.add(move3);
                    System.out.println("selected: "+cardsForPlayer.size()+"/"+5);
//            getInfo(move3);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.R)) {
                    time = 0;

                    cardsForPlayer.add(right);
                    System.out.println("selected: "+cardsForPlayer.size()+"/"+5);
//            getInfo(right);
                }

                if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                    time = 0;

                    cardsForPlayer.add(left);
                    System.out.println("selected: "+cardsForPlayer.size()+"/"+5);
//            getInfo(left);
                }

                if (Gdx.input.isKeyPressed(Input.Keys.U)) {
                    time = 0;

                    cardsForPlayer.add(uTurn);
                    System.out.println("selected: "+cardsForPlayer.size()+"/"+5);
//            getInfo(uTurn);
                }

                if (Gdx.input.isKeyPressed(Input.Keys.B)) {
                    time = 0;
                    cardsForPlayer.add(backUp);
                    System.out.println("selected: "+cardsForPlayer.size()+"/"+5);

//            getInfo(backUp);
                }
            }
            //Test of movement according to program cards (using movePlayer() for testing)
            else{
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    time = 0;

                    gameMap.getPlayers().get(0).getPlayerDeck().setPlayerHand(cardsForPlayer);
                    givePlayersRotateHand();
                    gameMap.addPlayerHandToNewRound();
                    cardsForPlayer.clear();
                    infoShowed = false;

                }
            }






        }
    }
    public void givePlayersRotateHand(){
        for(int i = 1;i<numberOfPlayers; i++){
            ArrayList<ProgramCard> temp = new ArrayList<>();
            ProgramCard card = new ProgramCard(ProgramType.ROTATERIGHT,i,"");
            card.setPlayerThatPlayedTheCard(i);
            for(int z = 0; z<5;z++){
                temp.add(card);
            }
            gameMap.getPlayers().get(i).getPlayerDeck().setPlayerHand(temp);
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

