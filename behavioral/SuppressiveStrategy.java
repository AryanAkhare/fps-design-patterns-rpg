package fps_rpg.behavioral;

import fps_rpg.creational.Enemy;
import fps_rpg.creational.PlayerCharacter;

public class SuppressiveStrategy implements AttackStrategy {
    @Override
    public boolean attack(PlayerCharacter player, Enemy enemy) {
        // suppressive: lower per-shot damage but may apply small defense reduction on enemy (not modeled fully)
        if (!player.getWeapon().consumeBullet()) { System.out.println("Click! No ammo. Reload."); return false; }
        int dmg = Math.max(1, player.getAttackPower() / 2);
        System.out.println(player.getName() + " suppresses fire for " + dmg + " damage.");
        enemy.takeDamage(dmg);
        return true;
    }
    @Override public String name() { return "Suppressive Fire"; }
}
