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
    private RoboRally game;
    private OrthographicCamera gameCam;
    private SpriteBatch batch;
    private GameMap gameMap;
    private ArrayList<Player> players;
    private Player testPlayer;
    private PlayerDeck playerDeck;
    private ArrayList<VisualCard> visualDeck;
    private Texture buttonTexture;
    private PlayScreen playScreen;
    private ArrayList<VisualCard> visualHand;
    private Button confirmedButton;

    public CardPickingScreen(RoboRally game, GameMap gameMap, PlayScreen playScreen) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        Viewport gamePort = new FitViewport(RoboRally.width, RoboRally.height, gameCam);
        this.gameMap = gameMap;
        this.playScreen = playScreen;
        this.players = this.gameMap.getPlayers();
        visualHand = new ArrayList<>();
        testPlayer = players.get(0);
        gameCam.position.set(gamePort.getWorldWidth() / 2, (gamePort.getWorldHeight() / 2), 0);
        this.gameMap.giveOutCardsToPlayer(testPlayer);
        this.playerDeck = testPlayer.getPlayerDeck();
        visualDeck = new ArrayList<>();
        batch = new SpriteBatch();
        fillVisualDeck();

        Texture confirmedButtonTexture = new Texture("assets/confirmButton.PNG");
        confirmedButton = new Button(confirmedButtonTexture, 800-confirmedButtonTexture.getWidth()/2, 0+confirmedButtonTexture.getHeight()/2 ,
                confirmedButtonTexture.getWidth()/2, confirmedButtonTexture.getHeight()/2);
    }

    /**
     * Fills up the visualDeck with the filenames connected to the actual playerDeck
     */
    private void fillVisualDeck() {
        buttonTexture = new Texture(playerDeck.get(0).getFilename());
        float firstCardPosX = 0;
        float firstCardPosY = Gdx.graphics.getHeight()/2 - buttonTexture.getHeight()/8;
        for(int i = 0; i < playerDeck.deckSize(); i++) {
            buttonTexture = new Texture(playerDeck.get(i).getFilename());
            float buttonPosX = firstCardPosX;
            float buttonPosY = firstCardPosY;
            VisualCard visualCard = new VisualCard(buttonTexture, buttonPosX, buttonPosY,buttonTexture.getWidth()/8, buttonTexture.getHeight()/8);
            firstCardPosX += buttonTexture.getWidth()/8;
            visualDeck.add(visualCard);
        }
    }

    public void update() {
        handleInput();
        cardCoordinateReplacer();
    }

    public void handleInput() {
        if(Gdx.input.justTouched()) {
            float xCoordinate = Gdx.input.getX();
            float yCoordinate = Gdx.input.getY();
            int indexInHand = 0;
            int totalCardsInHandAndDeck = visualDeck.size() + visualHand.size();
            for(int indexInDeck = 0; indexInDeck < totalCardsInHandAndDeck; indexInDeck++) {
                VisualCard currentCard = null;
                if(indexInDeck < visualDeck.size()) {
                    currentCard = visualDeck.get(indexInDeck);
                }
                else if(indexInHand < visualHand.size()) {
                    currentCard = visualHand.get(indexInHand);
                    indexInHand++;
                }
                if (currentCard != null && currentCard.checkIfClicked(xCoordinate, yCoordinate)) {
                    moveCardFromDeckToHand(currentCard, indexInDeck);

                    if(!(currentCard.getHaveBeenClicked()) && indexInHand > 0) {
                        moveCardFromHandToDeck(currentCard, indexInHand);
                    }
                    break;
                }
            }
            if(confirmedButton.checkIfClicked(xCoordinate,yCoordinate) && visualHand.size() == 5) {
                game.setScreen(playScreen);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.P)){
            game.setScreen(playScreen);
        }
    }

    /**
     * Moves the card from the visualDeckarray to the hand and moves the actual card from deck to hand
     * @param currentCard
     * @param indexInDeck
     */
    private void moveCardFromDeckToHand(VisualCard currentCard, int indexInDeck) {
        if(visualHand.size() < 5 && currentCard.getHaveBeenClicked()) {
            playerDeck.selectCardForHand(indexInDeck);
            visualHand.add(currentCard);
            visualDeck.remove(currentCard);
        }
    }

    /**
     * Moves the card from the visualHand to the visualDeck, and the actual card from hand to deck
     * @param currentCard
     * @param indexInHand
     */
    private void moveCardFromHandToDeck(VisualCard currentCard, int indexInHand) {
        visualDeck.add(currentCard);
        visualHand.remove(currentCard);
        playerDeck.removeCardFromHand(indexInHand-1);
    }

    /**
     * Method which updates the coordinates for the visual representation of the cards
     */
    private  void cardCoordinateReplacer() {
        float startXCoordinate = 0;
        float upperYCoordinate = Gdx.graphics.getHeight()/10 + buttonTexture.getHeight()/8;
        for (int i = 0; i < visualDeck.size(); i++) {
            visualDeck.get(i).setCoordinates(startXCoordinate, upperYCoordinate);
            startXCoordinate += buttonTexture.getWidth()/8;
        }
        float lowerYCoordinate = Gdx.graphics.getHeight() - buttonTexture.getHeight()/8;
        startXCoordinate = 0;
        for(int j = 0; j < visualHand.size(); j++) {
            visualHand.get(j).setCoordinates(startXCoordinate, lowerYCoordinate);
            startXCoordinate += buttonTexture.getWidth()/8;
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update();

        batch.begin();
        for(int i = 0; i < visualDeck.size(); i++) {
            visualDeck.get(i).draw(batch);
        }
        for(int j = 0; j < visualHand.size(); j++) {
            visualHand.get(j).draw(batch);
        }
        confirmedButton.draw(batch);
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
