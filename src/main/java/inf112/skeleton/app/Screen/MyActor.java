package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import inf112.skeleton.app.RoboRally;

public class MyActor extends Actor {

    private Sprite skin;
    private Boolean haveBeenClicked = false;
    private float x;
    private float y;


    public MyActor(Texture texture, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        skin = new Sprite(texture);
        skin.setPosition(this.x,this.y);
        skin.setSize(width, height);
    }

    //to make button clickable when window is resized, DON'T CHANGE :O
    public float getButtonStartX() {
        return skin.getX()*(float)Gdx.graphics.getWidth()/ RoboRally.cfgWidth;
    }
    public float getButtonEndX(){
        return (skin.getX()+skin.getWidth())*(float)Gdx.graphics.getWidth()/ RoboRally.cfgWidth;
    }
    public float getButtonStartY(){

        float yPos = Gdx.graphics.getHeight()-(skin.getY()*(float)Gdx.graphics.getHeight()/ RoboRally.cfgHeight);
        return yPos -(skin.getHeight()*(float)Gdx.graphics.getHeight()/ RoboRally.cfgHeight);
    }

    public float getButtonEndY() {
        return Gdx.graphics.getHeight()-(skin.getY()*(float)Gdx.graphics.getHeight()/ RoboRally.cfgHeight);

    }


    public void draw(SpriteBatch batch) {
        skin.draw(batch);
    }

    public boolean checkIfClicked(float inputX, float inputY){

        if(inputX>getButtonStartX()&&inputX<getButtonEndX()){
            if(inputY>getButtonStartY()&&inputY<getButtonEndY()){
                clickedAgain();
                return true;
            }

        }
        return false;
    }
    public void clickedAgain() {
        haveBeenClicked = !haveBeenClicked;
    }

    public Boolean getHaveBeenClicked() {
        return haveBeenClicked;
    }
    public void setCoordinates(float x, float y){
        this.x = x;
        this.y = y;
    }
}
