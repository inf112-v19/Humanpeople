package inf112.skeleton.app.Directions;

import inf112.skeleton.app.Directions.Position;

public class TestStartingPositions implements IStartingPosition {

    private Position start0;
    private Position start1;
    private Position start2;
    private Position start3;

    public TestStartingPositions(int gameMapWidth, int gameMapHeight) {
        int x0 = 8;
        int y0 = 2;
        start0 = new Position(x0,y0);

        int x1 = 8;
        int y1 = 3;
        start1 = new Position(x1,y1);

        int x2 = gameMapWidth-2;
        int y2 = gameMapHeight-2;
        start2 = new Position(x2, y2);

        int x3 = 1;
        int y3 = gameMapHeight-2;
        start3 = new Position(x3, y3);
    }

    public Position getStartingPosition(int startIndex) {
        switch (startIndex) {
            case 0:
                return start0;
            case 1:
                return start1;
            case 2:
                return start2;
            case 3:
                return start3;
                default: return new Position(0,0);
        }
    }
}
