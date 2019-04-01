package inf112.skeleton.app.GameObjects;

import inf112.skeleton.app.Directions.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroundLayerObjectTest {
    GroundLayerObject northWall;
    GroundLayerObject northWall1;
    GroundLayerObject groundLayerObject2;
    Direction northDir;
    Direction southDir;
    Direction westDir;
    Direction eastDir;
    @Before
    public void setup() {
        northWall = new GroundLayerObject(1);
        northWall1 = new GroundLayerObject(1);
        groundLayerObject2 = new GroundLayerObject(2);

        northDir = Direction.NORTH;
        southDir = Direction.SOUTH;
        westDir = Direction.WEST;
        eastDir = Direction.EAST;

    }

    @Test
    public void canGoNorth() {
        assertFalse(northWall.canGo(Direction.NORTH));
    }

    @Test
    public void canGoSouth() {
        GroundLayerObject southWall = new GroundLayerObject(2);
        assertFalse(southWall.canGo(southDir));

    }
    @Test
    public void canGoEast() {
        GroundLayerObject eastWall = new GroundLayerObject(3);
        assertFalse(eastWall.canGo(eastDir));
    }
    @Test
    public void canGoWest() {
        GroundLayerObject westWall = new GroundLayerObject(4);
        assertFalse(westWall.canGo(westDir));

    }
    @Test
    public void canGoNorthEast() {
        GroundLayerObject northEastWall = new GroundLayerObject(12);
        assertFalse(northEastWall.canGo(northDir));
        assertFalse(northEastWall.canGo(eastDir));
    }
    @Test
    public void canGoNorthWest() {
        GroundLayerObject northWestWall = new GroundLayerObject(11);
        assertFalse(northWestWall.canGo(northDir));
        assertFalse(northWestWall.canGo(westDir));
    }
    @Test
    public void canGoSouthWest() {
        GroundLayerObject southWestWall = new GroundLayerObject(13);
        assertFalse(southWestWall.canGo(southDir));
        assertFalse(southWestWall.canGo(westDir));
    }
    @Test
    public void canGoSouthEast() {
        GroundLayerObject southEastWall = new GroundLayerObject(14);
        assertFalse(southEastWall.canGo(eastDir));
        assertFalse(southEastWall.canGo(southDir));

    }

    @Test(expected =  IllegalArgumentException.class)
    public void illegalIdTest() {
        GroundLayerObject illegalId = new GroundLayerObject(991234);
    }

    @Test
    public void getId() {
        assertEquals(northWall.getId(), 1);
    }

    @Test
    public void equals() {
        assertTrue(northWall.equals(northWall1));
    }

    @Test
    public void compareTo() {
        assertTrue(northWall.compareTo(northWall1) == 0);
    }
}