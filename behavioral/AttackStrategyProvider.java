package fps_rpg.behavioral;

import java.util.Map;

/**
 * Abstraction for providing available AttackStrategy options at runtime.
 */
public interface AttackStrategyProvider {
    /**
     * Map option number -> label
     */
    Map<Integer, String> getMenuOptions();

    /**
     * Return an AttackStrategy instance for the chosen option.
     * If the choice doesn't map to a strategy, return the currentStrategy unchanged.
     */
    AttackStrategy getStrategyForChoice(int choice, AttackStrategy currentStrategy);
}
