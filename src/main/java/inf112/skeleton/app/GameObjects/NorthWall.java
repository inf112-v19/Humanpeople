package inf112.skeleton.app.GameObjects;

public class NorthWall implements GameObjects{
    //TODO

    private int id;

    public NorthWall(){
        id = 1;
    }
    @Override
    public boolean moveToFromSouthAllowed() {
        return true;
    }

    @Override
    public boolean moveToFromNorthAllowed() {
        return false;
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
