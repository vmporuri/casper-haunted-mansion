package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;

import java.util.Set;

/** The game. */
public class World {

    private static final int OFFSET = 2;
    private TERenderer ter;
    private Map map;
    private HUD hud;
    private final InputDevice input;

    /** Creates the world. */
    public World(long seed, TERenderer ter) {
        this.ter = ter;
        RandomWorldGenerator rwg = new RandomWorldGenerator(seed);
        map = rwg.getMap();
        hud = new HUD(ter);
        input = new InputDevice();
        renderFrameWithHUD();
    }

    /** Loads the world from an existing map. */
    public World(Map map, TERenderer ter) {
        this.ter = ter;
        this.map = map;
        hud = new HUD(ter);
        input = new InputDevice();
        renderFrameWithHUD();
    }

    /** Constructs a world from an INPUTSTRING. */
    public World(String inputString) {
        input = new InputDevice(inputString);
    }

    /** Constructs a world from a string and an existing MAP. */
    public World(Map map, String inputString) {
        this.map = map;
        input = new InputDevice(inputString);
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
            } else if (!shouldRender() & !input.isEmpty()) {
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
