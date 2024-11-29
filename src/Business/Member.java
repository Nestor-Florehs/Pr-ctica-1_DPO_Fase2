package Business;

public class Member {
    private Long id;
    private String strategy;
    private String name;
    private Item armor;
    private Item weapon;
    private int damageReceived;

    public Member(Long id, String strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    public long getId() {
        return id;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String memberString;

        memberString = "Member id: " + id;
        memberString += "\n\tMember strategy: " + strategy;
        memberString += "\n\tMember Armor: " + armor.toString();
        memberString += "\n\tMember Weapon: " + weapon.toString();

        return memberString;
    }

    public void setArmor(Item armor) {
        this.armor = armor;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public Item getArmor() {
        return armor;
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setDamageReceived(int damageReceived) {
        this.damageReceived = damageReceived;
    }
}
