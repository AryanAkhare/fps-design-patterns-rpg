package fps_rpg.creational;

public class CharacterFactory {
    // Factory Method: centralized creation of player types and enemies
    public static PlayerCharacter createPlayer(String type, String name) {
        switch (type.toLowerCase()) {
            case "sniper": return new Sniper(name);
            case "engineer": return new Engineer(name);
            default: return new Assault(name);
        }
    }

    public static Enemy createEnemy(String type) {
        switch (type.toLowerCase()) {
            case "mech": return new Enemy("Mech", "Mech", 220, 30, 18, 0.6);
            case "grunt":
            default: return new Enemy("Grunt", "Grunt", 60, 12, 4, 0.6);
        }
    }
}
