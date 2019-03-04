package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.Cards.PlayerDeck;
import inf112.skeleton.app.GameMap;
import inf112.skeleton.app.Player;
import inf112.skeleton.app.RoboRally;
import com.badlogic.gdx.Screen;

import java.util.ArrayList;


public class CardPickingScreen implements Screen {
    RoboRally game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    float time = 0;
    private SpriteBatch batch;
    private GameMap gameMap;
    private ArrayList<Player> players;
    private Player testPlayer;
    private PlayerDeck playerDeck;
    private ArrayList<Button> listOfCards;
    private Texture buttonTexture;
    private PlayScreen playScreen;


    public CardPickingScreen(RoboRally game, GameMap gameMap, PlayScreen playScreen) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        this.gamePort = new FitViewport(RoboRally.width, RoboRally.height, gameCam);
        this.gameMap = gameMap;
        this.playScreen = playScreen;
        this.players = gameMap.getPlayers();
        testPlayer = players.get(0);

        gameMap.giveOutCardsToPlayer(testPlayer);

        this.playerDeck = testPlayer.getPlayerDeck();

        gameCam.position.set(gamePort.getWorldWidth() / 2, (gamePort.getWorldHeight() / 2), 0);
        listOfCards = new ArrayList<>();
        batch = new SpriteBatch();

        buttonTexture = new Texture(playerDeck.get(0).getFilename());
        int firstCardPosX = 0;
        int firstCardPosY = Gdx.graphics.getHeight()/2 - buttonTexture.getHeight()/8;

        for(int i = 0; i < playerDeck.deckSize(); i++) {
            System.out.println(playerDeck.get(i).getProgramType());
            buttonTexture = new Texture(playerDeck.get(i).getFilename());

            int buttonPosX = firstCardPosX;
            int buttonPosY = firstCardPosY;
            System.out.println("Graphics.height/8: " + Gdx.graphics.getHeight()/8);
            System.out.println("buttoonTexture.height/8 " + (buttonTexture.getHeight()/8));

            Button button = new Button(buttonTexture, buttonPosX, buttonPosY,buttonTexture.getWidth()/8, buttonTexture.getHeight()/8);
            System.out.println("buttonposX: " +buttonPosX);
            System.out.println("buttonPosY: " + buttonPosY);

            firstCardPosX += buttonTexture.getWidth()/8;

            listOfCards.add(button);
        }

    }



    public void update(float deltaTime) {
        handleInput(deltaTime);

    }

    public void handleInput(float deltaTime) {
        time += deltaTime;

        if(Gdx.input.justTouched()) {
            float xCoordinate = Gdx.input.getX();
            float yCoordinate  = Gdx.input.getY();
            System.out.println("xClicked: " + xCoordinate + " yClicked: " + yCoordinate);

            //gotta change to playerDeck.decksize
            for(int i = 0; i < listOfCards.size(); i++) {
                if (listOfCards.get(i).checkIfClicked(xCoordinate, yCoordinate)) {
                    System.out.println("cardsXBetween: " +listOfCards.get(i).getButtonStartX() + "|| " + listOfCards.get(i).getButtonEndX());
                    System.out.println("cardsYBetween: " +listOfCards.get(i).getButtonStartY() + "|| " + listOfCards.get(i).getButtonEndY());
                    if(playerDeck.handSize() < 5) {
                        playerDeck.selectCardForHand(i);
                        listOfCards.remove(i);
                        System.out.println(playerDeck.deckSize());
                        System.out.println(playerDeck.handSize());

                    }
                }

            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.P)){
            game.setScreen(playScreen);
        }
    }





    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(Gdx.graphics.getDeltaTime());

        batch.begin();
        for(int i = 0; i < listOfCards.size(); i++) {
            listOfCards.get(i).draw(batch);
        }
        batch.end();

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
