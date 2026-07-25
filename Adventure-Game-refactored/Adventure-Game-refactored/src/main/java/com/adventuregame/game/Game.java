package com.adventuregame.game;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.location.Cave;
import com.adventuregame.location.Forest;
import com.adventuregame.location.Location;
import com.adventuregame.location.River;
import com.adventuregame.location.SafeHouse;
import com.adventuregame.location.ToolStore;
import com.adventuregame.model.CharacterType;
import com.adventuregame.model.Player;

import java.util.Objects;
import java.util.Random;

/**
 * Coordinates character creation, location selection, victory, and defeat.
 */
public final class Game {
    private final ConsoleIO console;
    private final Random random;
    private Player player;

    public Game(ConsoleIO console, Random random) {
        this.console = Objects.requireNonNull(console, "console must not be null");
        this.random = Objects.requireNonNull(random, "random must not be null");
    }

    public void run() {
        console.println("Welcome to Adventure Game!");
        String playerName = console.readNonBlankLine("Enter your name: ");
        CharacterType characterType = selectCharacter();
        player = new Player(playerName, characterType);

        printCharacterSummary();
        playGameLoop();
    }

    private CharacterType selectCharacter() {
        console.println();
        console.println("Select a character:");
        for (CharacterType type : CharacterType.values()) {
            console.println(type.getId() + ". " + type.getDisplayName()
                    + " | Damage: " + type.getDamage()
                    + " | Health: " + type.getHealth()
                    + " | Money: " + type.getStartingMoney());
        }

        int characterId = console.readIntInRange("Your selection: ", 1, CharacterType.values().length);
        return CharacterType.fromId(characterId);
    }

    private void printCharacterSummary() {
        console.println();
        console.println("Character selected:");
        console.println("Player: " + player.getName());
        console.println("Class: " + player.getCharacterType().getDisplayName());
        console.println("Damage: " + player.getTotalDamage());
        console.println("Health: " + player.getHealth());
        console.println("Money: " + player.getMoney());
    }

    private void playGameLoop() {
        while (player.isAlive()) {
            printLocationMenu();
            int locationId = console.readIntInRange("Choose a location: ", 1, 5);
            Location location = createLocation(locationId);

            if (!location.enter()) {
                break;
            }

            if (location instanceof SafeHouse && player.getInventory().hasAllRewards()) {
                console.println();
                console.println("Congratulations, " + player.getName() + "! You won the game.");
                return;
            }
        }

        console.println();
        console.println("Game over.");
    }

    private void printLocationMenu() {
        console.println();
        console.println("=================================================");
        console.println("Select a location:");
        console.println("1. Safe House - Restore health and check victory");
        console.println("2. Cave - Zombies, reward: Food");
        console.println("3. Forest - Vampires, reward: Firewood");
        console.println("4. River - Bears, reward: Water");
        console.println("5. Tool Store - Buy weapons or armor");
    }

    private Location createLocation(int locationId) {
        switch (locationId) {
            case 1:
                return new SafeHouse(player, console);
            case 2:
                return new Cave(player, console, random);
            case 3:
                return new Forest(player, console, random);
            case 4:
                return new River(player, console, random);
            case 5:
                return new ToolStore(player, console);
            default:
                throw new IllegalArgumentException("Unknown location id: " + locationId);
        }
    }
}
