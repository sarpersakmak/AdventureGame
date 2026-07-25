package com.adventuregame.model;

/**
 * Armor options available in the tool store.
 */
public enum Armor {
    NONE(0, "None", 0, 0),
    LIGHT(1, "Light Armor", 1, 15),
    MEDIUM(2, "Medium Armor", 3, 25),
    HEAVY(3, "Heavy Armor", 5, 40);

    private final int id;
    private final String displayName;
    private final int block;
    private final int price;

    Armor(int id, String displayName, int block, int price) {
        this.id = id;
        this.displayName = displayName;
        this.block = block;
        this.price = price;
    }

    public static Armor fromId(int id) {
        for (Armor armor : values()) {
            if (armor.id == id && armor != NONE) {
                return armor;
            }
        }
        throw new IllegalArgumentException("Unknown armor id: " + id);
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getBlock() {
        return block;
    }

    public int getPrice() {
        return price;
    }
}
