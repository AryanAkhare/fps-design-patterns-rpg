package fps_rpg.structural;

public class SilencerAttachment extends WeaponDecorator {
    private int dmgPenalty = 2;
    private double accPenalty = -0.05;
    public SilencerAttachment(Weapon w) { super(w); }
    @Override public String getDescription() { return wrappee.getDescription() + " + Silencer"; }
    @Override public int getBonusDamage() { return Math.max(0, wrappee.getBonusDamage() - dmgPenalty); }
    @Override public double getBonusAccuracy() { return Math.max(0.0, wrappee.getBonusAccuracy() + accPenalty); }
}
