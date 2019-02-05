package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.Screen.PlayScreen;

public class Player implements GameObjects {
    //TODO

    private int id;

    public Player(){
        id = 5;
    }
    @Override
    public boolean moveToFromSouthAllowed() {
        return false;
    }

    @Override
    public boolean moveToFromNorthAllowed() {
        return false;
    }

    @Override
    public boolean moveToFromEastAllowed() {
        return false;
    }

    @Override
    public boolean moveToFromWestAllowed() {
        return false;
    }

    @Override
    public int getId() {
        return id;
    }
}
