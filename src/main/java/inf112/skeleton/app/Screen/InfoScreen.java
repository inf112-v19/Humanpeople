package inf112.skeleton.app.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import inf112.skeleton.app.Player.Player;

import java.util.ArrayList;

public class InfoScreen {

    private ArrayList<Player> players;
    private float width;
    private float height;
    private Table table;
    private Stage stage;
    private int id;
    private String currentPlayer;
    private Color currentColor;

    public InfoScreen(ArrayList<Player> players, float width, float height){
        this.stage = new Stage();
        this.players = players;
        this.width = width;
        this.height = height;
        this.table = new Table();

        //Set green as default
        this.id = 0;
        this.currentPlayer = "Green";

        table.setWidth(width);
        table.setHeight(height);
        table.setPosition(25, 0);
        stage.addActor(table);

        createStatusTable();
    }

    public void createStatusTable() {
        int counter = 0;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont();
        labelStyle.font = myFont;

        //Create a header
        String head = "STATUSBOARD\n";
        Label headLabel = new Label(head, labelStyle);
        headLabel.setWrap(true);
        headLabel.setFontScale(1.5f);
        table.align(Align.top);
        table.add(headLabel);
        table.row();

        //Create a field of information for each player
        for(Player player : players){
            if(counter == players.size()/2)
                table.row();
            String playerInfo = player.getStatus();
            Label statusLabel = new Label(playerInfo, labelStyle);
            Color cl = Color.GOLD;
            String color = player.getPlayerTile().getColor();
            switch (color){
                case "Green": cl = Color.GREEN;
                    break;
                case "Dark blue": cl = Color.BLUE;
                    break;
                case "Yellow": cl = Color.YELLOW;
                    break;
                case "Light blue": cl = Color.SKY;
                    break;
            }

            if(player.getId() == id) {
                currentPlayer = color;
                currentColor = cl;
            }
            statusLabel.setColor(cl);
            table.align(Align.topRight);
            table.add(statusLabel).width(200);
            counter++;

        }
        table.row();

        //Create a label specifying which color you are playing
        String playingAs = "You are playing as: ";
        String player = currentPlayer;
        Label playingAsLabel = new Label(playingAs, labelStyle);
        Label playerLabel = new Label(player, labelStyle);
        playerLabel.setColor(currentColor);
        table.add(playingAsLabel).width(65);
        table.add(playerLabel).width(0);
        table.getCell(playerLabel).align(Align.left);
    }

    public Stage getStage(){
        return stage;
    }

    public void update(ArrayList<Player> players, int id){
        this.players = players;
        this.id = id;
        table.clear();
        createStatusTable();
    }

}
