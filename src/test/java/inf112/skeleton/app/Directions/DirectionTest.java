package inf112.skeleton.app.Directions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {
    int east;
    int south;
    int west;
    int north;
    Direction northDir;
    Direction southDir;
    Direction westDir;
    Direction eastDir;

    @Before
    public void setUp() throws Exception {
        east = 1;
        south = 2;
        west = 3;
        north = 0;
        northDir = Direction.NORTH;
        southDir = Direction.SOUTH;
        westDir = Direction.WEST;
        eastDir = Direction.EAST;

    }

    @Test
    public void randomDirection() {
        assertTrue(Direction.randomDirection() instanceof Direction);

    }

    @Test
    public void getDir() {
        assertEquals(Direction.getDir(east), eastDir);
        assertEquals(Direction.getDir(west), westDir);
        assertEquals(Direction.getDir(north), northDir);
        assertEquals(Direction.getDir(south), southDir);

    }

    @Test
    public void getValue() {
        assertEquals(Direction.getValue(eastDir), east);
        assertEquals(Direction.getValue(northDir), north);
        assertEquals(Direction.getValue(westDir), west);
        assertEquals(Direction.getValue(southDir), south);
    }

    @Test
    public void rotate() {
        int clockwise = 1;
        int counterClockWise = -1;

        assertEquals(Direction.rotate(northDir, clockwise), eastDir);

        assertEquals(Direction.rotate(westDir, counterClockWise), southDir);

        assertEquals(Direction.rotate(northDir, 0), northDir);


    }
}