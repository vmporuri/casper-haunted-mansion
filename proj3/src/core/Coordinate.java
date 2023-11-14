package core;

import java.io.Serializable;

/** Stores an x and y coordinate pair. */
public class Coordinate implements Serializable {

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

    /** Adds two coordinates together. */
    public Coordinate plus(Coordinate other) {
        int sumX = x + other.getX();
        int sumY = y + other.getY();
        return new Coordinate(sumX, sumY);
    }

    /** Subtracts OTHER from THIS Coordinate. */
    public Coordinate minus(Coordinate other) {
        int diffX = x - other.getX();
        int diffY = y - other.getY();
        return new Coordinate(diffX, diffY);
    }

    /** Computes the coordinates of the hinge with THIS's x and OTHER's y coordinate. */
    public Coordinate hinge(Coordinate other) {
        return new Coordinate(x, other.getY());
    }

    /** Two coordinates are equal if and only if their x and y coordinates are the same. */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinate other) {
            return x == other.getX() && y == other.getY();
        }
        return false;
    }

    /** Computes the distance between THIS and OTHER. */
    public double distance(Coordinate other) {
        double differenceX = x - other.getX();
        double differenceY = y - other.getY();
        return Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));
    }
}
