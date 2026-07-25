package com.adventuregame.location;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.model.Armor;
import com.adventuregame.model.Player;
import com.adventuregame.model.Weapon;

/**
 * Lets the player purchase one weapon or armor item per visit.
 */
public final class ToolStore extends NormalLocation {

    public ToolStore(Player player, ConsoleIO console) {
        super(player, console, "Tool Store");
    }

    @Override
    public boolean enter() {
        console.println("Money: " + player.getMoney());
        console.println("1. Weapons");
        console.println("2. Armor");
        console.println("3. Exit");

        int category = console.readIntInRange("Your selection: ", 1, 3);
        if (category == 1) {
            showWeaponMenu();
        } else if (category == 2) {
            showArmorMenu();
        }
        return true;
    }

    private void showWeaponMenu() {
        for (Weapon weapon : Weapon.values()) {
            if (weapon != Weapon.NONE) {
                console.println(weapon.getId() + ". " + weapon.getDisplayName()
                        + " | Price: " + weapon.getPrice()
                        + " | Damage: " + weapon.getDamage());
            }
        }
        console.println("4. Exit");

        int selection = console.readIntInRange("Your selection: ", 1, 4);
        if (selection != 4) {
            buyWeapon(Weapon.fromId(selection));
        }
    }

    private void showArmorMenu() {
        for (Armor armor : Armor.values()) {
            if (armor != Armor.NONE) {
                console.println(armor.getId() + ". " + armor.getDisplayName()
                        + " | Price: " + armor.getPrice()
                        + " | Block: " + armor.getBlock());
            }
        }
        console.println("4. Exit");

        int selection = console.readIntInRange("Your selection: ", 1, 4);
        if (selection != 4) {
            buyArmor(Armor.fromId(selection));
        }
    }

    /**
     * Purchases and equips a weapon when the player has enough money.
     */
    public boolean buyWeapon(Weapon weapon) {
        if (weapon == null || weapon == Weapon.NONE) {
            throw new IllegalArgumentException("A purchasable weapon is required");
        }
        if (!player.spendMoney(weapon.getPrice())) {
            console.println("You do not have enough money.");
            return false;
        }

        player.getInventory().equipWeapon(weapon);
        console.println("Purchased " + weapon.getDisplayName()
                + ". Total damage: " + player.getTotalDamage());
        console.println("Remaining money: " + player.getMoney());
        return true;
    }

    /**
     * Purchases and equips armor when the player has enough money.
     */
    public boolean buyArmor(Armor armor) {
        if (armor == null || armor == Armor.NONE) {
            throw new IllegalArgumentException("Purchasable armor is required");
        }
        if (!player.spendMoney(armor.getPrice())) {
            console.println("You do not have enough money.");
            return false;
        }

        player.getInventory().equipArmor(armor);
        console.println("Purchased " + armor.getDisplayName()
                + ". Damage block: " + armor.getBlock());
        console.println("Remaining money: " + player.getMoney());
        return true;
    }
}
