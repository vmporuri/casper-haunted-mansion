import core.AutograderBuddy;
import edu.princeton.cs.algs4.StdDraw;
import org.junit.jupiter.api.Test;
import tileengine.TERenderer;
import tileengine.TETile;

import static com.google.common.truth.Truth.assertThat;

/** A basic test suit for world generation. */
public class WorldGenTests {

    /** Basic world generation test. */
    @Test
    public void basicTest() {
        // put different seeds here to test different worlds
        TETile[][] tiles = AutograderBuddy.getWorldFromInput("n1234567890123456789s");

        TERenderer ter = new TERenderer();
        ter.initialize(tiles.length, tiles[0].length);
        ter.renderFrame(tiles);
        StdDraw.pause(5000); // pause for 5 seconds so you can see the output
    }

    /** Tests that using the same seed returns in the same world. */
    @Test
    public void testSeed() {
        TETile[][] tiles1 = AutograderBuddy.getWorldFromInput("n1234567890123456789s");
        TETile[][] tiles2 = AutograderBuddy.getWorldFromInput("n1234567890123456789s");
        assertThat(tiles1).isEqualTo(tiles2);
    }

    /** Basic interactivity test. */
    @Test
    public void basicInteractivityTest() {
        // TODO: write a test that uses an input like "n123swasdwasd"
    }

    /** Basic persistence test. */
    @Test
    public void basicSaveTest() {
        // TODO: write a test that calls getWorldFromInput twice, with "n123swasd:q" and with "lwasd"
    }
}
