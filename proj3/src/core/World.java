package core;

import tileengine.TETile;

/** The game. */
public class World {

    private final Map map;

    /** Creates the world. */
    public World(long seed) {
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
            // the game loop
        }
    }
}
