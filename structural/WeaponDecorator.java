package fps_rpg.structural;

public abstract class WeaponDecorator implements Weapon {
    protected final Weapon wrappee;
    protected WeaponDecorator(Weapon w) { this.wrappee = w; }
    @Override public String getDescription() { return wrappee.getDescription(); }
    @Override public int getBonusDamage() { return wrappee.getBonusDamage(); }
    @Override public int getBonusDefense() { return wrappee.getBonusDefense(); }
    @Override public double getBonusAccuracy() { return wrappee.getBonusAccuracy(); }
    @Override public boolean consumeBullet() { return wrappee.consumeBullet(); }
    @Override public void reload() { wrappee.reload(); }
    @Override public int getAmmoInMag() { return wrappee.getAmmoInMag(); }
    @Override public int getMaxAmmo() { return wrappee.getMaxAmmo(); }
}
