package inf112.skeleton.app.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.RoboRally;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;

    Label worldLabel;


    public Hud(SpriteBatch spriteBatch){
    worldTimer = 300;
    timeCount = 0;

    viewport = new FitViewport(RoboRally.width,RoboRally.height,new OrthographicCamera());
    stage = new Stage(viewport, spriteBatch);

    Table table = new Table();
    table.top();
    table.setFillParent(true);

    worldLabel = new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.CYAN));

    table.add(worldLabel).padTop(10);
    table.row();


    stage.addActor(table);
    }
}
