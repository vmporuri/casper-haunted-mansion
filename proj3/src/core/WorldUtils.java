package core;

import tileengine.TERenderer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

/** Various utilities for creating, saving, and loading worlds. */
public class WorldUtils {

    private static final String SAVE_FILE_NAME = "save_file.txt";

    /** Saves the world to save_file.txt
     * @source Usage of Serializable interface from
     * https://www.simplilearn.com/tutorials/java-tutorial/serialization-in-java
     * @source Using a List<Object> to serialize multiple objects from
     * https://stackoverflow.com/a/30022340
     */
    public static void saveWorld(World world) {
        try {
            FileOutputStream fileOut = new FileOutputStream(SAVE_FILE_NAME);
            ObjectOutputStream output = new ObjectOutputStream(fileOut);
            output.writeObject(world);
            output.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Could not save the game.");
        }
    }

    /** Loads the world from save_file.txt if it exists.
     * @source Usage of Serializable interface from
     * https://www.simplilearn.com/tutorials/java-tutorial/serialization-in-java
     * @source Using a List<Object> to serialize multiple objects from
     * https://stackoverflow.com/a/30022340
     */
    public static World loadWorld() {
        // @source Usage of Serializable interface from
        // https://www.simplilearn.com/tutorials/java-tutorial/serialization-in-java
        try {
            FileInputStream fileIn = new FileInputStream(SAVE_FILE_NAME);
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            World world = (World) inputStream.readObject();
            inputStream.close();
            fileIn.close();
            return world;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading save file.");
            return null;
        }
    }

    /** Processes world creation/loading from a provided input string. */
    public static World getWorldFromString(InputDevice input, TERenderer ter) {
        char nextChar = input.nextChar();
        switch (nextChar) {
            case 'n', 'N' -> {
                long seed = getSeedFromInputDevice(input);
                return new World(seed, input);
            }
            case 'l', 'L' -> {
                World world = loadWorld();
                if (world == null) {
                    System.exit(0);
                }
                return world;
            }
            default -> {
                return null;
            }
        }
    }

    /** Extracts the seed from the input device. */
    private static long getSeedFromInputDevice(InputDevice input) {
        StringBuilder seedString = new StringBuilder();
        char nextChar = input.nextChar();
        while (!Set.of('s', 'S').contains(nextChar)) {
            seedString.append(nextChar);
            nextChar = input.nextChar();
        }
        return Long.parseLong(seedString.toString());
    }
}
