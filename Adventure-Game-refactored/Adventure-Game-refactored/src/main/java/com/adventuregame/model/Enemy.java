package com.adventuregame.model;

import java.util.Objects;
import java.util.Random;

/**
 * Represents an enemy template and the health of the current encounter.
 */
public final class Enemy {
    private final String name;
    private final int damage;
    private final int maxHealth;
    private final int moneyAward;
    private final int maximumCount;
    private int health;

    public Enemy(String name, int damage, int maxHealth, int moneyAward, int maximumCount) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (damage < 0 || maxHealth <= 0 || moneyAward < 0 || maximumCount <= 0) {
            throw new IllegalArgumentException("enemy statistics are invalid");
        }
        this.name = name.trim();
        this.damage = damage;
        this.maxHealth = maxHealth;
        this.moneyAward = moneyAward;
        this.maximumCount = maximumCount;
        this.health = maxHealth;
    }

    public int generateCount(Random random) {
        Objects.requireNonNull(random, "random must not be null");
        return random.nextInt(maximumCount) + 1;
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must not be negative");
        }
        health = Math.max(0, health - amount);
    }

    public void resetHealth() {
        health = maxHealth;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMoneyAward() {
        return moneyAward;
    }

    public int getMaximumCount() {
        return maximumCount;
    }
}
