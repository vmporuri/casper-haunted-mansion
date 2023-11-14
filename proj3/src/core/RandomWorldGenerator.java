package core;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import tileengine.TETile;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Generates a world randomly. */
public class RandomWorldGenerator {

    private static final int MAX_NUM_ROOMS = 25;

    private final Map map;
    private List<Room> rooms;
    private List<Hallway> hallways;
    private final Random random;
    private WeightedQuickUnionUF wqu;

    /** Generates a random world without an input string. */
    public RandomWorldGenerator(long seed) {
        map = new Map();
        this.random = new Random(seed);
        drawAllRooms();
        drawAllHallways();
        placePlayer();
    }

    /** Returns the world map. */
    public Map getMap() {
        return map;
    }

    /** Returns the world. */
    public TETile[][] getWorld() {
        return map.getWorld();
    }


    /** Draws up to MAX_NUM_ROOMS rooms. */
    private void drawAllRooms() {
        rooms = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_ROOMS; i++) {
            Room newRoom = new Room(random, map, rooms.size());
            if (newRoom.drewRoom()) {
                rooms.add(newRoom);
            }
        }
    }

    /** Draws hallways until all rooms are connected. */
    private void drawAllHallways() {
        hallways = new ArrayList<>();
        wqu = new WeightedQuickUnionUF(rooms.size());
        while (wqu.count() > 1) {
            int rand1 = RandomUtils.uniform(random, rooms.size());
            int rand2 = RandomUtils.uniform(random, rooms.size());
            Room room1 = rooms.get(rand1);
            Room room2 = rooms.get(rand2);
            Hallway hw = new Hallway(map, random, room1, room2, wqu);
            hallways.add(hw);
            wqu.union(rand1, rand2);
        }
    }

    /** Places the player's avatar in a random room. */
    private void placePlayer() {
        Room firstRoom = rooms.get(0);
        Coordinate center = firstRoom.getRoomCenter();
        map.placePlayer(center.getX(), center.getY());
    }

    /** Returns the random number generator used to create the world. */
    public Random getRNG() {
        return random;
    }
}
