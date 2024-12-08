package Business;

import Persistence.ItemDao;
import Persistence.implementations.ItemJsonDao;

import java.util.ArrayList;

/**
 * Manages the operations related to items, including retrieving
 * item details and items.
 */
public class ItemManager {
    private final ItemDao itemDao;

    /**
     * Constructs an ItemManager object, initializing the DAO for item management.
     */
    public ItemManager() {
        this.itemDao = new ItemJsonDao();
    }

    /**
     * Retrieves a list of all item names.
     *
     * @return a list of item names as strings.
     */
    public ArrayList<String> listItems() {
        ArrayList<String> listItemsName = itemDao.getAllItemsName();
        return listItemsName;
    }

    /**
     * Retrieves the attributes of an item by its index.
     *
     * @param index the index of the item to retrieve.
     * @return the `Item` object at the specified index.
     */
    public Item listItemAttribute(int index) {
        Item item;
        item = itemDao.getItem(index);
        return item;
    }

    /**
     * Retrieves a random weapon from the available items.
     *
     * @return a randomly selected weapon.
     */
    public Item getRandomWeapon() {
        Item item;
        item = itemDao.getRandomWeapon();
        return item;
    }

    /**
     * Retrieves a random armor from the available items.
     *
     * @return a randomly selected armor.
     */
    public Item getRandomArmor() {
        Item item;
        item = itemDao.getRandomArmor();
        return item;
    }

    /**
     * Check the validity of the json file.
     *
     * @return True if the item.json exist and False if not exist.
     */
    public boolean validateItemJson() {
        return itemDao.validateJson();
    }
}