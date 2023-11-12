package core;

import tileengine.TETile;
import utils.FileUtils;

/** Handles persistence. */
public class SaveLoadWorld {

    /** Saves the world to save_file.txt */
    public static void saveWorld(Map map) {
        String fileName = "save_file.txt";
        StringBuilder worldSave = new StringBuilder();
        TETile[][] world = map.getWorld();
        for (TETile[] row : world) {
            for (TETile tile : row) {
                worldSave.append(tile.character());
            }
            worldSave.append('\n');
        }
        FileUtils.writeFile(fileName, worldSave.toString());
    }
}
