package com.adventuregame;

import com.adventuregame.game.Game;
import com.adventuregame.io.ConsoleIO;

import java.util.Random;

/**
 * Application entry point.
 */
public final class Main {

    private Main() {
        // Prevent instantiation of the application entry-point class.
    }

    public static void main(String[] args) {
        ConsoleIO console = new ConsoleIO(System.in, System.out);
        Game game = new Game(console, new Random());
        game.run();
    }
}
