package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/** Renders light/darkness around the avatar. */
public class LightUtils {

    private static final double LIGHT_RADIUS = 4;

    /** Renders the world with darkness. */
    public static void renderDarkness(Map map, TERenderer ter) {
        TETile[][] tilesWithDarkness = coverWithDarkness(map);
        ter.renderFrame(tilesWithDarkness);
    }

    /** Computes the distance to the avatar's current position. */
    private static double distanceToAvatar(Map map, Coordinate coords) {
        Coordinate playerLocation = map.getPlayerLocation();
        return playerLocation.distance(coords);
    }

    /** Returns the TETile[][] array with darkness covering tiles that are far from the avatar. */
    private static TETile[][] coverWithDarkness(Map map) {
        TETile[][] originalTiles = map.getWorld();
        TETile[][] copyWithDarkness = TETile.copyOf(originalTiles);

        for (int i = 0; i < copyWithDarkness.length; i++) {
            for (int j = 0; j < copyWithDarkness[0].length; j++) {
                Coordinate currentPosition = new Coordinate(i, j);
                if (distanceToAvatar(map, currentPosition) > LIGHT_RADIUS) {
                    copyWithDarkness[i][j] = Tileset.NOTHING;
                }
            }
        }
        return copyWithDarkness;
    }
}
