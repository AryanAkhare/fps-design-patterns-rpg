package fps_rpg.game;

import fps_rpg.creational.*;
import fps_rpg.behavioral.*;

/**
 * Combat engine separated from IO. Uses injected ConsoleIO and a default AttackStrategy.
 */
public class CombatSystem {
    private final ConsoleIO io;
    private final AttackStrategy defaultStrategy;
    private final fps_rpg.behavioral.AttackStrategyProvider strategyProvider;

    public CombatSystem(ConsoleIO io, fps_rpg.behavioral.AttackStrategyProvider strategyProvider, AttackStrategy defaultStrategy) {
        this.io = io;
        this.strategyProvider = strategyProvider;
        this.defaultStrategy = defaultStrategy;
    }

    public void startCombat(PlayerCharacter player, Enemy enemy) {
        io.println("\n=== COMBAT STARTED ===");
        enemy.displayInfo();
        AttackStrategy currentStrategy = defaultStrategy;

        while (player.isAlive() && enemy.isAlive()) {
            io.println("\nPlayer HP: " + player.getHealth() + "/" + player.getMaxHealth());
            io.println("Enemy HP: " + enemy.getHealth());
            // build menu from provider
            StringBuilder menu = new StringBuilder("Actions: ");
            for (var e : strategyProvider.getMenuOptions().entrySet()) {
                menu.append(e.getKey()).append(") ").append(e.getValue()).append("  ");
            }
            menu.append("4) Reload  5) Status");
            io.println(menu.toString());
            io.print("Choice: ");
            int choice = 1;
            try { choice = Integer.parseInt(io.readLine().trim()); } catch (Exception e) { }

            switch (choice) {
                case 4: player.getReloadableWeapon().reload(); io.println("Reloaded."); break;
                case 5: player.displayStats(); enemy.displayInfo(); break;
                default:
                    // ask provider for a strategy if the choice matches a strategy option
                    currentStrategy = strategyProvider.getStrategyForChoice(choice, currentStrategy);
                    boolean fired = currentStrategy.attack(player, enemy);
                    if (!fired) { /* no ammo */ }
            }

            if (!enemy.isAlive()) { io.println("Enemy defeated!"); break; }

            // enemy turn
            int ed = enemy.getAttackPower();
            player.takeDamage(ed);
            io.println(enemy.getName() + " hits you for " + Math.max(0, ed - player.getDefense()) + " damage.");
        }
    }
}
