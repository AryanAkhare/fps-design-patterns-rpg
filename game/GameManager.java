package fps_rpg.game;

import fps_rpg.creational.*;
import fps_rpg.structural.*;
import fps_rpg.behavioral.*;

public class GameManager {
    private final ConsoleIO io;
    private final CharacterFactory factory;
    private PlayerCharacter player;

    public GameManager() {
        this(new ConsoleConsoleIO(), new CharacterFactory());
    }

    public GameManager(ConsoleIO io, CharacterFactory factory) {
        this.io = io;
        this.factory = factory;
    }

    public void start() {
        displayWelcome();
        createPlayer();
        equipWeapon();
    // inject a default strategy (HipFire) and strategy provider into CombatSystem
    CombatSystem combat = new CombatSystem(io, new fps_rpg.behavioral.DefaultAttackStrategyProvider(), new HipFireStrategy());

        // First encounter
        io.println("\n--- Encounter: Grunt ---");
        Enemy grunt = factory.createEnemy("grunt");
        combat.startCombat(player, grunt);

        if (!player.isAlive()) { io.println("You died. Game over."); return; }

        // Boss encounter
        io.println("\n--- Encounter: Mech ---");
        Enemy mech = factory.createEnemy("mech");
        combat.startCombat(player, mech);

        if (player.isAlive()) io.println("\nMission Complete! You survived the demo.");
    }

    private void displayWelcome() {
        io.println("=== FPS-RPG CLI Demo — Factory/Decorator/Strategy ===\n");
    }

    private void createPlayer() {
        io.print("Enter your name: ");
        String name = io.readLine().trim();
        io.println("Choose class: 1) Assault  2) Sniper  3) Engineer");
        io.print("Choice: ");
        int c = 1;
        try { c = Integer.parseInt(io.readLine().trim()); } catch (Exception e) {}
        String type = switch (c) { case 2 -> "sniper"; case 3 -> "engineer"; default -> "assault"; };
        player = factory.createPlayer(type, name.isEmpty() ? "Player" : name);
        player.displayStats();
    }

    private void equipWeapon() {
        io.println("\nYou are given a base rifle.");
        Weapon rifle = new BaseRifle("AR-1", 10, 30);
        io.print("Add Scope? (y/n): "); if (io.readLine().trim().equalsIgnoreCase("y")) rifle = new ScopeAttachment(rifle);
        io.print("Add Extended Mag? (y/n): "); if (io.readLine().trim().equalsIgnoreCase("y")) rifle = new ExtendedMagAttachment(rifle);
        io.print("Add Silencer? (y/n): "); if (io.readLine().trim().equalsIgnoreCase("y")) rifle = new SilencerAttachment(rifle);
        player.equip(rifle);
        io.println("Equipped -> " + rifle.getDescription());
        player.displayStats();
    }
}
