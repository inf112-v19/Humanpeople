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
    private RoboRally game;
    private OrthographicCamera gameCam;
    private SpriteBatch batch;
    private PlayerDeck playerDeck;
    private ArrayList<VisualCard> visualDeck;
    private Texture visualCardTexture;
    private PlayScreen playScreen;
    private ArrayList<VisualCard> visualHand;
    private Button confirmedButton;
    private Viewport gamePort;

    public CardPickingScreen(RoboRally game, GameMap gameMap, PlayScreen playScreen) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        gamePort = new FitViewport(RoboRally.width, RoboRally.height, gameCam);

        this.playScreen = playScreen;
        gameCam.position.set(gamePort.getWorldWidth() / 2, (gamePort.getWorldHeight() / 2), 0);
        visualHand = new ArrayList<>();

        ArrayList<Player> players = gameMap.getPlayers();
        Player testPlayer = players.get(0);
        gameMap.giveOutCardsToPlayer(testPlayer);
        this.playerDeck = testPlayer.getPlayerDeck();
        visualDeck = new ArrayList<>();
        batch = new SpriteBatch();
        fillVisualDeck();

        Texture confirmedButtonTexture = new Texture("assets/confirmButton.PNG");
        confirmedButton = new Button(confirmedButtonTexture, 800-confirmedButtonTexture.getWidth()/2, 50 ,
                confirmedButtonTexture.getWidth()/2, confirmedButtonTexture.getHeight()/2);
    }

    /**
     * Fills up the visualDeck with the filenames connected to the actual playerDeck
     */
    private void fillVisualDeck() {
        visualCardTexture = new Texture(playerDeck.get(0).getFilename());
        int cardHeight = visualCardTexture.getHeight()/8;
        int cardWidth = visualCardTexture.getWidth()/8;
        int firstCardPosX = 0;
        int firstCardPosY = Gdx.graphics.getHeight()/2 - cardHeight;
        for(int i = 0; i < playerDeck.deckSize(); i++) {
            visualCardTexture = new Texture(playerDeck.get(i).getFilename());
            float buttonPosX = firstCardPosX;
            VisualCard visualCard = new VisualCard(visualCardTexture, buttonPosX, firstCardPosY, cardWidth, cardHeight);
            firstCardPosX += cardWidth;
            visualDeck.add(visualCard);
        }
    }

    private void update() {
        handleInput();
        cardCoordinateReplacer();
    }

    private void handleInput() {
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
                System.out.println(playerDeck.handSize());
                for(int i = 0; i < playerDeck.handSize(); i++) {
                    System.out.println(playerDeck.getHand().get(i).getFilename());
                }
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
        int startXCoordinate = 0;
        int lowerYCoordinate = 0;
        for (VisualCard visualCard : visualDeck) {
            visualCard.setCoordinates(startXCoordinate, lowerYCoordinate);
            startXCoordinate += visualCardTexture.getWidth() / 8;
        }

        int upperYCoordinate = lowerYCoordinate + 2* visualCardTexture.getHeight()/8;

        startXCoordinate = 0;
        for (VisualCard visualCard : visualHand) {
            visualCard.setCoordinates(startXCoordinate, upperYCoordinate);
            startXCoordinate += visualCardTexture.getWidth() / 8;
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
        for (VisualCard visualCard1 : visualDeck) {
            visualCard1.draw(batch);
        }
        for (VisualCard visualCard : visualHand) {
            visualCard.draw(batch);
        }
        confirmedButton.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
//        gamePort.update(width, height);
//        gameCam.update();
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
