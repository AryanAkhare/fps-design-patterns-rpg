package fps_rpg.game;

import fps_rpg.creational.*;
import fps_rpg.behavioral.*;
import java.util.Scanner;

public class CombatSystem {
    private Scanner scanner;
    public CombatSystem(Scanner scanner) { this.scanner = scanner; }

    public void startCombat(PlayerCharacter player, Enemy enemy) {
        System.out.println("\n=== COMBAT STARTED ===");
        enemy.displayInfo();
        AttackStrategy currentStrategy = new HipFireStrategy();

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\nPlayer HP: " + player.getHealth() + "/" + player.getMaxHealth());
            System.out.println("Enemy HP: " + enemy.getHealth());
            System.out.println("Actions: 1) Shoot  2) Aim  3) Suppressive  4) Reload  5) Status");
            System.out.print("Choice: ");
            int choice = 1;
            try { choice = Integer.parseInt(scanner.nextLine().trim()); } catch (Exception e) {}

            switch (choice) {
                case 2: currentStrategy = new AimStrategy(); break;
                case 3: currentStrategy = new SuppressiveStrategy(); break;
                case 4: player.getWeapon().reload(); System.out.println("Reloaded."); break;
                case 5: player.displayStats(); enemy.displayInfo(); break;
                default:
                    // shoot using selected strategy
                    boolean fired = currentStrategy.attack(player, enemy);
                    if (!fired) { /* no ammo */ }
            }

            if (!enemy.isAlive()) { System.out.println("Enemy defeated!"); break; }

            // enemy turn
            int ed = enemy.getAttackPower();
            player.takeDamage(ed);
            System.out.println(enemy.getName() + " hits you for " + Math.max(0, ed - player.getDefense()) + " damage.");
        }
    }
}
