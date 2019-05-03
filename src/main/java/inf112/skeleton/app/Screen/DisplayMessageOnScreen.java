package inf112.skeleton.app.Screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;


public class DisplayMessageOnScreen {

    private String message;
    private Table table;

    public DisplayMessageOnScreen(String message, int x, int y) {
        this.message = message;
        this.table = new Table();
        table.setWidth(200);
        table.setHeight(200);
        table.setPosition(x, y);

        createTable();
    }

    /**
     * Sets up victory table
     */
    public void createTable() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont();
        labelStyle.font = myFont;

        Label messageLabel = new Label(message, labelStyle);
        table.add(messageLabel);
    }

    public Table getTable() {
        return table;
    }
}
