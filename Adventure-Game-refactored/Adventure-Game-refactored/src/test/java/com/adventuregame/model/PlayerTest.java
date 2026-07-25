package com.adventuregame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    @Test
    void shouldInitializeFromCharacterTemplate() {
        Player player = new Player("Eren", CharacterType.ARCHER);

        assertEquals("Eren", player.getName());
        assertEquals(18, player.getHealth());
        assertEquals(20, player.getMoney());
        assertEquals(7, player.getTotalDamage());
    }

    @Test
    void shouldApplyArmorWithoutAllowingNegativeDamage() {
        Player player = new Player("Eren", CharacterType.KNIGHT);
        player.getInventory().equipArmor(Armor.HEAVY);

        int receivedDamage = player.takeDamage(3);

        assertEquals(0, receivedDamage);
        assertEquals(24, player.getHealth());
    }

    @Test
    void shouldAllowPurchaseWithExactAmount() {
        Player player = new Player("Eren", CharacterType.SAMURAI);

        assertTrue(player.spendMoney(15));
        assertEquals(0, player.getMoney());
        assertFalse(player.spendMoney(1));
    }

    @Test
    void shouldRestoreHealthToMaximum() {
        Player player = new Player("Eren", CharacterType.ARCHER);
        player.takeDamage(10);

        player.healFully();

        assertEquals(player.getMaxHealth(), player.getHealth());
    }
}
