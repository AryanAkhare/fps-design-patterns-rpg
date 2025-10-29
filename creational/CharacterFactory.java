package fps_rpg.creational;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Registry-based factory to allow extension without modifying this class.
 * Register new player/enemy creators via registerPlayer/registerEnemy.
 */
public class CharacterFactory {
    private final Map<String, Function<String, PlayerCharacter>> playerCreators = new HashMap<>();
    private final Map<String, Supplier<Enemy>> enemyCreators = new HashMap<>();

    public CharacterFactory() {
        // default registrations
        registerPlayer("sniper", Sniper::new);
        registerPlayer("engineer", Engineer::new);
        registerPlayer("assault", Assault::new);

        registerEnemy("mech", () -> new Enemy("Mech", "Mech", 220, 30, 18, 0.6));
        registerEnemy("grunt", () -> new Enemy("Grunt", "Grunt", 60, 12, 4, 0.6));
    }

    public void registerPlayer(String key, Function<String, PlayerCharacter> creator) {
        playerCreators.put(key.toLowerCase(), creator);
    }

    public void registerEnemy(String key, Supplier<Enemy> creator) {
        enemyCreators.put(key.toLowerCase(), creator);
    }

    public PlayerCharacter createPlayer(String type, String name) {
        Function<String, PlayerCharacter> f = playerCreators.get(type.toLowerCase());
        if (f != null) return f.apply(name);
        // fallback to assault
        return new Assault(name);
    }

    public Enemy createEnemy(String type) {
        Supplier<Enemy> s = enemyCreators.get(type.toLowerCase());
        if (s != null) return s.get();
        return new Enemy("Grunt", "Grunt", 60, 12, 4, 0.6);
    }
}
