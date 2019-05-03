package inf112.skeleton.app.Directions;

/**
 * Starting positions for the map "Risky Exchange"
 */
public class StartingPositionsRiskyExchange implements IStartingPosition {

    private Position start0;
    private Position start1;
    private Position start2;
    private Position start3;

    public StartingPositionsRiskyExchange(int gameMapWidth) {
        int middleOfBoardX = (gameMapWidth / 2) - 1;
        int x0 = middleOfBoardX-1;
        int y0 = 0;
        start0 = new Position(x0,y0);

        int x1 = middleOfBoardX+2;
        int y1 = 0;
        start1 = new Position(x1,y1);

        int x2 = middleOfBoardX-3;
        int y2 = 0;
        start2 = new Position(x2, y2);

        int x3 = middleOfBoardX+4;
        int y3 = 0;
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
