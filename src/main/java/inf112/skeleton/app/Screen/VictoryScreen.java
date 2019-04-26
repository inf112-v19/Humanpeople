package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import inf112.skeleton.app.Game.GameMap;
import inf112.skeleton.app.Player.Player;

public class VictoryScreen {

    private Table window;
    private float width;
    private float height;

    private Player victor;

    public VictoryScreen(Player player) {
        this.victor = player;

        this.width = 150;
        this.height = 150;
        this.window = new Window("Congratulations!", new Skin());
        window.setWidth(width);
        window.setHeight(height);

        createTable();
    }

    /**
     * Sets up table
     */
    public void createTable() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont();
        labelStyle.font = myFont;

        String victoryMessage = getVictoryMessage();
        Label victoryLabel = new Label(victoryMessage, labelStyle);

        Image playerAvatar = getPlayerImage();

        window.add(victoryLabel);
        window.add(playerAvatar);
    }

    /**
     * Returns a victory message based on who won
     * @return
     */
    public String getVictoryMessage() {
        String string = "Congratulations!\nPlayer " + victor.getId() + "has won!";
        return string;
    }

    /**
     * @return image of the victors east facing avatar
     */
    public Image getPlayerImage() {
        TiledMapTile playerTile = victor.getPlayerTile().getAvatar();
        TextureRegion textureRegion = playerTile.getTextureRegion();
        Texture playerTexture = textureRegion.getTexture();
        Sprite playerSprite = new Sprite(playerTexture);
        Image playerImage = new Image(new SpriteDrawable(playerSprite));
        return playerImage;
    }

    public Table getTable() {
        return window;
    }
}
