package inf112.skeleton.app.GameObjects;

/**
 * South East wall object
 * @author Stian
 *
 */
public class seWall implements GameObjects{
    
	private final int id;

    public seWall() {
        id = 14;
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
