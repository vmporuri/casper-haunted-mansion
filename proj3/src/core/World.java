package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;

import java.util.Set;

/** The primary game engine. */
public class World {

    private static final int OFFSET = 2;
    private final Map map;
    private final InputDevice input;
    private TERenderer ter;
    private HUD hud;

    /** Constructs a world from an InputDevice and an existing MAP. */
    public World(long seed, InputDevice input) {
        RandomWorldGenerator rwg = new RandomWorldGenerator(seed);
        map = rwg.getMap();
        this.input = input;
    }

    /** Constructs a world from a string and an existing MAP. */
    public World(Map map, InputDevice input) {
        this.map = map;
        this.input = input;
    }

    /** Creates the world. */
    public World(long seed) {
        this(seed, new InputDevice());
    }

    /** Loads the world from an existing map. */
    public World(Map map) {
        this(map, new InputDevice());
    }

    /** Renders the world for the first time. */
    public void renderWorld(TERenderer ter) {
        this.ter = ter;
        hud = new HUD(ter);
        StdDraw.setFont();
        renderFrameWithHUD();
    }


    /** Returns whether StdDraw functions should be executed. */
    private boolean shouldRender() {
        return ter != null;
    }

    /** Returns the world of tiles. */
    public TETile[][] getWorld() {
        return map.getWorld();
    }

    /** The game loop. */
    public void playGame() {
        while (true) {
            if (shouldRender() && StdDraw.hasNextKeyTyped()) {
                input.addChar(StdDraw.nextKeyTyped());
            } else if (!input.isEmpty()) {
                characterDispatch();
                renderFrameWithHUD();
            } else if (!shouldRender() & input.isEmpty()) {
                return;
            }
            updateHUDIfNewTile();
        }
    }

    /** Selects what to do based on what the inputted character is. */
    private void characterDispatch() {
        char nextChar = input.nextChar();
        switch (nextChar) {
            case 'w', 'W' -> moveAvatar(new Coordinate(0, 1));
            case 'a', 'A' -> moveAvatar(new Coordinate(-1, 0));
            case 's', 'S' -> moveAvatar(new Coordinate(0, -1));
            case 'd', 'D' -> moveAvatar(new Coordinate(1, 0));
            case ':' -> quitIfQ();
            default -> {
            }
        }
    }

    /** Re-renders the world and re-displays the HUD. */
    private void renderFrameWithHUD() {
        if (shouldRender()) {
            ter.renderFrame(map.getWorld());
            hud.redrawHUD();
        }
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
        if (!shouldRender()) {
            return;
        }
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
        if (!shouldRender()) {
            char nextChar = input.nextChar();
            if (Set.of('q', 'Q').contains(nextChar)) {
                WorldUtils.saveWorld(map);
            }
            return;
        }
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
        WorldUtils.saveWorld(map);
        System.exit(0);
    }
}
