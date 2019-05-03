package inf112.skeleton.app.Directions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StartingPositionsRiskyExchangeTest {

    private StartingPositionsRiskyExchange sP;

    @Before
    public void setup(){
        sP = new StartingPositionsRiskyExchange(12);
    }

    @Test
    public void testStartingPositionsRE1y() {
        Position pos1 = new Position(0, 0);
        Assert.assertEquals(pos1.getY(), sP.getStartingPosition(0).getY());
    }

    @Test
    public void testStartingPositionsRE2y() {
        Position pos2 = new Position(0, 0);
        Assert.assertEquals(pos2.getY(), sP.getStartingPosition(1).getY());
    }

    @Test
    public void testStartingPositionsRE1x() {

        Assert.assertEquals(4, sP.getStartingPosition(0).getX());
    }

    @Test
    public void testStartingPositionsRE2x() {
        Assert.assertEquals(7, sP.getStartingPosition(1).getX());
    }
}
