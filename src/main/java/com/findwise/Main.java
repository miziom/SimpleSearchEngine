package com.findwise;

import java.io.IOException;

public class Main {

    private static MenuEngine menuEngine = new MenuEngine();

    public static void main(String[] args) throws IOException {
        while (true) {
            menuEngine.mainMenu();
        }
    }
}


