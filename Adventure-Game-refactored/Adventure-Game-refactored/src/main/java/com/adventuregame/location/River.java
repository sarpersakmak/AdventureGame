package com.adventuregame.location;

import com.adventuregame.io.ConsoleIO;
import com.adventuregame.model.Enemy;
import com.adventuregame.model.Player;
import com.adventuregame.model.Reward;

import java.util.Random;

/**
 * Battle location that rewards water after all bears are defeated.
 */
public final class River extends BattleLocation {

    public River(Player player, ConsoleIO console, Random random) {
        super(player, console, "River", new Enemy("Bear", 7, 20, 12, 2), Reward.WATER, random);
    }
}
