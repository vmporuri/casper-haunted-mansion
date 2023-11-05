package core;

/** Stores an x and y coordinate pair. */
public class Coordinate {

    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] get() {
        return new int[]{x, y};
    }
}
