package inf112.skeleton.app.GameObjects;

/**
 * North wall object
 * @author Stian
 *
 */
public class NorthWall implements GameObjects{

    private final int id;

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
