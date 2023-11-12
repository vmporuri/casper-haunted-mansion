package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;

/** The game. */
public class World {

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

    /** Returns the world of tiles. */
    public TETile[][] getWorld() {
        return map.getWorld();
    }

    /** The game loop. */
    public void playGame() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char nextChar = Character.toLowerCase(StdDraw.nextKeyTyped());
                moveAvatar(nextChar);
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
    private void moveAvatar(char moveChar) {
        Coordinate movement;
        switch (moveChar) {
            case 'w' -> movement = new Coordinate(0, 1);
            case 'a' -> movement = new Coordinate(-1, 0);
            case 's' -> movement = new Coordinate(0, -1);
            case 'd' -> movement = new Coordinate(1, 0);
            default -> movement = new Coordinate(0, 0);
        }
        Coordinate oldLocation = map.getPlayerLocation();
        Coordinate newLocation = oldLocation.plus(movement);
        if (!map.isWall(newLocation)) {
            map.placePlayer(newLocation);
            map.placeFloor(oldLocation);
        }
    }

    /** Gets the location of the mouse. */
    private Coordinate getMouseLocation() {
        int x = (int) Math.floor(StdDraw.mouseX());
        int y = (int) Math.floor(StdDraw.mouseY());
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
            }
        }
    }
}
