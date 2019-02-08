package inf112.skeleton.app.GameObjects;

/**
 * East wall object
 * @author Stian
 *
 */
public class EastWall implements GameObjects {
	
    private final int id;

    public EastWall(){
        id = 3;
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
