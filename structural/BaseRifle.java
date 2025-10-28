package fps_rpg.structural;

public class BaseRifle implements Weapon {
    private String name;
    private int bonusDamage;
    private int maxAmmo;
    private int ammoInMag;

    public BaseRifle(String name, int bonusDamage, int maxAmmo) {
        this.name = name; this.bonusDamage = bonusDamage; this.maxAmmo = maxAmmo; this.ammoInMag = maxAmmo;
    }

    @Override public String getDescription() { return name; }
    @Override public int getBonusDamage() { return bonusDamage; }
    @Override public int getBonusDefense() { return 0; }
    @Override public double getBonusAccuracy() { return 0.0; }
    @Override public boolean consumeBullet() { if (maxAmmo<=0) return true; if (ammoInMag<=0) return false; ammoInMag--; return true; }
    @Override public void reload() { ammoInMag = maxAmmo; }
    @Override public int getAmmoInMag() { return ammoInMag; }
    @Override public int getMaxAmmo() { return maxAmmo; }
}
