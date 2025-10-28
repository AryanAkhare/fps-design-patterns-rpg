package fps_rpg.behavioral;

import fps_rpg.creational.Enemy;
import fps_rpg.creational.PlayerCharacter;

public interface AttackStrategy {
    // returns true if attack occurred (ammo), and apply damage to enemy
    boolean attack(PlayerCharacter player, Enemy enemy);
    String name();
}
