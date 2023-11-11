package core;

import tileengine.TETile;
import tileengine.Tileset;

/** Contains the world map and the game state for persistence. */
public class Map {

    private static final int WORLD_LENGTH = 80;
    private static final int WORLD_HEIGHT = 40;
    private static final int WALL_ID = -1;
    private static final int NOTHING_ID = -2;
    private static final int AVATAR_ID = -3;
    private TETile[][] world;
    private int[][] gameState;

    /** Creates a new Map instance. */
    public Map() {
        world = new TETile[WORLD_LENGTH][WORLD_HEIGHT];
        gameState = new int[WORLD_LENGTH][WORLD_HEIGHT];
        setUpWorld();
    }

    /** Fills the world array with NOTHINGs. */
    private void setUpWorld() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                world[i][j] = Tileset.NOTHING;
                gameState[i][j] = NOTHING_ID;
            }
        }
    }

    public TETile[][] getWorld() {
        return world;
    }

    public int getWorldLength() {
        return WORLD_LENGTH;
    }

    public int getWorldHeight() {
        return WORLD_HEIGHT;
    }

    /** Checks if a set of coordinates is inside the world. */
    public boolean validatePosition(int x, int y) {
        return 0 <= x && x < WORLD_LENGTH && 0 <= y && y < WORLD_HEIGHT;
    }

    /** Checks if a position is empty. */
    public boolean checkEmpty(int x, int y) {
        return world[x][y] != Tileset.NOTHING;
    }

    /** Places a wall and records its position in gameState. */
    public void placeWall(int x, int y) {
        world[x][y] = Tileset.WALL;
        gameState[x][y] = WALL_ID;
    }

    /** Places a wall if the position is open. */
    public void placeWallIfEmpty(int x, int y) {
        if (world[x][y] == Tileset.NOTHING) {
            placeWall(x, y);
        }
    }

    /** Places a wall and records its position in gameState. */
    public void placeFloor(int x, int y, int id) {
        world[x][y] = Tileset.FLOOR;
        gameState[x][y] = id;
    }

    /** Places the player's avatar. */
    public void placePlayer(int x, int y) {
        world[x][y] = Tileset.AVATAR;
        gameState[x][y] = AVATAR_ID;
    }
}
