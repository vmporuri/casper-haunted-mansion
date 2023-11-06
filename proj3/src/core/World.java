package core;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    private static final int WORLD_LENGTH = 80;
    private static final int WORLD_HEIGHT = 40;
    private static final int MAX_NUM_ROOMS = 100;

    private TETile[][] world;
    private List<Room> rooms;
    private Random random;
    private WeightedQuickUnionUF wqu;

    /** Generates a random world without an input string. */
    public World() {
        world = new TETile[WORLD_LENGTH][WORLD_HEIGHT];
        this.random = new Random();
        setUpWorld();
        drawAllRooms();
        drawAllHallways();
    }

    /** Generates a random world given an input string. */
    public World(String inputString) {
        world = new TETile[WORLD_LENGTH][WORLD_HEIGHT];
        this.random = new Random(getSeed(inputString));
        setUpWorld();
        drawAllRooms();
        drawAllHallways();
    }

    /** Retrieves the seed from an input string. */
    private long getSeed(String inputString) {
        if (inputString.toUpperCase().charAt(0) == 'N'
            && inputString.toUpperCase().charAt(inputString.length()-1) == 'S') {
            String seedString = inputString.substring(1, inputString.length()-2);
            return Long.parseLong(seedString); // {@source https://stackoverflow.com/a/7693344}
        }
        return 0;
    }

    /** Returns the world. */
    public TETile[][] getWorld() {
        return world;
    }

    /** Fills the world array with NOTHINGs. */
    private void setUpWorld() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    /** Draws up to MAX_NUM_ROOMS rooms. */
    private void drawAllRooms() {
        rooms = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_ROOMS; i++) {
            Room newRoom = new Room(random, world);
            if (newRoom.drewRoom()) {
                rooms.add(newRoom);
            }
        }
    }

    /** Draws hallways until all rooms are connected. */
    private void drawAllHallways() {
        wqu = new WeightedQuickUnionUF(rooms.size());
        while (wqu.count() != 1) {
            int rand1 = RandomUtils.uniform(random, rooms.size());
            int rand2 = RandomUtils.uniform(random, rooms.size());
            Room room1 = rooms.get(rand1);
            Room room2 = rooms.get(rand2);
            Hallway hw = new Hallway(world, random, room1, room2);
            wqu.union(rand1, rand2);
        }
    }
}
