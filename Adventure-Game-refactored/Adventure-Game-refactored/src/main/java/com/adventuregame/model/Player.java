package com.adventuregame.model;

import java.util.Objects;

/**
 * Represents the player and owns all mutable player state.
 */
public final class Player {
    private final String name;
    private final CharacterType characterType;
    private final Inventory inventory;
    private int health;
    private int money;

    public Player(String name, CharacterType characterType) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.name = name.trim();
        this.characterType = Objects.requireNonNull(characterType, "characterType must not be null");
        this.inventory = new Inventory();
        this.health = characterType.getHealth();
        this.money = characterType.getStartingMoney();
    }

    public int takeDamage(int rawDamage) {
        if (rawDamage < 0) {
            throw new IllegalArgumentException("rawDamage must not be negative");
        }
        int receivedDamage = Math.max(0, rawDamage - inventory.getArmor().getBlock());
        health = Math.max(0, health - receivedDamage);
        return receivedDamage;
    }

    public void healFully() {
        health = getMaxHealth();
    }

    public boolean spendMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must not be negative");
        }
        if (money < amount) {
            return false;
        }
        money -= amount;
        return true;
    }

    public void earnMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must not be negative");
        }
        money += amount;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getTotalDamage() {
        return characterType.getDamage() + inventory.getWeapon().getDamage();
    }

    public String getName() {
        return name;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return characterType.getHealth();
    }

    public int getMoney() {
        return money;
    }
}
