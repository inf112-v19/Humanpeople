package inf112.skeleton.app.GameObjects;

public class SouthWall implements GameObjects{
    //TODO

    private int id;

    public SouthWall(){
        id = 2;
    }

    @Override
    public boolean moveToFromSouthAllowed() {
        return false;
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
