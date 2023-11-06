package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.Random;

public class Hallway {

    private TETile[][] world;
    private Room startRoom;
    private Room endRoom;
    private Random random;

    public Hallway(TETile[][] world, Random random, Room room1, Room room2) {
        this.world = world;
        startRoom = room1;
        endRoom = room2;
        drawHallway();
    }

    /** Draws an L-shaped hallway connecting STARTROOM and ENDROOM. */
    private void drawHallway() {
        Coordinate startCenter = startRoom.bottomLeft.average(startRoom.topRight);
        Coordinate endCenter = endRoom.bottomLeft.average(endRoom.topRight);
        Coordinate hinge = startCenter.findCorner(endCenter);
        boolean drawUpsideDown = random.nextBoolean();
        if (drawUpsideDown) {
            drawUpsideDownL(startCenter, endCenter, hinge);
        } else {
            drawUprightL(startCenter, endCenter, hinge);
        }
    }

    private void drawUpsideDownL(Coordinate startCenter, Coordinate endCenter, Coordinate hinge) {
        int startX = startCenter.getX();
        int startY = startCenter.getY();
        int hingeX = hinge.getX();
        int hingeY = hinge.getY();
        for (int j = startY; j <= hingeY; j++) {
            if (world[startX-1][j] == Tileset.NOTHING) {
                world[startX-1][j] = Tileset.WALL;
            }
            world[startX][j] = Tileset.FLOOR;
            if (world[startX+1][j] == Tileset.NOTHING) {
                world[startX+1][j] = Tileset.WALL;
            }
        }
        for (int i = startX; i <= endCenter.getX(); i++) {
            if (world[i][hingeY-1] == Tileset.NOTHING) {
                world[i][hingeY-1] = Tileset.WALL;
            }
            world[i][hingeX] = Tileset.FLOOR;
            if (world[i][hingeY+1] == Tileset.NOTHING) {
                world[i][hingeY+1] = Tileset.WALL;
            }
        }
    }

    private void drawUprightL(Coordinate startCenter, Coordinate endCenter, Coordinate hinge) {
        int startX = startCenter.getX();
        int startY = startCenter.getY();
        int hingeX = hinge.getX();
        int hingeY = hinge.getY();
        for (int i = startX; i <= endCenter.getX(); i++) {
            if (world[i][startY-1] == Tileset.NOTHING) {
                world[i][startY-1] = Tileset.WALL;
            }
            world[i][hingeX] = Tileset.FLOOR;
            if (world[i][startY+1] == Tileset.NOTHING) {
                world[i][startY+1] = Tileset.WALL;
            }
        }
        for (int j = startY; j <= endCenter.getY(); j++) {
            if (world[hingeX-1][j] == Tileset.NOTHING) {
                world[hingeX-1][j] = Tileset.WALL;
            }
            world[startX][j] = Tileset.FLOOR;
            if (world[hingeX+1][j] == Tileset.NOTHING) {
                world[hingeX+1][j] = Tileset.WALL;
            }
        }
    }

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
