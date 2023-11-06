package core;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import tileengine.TETile;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    private static final int WORLD_LENGTH = 50;
    private static final int WORLD_HEIGHT = 30;
    private static final int MAX_NUM_ROOMS = 20;

    private TETile[][] world;
    private List<Room> rooms;
    private Random random;
    private WeightedQuickUnionUF wqu;

    /** Generates a random world. */
    public World(Random random) {
        world = new TETile[WORLD_LENGTH][WORLD_HEIGHT];
        this.random = random;
        drawAllRooms();
        drawAllHallways();
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
