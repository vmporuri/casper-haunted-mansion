package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.io.Serializable;

/** Contains the world map and the game state for persistence. */
public class Map implements Serializable {

    private static final int WORLD_LENGTH = 80;
    private static final int WORLD_HEIGHT = 40;
    private static final int NOTHING_ID = -1;
    private static final int WALL_ID = -2;
    private static final int HALLWAY_ID = -3;
    private static final int AVATAR_ID = -4;
    private final TETile[][] world;
    private final int[][] gameState;
    private Coordinate playerLocation;

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

    /** Checks if a set of coordinates is inside the world. */
    public boolean validatePosition(Coordinate coords) {
        return validatePosition(coords.getX(), coords.getY());
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

    /** Places a wall if the position is open. */
    public void placeWallIfEmpty(Coordinate coords) {
        placeWallIfEmpty(coords.getX(), coords.getY());
    }

    /** Returns true if the location is a wall. */
    public boolean isWall(int x, int y) {
        return world[x][y] == Tileset.WALL;
    }

    /** Returns true if the Coordinate is a wall. */
    public boolean isWall(Coordinate coords) {
        return isWall(coords.getX(), coords.getY());
    }

    /** Places a floor tile and records its position in gameState. */
    public void placeFloor(int x, int y) {
        world[x][y] = Tileset.FLOOR;
        if (!isRoomID(getID(x, y))) {
            gameState[x][y] = HALLWAY_ID;
        }
    }

    /** Places a floor tile and records its position in gameState. */
    public void placeFloor(Coordinate coords) {
        placeFloor(coords.getX(), coords.getY());
    }

    /** Places a floor tile and records its position in gameState. */
    public void placeFloorWithID(int x, int y, int id) {
        placeFloor(x, y);
        gameState[x][y] = id;
    }

    /** Retrieves the ID of the location if there is one. */
    public int getID(int x, int y) {
        return gameState[x][y];
    }

    /** Retrieves the ID of the location if there is one. */
    public int getID(Coordinate coords) {
        return getID(coords.getX(), coords.getY());
    }

    /** Returns true if an ID is a room id and false otherwise. */
    public boolean isRoomID(int id) {
        return id >= 0;
    }

    /** Places the player's avatar. */
    public void placePlayer(int x, int y) {
        placePlayer(new Coordinate(x, y));
    }

    /** Places the player's avatar. */
    public void placePlayer(Coordinate coords) {
        int x = coords.getX();
        int y = coords.getY();
        world[x][y] = Tileset.AVATAR;
        gameState[x][y] = AVATAR_ID;
        playerLocation = coords;
    }

    /** Gets the player's location. */
    public Coordinate getPlayerLocation() {
        return playerLocation;
    }

    /** Returns the tile at a provided location. */
    public TETile getTileAtLocation(int x, int y) {
        return world[x][y];
    }

    /** Returns the tile at COORDS. */
    public TETile getTileAtLocation(Coordinate coords) {
        return getTileAtLocation(coords.getX(), coords.getY());
    }
}
