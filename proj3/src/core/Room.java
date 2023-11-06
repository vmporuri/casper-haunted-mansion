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
    private int worldLength;
    private int worldHeight;
    public Coordinate bottomLeft;
    public Coordinate bottomRight;
    public Coordinate topLeft;
    public Coordinate topRight;
    private boolean drewRoom = false;

    public Room(Random random, TETile[][] world) {
        length = RandomUtils.uniform(random, MIN_ROOM_LENGTH, MAX_ROOM_LENGTH);
        height = RandomUtils.uniform(random, MIN_ROOM_HEIGHT, MAX_ROOM_HEIGHT);
        worldLength = world.length;
        worldHeight = world[0].length;
        int bottomLeftX = RandomUtils.uniform(random, worldLength - 1);
        int bottomLeftY = RandomUtils.uniform(random, worldHeight - 1);
        bottomLeft = new Coordinate(bottomLeftX, bottomLeftY);
        bottomRight = new Coordinate(bottomLeftX + length, bottomLeftY);
        topLeft = new Coordinate(bottomLeftX, bottomLeftY + height);
        topRight = new Coordinate(bottomLeftX + length, bottomLeftY + height);
        if (validateRoom(world)) {
            drawRoom(world);
            drewRoom = true;
        }
    }

    /** Checks if a set of coordinates is inside the world. */
    public boolean validatePosition(int x, int y) {
        return 0 <= x && x < worldLength && 0 <= y && y < worldHeight;
    }

    /** Checks if the new room overlaps with any old rooms. */
    public boolean validateRoom(TETile[][] world) {
        for (int i = bottomLeft.getX(); i <= bottomRight.getX(); i++) {
            for (int j = bottomLeft.getY(); j <= topRight.getY(); j++) {
                if (!validatePosition(i, j)) {
                    return false;
                }
                if (world[i][j] != Tileset.NOTHING) {
                    return false;
                }
            }
        }
        return true;
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
