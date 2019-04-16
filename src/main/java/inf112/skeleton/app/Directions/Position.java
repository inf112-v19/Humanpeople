package inf112.skeleton.app.Directions;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position north() {
        return new Position(x, y + 1);
    }

    public Position south() {
        return new Position(x, y - 1);
    }

    public Position east() {
        return new Position(x + 1, y);
    }

    public Position west() {
        return new Position(x - 1, y);
    }
}
