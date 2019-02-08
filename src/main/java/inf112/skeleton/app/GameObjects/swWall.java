package inf112.skeleton.app.GameObjects;

/**
 * South West wall object
 * @author Stian
 *
 */

public class swWall implements GameObjects {
    
	private final int id;
    
	public swWall() {
        id = 13;
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
        return false;
    }

    @Override
    public int getId() {
        return id;
    }
}
