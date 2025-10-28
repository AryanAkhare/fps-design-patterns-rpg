package fps_rpg.creational;

public class Enemy {
    private String name;
    private String type;
    private int health;
    private int attackPower;
    private int defense;
    private double accuracy;

    public Enemy(String name, String type, int hp, int atk, int def, double acc) {
        this.name = name; this.type = type; this.health = hp; this.attackPower = atk; this.defense = def; this.accuracy = acc;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttackPower() { return attackPower; }
    public int getDefense() { return defense; }
    public double getAccuracy() { return accuracy; }

    public boolean isAlive() { return health > 0; }

    public void takeDamage(int raw) { int actual = Math.max(0, raw - defense); health -= actual; if (health < 0) health = 0; }

    public void displayInfo() { System.out.println(type + " " + name + " HP:" + health + " ATK:" + attackPower + " DEF:" + defense); }
}
