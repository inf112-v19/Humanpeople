package inf112.skeleton.app.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import inf112.skeleton.app.Cards.PlayerDeck;
import inf112.skeleton.app.Cards.ProgramCard;
import inf112.skeleton.app.Directions.Position;
import inf112.skeleton.app.Game.RoboRally;
import inf112.skeleton.app.Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UserInterface {

    private ImageButton playButton;
    private ImageButton powerDownButton;

    private Image cardSlotsTop;
    private Image cardSlotsMid;
    private Image cardSlotsBottom;

    Position pos[] = new Position[5];
    ProgramCard chosenCards[] = new ProgramCard[5];
    HashMap<Image, ProgramCard> cardMap = new HashMap<>();
    HashMap<ProgramCard, Image> imageMap = new HashMap<>();

    private Stage stage;
    private float width;
    private float height;
    private Player player;

    public UserInterface(float width, float height, Player player){
    this.height = height;
    this.width = width;
    this.player = player;
    stage = new Stage();

    initializePlayButton();
    initializePowerDownButton();
    initializeCardSlots();
    }

    public Player getPlayer(){
        return player;
    }

    public Stage getStage(){
        return stage;
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

    public void initializePlayButton() {
        Sprite picture = new Sprite(new Texture("assets/mainMenu/playBtn.png"));
        playButton = new ImageButton(new SpriteDrawable(picture));
        playButton.setWidth(picture.getWidth()/2);
        playButton.setHeight((picture.getHeight() - 5) / 2);
        playButton.setPosition((int) (width / 1.7), height / 2 - picture.getHeight() - 4);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (int i = 0; i < chosenCards.length; i++) {
                    if (chosenCards[i] == null) {
                        System.out.println("Choose " + (chosenCards.length - i) + " more cards");
                        return;
                    }
                }

//                Player player = gameMap.getPlayers().get(0);

                if (!player.getHandChosen()) {
                    System.out.println("Cards selected");
                    ArrayList<ProgramCard> list = new ArrayList<ProgramCard>(Arrays.asList(chosenCards));

                    player.getPlayerDeck().setPlayerHand(list);
                    player.setHandChosen(true);
                }
            }
        });
    }

    public void initializePowerDownButton() {
        Sprite picture = new Sprite(new Texture("assets/mainMenu/PowerDownBtn.png"));
        powerDownButton = new ImageButton(new SpriteDrawable(picture));
        powerDownButton.setWidth(picture.getWidth()/2);
        powerDownButton.setHeight((picture.getHeight() - 5) / 2);
        powerDownButton.setPosition((int) (width / 1.3), height / 2 - picture.getHeight() - 4);
        powerDownButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                Player player = gameMap.getPlayers().get(0);
                player.powerDown();
                player.setHandChosen(true);
                System.out.println("kake");
            }
        });
    }

    public void initializeCardSelection() {
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
            imageMap.put(programCard, cardImage);
        }
    }

    public void cardListener(Image cardImage) {
        //Coordinates for card drop zone
        float xLimit = width / 2;
        float yLimit = height / 3;

        //Coordinates for dropped card
        ProgramCard programCard = cardMap.get(cardImage);
        float x = cardImage.getX() + cardImage.getWidth()/2;
        float y = cardImage.getTop() - cardImage.getHeight() / 2;

        for(int i = 0; i < pos.length; i++) {
            if (x > pos[i].getX()-cardImage.getWidth()/2 && x < pos[i].getX()+cardImage.getWidth() && y < yLimit && !programCard.isMarked()) {
//                    if (chosenCards[i] == null) {

                //Reset card already there
                if(chosenCards[i] != null) {
                    Image im = imageMap.get(chosenCards[i]);
                    im.setPosition(im.getOriginX(), im.getOriginY());
                    chosenCards[i].setMarked(false);
                }
                //Set new card
                cardImage.setPosition(pos[i].getX(), pos[i].getY());
                programCard.setMarked(true);
                chosenCards[i] = programCard;
                return;
            }
            else {
                cardImage.setPosition(cardImage.getOriginX(), cardImage.getOriginY());
                programCard.setMarked(false);
                for (int k = 0; k < chosenCards.length; k++) {
                    if (chosenCards[k] != null && chosenCards[k].equals(programCard)) {
                        chosenCards[k] = null;
                        return;
                    }
                }
            }
        }
    }

    public void prepareNextRound() {
        chosenCards = new ProgramCard[5];
        cardMap.clear();
        imageMap.clear();
        stage.clear();
        stage.addActor(cardSlotsTop);
        stage.addActor(cardSlotsMid);
        stage.addActor(cardSlotsBottom);
        stage.addActor(playButton);
        stage.addActor(powerDownButton);
    }

}
