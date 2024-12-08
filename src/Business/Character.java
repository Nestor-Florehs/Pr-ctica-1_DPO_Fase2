package Business;

/**
 * Represents a character with attributes such as ID, name, and weight.
 */
public class Character {
    private long id;
    private String name;
    private int weight;

    /**
     * Constructs a new Character object with the specified attributes.
     *
     * @param id the ID of the character.
     * @param name the name of the character.
     * @param weight the weight of the character.
     */
    public Character(long id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    /**
     * Returns a string representation of the character's attributes.
     *
     * @return a formatted string containing the character's ID, name, and weight.
     */
    public String toString() {
        String characterAttributes;

        characterAttributes = "\n\tID:\t\t" + id;
        characterAttributes += "\n\tNAME:\t" + name;
        characterAttributes += "\n\tWEIGHT:\t" + weight;

        return characterAttributes;
    }

    /**
     * Returns the ID of the character.
     *
     * @return the ID of the character.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of the character.
     *
     * @return the name of the character.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the weight of the character.
     *
     * @return the weight of the character.
     */
    public int getWeight() {
        return weight;
    }
}
