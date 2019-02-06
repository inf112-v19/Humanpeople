package inf112.skeleton.app.GameObjects;

public class nwWall implements GameObjects{
    int id;

    public nwWall() {
        this.id = 11;
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
        return false;
    }

    @Override
    public int getId() {
        return id;
    }
}
