package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.GameObjects.Directions.Position;
import inf112.skeleton.app.Screen.PlayScreen;

/**
 * Player object
 * @author Stian
 *
 */
public class Player implements GameObjects {

    private final int id;
    private Position position;

    public Player(){
        id = 6;
        position = new Position(1,1);
    }
    @Override
    public boolean moveNorthFromAllowed() {
        return false;
    }

    @Override
    public boolean moveSouthFromAllowed() {
        return false;
    }

    @Override
    public boolean moveEastFromAllowed() {
        return false;
    }

    @Override
    public boolean moveWestFromAllowed() {
        return false;
    }

    @Override
    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
