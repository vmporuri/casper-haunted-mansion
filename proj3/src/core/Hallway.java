package core;

import java.util.Random;

public class Hallway {

    private Map map;
    private Room startRoom;
    private Room endRoom;
    private Random random;
    private int hallwayID;

    /** Makes a brand new Hallway. */
    public Hallway(Map map, Random random, Room room1, Room room2, int hallwayID) {
        this.map = map;
        this.random = random;
        startRoom = room1;
        endRoom = room2;
        this.hallwayID = hallwayID;
        drawHallway();
    }

    /** Draws an L-shaped hallway connecting STARTROOM and ENDROOM. */
    private void drawHallway() {
        Coordinate startCenter = startRoom.bottomLeft.average(startRoom.topRight);
        Coordinate endCenter = endRoom.bottomLeft.average(endRoom.topRight);
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
            map.placeFloor(i, y, hallwayID);
            map.placeWallIfEmpty(i, y + 1);
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
            map.placeFloor(x, i, hallwayID);
            map.placeWallIfEmpty(x + 1, i);
        }
    }
}
