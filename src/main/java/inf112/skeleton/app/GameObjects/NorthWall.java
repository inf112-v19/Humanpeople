package inf112.skeleton.app.GameObjects;

public class NorthWall implements GameObjects{
    //TODO

    private int id;

    public NorthWall(){
        id = 1;
    }
    @Override
    public boolean moveNorthFromAllowed() {
        return false;
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
        return true;
    }

    @Override
    public int getId() {
        return id;
    }
}
