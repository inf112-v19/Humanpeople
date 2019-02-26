package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.GameObjects.Directions.Direction;

/**
 * Interface for all Game Objects
 * @author Stian
 *
 */
public interface GameObject {

   boolean canGo(Direction dir);

   int getId();

}
