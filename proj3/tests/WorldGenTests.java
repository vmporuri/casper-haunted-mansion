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
        testEqualityOfTiles(tiles1, tiles2);
    }

    /** Basic interactivity test. */
    @Test
    public void basicInteractivityTest() {
        TETile[][] originalTiles = AutograderBuddy.getWorldFromInput("n123s");
        TETile[][] onlyW = AutograderBuddy.getWorldFromInput("n123swww");
        TETile[][] onlyS = AutograderBuddy.getWorldFromInput("n123ssss");
        TETile[][] onlyA = AutograderBuddy.getWorldFromInput("n123saaa");
        TETile[][] onlyD = AutograderBuddy.getWorldFromInput("n123sddd");
        TETile[][] allMovements = AutograderBuddy.getWorldFromInput("n123swwasdd");

        assertThat(originalTiles[63][12].character()).isEqualTo('@');
        assertThat(onlyW[63][15].character()).isEqualTo('@');
        assertThat(onlyW[63][12].character()).isEqualTo('·');
        assertThat(onlyS[63][9].character()).isEqualTo('@');
        assertThat(onlyS[63][12].character()).isEqualTo('·');
        assertThat(onlyA[60][12].character()).isEqualTo('@');
        assertThat(onlyA[63][12].character()).isEqualTo('·');
        assertThat(onlyD[66][12].character()).isEqualTo('@');
        assertThat(onlyD[63][12].character()).isEqualTo('·');
        assertThat(allMovements[64][13].character()).isEqualTo('@');
        assertThat(allMovements[63][12].character()).isEqualTo('·');
    }

    /** Tests that the avatar only moves when the WASD keys are pressed. */
    @Test
    public void wallsBlockPlayerTest() {
        TETile[][] touchingWall = AutograderBuddy.getWorldFromInput("n123sssss");
        TETile[][] tryingToMoveThroughWall = AutograderBuddy.getWorldFromInput("n123sssss");
        testEqualityOfTiles(touchingWall, tryingToMoveThroughWall);
    }

    /** Tests that the avatar only moves when the WASD keys are pressed. */
    @Test
    public void validInputsTest() {
        TETile[][] originalTiles = AutograderBuddy.getWorldFromInput("n123s");
        TETile[][] tilesAfterNonWASDInput = AutograderBuddy.getWorldFromInput("n123sqertyuiop");
        testEqualityOfTiles(originalTiles, tilesAfterNonWASDInput);
    }

    /** Basic persistence test. */
    @Test
    public void basicSaveTest() {
        TETile[][] tiles1 = AutograderBuddy.getWorldFromInput("n123swasd:q");
        TETile[][] tiles2 = AutograderBuddy.getWorldFromInput("lwasd");
        TETile[][] tiles3 = AutograderBuddy.getWorldFromInput("n123swwwwwwwwwww:q");
        TETile[][] tiles4 = AutograderBuddy.getWorldFromInput("l:q");

        testEqualityOfTiles(tiles1, tiles2);
        testEqualityOfTiles(tiles3, tiles4);
    }

    /** Tests if two 2D matrices of tiles are equal. */
    private void testEqualityOfTiles(TETile[][] tiles1, TETile[][] tiles2) {
        for (int i = 0; i < tiles1.length; i++) {
            for (int j = 0; j < tiles1[0].length; j++) {
                assertThat(tiles1[i][j].character()).isEqualTo(tiles2[i][j].character());
            }
        }
    }
}
