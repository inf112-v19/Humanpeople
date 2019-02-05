package inf112.skeleton.app.GameObjects;

public class EastWall implements GameObjects{
    private final int id;

    //TODO
    public EastWall(){
        id = 3;
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
        return false;
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
