package core;

import tileengine.TETile;

import java.util.List;
import java.util.Random;

public class World {

    // build your own world!
    private static final int WORLD_LENGTH = 50;
    private static final int WORLD_HEIGHT = 30;
    private static final int MAX_NUM_ROOMS = 20;
    private TETile[][] world;
    private List<Room> rooms;
    private Random random;

    public World(Random random) {
        world = new TETile[WORLD_LENGTH][WORLD_HEIGHT];
        this.random = random;
        drawAllRooms();
    }

    private void drawAllRooms() {
        for (int i = 0; i < MAX_NUM_ROOMS; i++) {
            Room newRoom = new Room(random, world);
            if (newRoom.drewRoom()) {
                rooms.add(newRoom);
            }
        }
    }

}
