package inf112.skeleton.app.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import inf112.skeleton.app.Player.Player;

public class VictoryScreen {

    private Table table;
    private float width;
    private float height;

    private Player winner;

    public VictoryScreen(Player player) {
        this.winner = player;

        this.width = 200;
        this.height = 200;
        this.table = new Table();
        table.setWidth(width);
        table.setHeight(height);
        table.setPosition(150, 150);

        createTable();
    }

    /**
     * Sets up victory table
     */
    public void createTable() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont();
        labelStyle.font = myFont;

        String victoryMessage = getVictoryMessage();
        Label victoryLabel = new Label(victoryMessage, labelStyle);

        //Image playerAvatar = getPlayerImage();

        table.add(victoryLabel);
        //table.add(playerAvatar);
    }

    /**
     * Returns a victory message based on who won
     * @return
     */
    public String getVictoryMessage() {
        String string = "Congratulations!\nPlayer " + winner.getPlayerTile().getColor().toLowerCase() + " has won!";
        return string;
    }

    /**
     * @return image of the victors east facing avatar
     */
    public Image getPlayerImage() {
        int playerId = winner.getId();
        TiledMapTile playerTile = winner.getPlayerTile().getAvatar();
        TextureRegion textureRegion = playerTile.getTextureRegion();
        TextureRegion[][] splitTextureRegion = textureRegion.split(32,32);
        int x = 0;
        int y = 3 + playerId;
        TextureRegion avatarTextureRegion = splitTextureRegion[0][0];
        Texture playerTexture = avatarTextureRegion.getTexture();

        Image playerImage = new Image(playerTexture);
        return playerImage;
    }

    public Table getTable() {
        return table;
    }
}
