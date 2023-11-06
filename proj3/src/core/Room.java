package core;

import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.util.Random;

public class Room {

    private static final int MIN_ROOM_LENGTH = 5;
    private static final int MIN_ROOM_HEIGHT = 5;
    private static final int MAX_ROOM_LENGTH = 10;
    private static final int MAX_ROOM_HEIGHT = 10;

    private int length;
    private int height;
    public Coordinate bottomLeft;
    public Coordinate bottomRight;
    public Coordinate topLeft;
    public Coordinate topRight;
    private boolean drewRoom = false;

    public Room(Random random, TETile[][] world) {
        length = RandomUtils.uniform(random, MIN_ROOM_LENGTH, MAX_ROOM_LENGTH);
        height = RandomUtils.uniform(random, MIN_ROOM_HEIGHT, MAX_ROOM_HEIGHT);
        int bottomLeftX = RandomUtils.uniform(random, MAX_ROOM_LENGTH);
        int bottomLeftY = RandomUtils.uniform(random, MAX_ROOM_HEIGHT);
        bottomLeft = new Coordinate(bottomLeftX, bottomLeftY);
        bottomRight = new Coordinate(bottomLeftX + length, bottomLeftY);
        topLeft = new Coordinate(bottomLeftX, bottomLeftY + height);
        topRight = new Coordinate(bottomLeftX + length, bottomLeftY + height);
        if (!overlaps(world)) {
            drawRoom(world);
            drewRoom = true;
        }
    }

    /** Checks if the new room overlaps with any old rooms. */
    public boolean overlaps(TETile[][] world) {
        for (int i = bottomLeft.getX(); i <= bottomRight.getX(); i++) {
            for (int j = bottomLeft.getY(); j <= topRight.getY(); j++) {
                if (world[i][j] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Draws the room on the grid. */
    public void drawRoom(TETile[][] world) {
        for (int i = bottomLeft.getX(); i <= bottomRight.getX(); i++) {
            for (int j = bottomLeft.getY(); j <= topRight.getY(); j++) {
                if (isEdge(i, j)) {
                    world[i][j] = Tileset.WALL;
                } else {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
    }

    public boolean isEdge(int x, int y) {
        return x == bottomLeft.getX() || x == bottomRight.getX()
                || y == bottomLeft.getY() || y == topRight.getY();
    }

    public boolean drewRoom() {
        return drewRoom;
    }
}
