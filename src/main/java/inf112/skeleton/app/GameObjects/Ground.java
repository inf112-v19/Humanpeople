package inf112.skeleton.app.GameObjects;

/**
 * Ground object
 * @author Stian
 *
 */
public class Ground implements GameObjects{
	
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
