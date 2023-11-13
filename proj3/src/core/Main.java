package core;

import tileengine.TERenderer;

public class Main {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        MainMenu mm = new MainMenu(ter);
        mm.openMainMenu();
        World world = mm.processPlayerWorldSelection();
        world.playGame();
    }
}
