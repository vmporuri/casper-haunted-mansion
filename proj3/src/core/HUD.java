package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import java.io.Serializable;

/** The HUD. */
public class HUD implements Serializable {

    private static final int HUD_X = 4;
    private static final int HUD_Y = 43;
    private final TERenderer ter;
    private String currentHUD;

    /** Creates the HUD. */
    public HUD(TERenderer ter) {
        this.ter = ter;
        updateHUD("");
    }

    /** Redraws the HUD. */
    public void redrawHUD() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(4, 41, currentHUD);
        StdDraw.show();
    }

    /** Updates the HUD. */
    public void updateHUD(String tileInfo) {
        currentHUD = tileInfo;
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(4, 41, tileInfo);
        StdDraw.show();
    }

    /** Returns the string currently displayed on the HUD. */
    public String getCurrentHUDString() {
        return currentHUD;
    }
}
