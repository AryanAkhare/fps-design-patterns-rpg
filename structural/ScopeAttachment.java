package fps_rpg.structural;

public class ScopeAttachment extends WeaponDecorator {
    private double accBonus = 0.15;
    public ScopeAttachment(Weapon w) { super(w); }
    @Override public String getDescription() { return wrappee.getDescription() + " + Scope"; }
    @Override public double getBonusAccuracy() { return wrappee.getBonusAccuracy() + accBonus; }
}
