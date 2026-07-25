package com.adventuregame.location;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.model.Enemy;
import com.adventuregame.model.Player;
import com.adventuregame.model.Reward;

import java.util.Random;

/**
 * Battle location that rewards food after all zombies are defeated.
 */
public final class Cave extends BattleLocation {

    public Cave(Player player, ConsoleIO console, Random random) {
        super(player, console, "Cave", new Enemy("Zombie", 3, 10, 4, 3), Reward.FOOD, random);
    }
}
