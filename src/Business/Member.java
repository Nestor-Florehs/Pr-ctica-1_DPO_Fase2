package Business;

/**
 * Represents a member in a team, containing their attributes such as ID, strategy,
 * armor, weapon, and the associated character. Members can receive damage and attack others.
 */
public class Member {
    private Long id;
    private String strategy;
    private String name;
    private Item armor;
    private Item weapon;
    private double damageReceived;
    private Character character;
    private boolean isKO;
    private boolean isDefending;

    /**
     ** Constructs a new Member object with the specified attributes.
     *
     * @param id the ID of the member.
     * @param strategy the strategy of the member.
     */
    public Member(Long id, String strategy) {
        CharacterManager characterManager = new CharacterManager();
        String memberId = String.valueOf(id);

        this.id = id;
        this.strategy = strategy;
        character = characterManager.getCharacterByIdOrName(memberId);
        isKO = false;
        isDefending = false;
    }

    /**
     * Returns the ID of the member.
     *
     * @return the ID of the member.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the strategy of the member.
     *
     * @return the strategy of the member.
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * Sets the name of the member.
     *
     * @param name the name of the member.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the member.
     *
     * @return the name of the member.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the member, including ID, strategy, armor, 
     * and weapon (if available).
     *
     * @return a formatted string representation of the member.
     */
    public String toString() {
        String memberString;

        memberString = "Member id: " + id;
        memberString += "\n\tMember strategy: " + strategy;
        memberString += "\n\tMember Armor: " + armor.toString();
        if (weapon !=null) {
            memberString += "\n\tMember Weapon: " + weapon.toString();
        }

        return memberString;
    }

    /**
     * Sets the member as KO.
     */
    public void setKO() {
        isKO = true;
    }

    /**
     * Sets the armor for the member.
     *
     * @param armor the armor to be equipped.
     */
    public void setArmor(Item armor) {
        this.armor = armor;
    }

    /**
     * Sets the weapon for the member.
     *
     * @param weapon the weapon to be equipped.
     */
    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    /**
     * Returns the armor equipped by the member.
     *
     * @return the armor item.
     */
    public Item getArmor() {
        return armor;
    }

    /**
     * Returns the weapon equipped by the member.
     *
     * @return the weapon item.
     */
    public Item getWeapon() {
        return weapon;
    }

    /**
     * Sets the damage received by the member.
     *
     * @param damageReceived the amount of damage received.
     */
    public void setDamageReceived(int damageReceived) {
        this.damageReceived = damageReceived;
    }

    /**
     * Calculates and returns the attack damage of the member based on their character's weight,
     * weapon durability, and weapon power.
     *
     * @return the calculated attack damage.
     */
    public double getAttackDamage () {
        double attackDamage;

        attackDamage = (double) (character.getWeight() * (1 - damageReceived)) / 10;

        if (weapon.getDurability() <= 0) {
            weapon = null;
        } else {
            weapon.useItem();
            attackDamage += (double) weapon.getPower() / 20;
            attackDamage += 18;
        }

        return attackDamage;
    }

    /**
     * Calculates and applies the damage received by the member. The damage is affected by the
     * armor (if equipped), character's weight, and whether the member is defending.
     *
     * @param damageReceived the incoming damage.
     * @return the final damage after accounting for defense and armor.
     */
    public double receiveDamage(double damageReceived) {
        double finalDamage;

        if (armor == null) {
            finalDamage = (damageReceived - (((double) (200 * (1 - this.damageReceived)) / character.getWeight())) * 1.4 ) / 100;
        } else {
            finalDamage = (damageReceived - (((double) (200 * (1 - this.damageReceived)) / character.getWeight()) + ((double) armor.getPower() / 20)) * 1.4 ) / 100;
            armor.useItem();
        }

        if (!isDefending) {
            this.damageReceived += finalDamage;
        } else {
            finalDamage = finalDamage - ((double) character.getWeight()/400);
            isDefending = false;
        }

        return finalDamage;
    }

    /**
     * Sets the member to defend, reducing incoming damage.
     */
    public void setDefending() {
        isDefending = true;
    }

    /**
     * Returns the damage percentage the member has received as a string, or "KO" if the member is knocked out.
     *
     * @return a string representing the damage percentage or KO status.
     */
    public String getPercentageOfDamage() {
        if (isKO) {
            return " (KO) ";
        } else {
            return " (" + String.format("%.2f", damageReceived * 100) + "%" + ") ";
        }
    }

    /**
     * Returns the total damage received by the member.
     *
     * @return the total damage received.
     */
    public double getDamageReceived() {
        return damageReceived;
    }

    /**
     * Returns whether the member is KO.
     *
     * @return true if the member is KO, false otherwise.
     */
    public boolean getKO() {
        return isKO;
    }
}