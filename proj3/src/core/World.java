package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;

import java.util.Set;

/** The game. */
public class World {

    private static final int OFFSET = 2;
    private final TERenderer ter;
    private final Map map;
    private final HUD hud;

    /** Creates the world. */
    public World(long seed, TERenderer ter) {
        this.ter = ter;
        RandomWorldGenerator rwg = new RandomWorldGenerator(seed);
        map = rwg.getMap();
        hud = new HUD(ter);
        renderFrameWithHUD();
    }

    /** Loads the world from an existing map. */
    public World(Map map, TERenderer ter) {
        this.ter = ter;
        this.map = map;
        hud = new HUD(ter);
        renderFrameWithHUD();
    }

    /** Returns the world of tiles. */
    public TETile[][] getWorld() {
        return map.getWorld();
    }

    /** The game loop. */
    public void playGame() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char nextChar = StdDraw.nextKeyTyped();
                switch (nextChar) {
                    case 'w', 'W' -> moveAvatar(new Coordinate(0, 1));
                    case 'a', 'A' -> moveAvatar(new Coordinate(-1, 0));
                    case 's', 'S' -> moveAvatar(new Coordinate(0, -1));
                    case 'd', 'D' -> moveAvatar(new Coordinate(1, 0));
                    case ':' -> quitIfQ();
                    default -> {}
                }
                renderFrameWithHUD();
            }
            updateHUDIfNewTile();
        }
    }

    /** Plays the game from a string. */
    public void playGameFromString() {}

    /** Re-renders the world and re-displays the HUD. */
    private void renderFrameWithHUD() {
        ter.renderFrame(map.getWorld());
        hud.redrawHUD();
    }

    /** Move avatar on the map. */
    private void moveAvatar(Coordinate movement) {
        Coordinate oldLocation = map.getPlayerLocation();
        Coordinate newLocation = oldLocation.plus(movement);
        if (!map.isWall(newLocation)) {
            map.placeFloor(oldLocation);
            map.placePlayer(newLocation);
        }
    }

    /** Gets the location of the mouse. */
    private Coordinate getMouseLocation() {
        int x = (int) Math.floor(StdDraw.mouseX()) - OFFSET;
        int y = (int) Math.floor(StdDraw.mouseY()) - OFFSET;
        return new Coordinate(x, y);
    }

    /** Gets the description of the tile at COORDS. */
    private String getTileType(Coordinate coords) {
        TETile tile = map.getTileAtLocation(coords);
        return tile.description();
    }

    /** Updates the HUD only if the tile is different. */
    private void updateHUDIfNewTile() {
        Coordinate mouseLocation = getMouseLocation();
        if (map.validatePosition(mouseLocation)) {
            String tileDescription = getTileType(mouseLocation);
            String newHUDDescription = "Tile: " + tileDescription;
            if (!newHUDDescription.equals(hud.getCurrentHUDString())) {
                hud.updateHUD(newHUDDescription);
                renderFrameWithHUD();
            }
        }
    }

    /** Quits and saves the game if the next key pressed is q/Q. */
    private void quitIfQ() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char nextChar = StdDraw.nextKeyTyped();
                if (Set.of('q', 'Q').contains(nextChar)) {
                    saveAndQuit();
                }
                return;
            }
        }
    }

    /** Saves and quits the game. */
    private void saveAndQuit() {
        SaveLoadWorld.saveWorld(map);
        System.exit(0);
    }
}
