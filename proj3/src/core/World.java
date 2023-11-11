package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;

/** The game. */
public class World {

    private final TERenderer ter;
    private final Map map;

    /** Creates the world. */
    public World(long seed, TERenderer ter) {
        this.ter = ter;
        RandomWorldGenerator rwg = new RandomWorldGenerator(seed);
        map = rwg.getMap();
    }

    /** Returns the world of tiles. */
    public TETile[][] getWorld() {
        return map.getWorld();
    }

    /** The game loop. */
    public void playGame() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char nextChar = Character.toLowerCase(StdDraw.nextKeyTyped());
                moveAvatar(nextChar);
                ter.renderFrame(map.getWorld());
            }
        }
    }

    /** Plays the game from a string. */
    public void playGameFromString() {}

    /** Move avatar on the map. */
    private void moveAvatar(char moveChar) {
        Coordinate movement;
        switch (moveChar) {
            case 'w' -> movement = new Coordinate(0, 1);
            case 'a' -> movement = new Coordinate(-1, 0);
            case 's' -> movement = new Coordinate(0, -1);
            case 'd' -> movement = new Coordinate(1, 0);
            default -> movement = new Coordinate(0, 0);
        }
        Coordinate oldLocation = map.getPlayerLocation();
        Coordinate newLocation = oldLocation.plus(movement);
        if (!map.isWall(newLocation)) {
            map.placePlayer(newLocation);
            map.placeFloor(oldLocation);
        }
    }
}
