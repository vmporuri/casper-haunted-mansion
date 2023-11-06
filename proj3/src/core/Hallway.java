package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.Random;

public class Hallway {

    private TETile[][] world;
    private Room startRoom;
    private Room endRoom;
    private Random random;

    /** Makes a brand new Hallway. */
    public Hallway(TETile[][] world, Random random, Room room1, Room room2) {
        this.world = world;
        this.random = random;
        startRoom = room1;
        endRoom = room2;
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
        for (int i = start.getX(); i <= end.getX(); i = i + increment) {
            if (world[i][y-1] == Tileset.NOTHING) {
                world[i][y-1] = Tileset.WALL;
            }
            world[i][y] = Tileset.FLOOR;
            if (world[i][y+1] == Tileset.NOTHING) {
                world[i][y+1] = Tileset.WALL;
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
        for (int i = start.getX(); i <= end.getX(); i = i + increment) {
            if (world[x-1][i] == Tileset.NOTHING) {
                world[x-1][i] = Tileset.WALL;
            }
            world[x][i] = Tileset.FLOOR;
            if (world[x+1][i] == Tileset.NOTHING) {
                world[x+1][i] = Tileset.WALL;
            }
        }
    }
}
