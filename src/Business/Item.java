package Business;

/**
 * This class represents an item with specific attributes like ID, name, power, durability, and type.
 * Items can be of different types, such as "Weapon" or "Armor".
 */
public class Item {
    private long id;
    private String name;
    private int power;
    private int durability;
    private String type;

    /**
     * Constructs a new Item object with the specified attributes.
     *
     * @param id the ID of the item.
     * @param name the name of the item.
     * @param power the power of the item.
     * @param durability the durability of the item.
     * @param type the type of the item (e.g., "Weapon", "Armor").
     */
    public Item(long id, String name, int power, int durability, String type) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.durability = durability;
        this.type = type;
    }

    /**
     * Returns a string representation of the item's attributes.
     *
     * @return a formatted string containing the item's ID, name, type, power, and durability.
     */
    public String toString(){
        String itemAttributes;
        itemAttributes = "\n\tID:\t\t\t" + id;
        itemAttributes += "\n\tNAME:\t\t"  + name;
        itemAttributes += "\n\tCLASS:\t\t" + type;
        itemAttributes += "\n\tPOWER:\t\t" + power;
        itemAttributes += "\n\tDURABILITY:\t" + durability;
        return itemAttributes;
    }

    /**
     * Returns the ID of the item.
     *
     * @return the ID of the item.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the item.
     *
     * @return the type of the item (e.g., "Weapon", "Armor").
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the power of the item.
     *
     * @return the power of the item.
     */
    public int getPower() {
        return power;
    }

    /**
     * Returns the durability of the item.
     *
     * @return the durability of the item.
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Decreases the durability of the item by one.
     */
    public void useItem() {
        durability--;
    }
}