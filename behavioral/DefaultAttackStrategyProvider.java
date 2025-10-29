package fps_rpg.behavioral;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Default registry of common attack strategies used by the demo.
 */
public class DefaultAttackStrategyProvider implements AttackStrategyProvider {
    private final Map<Integer, Supplier<AttackStrategy>> registry = new LinkedHashMap<>();
    private final Map<Integer, String> labels = new LinkedHashMap<>();

    public DefaultAttackStrategyProvider() {
        // keep ordering predictable for menu display
        registry.put(1, () -> new HipFireStrategy()); labels.put(1, "Shoot (Hip-Fire)");
        registry.put(2, () -> new AimStrategy()); labels.put(2, "Aim Shot");
        registry.put(3, () -> new SuppressiveStrategy()); labels.put(3, "Suppressive Fire");
    }

    @Override public Map<Integer, String> getMenuOptions() { return labels; }

    @Override public AttackStrategy getStrategyForChoice(int choice, AttackStrategy currentStrategy) {
        Supplier<AttackStrategy> s = registry.get(choice);
        if (s != null) return s.get();
        return currentStrategy;
    }
}
