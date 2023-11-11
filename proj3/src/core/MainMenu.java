package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;

import java.awt.*;

/** The main menu. */
public class MainMenu {

    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 60);
    private static final Font OPTION_FONT = new Font("SansSerif", Font.BOLD, 30);
    private static final int WINDOW_LENGTH = 80;
    private static final int WINDOW_HEIGHT = 40;
    private static final double centerX = WINDOW_LENGTH / 2.;
    private static final double centerY = WINDOW_HEIGHT / 2.;
    private static final double optionY = WINDOW_HEIGHT / 4.;
    private static final double titleY = 3. * WINDOW_HEIGHT / 4;
    private static final int OFFSET = 2;
    private final TERenderer ter;

    /** A main menu instance. */
    public MainMenu(TERenderer ter) {
        this.ter = ter;
        ter.initialize(WINDOW_LENGTH, WINDOW_HEIGHT);
    }

    /** Opens the main menu. */
    public void openMainMenu() {
        createMainMenu();
        getPlayerInput();
    }

    /** Creates the main menu. */
    private void createMainMenu() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(TITLE_FONT);
        StdDraw.text(centerX, titleY, "CS61B: THE GAME");
        StdDraw.setFont(OPTION_FONT);
        StdDraw.text(centerX, optionY + OFFSET, "New Game (N)");
        StdDraw.text(centerX, optionY, "Load Game (L)");
        StdDraw.text(centerX, optionY - OFFSET, "Quit (Q)");
        StdDraw.show();
    }

    /** Listens for keyboard input. */
    private void getPlayerInput() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char nextChar = Character.toLowerCase(StdDraw.nextKeyTyped());
                handleKeyInput(nextChar);
            }
        }
    }

    /** Handles the key input. */
    private void handleKeyInput(char nextChar) {
        switch (nextChar) {
            case 'n' -> createNewWorld();
//            case 'l' -> loadGameFromSave();
//            case 'q' -> quitGame();
        }
    }

    /** Creates a new world from a seed inputted by the player. */
    private void createNewWorld() {
        long seed = getSeed();
        StdDraw.setFont();
        World world = new World(seed);
        TETile[][] worldState = world.getWorld();
        ter.renderFrame(worldState);
    }

    /** Gets the seed from player input until 's' is pressed. */
    private long getSeed() {
        long seed = 0;
        updateGetSeedScreen(0);
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char nextChar = StdDraw.nextKeyTyped();
                if (nextChar == 's' || nextChar == 'S') {
                    return seed;
                } else if (nextChar == '\b') {
                    seed /= 10;
                    updateGetSeedScreen(seed);
                } else if (Character.isDigit(nextChar)) {
                    long nextDigit = Character.getNumericValue(nextChar);
                    seed = seed * 10 + nextDigit;
                    updateGetSeedScreen(seed);
                }
            }
        }
    }

    /** Updates the seed screen. */
    private void updateGetSeedScreen(long seed) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.text(centerX, centerY + OFFSET, "Please input a random seed:");
        if (seed != 0) {
            StdDraw.text(centerX, centerY - OFFSET, Long.toString(seed));
        }
        StdDraw.show();
    }
}
