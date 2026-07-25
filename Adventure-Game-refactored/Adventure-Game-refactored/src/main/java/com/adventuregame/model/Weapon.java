package com.adventuregame.model;

/**
 * Weapons available in the tool store.
 */
public enum Weapon {
    NONE(0, "None", 0, 0),
    PISTOL(1, "Pistol", 2, 25),
    SWORD(2, "Sword", 3, 35),
    RIFLE(3, "Rifle", 7, 45);

    private final int id;
    private final String displayName;
    private final int damage;
    private final int price;

    Weapon(int id, String displayName, int damage, int price) {
        this.id = id;
        this.displayName = displayName;
        this.damage = damage;
        this.price = price;
    }

    public static Weapon fromId(int id) {
        for (Weapon weapon : values()) {
            if (weapon.id == id && weapon != NONE) {
                return weapon;
            }
        }
        throw new IllegalArgumentException("Unknown weapon id: " + id);
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

    public int getPrice() {
        return price;
    }
}
