package core;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Random;

/** A hallway between two rooms. */
public class Hallway {

    private final Map map;
    private final Room startRoom;
    private final Room endRoom;
    private final Random random;
    private final WeightedQuickUnionUF wqu;

    /** Makes a brand-new Hallway. */
    public Hallway(Map map, Random random, Room room1, Room room2, WeightedQuickUnionUF wqu) {
        this.map = map;
        this.random = random;
        startRoom = room1;
        endRoom = room2;
        this.wqu = wqu;
        wqu.union(startRoom.getRoomID(), endRoom.getRoomID());
        drawHallway();
    }

    /** Draws an L-shaped hallway connecting STARTROOM and ENDROOM. */
    private void drawHallway() {
        Coordinate startCenter = startRoom.getBottomLeft().average(startRoom.getTopRight());
        Coordinate endCenter = endRoom.getBottomLeft().average(endRoom.getTopRight());
        boolean horizontalFirst = random.nextBoolean();
        if (horizontalFirst) {
            drawHorizontalHallway(startCenter, endCenter, startCenter.getY());
            drawVerticalHallway(startCenter, endCenter, endCenter.getX());
        } else {
            drawVerticalHallway(startCenter, endCenter, startCenter.getX());
            drawHorizontalHallway(startCenter, endCenter, endCenter.getY());
        }
    }

    /** Draws the horizontal portion of an L-shaped hallway. */
    private void drawHorizontalHallway(Coordinate start, Coordinate end, int y) {
        // @source https://www.geeksforgeeks.org/integer-signum-method-in-java/#
        int increment = Integer.signum(end.getX() - start.getX());
        if (increment == 0) {
            return;
        }

        for (int i = start.getX(); i != end.getX() + increment; i = i + increment) {
            map.placeWallIfEmpty(i, y - 1);
            map.placeFloor(i, y);
            map.placeWallIfEmpty(i, y + 1);
            int id = map.getID(i, y);
            if (map.isRoomID(id)) {
                wqu.union(startRoom.getRoomID(), id);
            }
        }
    }

    /** Draws the vertical portion of an L-shaped hallway. */
    private void drawVerticalHallway(Coordinate start, Coordinate end, int x) {
        // @source https://www.geeksforgeeks.org/integer-signum-method-in-java/#
        int increment = Integer.signum(end.getY() - start.getY());
        if (increment == 0) {
            return;
        }

        for (int i = start.getY(); i != end.getY() + increment; i = i + increment) {
            map.placeWallIfEmpty(x - 1, i);
            map.placeFloor(x, i);
            map.placeWallIfEmpty(x + 1, i);
            int id = map.getID(x, i);
            if (map.isRoomID(id)) {
                wqu.union(startRoom.getRoomID(), id);
            }
        }
    }
}
