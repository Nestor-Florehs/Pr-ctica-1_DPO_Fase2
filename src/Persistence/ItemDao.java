package Persistence;

import Business.Item;

import java.util.ArrayList;

/**
 * This interface defines the methods required for interacting with team data
 * in a data source, such as a JSON file or database. Implementations of this interface
 * will provide specific mechanisms for retrieving team information.
 */
public interface ItemDao {

    ArrayList<String> getAllItemsName();

    Item getItem(int index);

    Item getRandomWeapon();

    Item getRandomArmor();

    boolean validateJson();

}