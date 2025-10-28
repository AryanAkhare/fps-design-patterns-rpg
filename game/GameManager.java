package fps_rpg.game;

import fps_rpg.creational.*;
import fps_rpg.structural.*;
import fps_rpg.behavioral.*;
import java.util.Scanner;

public class GameManager {
    private Scanner scanner = new Scanner(System.in);
    private PlayerCharacter player;

    public void start() {
        displayWelcome();
        createPlayer();
        equipWeapon();
        CombatSystem combat = new CombatSystem(scanner);

        // First encounter
        System.out.println("\n--- Encounter: Grunt ---");
        Enemy grunt = CharacterFactory.createEnemy("grunt");
        combat.startCombat(player, grunt);

        if (!player.isAlive()) { System.out.println("You died. Game over."); return; }

        // Boss encounter
        System.out.println("\n--- Encounter: Mech ---");
        Enemy mech = CharacterFactory.createEnemy("mech");
        combat.startCombat(player, mech);

        if (player.isAlive()) System.out.println("\nMission Complete! You survived the demo.");
    }

    private void displayWelcome() {
        System.out.println("=== FPS-RPG CLI Demo — Factory/Decorator/Strategy ===\n");
    }

    private void createPlayer() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        System.out.println("Choose class: 1) Assault  2) Sniper  3) Engineer");
        System.out.print("Choice: ");
        int c = 1;
        try { c = Integer.parseInt(scanner.nextLine().trim()); } catch (Exception e) {}
        String type = switch (c) { case 2 -> "sniper"; case 3 -> "engineer"; default -> "assault"; };
        player = CharacterFactory.createPlayer(type, name.isEmpty() ? "Player" : name);
        player.displayStats();
    }

    private void equipWeapon() {
        System.out.println("\nYou are given a base rifle.");
        Weapon rifle = new BaseRifle("AR-1", 10, 30);
        System.out.print("Add Scope? (y/n): "); if (scanner.nextLine().trim().equalsIgnoreCase("y")) rifle = new ScopeAttachment(rifle);
        System.out.print("Add Extended Mag? (y/n): "); if (scanner.nextLine().trim().equalsIgnoreCase("y")) rifle = new ExtendedMagAttachment(rifle);
        System.out.print("Add Silencer? (y/n): "); if (scanner.nextLine().trim().equalsIgnoreCase("y")) rifle = new SilencerAttachment(rifle);
        player.equip(rifle);
        System.out.println("Equipped -> " + rifle.getDescription());
        player.displayStats();
    }
}
