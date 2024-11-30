package Business;

public class Member {
    private Long id;
    private String strategy;
    private String name;
    private Item armor;
    private Item weapon;
    private double damageReceived;
    private Character character;

    public Member(Long id, String strategy) {
        CharacterManager characterManager = new CharacterManager();
        String memberId = String.valueOf(id);

        this.id = id;
        this.strategy = strategy;
        character = characterManager.getCharacterByIdOrName(memberId);
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

    public double getAttackDamage () {
        double attackDamage;

        attackDamage = (double) (character.getWeight() * (1 - damageReceived)) / 10;
        attackDamage += (double) weapon.getPower() / 20;
        attackDamage += 18;
        weapon.useItem();

        if (weapon.getDurability() <= 0) {
            weapon = null;
            System.out.println("La arma se ha roto");
        }

        return attackDamage;
    }

    public void receiveDamage(double damageReceived) {
        double finalDamage;

        finalDamage = (damageReceived - (((double) (200 * (1 - damageReceived)) / character.getWeight()) + ((double) armor.getPower() / 20)) * 1.4 ) / 100;
        armor.useItem();

        if (armor.getDurability() <= 0) {
            armor = null;
            System.out.println("La arma se ha roto");
        }

        this.damageReceived += finalDamage;
    }
}
