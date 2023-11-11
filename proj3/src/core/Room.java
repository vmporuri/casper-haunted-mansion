package core;

import utils.RandomUtils;

import java.util.Random;

/** A rectangular room. */
public class Room {

    private static final int MIN_ROOM_LENGTH = 6;
    private static final int MIN_ROOM_HEIGHT = 6;
    private static final int MAX_ROOM_LENGTH = 12;
    private static final int MAX_ROOM_HEIGHT = 12;

    private final int roomID;
    private int length;
    private int height;
    private Coordinate bottomLeft;
    private Coordinate topRight;
    private boolean drewRoom = false;

    /** Creates a new Room instance. */
    public Room(Random random, Map map, int roomID) {
        this.roomID = roomID;
        setRoomDimensions(random, map);
        setRoomLocation(random, map);
        if (validateRoom(map)) {
            drawRoom(map);
            drewRoom = true;
        }
    }

    /** Returns the coordinates of the bottom-left corner of the room. */
    public Coordinate getBottomLeft() {
        return bottomLeft;
    }

    /** Returns the coordinates of the bottom-left corner of the room. */
    public Coordinate getTopRight() {
        return topRight;
    }

    /** Randomly assigns a size for the room. */
    private void setRoomDimensions(Random random, Map map) {
        length = RandomUtils.uniform(random, MIN_ROOM_LENGTH, MAX_ROOM_LENGTH);
        height = RandomUtils.uniform(random, MIN_ROOM_HEIGHT, MAX_ROOM_HEIGHT);
    }

    /** Randomly assigns a location for the room. */
    private void setRoomLocation(Random random, Map map) {
        int bottomLeftX = RandomUtils.uniform(random, map.getWorldLength() - 1);
        int bottomLeftY = RandomUtils.uniform(random, map.getWorldHeight() - 1);
        bottomLeft = new Coordinate(bottomLeftX, bottomLeftY);
        topRight = new Coordinate(bottomLeftX + length, bottomLeftY + height);
    }

    /** Checks if the new room overlaps with any old rooms. */
    private boolean validateRoom(Map map) {
        for (int i = bottomLeft.getX(); i <= topRight.getX(); i++) {
            for (int j = bottomLeft.getY(); j <= topRight.getY(); j++) {
                if (!map.validatePosition(i, j)) {
                    return false;
                }
                if (map.checkEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Draws the room on the grid. */
    private void drawRoom(Map map) {
        for (int i = bottomLeft.getX(); i <= topRight.getX(); i++) {
            for (int j = bottomLeft.getY(); j <= topRight.getY(); j++) {
                if (isEdge(i, j)) {
                    map.placeWall(i, j);
                } else {
                    map.placeFloorWithID(i, j, roomID);
                }
            }
        }
    }

    /** Returns true if it is an edge of the room. */
    private boolean isEdge(int x, int y) {
        return x == bottomLeft.getX() || x == topRight.getX()
                || y == bottomLeft.getY() || y == topRight.getY();
    }

    /** Returns true if we drew the room. */
    public boolean drewRoom() {
        return drewRoom;
    }

    /** Returns the location of the center of the room. */
    public Coordinate getRoomCenter() {
        return bottomLeft.average(topRight);
    }

    /** Returns the roomID. */
    public int getRoomID() {
        return roomID;
    }
}
