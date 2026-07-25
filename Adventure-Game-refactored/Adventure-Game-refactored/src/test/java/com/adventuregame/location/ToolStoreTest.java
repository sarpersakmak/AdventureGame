package com.adventuregame.location;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.model.Armor;
import com.adventuregame.model.CharacterType;
import com.adventuregame.model.Player;
import com.adventuregame.model.Weapon;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToolStoreTest {

    @Test
    void shouldBuyItemWithExactAmountOfMoney() {
        Player player = new Player("Eren", CharacterType.SAMURAI);
        ToolStore store = createStore(player);

        assertTrue(store.buyArmor(Armor.LIGHT));
        assertEquals(0, player.getMoney());
        assertEquals(Armor.LIGHT, player.getInventory().getArmor());
    }

    @Test
    void shouldUseCorrectMediumArmorPrice() {
        assertEquals(25, Armor.MEDIUM.getPrice());
    }

    @Test
    void shouldNotChangeEquipmentWhenMoneyIsInsufficient() {
        Player player = new Player("Eren", CharacterType.KNIGHT);
        ToolStore store = createStore(player);

        assertFalse(store.buyWeapon(Weapon.RIFLE));
        assertEquals(Weapon.NONE, player.getInventory().getWeapon());
        assertEquals(5, player.getMoney());
    }

    private ToolStore createStore(Player player) {
        ConsoleIO console = new ConsoleIO(
                new ByteArrayInputStream(new byte[0]),
                new PrintStream(new ByteArrayOutputStream(), true, StandardCharsets.UTF_8));
        return new ToolStore(player, console);
    }
}
