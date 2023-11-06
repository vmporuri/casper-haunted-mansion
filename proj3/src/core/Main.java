package core;

import tileengine.TERenderer;
import tileengine.TETile;

public class Main {
    public static void main(String[] args) {
        // {@source} From Proj 2, Video 2 Skeleton Code Structure
        if (args.length > 1) {
            System.out.println("Can only have one argument.");
            System.exit(0);
        } else if (args.length == 1) {
            World world = new World(args[0]);
            TETile[][] worldState = world.getWorld();
            TERenderer ter = new TERenderer();
            ter.initialize(worldState.length, worldState[0].length);
            ter.renderFrame(worldState);
        } else {
            World world = new World();
            TETile[][] worldState = world.getWorld();
            TERenderer ter = new TERenderer();
            ter.initialize(worldState.length, worldState[0].length);
            ter.renderFrame(worldState);
        }
    }
}
