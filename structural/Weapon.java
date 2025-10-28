package fps_rpg.structural;

public interface Weapon {
    String getDescription();
    int getBonusDamage();
    int getBonusDefense();
    double getBonusAccuracy();
    boolean consumeBullet();
    void reload();
    int getAmmoInMag();
    int getMaxAmmo();
}
