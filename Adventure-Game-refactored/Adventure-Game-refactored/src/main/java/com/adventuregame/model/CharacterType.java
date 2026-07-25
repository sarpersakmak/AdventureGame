package com.adventuregame.model;

/**
 * Playable character templates and their starting statistics.
 */
public enum CharacterType {
    SAMURAI(1, "Samurai", 5, 21, 15),
    ARCHER(2, "Archer", 7, 18, 20),
    KNIGHT(3, "Knight", 8, 24, 5);

    private final int id;
    private final String displayName;
    private final int damage;
    private final int health;
    private final int startingMoney;

    CharacterType(int id, String displayName, int damage, int health, int startingMoney) {
        this.id = id;
        this.displayName = displayName;
        this.damage = damage;
        this.health = health;
        this.startingMoney = startingMoney;
    }

    public static CharacterType fromId(int id) {
        for (CharacterType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown character id: " + id);
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getStartingMoney() {
        return startingMoney;
    }
}
