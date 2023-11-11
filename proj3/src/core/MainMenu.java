package core;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/** The main menu. */
public class MainMenu {

    private static Font TITLE_FONT = new Font("Laro Soft", Font.BOLD, 60);
    private static Font OPTION_FONT = new Font("Jam Grotesque", Font.BOLD, 30);
    private static int OFFSET = 2;
    private double centerX;
    private double optionY;
    private double titleY;

    public MainMenu(int length, int height) {
        centerX = length / 2.;
        optionY = height / 4.;
        titleY = 3. * height / 4;
    }

    /** Creates the Main Menu. */
    public void createMainMenu() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(TITLE_FONT);
        StdDraw.text(centerX, titleY, "CS61B: THE GAME");
        StdDraw.setFont(OPTION_FONT);
        StdDraw.text(centerX, optionY + OFFSET, "New Game (N)");
        StdDraw.text(centerX, optionY, "Load Game (L)");
        StdDraw.text(centerX, optionY - OFFSET, "Quit (Q)");
        StdDraw.show();
    }

}
