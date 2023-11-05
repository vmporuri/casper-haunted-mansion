package core;

import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.util.Random;

public class Room {

    private static final int MIN_ROOM_LENGTH = 3;
    private static final int MIN_ROOM_HEIGHT = 3;
    private static final int MAX_ROOM_LENGTH = 10;
    private static final int MAX_ROOM_HEIGHT = 10;

    private int length;
    private int height;
    private Coordinate bottomLeft;
    private Coordinate bottomRight;
    private Coordinate topLeft;
    private Coordinate topRight;

    public Room(Random random, int maxRoomX, int maxRoomY, TETile[][] world) {
        length = RandomUtils.uniform(random, MIN_ROOM_LENGTH, MAX_ROOM_LENGTH);
        height = RandomUtils.uniform(random, MIN_ROOM_HEIGHT, MAX_ROOM_HEIGHT);
        int bottomLeftX = RandomUtils.uniform(random, maxRoomX);
        int bottomLeftY = RandomUtils.uniform(random, maxRoomY);
        bottomLeft = new Coordinate(bottomLeftX, bottomLeftY);
        bottomRight = new Coordinate(bottomLeftX + length, bottomLeftY);
        topLeft = new Coordinate(bottomLeftX, bottomLeftY + height);
        topRight = new Coordinate(bottomLeftX + length, bottomLeftY + height);
        if (!overlaps(world)) {
            drawRoom();
        }
    }

    public boolean overlaps(TETile[][] world) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                if (world[i][j] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }

    public void drawRoom() {

    }

}
