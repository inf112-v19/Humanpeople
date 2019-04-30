package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
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
import inf112.skeleton.app.Networking.GameServer;
import inf112.skeleton.app.Player.Player;

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

    private ImageButton playButton;

    private Image cardSlotsTop;
    private Image cardSlotsMid;
    private Image cardSlotsBottom;

    Position pos[] = new Position[5];
    ProgramCard chosenCards[] = new ProgramCard[5];
    HashMap<Image, ProgramCard> cardMap = new HashMap<>();
    private int myID;


    public PlayScreen(RoboRally game, int nPlayers) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(RoboRally.width * 2, RoboRally.height, gameCam);
        gameCam.translate(RoboRally.width, RoboRally.height / 2);
        width = RoboRally.width * 2;
        height = RoboRally.height;
        this.gameMap = new GameMap("assets/map3.tmx", nPlayers);
        this.map = gameMap.getMap();
        this.renderer = new OrthogonalTiledMapRenderer(map);

    }
    public void setMyID(int id ) {
        myID = id;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void update(float deltaTime) {
        tickTime += deltaTime;
        if (gameMap.getCardsDealt()) {
            gameMap.setCardsDealt(false);
            prepareNextRound();
            Player player = gameMap.getPlayers().get(myID);
            initializeCardSelection(player);
        }
        if (gameMap.hasAllPlayersChosenHands())
            gameMap.addPlayerHandToNewRound();

        updateMap();
        if (tickTime > 0.4) {
            tickTime = 0;
            gameMap.preformNextMovement();
        }
    }

    private void updateMap() {
        map = gameMap.getMap();
    }

    @Override
    public void show() {

        stage = new Stage();
        initializePlayButton();
        initializeCardSlots();

        Gdx.input.setInputProcessor(stage);
    }

    public void initializePlayButton() {
        Sprite picture = new Sprite(new Texture("assets/mainMenu/playBtn.png"));
        playButton = new ImageButton(new SpriteDrawable(picture));
        playButton.setWidth(width / 2);
        playButton.setHeight((picture.getHeight() - 5) / 2);
        playButton.setPosition(width / 2, height / 2 - picture.getHeight() - 4);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (int i = 0; i < chosenCards.length; i++) {
                    if (chosenCards[i] == null) {
                        System.out.println("Choose " + (chosenCards.length - i) + " more cards");
                        return;
                    }
                }
                //CHANGEED HERE
                Player player = gameMap.getPlayers().get(myID);
                if (!player.getHandChosen()) {
                    System.out.println("Cards selected");
                    ArrayList<ProgramCard> list = new ArrayList<ProgramCard>(Arrays.asList(chosenCards));

                    player.getPlayerDeck().setPlayerHand(list);
                    player.setHandChosen(true);
                }
            }
        });
    }

    public void initializeCardSlots(){
        //Creates the slot bars
        Sprite picture = new Sprite(new Texture("assets/hand/hand5v3.png"));
        cardSlotsBottom = new Image(new SpriteDrawable(picture));
        cardSlotsBottom.setWidth(width / 2);
        cardSlotsBottom.setHeight(picture.getHeight() + 25);
        cardSlotsBottom.setPosition(width / 2, 0);
        cardSlotsBottom.setColor(Color.LIME);

        picture = new Sprite(new Texture("assets/hand/hand5v3.png"));
        cardSlotsTop = new Image(new SpriteDrawable(picture));
        cardSlotsTop.setWidth(width / 2);
        cardSlotsTop.setHeight(picture.getHeight() + 25);
        cardSlotsTop.setPosition(width / 2, height - (picture.getHeight() + 25));

        picture = new Sprite(new Texture("assets/hand/hand5v3.png"));
        cardSlotsMid = new Image(new SpriteDrawable(picture));
        cardSlotsMid.setWidth(width / 2);
        cardSlotsMid.setHeight(picture.getHeight() + 25);
        cardSlotsMid.setPosition(width / 2, height - (picture.getHeight() + 25) * 2);
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


    public void initializeCardSelection(Player player) {
        PlayerDeck deck = player.getPlayerDeck();
        for (int i = 0; i < deck.deckSize(); i++) {
            ProgramCard programCard = deck.getCard(i);
            Sprite picture = new Sprite(new Texture(programCard.getFilename()));
            final Image cardImage = new Image(new SpriteDrawable(picture));
            float pWidth = picture.getWidth() / 8;
            float pHeight = picture.getHeight() / 8;
            cardImage.setWidth(pWidth);
            cardImage.setHeight(pHeight);

            //Place 5 first cards in top row and save position as origin
            if (i < 5) {
                cardImage.setPosition(width / 2 + (i * pWidth) + 5, height - pHeight - 10);
                cardImage.setOrigin(width / 2 + (i * pWidth) + 5, height - pHeight - 10);

                //List of coordinates for the bottom slot row for easy access
                pos[i] = new Position((int) (width / 2 + (i * pWidth) + 5), 0 + 7);
            }

            //Place remaining 4 cards in row beneath
            else {
                cardImage.setPosition(width / 2 + (i * pWidth) - 5 * pWidth + 5, height - pHeight * 2 - 27);
                cardImage.setOrigin(width / 2 + (i * pWidth) - 5 * pWidth + 5, height - pHeight * 2 - 27);
            }

            //Adds dragging functionality to each image
            cardImage.addListener(new DragListener() {
                public void drag(InputEvent event, float x, float y, int pointer) {
                    cardImage.moveBy(x - cardImage.getWidth() / 2, y - cardImage.getHeight() / 2);
                    cardImage.toFront();
                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    super.dragStop(event, x, y, pointer);
                    cardListener(cardImage);
                }
            });

            stage.addActor(cardImage);
            cardMap.put(cardImage, programCard);
        }
    }

    public void cardListener(Image cardImage) {

        //Coordinates for card drop zone
        float xLimit = width / 2;
        float yLimit = height / 2;

        //Coordinates for dropped card
        ProgramCard programCard = cardMap.get(cardImage);
        float x = cardImage.getX();
        float y = cardImage.getTop() - cardImage.getHeight() / 2;

        //If in the zone and not there already
        if (x > xLimit && y < yLimit && !programCard.isMarked()) {
            for (int i = 0; i < chosenCards.length; i++) {
                if (chosenCards[i] == null) {
                    cardImage.setPosition(pos[i].getX(), pos[i].getY());
                    programCard.setMarked(true);
                    chosenCards[i] = programCard;
                    return;
                }
            }
            //If the zone is full
            cardImage.setPosition(cardImage.getOriginX(), cardImage.getOriginY());
        }
        //If moved out of zone, set to origin
        else {
            cardImage.setPosition(cardImage.getOriginX(), cardImage.getOriginY());
            programCard.setMarked(false);
            for (int i = 0; i < chosenCards.length; i++) {
                if (chosenCards[i] != null && chosenCards[i].equals(programCard)) {
                    chosenCards[i] = null;
                    return;
                }
            }
        }
    }

    public void prepareNextRound() {
        chosenCards = new ProgramCard[5];
        cardMap.clear();
        stage.clear();
        stage.addActor(cardSlotsTop);
        stage.addActor(cardSlotsMid);
        stage.addActor(cardSlotsBottom);
        stage.addActor(playButton);
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
