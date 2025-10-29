package fps_rpg.structural;

/**
 * Separate interface for reload/consumption behavior to satisfy ISP.
 */
public interface Reloadable {
    boolean consumeBullet();
    void reload();
    int getAmmoInMag();
    int getMaxAmmo();
}
