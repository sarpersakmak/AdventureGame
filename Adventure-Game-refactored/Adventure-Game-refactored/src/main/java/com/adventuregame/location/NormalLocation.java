package com.adventuregame.location;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.model.Player;

/**
 * Base class for locations that do not contain enemies.
 */
public abstract class NormalLocation extends Location {

    protected NormalLocation(Player player, ConsoleIO console, String name) {
        super(player, console, name);
    }
}
