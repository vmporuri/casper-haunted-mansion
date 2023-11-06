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

    public Coordinate average(Coordinate other) {
        int midX = (x + other.getX()) / 2;
        int midY = (y + other.getY()) / 2;
        return new Coordinate(midX, midY);
    }

    public Coordinate findCorner(Coordinate other) {
        int cornerX = other.getX() - x;
        int cornerY = other.getY() - y;
        return new Coordinate(cornerX, cornerY);
    }
}
