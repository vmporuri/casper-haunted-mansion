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
        Coordinate startCenter = startRoom.getRoomCenter();
        Coordinate endCenter = endRoom.getRoomCenter();
        boolean horizontalFirst = random.nextBoolean();
        if (horizontalFirst) {
            Coordinate hinge = endCenter.hinge(startCenter);
            drawHorizontalHallway(startCenter, hinge, startCenter.getY());
            drawVerticalHallway(hinge, endCenter, endCenter.getX());
        } else {
            Coordinate hinge = startCenter.hinge(endCenter);
            drawVerticalHallway(startCenter, hinge, startCenter.getX());
            drawHorizontalHallway(hinge, endCenter, endCenter.getY());
        }
    }

    /** Draws the horizontal portion of an L-shaped hallway. */
    private void drawHorizontalHallway(Coordinate start, Coordinate end, int y) {
        // @source https://www.geeksforgeeks.org/integer-signum-method-in-java/#
        int increment = Integer.signum(end.getX() - start.getX());
        Coordinate loopIncrement = new Coordinate(increment, 0);
        Coordinate wallIncrement = new Coordinate(0, 1);
        drawHallway(start, end, loopIncrement, wallIncrement);
    }

    /** Draws the vertical portion of an L-shaped hallway. */
    private void drawVerticalHallway(Coordinate start, Coordinate end, int x) {
        // @source https://www.geeksforgeeks.org/integer-signum-method-in-java/#
        int increment = Integer.signum(end.getY() - start.getY());
        Coordinate loopIncrement = new Coordinate(0, increment);
        Coordinate wallIncrement = new Coordinate(1, 0);
        drawHallway(start, end, loopIncrement, wallIncrement);
    }

    /** Draws a hallway. */
    private void drawHallway(Coordinate start, Coordinate end, Coordinate loopIncrement, Coordinate wallIncrement) {
        if (loopIncrement.equals(new Coordinate(0, 0))) {
            return;
        }
        for (Coordinate shovel = start; !shovel.equals(end.plus(loopIncrement)); shovel = shovel.plus(loopIncrement)) {
            map.placeWallIfEmpty(shovel.plus(wallIncrement));
            map.placeFloor(shovel);
            map.placeWallIfEmpty(shovel.minus(wallIncrement));
            int id = map.getID(shovel);
            if (map.isRoomID(id)) {
                wqu.union(startRoom.getRoomID(), id);
            }
        }

        // Adds the corner to the hallway
        map.placeWallIfEmpty(end.plus(loopIncrement).plus(wallIncrement));
        map.placeWallIfEmpty(end.plus(loopIncrement).minus(wallIncrement));
    }
}
