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
import inf112.skeleton.app.Cards.ProgramCard;
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
    private ArrayList<ProgramCard> programCardArrayList;
    private ArrayList<Button> visualHand;
    private Button confirmedDeck;

    public CardPickingScreen(RoboRally game, GameMap gameMap, PlayScreen playScreen) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        this.gamePort = new FitViewport(RoboRally.width, RoboRally.height, gameCam);
        this.gameMap = gameMap;
        this.playScreen = playScreen;
        this.players = this.gameMap.getPlayers();
        programCardArrayList = new ArrayList<>();
        visualHand = new ArrayList<>();
        testPlayer = players.get(0);

        gameMap.giveOutCardsToPlayer(testPlayer);

        this.playerDeck = testPlayer.getPlayerDeck();

        gameCam.position.set(gamePort.getWorldWidth() / 2, (gamePort.getWorldHeight() / 2), 0);
        listOfCards = new ArrayList<>();
        batch = new SpriteBatch();

        buttonTexture = new Texture(playerDeck.get(0).getFilename());
        float firstCardPosX = 0;
        float firstCardPosY = Gdx.graphics.getHeight()/2 - buttonTexture.getHeight()/8;

        for(int i = 0; i < playerDeck.deckSize(); i++) {
            buttonTexture = new Texture(playerDeck.get(i).getFilename());
            float buttonPosX = firstCardPosX;
            float buttonPosY = firstCardPosY;
            Button button = new Button(buttonTexture, buttonPosX, buttonPosY,buttonTexture.getWidth()/8, buttonTexture.getHeight()/8);
            firstCardPosX += buttonTexture.getWidth()/8;
            listOfCards.add(button);
        }
        confirmedDeck = new Button(buttonTexture, 800-buttonTexture.getWidth()/16, 0+buttonTexture.getHeight()/16,
                buttonTexture.getWidth()/16, buttonTexture.getHeight()/16);

    }



    public void update(float deltaTime) {

        handleInput(deltaTime);
//        cardCoordinateReplacer();
    }

    public void handleInput(float deltaTime) {
        time += deltaTime;

        if(Gdx.input.justTouched()) {

            float xCoordinate = Gdx.input.getX();
            float yCoordinate = Gdx.input.getY();

            //gotta change to playerDeck.decksize
            int j = 0;
            System.out.println("forloop");
            int counter = 0;
            for(int i = 0; i < listOfCards.size(); i++) {
                Button currentCard = null;
                if(i < listOfCards.size()) {
                    currentCard = listOfCards.get(i);
                }
//                else if (j < visualHand.size()) {
//                    currentCard = visualHand.get(j);
//                    j++;
//                }

                if (currentCard.checkIfClicked(xCoordinate, yCoordinate)) {
                    System.out.println(i);

//                System.out.println(currentCard.getButtonStartX());

//                    System.out.println(currentCard.getHaveBeenClicked());
                    if(visualHand.size() < 5 && currentCard.getHaveBeenClicked()) {
                        System.out.println(currentCard.getButtonStartX());
                        programCardArrayList.add(playerDeck.get(i+j));
                        visualHand.add(currentCard);
                        listOfCards.remove(currentCard);
                        System.out.println("vHsize: " + visualHand.size());

                    }
                    System.out.println(currentCard.getButtonStartX() );
                    if(!(currentCard.getHaveBeenClicked())) {

                        visualHand.remove(currentCard);
                        listOfCards.add(currentCard);
                        programCardArrayList.remove(currentCard);
                        System.out.println("vhSize2: " + visualHand.size());
                    }
                }
            }
            if(confirmedDeck.checkIfClicked(xCoordinate,yCoordinate) && visualHand.size() == 5) {
                for(int i = 0; i < visualHand.size(); i++) {
                    playerDeck.
                }
                game.setScreen(playScreen);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.P)){
            game.setScreen(playScreen);
        }

    }

    private  void cardCoordinateReplacer() {
        float startXcoordinate = 0;
        float upperYCoordinate = Gdx.graphics.getHeight()/10 + buttonTexture.getHeight()/8;

        for (int i = 0; i < listOfCards.size(); i++) {
            listOfCards.get(i).setCoordinates(startXcoordinate, upperYCoordinate);
            startXcoordinate += buttonTexture.getWidth()/8;

        }
        float lowerYCoordinate = Gdx.graphics.getHeight() - buttonTexture.getHeight()/8;

//        System.out.println("upperYCoordinate: " + upperYCoordinate);
//        System.out.println("lowerYCoordinate: " + lowerYCoordinate);

        startXcoordinate = 0;
        for(int j = 0; j < visualHand.size(); j++) {
            visualHand.get(j).setCoordinates(startXcoordinate, lowerYCoordinate);
            startXcoordinate += buttonTexture.getWidth()/8;


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
//            System.out.println("startX: " + listOfCards.get(i).getButtonStartX());
//            System.out.println("startY: " +listOfCards.get(i).getButtonStartY());
//            System.out.println("LOC size: " + listOfCards.size());
            listOfCards.get(i).draw(batch);

        }
        for(int j = 0 ; j < visualHand.size(); j++) {
//            System.out.println("vH startX: " + visualHand.get(j).getButtonStartX());
//            System.out.println("vh startY: " + visualHand.get(j).getButtonStartY());
//            System.out.println("vh size: " + visualHand.size());
            visualHand.get(j).draw(batch);
        }
        confirmedDeck.draw(batch);
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
