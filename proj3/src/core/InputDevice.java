package core;

import java.util.ArrayDeque;

/** Handles input from keyboards and hard-coded strings. */
// @source Inspired by Prof Hug's SWE II video
public class InputDevice {

    ArrayDeque<Character> characterQueue;

    /** Creates a new input device without any starting text. */
    public InputDevice() {
        characterQueue = new ArrayDeque<>();
    }

    /** Creates an input device with a string already inside. */
    public InputDevice(String inputString) {
        characterQueue = new ArrayDeque<>();
        addString(inputString);
    }

    /** Returns the next character. */
    public Character nextChar() {
        return characterQueue.pollFirst();
    }

    /** Adds a new character to the end of the InputDevice. */
    public void addChar(char c) {
        characterQueue.addLast(c);
    }

    /** Adds all the characters in the given string to the end of the InputDevice. */
    public void addString(String inputString) {
        for (int i = 0; i < inputString.length(); i++) {
            characterQueue.addLast(inputString.charAt(i));
        }
    }
}
