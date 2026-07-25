package com.adventuregame.location;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.model.Enemy;
import com.adventuregame.model.Player;
import com.adventuregame.model.Reward;

import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * Base class for dangerous locations that contain one or more enemies.
 */
public abstract class BattleLocation extends Location {
    private static final Set<String> FIGHT_OR_ESCAPE = Set.of("F", "E");

    private final Enemy enemy;
    private final Reward reward;
    private final Random random;

    protected BattleLocation(
            Player player,
            ConsoleIO console,
            String name,
            Enemy enemy,
            Reward reward,
            Random random) {
        super(player, console, name);
        this.enemy = Objects.requireNonNull(enemy, "enemy must not be null");
        this.reward = Objects.requireNonNull(reward, "reward must not be null");
        this.random = Objects.requireNonNull(random, "random must not be null");
    }

    @Override
    public boolean enter() {
        int enemyCount = enemy.generateCount(random);
        console.println("You entered the " + getName() + ".");
        console.println("Warning: " + enemyCount + " " + enemy.getName()
                + (enemyCount == 1 ? " is" : "s are") + " waiting here.");

        String choice = console.readChoice("<F>ight or <E>scape: ", FIGHT_OR_ESCAPE);
        if ("E".equals(choice)) {
            return true;
        }

        boolean cleared = fightEnemies(enemyCount);
        if (cleared) {
            grantLocationReward();
        }
        return player.isAlive();
    }

    private boolean fightEnemies(int enemyCount) {
        for (int index = 1; index <= enemyCount; index++) {
            enemy.resetHealth();
            console.println();
            console.println("Enemy " + index + " of " + enemyCount);
            printPlayerStats();
            printEnemyStats();

            while (player.isAlive() && enemy.isAlive()) {
                String choice = console.readChoice("<F>ight or <E>scape: ", FIGHT_OR_ESCAPE);
                if ("E".equals(choice)) {
                    enemy.resetHealth();
                    return false;
                }

                enemy.takeDamage(player.getTotalDamage());
                console.println("You attacked the " + enemy.getName() + ".");
                printHealthSummary();

                if (enemy.isAlive()) {
                    int receivedDamage = player.takeDamage(enemy.getDamage());
                    console.println("The " + enemy.getName() + " attacked you for "
                            + receivedDamage + " damage.");
                    printHealthSummary();
                }
            }

            if (!player.isAlive()) {
                return false;
            }

            player.earnMoney(enemy.getMoneyAward());
            console.println("You defeated the " + enemy.getName() + ".");
            console.println("Money earned: " + enemy.getMoneyAward());
            console.println("Current money: " + player.getMoney());
        }
        return true;
    }

    private void grantLocationReward() {
        if (player.getInventory().addReward(reward)) {
            console.println("Location cleared. You earned: " + reward.getDisplayName());
        } else {
            console.println("Location cleared. You already own the " + reward.getDisplayName() + ".");
        }
    }

    private void printPlayerStats() {
        console.println("Player Stats");
        console.println("------------");
        console.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        console.println("Damage: " + player.getTotalDamage());
        console.println("Money: " + player.getMoney());
        console.println("Weapon: " + player.getInventory().getWeapon().getDisplayName());
        console.println("Armor: " + player.getInventory().getArmor().getDisplayName());
    }

    private void printEnemyStats() {
        console.println();
        console.println(enemy.getName() + " Stats");
        console.println("------------");
        console.println("Health: " + enemy.getHealth());
        console.println("Damage: " + enemy.getDamage());
        console.println("Money award: " + enemy.getMoneyAward());
    }

    private void printHealthSummary() {
        console.println("Player health: " + player.getHealth());
        console.println(enemy.getName() + " health: " + enemy.getHealth());
    }
}
