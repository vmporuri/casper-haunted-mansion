package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import java.awt.Font;
import java.util.Set;

/** The main menu. */
public class MainMenu {

    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 60);
    private static final Font OPTION_FONT = new Font("SansSerif", Font.BOLD, 30);
    private static final int WINDOW_LENGTH = 84;
    private static final int WINDOW_HEIGHT = 42;
    private static final double CENTER_X = WINDOW_LENGTH / 2.;
    private static final double CENTER_Y = WINDOW_HEIGHT / 2.;
    private static final double OPTION_Y = WINDOW_HEIGHT / 4.;
    private static final double TITLE_Y = 3. * WINDOW_HEIGHT / 4;
    private static final int OFFSET = 2;
    private final TERenderer ter;

    /** A main menu instance. */
    public MainMenu(TERenderer ter) {
        this.ter = ter;
        ter.initialize(WINDOW_LENGTH, WINDOW_HEIGHT, OFFSET, OFFSET);
    }

    /** Creates and opens the main menu. */
    public void openMainMenu() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(TITLE_FONT);
        StdDraw.text(CENTER_X, TITLE_Y, "CS61B: THE GAME");
        StdDraw.setFont(OPTION_FONT);
        StdDraw.text(CENTER_X, OPTION_Y + OFFSET, "New Game (N)");
        StdDraw.text(CENTER_X, OPTION_Y, "Load Game (L)");
        StdDraw.text(CENTER_X, OPTION_Y - OFFSET, "Quit (Q)");
        StdDraw.show();
    }

    /** Listens for keyboard input. */
    public World processPlayerWorldSelection() {
        char nextChar;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                nextChar = Character.toLowerCase(StdDraw.nextKeyTyped());
                if (Set.of('n', 'l', 's', 'q').contains(nextChar)) {
                    break;
                }
            }
        }
        return handleKeyInput(nextChar);
    }

    /** Handles the key input. */
    private World handleKeyInput(char nextChar) {
        switch (nextChar) {
            case 'n', 'N' -> {
                return createNewWorld();
            }
            case 'l', 'L' -> {
                return loadGameFromSave();
            }
            case 'q', 'Q' -> {
                return quitGame();
            }
            default -> {
                return processPlayerWorldSelection();
            }
        }
    }

    /** Creates a new world from a seed inputted by the player. */
    private World createNewWorld() {
        long seed = getSeed();
        StdDraw.setFont();
        return new World(seed, ter);
    }

    /** Gets the seed from player input until 's' is pressed. */
    private long getSeed() {
        StringBuilder seedString = new StringBuilder();
        updateGetSeedScreen(seedString);
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char nextChar = StdDraw.nextKeyTyped();
                if (nextChar == 's' || nextChar == 'S') {
                    if (seedString.isEmpty()) {
                        return 0;
                    }
                    return Long.parseLong(seedString.toString());
                } else if (nextChar == '\b') {
                    // @source From https://stackoverflow.com/a/3395329
                    if (!seedString.isEmpty()) {
                        seedString.setLength(seedString.length() - 1);
                    }
                    updateGetSeedScreen(seedString);
                } else if (Character.isDigit(nextChar)) {
                    seedString.append(nextChar);
                    updateGetSeedScreen(seedString);
                }
            }
        }
    }

    /** Updates the seed screen. */
    private void updateGetSeedScreen(StringBuilder seed) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.text(CENTER_X, CENTER_Y + OFFSET, "Please input a random seed:");
        StdDraw.text(CENTER_X, CENTER_Y - OFFSET, seed.toString());
        StdDraw.show();
    }

    /** Loads the game from a previously saved text file. If a save doesn't exist, closes the program. */
    private World loadGameFromSave() {
        Map map = SaveLoadWorld.loadWorld();
        if (map == null) {
            System.exit(0);
        }
        StdDraw.setFont();
        return new World(map, ter);
    }

    /** Quits the program. */
    private World quitGame() {
        System.exit(0);
        return null;
    }
}
