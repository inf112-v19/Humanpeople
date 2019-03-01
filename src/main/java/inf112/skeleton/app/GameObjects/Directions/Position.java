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
        //y = ++y;
        return new Position(x,y+1);
    }
    public Position South(){
        //y = --y;
        return new Position(x,y-1);
    }
    public Position East(){
       // x = ++x;
        return new Position(x+1,y);
    }
    public Position West(){
       // x = --x;
        return new Position(x-1,y);
    }
}
