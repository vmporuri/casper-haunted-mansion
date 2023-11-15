# Build Your Own World Design Document

## Partner 1: Venu Poruri
## Partner 2: Peixin Yu

## Classes and Data Structures:
* Room
    * Coordinates for each corner and the center of the room
    * Integer size
    * Max room length/width
        * 12
    * Min room length/width
        * 6
    * Check that there isn’t a room already placed, no intersect/overlap
        * If everything is null, nothing overlaps
    * Generating the room and drawing on grid
* Hallway
    * Coordinates
    * Integer size
*  Coordinate
    * get(x, y)
    * Average distance
    * Get distance between 2 coordinates
* RandomWorldGenerator
    * List of rooms
    * List of hallways
    * World size
        * 80 X 40
        * Everything starts as Tileset.NOTHING
    * WeightedQuickUnionUF
* Map
    * Place floor and wall
    * Have an id for floor and wall
    * WeightedQuickUnionUF to add keep track of hallways we connect to along the way
* HUD
    * Created the HUD
    * Have the HUD update when mouse is over another object
* MainMenu
    * Have the main screen
    * Handles the inputs for new, load, and save
* World
    * Moves the avatar
    * Plays the game
* InputDevice
    * nextChar()
        * Return next character in device
    * Add string/character
        * Queue
    * Input device
* WorldUtils
    * Use serializable interface
    * Save world
    * Load world from save file
* LightUtils
    * Lights a distance from avatar
    * Toggle on and off
    * Render darkness

## Assets:
* Pixel art
    * Wall, floor, avatar

## Algorithms:
* Random World Generation:
    * Gets room size and location using random number generator
    * If the room does not overlap with an existing room, draws the room; if there is an overlap, then the new room is not drawn
    * Do this some fixed number of times (we chose 25)
* Hallway generation:
    * Generate hallways after all rooms have been generated
    * Use weighted quick union to keep looping until all rooms are connected
    * Select a pair of rooms randomly
    * Randomly pick whether to dig horizontally or vertically first
    * Draw an L-shaped hallway connecting the centers of both rooms
    * Connect all rooms that we tunnel through

## Persistence:
* World implements Serializable
* WorldUtils serializes and deserializes the world
