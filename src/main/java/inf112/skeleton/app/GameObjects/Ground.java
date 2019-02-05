package inf112.skeleton.app.GameObjects;

public class Ground implements GameObjects{
    //TODO
    private final int id;

    public Ground(){
        id = 5;
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
        return true;
    }

    @Override
    public int getId() {
        return id;
    }
}
