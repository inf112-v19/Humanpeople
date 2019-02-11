package inf112.skeleton.app.GameObjects.Directions;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position North(){
        y = ++y;
        return this;
    }
    public Position South(){
        y = --y;
        return this;
    }
    public Position East(){
        x = ++x;
        return this;
    }
    public Position West(){
        x = --x;
        return this;
    }
}
