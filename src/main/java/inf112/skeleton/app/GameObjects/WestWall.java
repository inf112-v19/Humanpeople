package inf112.skeleton.app.GameObjects;

public class WestWall implements GameObjects{
    //TODO
    private int id;

    public WestWall(){
        id = 4;
    }
    @Override
    public boolean moveNorthFromAllowed() {
        return true;
    }

    @Override
    public boolean moveSouthFromAllowed() {
        return true;
    }

    @Override
    public boolean moveEastFromAllowed() {
        return true;
    }

    @Override
    public boolean moveWestFromAllowed() {
        return false;
    }

    @Override
    public int getId() {
        return id;
    }
}
