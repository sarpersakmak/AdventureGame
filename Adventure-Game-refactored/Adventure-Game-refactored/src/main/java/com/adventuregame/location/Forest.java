package com.adventuregame.location;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.model.Enemy;
import com.adventuregame.model.Player;
import com.adventuregame.model.Reward;

import java.util.Random;

/**
 * Battle location that rewards firewood after all vampires are defeated.
 */
public final class Forest extends BattleLocation {

    public Forest(Player player, ConsoleIO console, Random random) {
        super(player, console, "Forest", new Enemy("Vampire", 4, 14, 7, 3), Reward.FIREWOOD, random);
    }
}
