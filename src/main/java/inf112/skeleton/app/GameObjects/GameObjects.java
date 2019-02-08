package inf112.skeleton.app.GameObjects;

/**
 * Interface for all Game Objects
 * @author Stian
 *
 */
public interface GameObjects {

    boolean moveNorthFromAllowed();

    boolean moveSouthFromAllowed();

    boolean moveEastFromAllowed();

    boolean moveWestFromAllowed();

    int getId();

}

