package fps_rpg.behavioral;

import fps_rpg.creational.Enemy;
import fps_rpg.creational.PlayerCharacter;

public class HipFireStrategy implements AttackStrategy {
    @Override
    public boolean attack(PlayerCharacter player, Enemy enemy) {
        // hip-fire: no extra accuracy, full attack
        if (!player.getWeapon().consumeBullet()) { System.out.println("Click! No ammo. Reload."); return false; }
        int dmg = player.getAttackPower();
        System.out.println(player.getName() + " hip-fires for " + dmg + " raw damage.");
        enemy.takeDamage(dmg);
        return true;
    }
    @Override public String name() { return "Hip-Fire"; }
}
