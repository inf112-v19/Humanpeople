package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {

    private Sprite skin;

    public Button(Texture texture, float x, float y, float width, float height) {
        skin = new Sprite(texture);
        skin.setPosition(x,y);
        skin.setSize(width, height);
    }

    public void draw(SpriteBatch batch) {
        skin.draw(batch);
    }

    public boolean checkIfClicked(float inputX, float inputY) {
        if (inputX > skin.getX() && inputX < (skin.getX() + skin.getWidth())) {
            if (inputY > (skin.getY() - skin.getHeight()/2) && inputY < (skin.getY() + skin.getHeight()/2)) {
                return true;
            }
        }
        return false;
    }
}
