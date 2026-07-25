package com.adventuregame.model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * Stores the player's equipment and collected location rewards.
 */
public final class Inventory {
    private final EnumSet<Reward> rewards = EnumSet.noneOf(Reward.class);
    private Weapon weapon = Weapon.NONE;
    private Armor armor = Armor.NONE;

    public Weapon getWeapon() {
        return weapon;
    }

    public void equipWeapon(Weapon weapon) {
        if (weapon == null) {
            throw new IllegalArgumentException("weapon must not be null");
        }
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void equipArmor(Armor armor) {
        if (armor == null) {
            throw new IllegalArgumentException("armor must not be null");
        }
        this.armor = armor;
    }

    public boolean addReward(Reward reward) {
        if (reward == null) {
            throw new IllegalArgumentException("reward must not be null");
        }
        return rewards.add(reward);
    }

    public boolean hasReward(Reward reward) {
        return rewards.contains(reward);
    }

    public boolean hasAllRewards() {
        return rewards.containsAll(EnumSet.allOf(Reward.class));
    }

    public Set<Reward> getRewards() {
        return Collections.unmodifiableSet(rewards);
    }
}
