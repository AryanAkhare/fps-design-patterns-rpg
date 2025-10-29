package fps_rpg.structural;

public abstract class WeaponDecorator implements Weapon, Reloadable {
    protected final Weapon wrappee;
    protected final Reloadable reloadableWrappee;
    protected WeaponDecorator(Weapon w) { this.wrappee = w; this.reloadableWrappee = (w instanceof Reloadable) ? (Reloadable) w : new NoReloadable(); }

    @Override public String getDescription() { return wrappee.getDescription(); }
    @Override public int getBonusDamage() { return wrappee.getBonusDamage(); }
    @Override public int getBonusDefense() { return wrappee.getBonusDefense(); }
    @Override public double getBonusAccuracy() { return wrappee.getBonusAccuracy(); }

    // Delegate reload behavior
    @Override public boolean consumeBullet() { return reloadableWrappee.consumeBullet(); }
    @Override public void reload() { reloadableWrappee.reload(); }
    @Override public int getAmmoInMag() { return reloadableWrappee.getAmmoInMag(); }
    @Override public int getMaxAmmo() { return reloadableWrappee.getMaxAmmo(); }

    // Null-object for non-reloadable base
    private static class NoReloadable implements Reloadable {
        @Override public boolean consumeBullet() { return true; }
        @Override public void reload() { }
        @Override public int getAmmoInMag() { return Integer.MAX_VALUE; }
        @Override public int getMaxAmmo() { return Integer.MAX_VALUE; }
    }
}
