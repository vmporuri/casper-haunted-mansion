package core;

import tileengine.TETile;
import utils.FileUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/** Handles persistence. */
public class SaveLoadWorld {

    private static final String SAVE_FILE_NAME = "save_file.txt";

    /** Saves the world to save_file.txt */
    public static void saveWorld(Map map) {
        // @source Usage of Serializable interface from
        // https://www.simplilearn.com/tutorials/java-tutorial/serialization-in-java
        try {
            FileOutputStream fileOut = new FileOutputStream(SAVE_FILE_NAME);
            ObjectOutputStream output = new ObjectOutputStream(fileOut);
            output.writeObject(map);
            output.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Could not save the game.");
        }
    }
}
