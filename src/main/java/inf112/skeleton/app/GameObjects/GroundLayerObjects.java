package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.GameObjects.Directions.Direction;

public class GroundLayerObjects implements GameObjects {

    protected boolean north = true;
    protected boolean south = true;
    protected boolean east = true;
    protected boolean west = true;
    private final int id;

    public GroundLayerObjects(int id) {
        this.id = id;

        switch (id) {
            case 1:
                north = false; // NORTH
                break;
            case 2:
                south = false; // SOUTH
                break;
            case 3:
                east = false; // EAST
                break;
            case 4:
                west = false; // WEST
                break;
            case 5: // GROUND
                break;

            case 11:
                north = false;
                west = false; // NORTHWEST
                break;
            case 12:
                north = false;
                east = false; // NORTHEAST
                break;
            case 13:
                south = false;
                west = false; // SOUTHWEST
                break;
            case 14:
                south = false;
                east = false; // SOUTHEAST
                break;
            default:
                throw new IllegalArgumentException("Id not recognized");
        }
    }
    @Override
    public boolean canGo(Direction dir) {
        switch (dir) {
            case NORTH:
                return north;
            case SOUTH:
                return south;
            case EAST:
                return east;
            case WEST:
                return west;
        }
        return false;
    }

    @Override
    public int getId() {
        return id;
    }
}