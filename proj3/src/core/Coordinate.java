package core;

/** Stores an x and y coordinate pair. */
public class Coordinate {

    int x;
    int y;

    /** Creates a new Coordinate instance. */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Gets the x-coordinate. */
    public int getX() {
        return x;
    }

    /** Gets the y-coordinate. */
    public int getY() {
        return y;
    }

    /** Returns the average position of two coordinates. */
    public Coordinate average(Coordinate other) {
        int midX = (x + other.getX()) / 2;
        int midY = (y + other.getY()) / 2;
        return new Coordinate(midX, midY);
    }
}
