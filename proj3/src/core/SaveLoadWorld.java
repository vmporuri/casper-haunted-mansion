package core;

import tileengine.TETile;
import utils.FileUtils;

import java.io.*;

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

    /** Loads the world from save_file.txt if it exists. */
    public static Map loadWorld() {
        // @source Usage of Serializable interface from
        // https://www.simplilearn.com/tutorials/java-tutorial/serialization-in-java
        try {
            FileInputStream fileIn = new FileInputStream(SAVE_FILE_NAME);
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            Map map = (Map) inputStream.readObject();
            inputStream.close();
            fileIn.close();
            return map;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading save file.");
            return null;
        }
    }
}
