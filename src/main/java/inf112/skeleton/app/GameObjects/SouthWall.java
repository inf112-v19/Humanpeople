package inf112.skeleton.app.GameObjects;

public class SouthWall implements GameObjects{
    //TODO

    private int id;

    public SouthWall(){
        id = 2;
    }

    @Override
    public boolean moveNorthFromAllowed() {
        return true;
    }

    @Override
    public boolean moveSouthFromAllowed() { return false; }

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
