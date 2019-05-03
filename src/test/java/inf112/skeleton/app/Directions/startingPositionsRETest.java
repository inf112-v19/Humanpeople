package inf112.skeleton.app.Directions;

import org.junit.Assert;
import org.junit.Test;

public class startingPositionsRETest {

    @Test
    public void testStartingPositionsRE1y() {
        StartingPositionsRE sP = new StartingPositionsRE(12, 12, 2);
        Position pos1 = new Position(0, 0);
        Assert.assertEquals(pos1.getY(), sP.getStartingPosition(0).getY());
    }

    @Test
    public void testStartingPositionsRE2y() {
        StartingPositionsRE sP = new StartingPositionsRE(12, 12, 2);
        Position pos2 = new Position(0, 0);
        Assert.assertEquals(pos2.getY(), sP.getStartingPosition(1).getY());
    }

    @Test
    public void testStartingPositionsRE1x() {
        StartingPositionsRE sP = new StartingPositionsRE(12, 12, 2);
        Assert.assertEquals(4, sP.getStartingPosition(0).getX());
    }

    @Test
    public void testStartingPositionsRE2x() {
        StartingPositionsRE sP = new StartingPositionsRE(12, 12, 2);
        Assert.assertEquals(7, sP.getStartingPosition(1).getX());
    }
}
