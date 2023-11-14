package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import java.util.Set;

public class InputWorldProcessor {

    /** Processes world creation/loading from a provided input string. */
    public static World getWorldFromString(InputDevice input, TERenderer ter) {
        char nextChar = input.nextChar();
        switch (nextChar) {
            case 'n', 'N' -> {
                long seed = getSeedFromInputDevice(input);
                RandomWorldGenerator rwg = new RandomWorldGenerator(seed);
                return new World(rwg.getMap(), input);
            }
            case 'l', 'L' -> {
                Map map = SaveLoadWorld.loadWorld();
                if (map == null) {
                    System.exit(0);
                }
                StdDraw.setFont();
                return new World(map, input);
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
