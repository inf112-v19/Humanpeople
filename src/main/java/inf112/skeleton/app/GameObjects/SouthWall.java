package inf112.skeleton.app.GameObjects;


/**
 * South wall object
 * @author Stian
 *
 */
public class SouthWall implements GameObjects{

    private final int id;

    public SouthWall(){
        id = 2;
    }

    @Override
    public boolean moveNorthFromAllowed() {
        return true;
    }

    @Override
    public boolean moveSouthFromAllowed() {
    	return false;
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
