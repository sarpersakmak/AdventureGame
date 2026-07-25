package com.adventuregame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InventoryTest {

    @Test
    void shouldStoreEquipmentAndRewards() {
        Inventory inventory = new Inventory();

        inventory.equipWeapon(Weapon.RIFLE);
        inventory.equipArmor(Armor.MEDIUM);
        inventory.addReward(Reward.FOOD);

        assertEquals(Weapon.RIFLE, inventory.getWeapon());
        assertEquals(Armor.MEDIUM, inventory.getArmor());
        assertTrue(inventory.hasReward(Reward.FOOD));
        assertFalse(inventory.hasAllRewards());
    }

    @Test
    void shouldRecognizeAllVictoryRewards() {
        Inventory inventory = new Inventory();

        for (Reward reward : Reward.values()) {
            inventory.addReward(reward);
        }

        assertTrue(inventory.hasAllRewards());
    }

    @Test
    void shouldExposeRewardsAsReadOnlySet() {
        Inventory inventory = new Inventory();
        inventory.addReward(Reward.WATER);

        assertThrows(UnsupportedOperationException.class,
                () -> inventory.getRewards().add(Reward.FOOD));
    }
}
