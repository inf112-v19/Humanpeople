package inf112.skeleton.app.Directions;

import org.junit.Assert;
import org.junit.Test;

public class startingPositionsTest {

    @Test
    public void testStartingPositions1y() {
        StartingPositions sP = new StartingPositions(12, 12, 2);
        Position pos1 = new Position(0, 0);
        Assert.assertEquals(pos1.getY(), sP.getStartingPosition(0).getY());
    }

    @Test
    public void testStartingPositions2y() {
        StartingPositions sP = new StartingPositions(12, 12, 2);
        Position pos2 = new Position(0, 0);
        Assert.assertEquals(pos2.getY(), sP.getStartingPosition(1).getY());
    }

    @Test
    public void testStartingPositions1x() {
        StartingPositions sP = new StartingPositions(12, 12, 2);
        Assert.assertEquals(5, sP.getStartingPosition(0).getX());
    }

    @Test
    public void testStartingPositions2x() {
        StartingPositions sP = new StartingPositions(12, 12, 2);
        Assert.assertEquals(7, sP.getStartingPosition(1).getX());
    }
}