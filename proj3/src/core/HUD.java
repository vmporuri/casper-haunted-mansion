package core;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.io.Serializable;

/** The HUD. */
public class HUD implements Serializable {

    private static final Font HUD_FONT = new Font("Chalkduster", Font.PLAIN, 20);
    private static final int OFFSET = 2;
    private static final int HUD_X = 6;
    private static final int HUD_Y = 43;
    private String currentHUD;

    /** Creates the HUD. */
    public HUD() {
        updateHUD("");
    }

    /** Redraws the HUD. */
    public void redrawHUD() {
        updateHUD(currentHUD);
    }

    /** Updates the HUD. */
    public void updateHUD(String tileInfo) {
        currentHUD = tileInfo;
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(HUD_FONT);
        StdDraw.text(HUD_X, HUD_Y - OFFSET, tileInfo);
        StdDraw.show();
        StdDraw.setFont();
    }

    /** Returns the string currently displayed on the HUD. */
    public String getCurrentHUDString() {
        return currentHUD;
    }
}
