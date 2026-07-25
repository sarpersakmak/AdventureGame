package com.adventuregame.model;

/**
 * Collectible rewards required to win the game.
 */
public enum Reward {
    FOOD("Food"),
    WATER("Water"),
    FIREWOOD("Firewood");

    private final String displayName;

    Reward(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
