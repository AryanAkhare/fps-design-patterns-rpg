package fps_rpg.structural;

public class ExtendedMagAttachment extends WeaponDecorator {
    private int extra = 15;
    public ExtendedMagAttachment(Weapon w) { super(w); }
    @Override public String getDescription() { return wrappee.getDescription() + " + ExtMag"; }
    @Override public int getMaxAmmo() { return wrappee.getMaxAmmo() + extra; }
    @Override public int getAmmoInMag() { return Math.min(wrappee.getAmmoInMag() + extra, getMaxAmmo()); }
}
