package com.adventuregame.location;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.model.Player;

/**
 * Restores the player's health and acts as the victory checkpoint.
 */
public final class SafeHouse extends NormalLocation {

    public SafeHouse(Player player, ConsoleIO console) {
        super(player, console, "Safe House");
    }

    @Override
    public boolean enter() {
        player.healFully();
        console.println("Your health has been fully restored.");
        console.println("You are safe inside the Safe House.");
        return true;
    }
}
