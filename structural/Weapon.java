package fps_rpg.structural;

/**
 * Weapon interface for static/stat modifiers only (ISP):
 * ammo-related behavior moved to `Reloadable`.
 */
public interface Weapon {
    String getDescription();
    int getBonusDamage();
    int getBonusDefense();
    double getBonusAccuracy();
}
