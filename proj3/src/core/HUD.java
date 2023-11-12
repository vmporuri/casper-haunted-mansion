package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import java.awt.*;

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
        StdDraw.text(2, 41, currentHUD);
        StdDraw.show();
    }

    /** Updates the HUD. */
    public void updateHUD(String tileInfo) {
        StdDraw.text(2, 41, tileInfo);
        StdDraw.show();
    }
}
