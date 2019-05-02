package inf112.skeleton.app.Directions;

import org.junit.Assert;
import org.junit.Test;

public class testStartingPositionTest {
    @Test
    public void testStartingPositions0y() {
        TestStartingPositions sP = new TestStartingPositions(12, 12);
        Position pos = new Position(1, 1);
        Assert.assertEquals(pos.getY(), sP.getStartingPosition(0).getY());
    }

    @Test
    public void testStartingPositions1y() {
        TestStartingPositions sP = new TestStartingPositions(12, 12);
        Position pos = new Position(1, 1);
        Assert.assertEquals(pos.getY(), sP.getStartingPosition(1).getY());
    }

    @Test
    public void testStartingPositions0x() {
        TestStartingPositions sP = new TestStartingPositions(12, 12);
        Position pos = new Position(1, 1);
        Assert.assertEquals(pos.getX(), sP.getStartingPosition(0).getX());
    }

    @Test
    public void testStartingPositions1x() {
        TestStartingPositions sP = new TestStartingPositions(12, 12);
        Position pos = new Position(10, 1);
        Assert.assertEquals(pos.getX(), sP.getStartingPosition(1).getX());
    }
}
