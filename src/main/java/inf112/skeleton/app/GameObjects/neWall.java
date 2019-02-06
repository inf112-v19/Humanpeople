package inf112.skeleton.app.GameObjects;

public class neWall implements GameObjects{
    int id;
    public neWall() {
        id = 12;
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
        return false;
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
