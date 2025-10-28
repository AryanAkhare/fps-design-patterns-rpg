package fps_rpg.behavioral;

import fps_rpg.creational.Enemy;
import fps_rpg.creational.PlayerCharacter;

public class AimStrategy implements AttackStrategy {
    @Override
    public boolean attack(PlayerCharacter player, Enemy enemy) {
        // aim: increases accuracy (simulated by increasing effective damage via accuracy)
        if (!player.getWeapon().consumeBullet()) { System.out.println("Click! No ammo. Reload."); return false; }
        double acc = player.getAccuracy();
        int dmg = (int)Math.round(player.getAttackPower() * (0.9 + acc));
        System.out.println(player.getName() + " aims and fires for " + dmg + " damage.");
        enemy.takeDamage(dmg);
        return true;
    }
    @Override public String name() { return "Aim Shot"; }
}
