package com.adventuregame.location;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.model.CharacterType;
import com.adventuregame.model.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SafeHouseTest {

    @Test
    void shouldRestorePlayerHealth() {
        Player player = new Player("Eren", CharacterType.ARCHER);
        player.takeDamage(8);
        SafeHouse safeHouse = new SafeHouse(player, silentConsole());

        assertTrue(safeHouse.enter());
        assertEquals(player.getMaxHealth(), player.getHealth());
    }

    private ConsoleIO silentConsole() {
        return new ConsoleIO(
                new ByteArrayInputStream(new byte[0]),
                new PrintStream(new ByteArrayOutputStream(), true, StandardCharsets.UTF_8));
    }
}
