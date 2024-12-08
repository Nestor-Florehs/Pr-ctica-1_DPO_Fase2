package Persistence.implementations;

import Business.Item;
import Persistence.ItemDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the ItemDao interface and provides functionality
 * to interact with item data stored in a JSON file.
 */
public class ItemJsonDao implements ItemDao {
    private static final String ITEM_FILENAME = "database/items.json";

    /**
     * Retrieves all items from a JSON file.
     *
     * @return a list of Item objects parsed from the JSON file. If the
     *         file does not exist or an error occurs, an empty list is returned.
     */
    private ArrayList<Item> getAllItems() {
        JSONParser parser = new JSONParser();
        ArrayList<Item> items = new ArrayList<>();

        try {
            FileReader reader = new FileReader(ITEM_FILENAME);

            JSONArray array = (JSONArray) parser.parse(reader);

            for (Object o : array) {
                JSONObject item = (JSONObject) o;

                // Extract fields
                long id = (long) item.get("id");
                String name = (String) item.get("name");
                long powerLong = (long) item.get("power");
                long durabilityLong = (long) item.get("durability");
                String type = (String) item.get("class");


                int power = (int) powerLong;
                int durability = (int) durabilityLong;


                Item i = new Item(id, name, power, durability, type);
                items.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    /**
     * Retrieves the names of all items.
     *
     * @return a list of item names.
     */
    public ArrayList<String> getAllItemsName() {
        ArrayList<String> itemsName = new ArrayList<>();
        ArrayList<Item> items = getAllItems();
        for (Item item : items) {
            itemsName.add(item.getName());
        }
        return itemsName;
    }

    /**
     * Retrieves an item by its index.
     *
     * @param index the position of the item in the list.
     * @return the item at the specified index.
     */
    public Item getItem(int index) {
        ArrayList<Item> items = getAllItems();
        return items.get(index - 1);
    }

    /**
     * Retrieves a random item of type "Weapon".
     *
     * @return a randomly selected weapon.
     */
    public Item getRandomWeapon() {
        ArrayList<Item> items = getAllItems();

        List<Item> weapons = items.stream()
                .filter(n -> n.getType().equals("Weapon"))
                .toList();

        Item randomItem = weapons.get((int) (Math.random() * weapons.size()));
        return randomItem;
    }

    /**
     * Retrieves a random item of type "Armor".
     *
     * @return a randomly selected armor.
     */
    public Item getRandomArmor() {
        ArrayList<Item> items = getAllItems();

        List<Item> armor = items.stream()
                .filter(n -> n.getType().equals("Armor"))
                .toList();

        Item randomItem = armor.get((int) (Math.random() * armor.size()));
        return randomItem;
    }

    /**
     * Validates the existence and path of a JSON file.
     *
     * @return true if the file exists and contains valid JSON; false otherwise.
     */
    public boolean validateJson() {
        File file = new File(ITEM_FILENAME);
        if (!file.exists()) {
            return false;
        }
        try (FileReader reader = new FileReader(file)) {
            JSONParser parser = new JSONParser();
            parser.parse(reader);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
