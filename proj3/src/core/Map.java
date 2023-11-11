package core;

import tileengine.TETile;
import tileengine.Tileset;

/** Contains the world map and the game state for persistence. */
public class Map {

    private static final int WORLD_LENGTH = 80;
    private static final int WORLD_HEIGHT = 40;
    private static final int NOTHING_ID = -1;
    private static final int WALL_ID = -2;
    private static final int HALLWAY_ID = -3;
    private static final int AVATAR_ID = -4;
    private final TETile[][] world;
    private final int[][] gameState;

    /** Creates a new Map instance. */
    public Map() {
        world = new TETile[WORLD_LENGTH][WORLD_HEIGHT];
        gameState = new int[WORLD_LENGTH][WORLD_HEIGHT];
        setUpWorld();
    }

    /** Fills the world array with NOTHINGs. */
    private void setUpWorld() {
        for (int i = 0; i < WORLD_LENGTH; i++) {
            for (int j = 0; j < WORLD_HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
                gameState[i][j] = NOTHING_ID;
            }
        }
    }

    /** Returns the world as an array of tiles. */
    public TETile[][] getWorld() {
        return world;
    }

    /** Returns the world length. */
    public int getWorldLength() {
        return WORLD_LENGTH;
    }

    /** Returns the world height. */
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
    public void placeFloor(int x, int y) {
        world[x][y] = Tileset.FLOOR;
        if (getID(x, y) == NOTHING_ID) {
            gameState[x][y] = HALLWAY_ID;
        }
    }

    /** Places a wall and records its position in gameState. */
    public void placeFloorWithID(int x, int y, int id) {
        placeFloor(x, y);
        gameState[x][y] = id;
    }

    /** Retrieves the ID of the location if there is one. */
    public int getID(int x, int y) {
        return gameState[x][y];
    }

    /** Returns true if an ID is a room id and false otherwise. */
    public boolean isRoomID(int id) {
        return id >= 0;
    }

    /** Places the player's avatar. */
    public void placePlayer(int x, int y) {
        world[x][y] = Tileset.AVATAR;
        gameState[x][y] = AVATAR_ID;
    }
}
