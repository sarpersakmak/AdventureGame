package com.adventuregame.location;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.model.Player;

import java.util.Objects;

/**
 * Base type for every place the player can visit.
 */
public abstract class Location {
    protected final Player player;
    protected final ConsoleIO console;
    private final String name;

    protected Location(Player player, ConsoleIO console, String name) {
        this.player = Objects.requireNonNull(player, "player must not be null");
        this.console = Objects.requireNonNull(console, "console must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    /**
     * Executes the location interaction.
     *
     * @return {@code false} when the game must end; otherwise {@code true}
     */
    public abstract boolean enter();

    public String getName() {
        return name;
    }
}
