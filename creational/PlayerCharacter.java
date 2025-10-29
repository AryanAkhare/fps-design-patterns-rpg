package fps_rpg.creational;

import fps_rpg.structural.Weapon;

public abstract class PlayerCharacter {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int baseAttack;
    protected int baseDefense;
    protected double baseAccuracy;
    protected Weapon equipped;

    public PlayerCharacter(String name, int hp, int atk, int def, double acc) {
        this.name = name; this.maxHealth = hp; this.health = hp; this.baseAttack = atk; this.baseDefense = def; this.baseAccuracy = acc;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public boolean isAlive() { return health > 0; }
    public void takeDamage(int dmg) { int actual = Math.max(0, dmg - getDefense()); health -= actual; if (health < 0) health = 0; }
    public void heal(int amt) { health = Math.min(maxHealth, health + amt); }

    public void equip(Weapon w) { this.equipped = w; }

    public int getAttackPower() { return baseAttack + (equipped != null ? equipped.getBonusDamage() : 0); }
    public int getDefense() { return baseDefense + (equipped != null ? equipped.getBonusDefense() : 0); }
    public double getAccuracy() { return Math.min(1.0, baseAccuracy + (equipped != null ? equipped.getBonusAccuracy() : 0.0)); }

    public Weapon getEquippedWeapon() { return equipped; }

    /**
     * Return the reloadable adapter for the equipped weapon.
     * If the equipped weapon doesn't support Reloadable, return a no-op implementation.
     */
    public fps_rpg.structural.Reloadable getReloadableWeapon() {
        if (equipped instanceof fps_rpg.structural.Reloadable) return (fps_rpg.structural.Reloadable) equipped;
        return new fps_rpg.structural.Reloadable() {
            @Override public boolean consumeBullet() { return true; }
            @Override public void reload() { }
            @Override public int getAmmoInMag() { return Integer.MAX_VALUE; }
            @Override public int getMaxAmmo() { return Integer.MAX_VALUE; }
        };
    }

    public void displayStats() {
        System.out.println("\n-- " + name + " --");
        System.out.println("HP: " + health + "/" + maxHealth);
        System.out.println("Attack: " + getAttackPower() + " Defense: " + getDefense() + " Acc: " + String.format("%.2f", getAccuracy()));
        if (equipped != null) {
            fps_rpg.structural.Reloadable r = getReloadableWeapon();
            System.out.println("Weapon: " + equipped.getDescription() + " (" + r.getAmmoInMag() + "/" + r.getMaxAmmo() + ")");
        }
    }
}
