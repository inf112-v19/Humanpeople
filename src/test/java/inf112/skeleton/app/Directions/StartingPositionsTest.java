package inf112.skeleton.app.Directions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StartingPositionsTest {


    private StartingPositions sP;

    @Before
    public void setup(){
        sP = new StartingPositions(12);
    }

    @Test
    public void testStartingPositions1y() {
        Position pos1 = new Position(0, 0);
        Assert.assertEquals(pos1.getY(), sP.getStartingPosition(0).getY());
    }

    @Test
    public void testStartingPositions2y() {
        Position pos2 = new Position(0, 0);
        Assert.assertEquals(pos2.getY(), sP.getStartingPosition(1).getY());
    }

    @Test
    public void testStartingPositions1x() {
        Assert.assertEquals(5, sP.getStartingPosition(0).getX());
    }

    @Test
    public void testStartingPositions2x() {
        Assert.assertEquals(7, sP.getStartingPosition(1).getX());
    }
}