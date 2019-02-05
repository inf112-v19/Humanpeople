package inf112.skeleton.app.GameObjects;

public class WestWall implements GameObjects{
    //TODO
    private int id;

    public WestWall(){
        id = 4;
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
