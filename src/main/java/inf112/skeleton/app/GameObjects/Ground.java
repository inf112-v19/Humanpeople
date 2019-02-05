package inf112.skeleton.app.GameObjects;

public class Ground implements GameObjects{
    //TODO
    private final int id;

    public Ground(){
        id = 5;
    }

    @Override
    public boolean moveToFromSouthAllowed() {
        return true;
    }

    @Override
    public boolean moveToFromNorthAllowed() {
        return true;
    }

    @Override
    public boolean moveToFromEastAllowed() {
        return true;
    }

    @Override
    public boolean moveToFromWestAllowed() {
        return true;
    }

    @Override
    public int getId() {
        return id;
    }
}
