package inf112.skeleton.app.Game;

public enum ConveyorBelt {
    NORTH, SOUTH, EAST, WEST, CLOCK_WISE, COUNTER_CLOCK_WISE, DOUBLE_NORTH, DOUBLE_SOUTH, DOUBLE_EAST, DOUBLE_WEST;

    @Override
    public String toString() {
        switch (this) {
            case NORTH: return "North";
            case SOUTH: return "South";
            case EAST: return "East";
            case WEST: return "West";
            case CLOCK_WISE: return "Clock Wise";
            case COUNTER_CLOCK_WISE: return "Counter Clock wise";
            case DOUBLE_EAST: return "Double East";
            case DOUBLE_NORTH: return "Double North";
            case DOUBLE_SOUTH: return "Double South";
            case DOUBLE_WEST: return "Double West";
        }
        return "";
    }
}
