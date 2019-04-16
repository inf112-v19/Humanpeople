package inf112.skeleton.app.Directions;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {


    @Test
    public void getX() {
        int x = 8;
        Position p = new Position(x, 2);
        assertEquals(p.getX(), x);
    }

    @Test
    public void getY() {
        int y = 4;
        Position p = new Position(4, y);
        assertEquals(p.getY(), y);
    }

    @Test
    public void north() {
        Position pos = new Position(3, 3);
        Position northPos = new Position(3, 4);
        assertEquals(pos.north().getY(), northPos.getY());

    }

    @Test
    public void south() {
        Position pos = new Position(3, 3);
        Position southPos = new Position(3, 2);
        assertEquals(pos.south().getY(), southPos.getY());
    }

    @Test
    public void east() {
        Position pos = new Position(3, 3);
        Position eastPos = new Position(4, 3);
        assertEquals(pos.east().getX(), eastPos.getX());
    }

    @Test
    public void west() {
        Position pos = new Position(3, 3);
        Position westPos = new Position(2, 3);
        assertEquals(pos.west().getX(), westPos.getX());
    }
}