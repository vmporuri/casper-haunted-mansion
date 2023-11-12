package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

/** The HUD. */
public class HUD {

    private final TERenderer ter;
    private String currentHUD;

    /** Creates the HUD. */
    public HUD(TERenderer ter) {
        this.ter = ter;
        updateHUD("test");
    }

    /** Redraws the HUD. */
    public void redrawHUD() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(8, 41, currentHUD);
        StdDraw.show();
    }

    /** Updates the HUD. */
    public void updateHUD(String tileInfo) {
        currentHUD = tileInfo;
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(8, 41, tileInfo);
        StdDraw.show();
    }

    /** Returns the string currently displayed on the HUD. */
    public String getCurrentHUDString() {
        return currentHUD;
    }
}
