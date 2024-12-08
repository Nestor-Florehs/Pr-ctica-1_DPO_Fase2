package Persistence.implementations;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import Business.Member;
import Business.Team;
import Persistence.CharacterDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Business.Character;

/**
 * This class implements the CharacterDao interface and provides functionality
 * to interact with character data stored in a JSON file.
 */
public class CharacterJsonDao implements CharacterDao {
    private static final String CHARACTER_FILENAME = "database/characters.json";

    /**
     * Retrieves all characters from a JSON file.
     *
     * @return a list of Character objects parsed from the JSON file. If the
     *         file does not exist or an error occurs, an empty list is returned.
     */
    private ArrayList<Character> getAllCharacters() {
        JSONParser parser = new JSONParser();
        ArrayList<Character> characters = new ArrayList<>();
        try {
            FileReader reader = new FileReader(CHARACTER_FILENAME);
            JSONArray array = (JSONArray) parser.parse(reader);
            for (Object o : array) {
                JSONObject character = (JSONObject) o;

                long id = (long) character.get("id");
                String name = (String) character.get("name");
                long weightLong = (long) character.get("weight");
                int weight = (int) weightLong;
                Character c = new Character(id, name, weight);
                characters.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return characters;
    }

    /**
     * Gets the names of all characters from the list.
     *
     * @return a list of character names. Returns an empty list if no
     *         characters exist.
     */
    public ArrayList<String> getAllCharactersName() {
        ArrayList<String> charactersName = new ArrayList<>();
        ArrayList<Character> characters = getAllCharacters();
        for (Character character : characters) {
            charactersName.add(character.getName());
        }
        return charactersName;
    }

    /**
     * Retrieves a character by its index from the list.
     *
     * @param index the index of the character (1-based).
     * @return the Character at the specified index.
     */
    public Character getCharacter(int index) {
        ArrayList<Character> characters = getAllCharacters();
        return characters.get(index - 1);
    }

    /**
     * Retrieves the members of a team enriched with character details.
     *
     * @param team the team whose members' characters are to be retrieved.
     * @return a list of enriched Member objects with character details.
     */
    public ArrayList<Member> getCharactersOfTeam(Team team) {
        ArrayList<Character> characters = getAllCharacters();
        ArrayList<Member> members = team.getMembers();
        ArrayList<Member> selectedMembers = new ArrayList<>();
        for (Member member : members) {
            for (Character character : characters) {
                if (character.getId() == member.getId()) {
                    selectedMembers.add(new Member(member.getId(), member.getStrategy()));
                    break;
                }
            }
        }
        return selectedMembers;
    }

    /**
     * Retrieves a character by its ID.
     *
     * @param id the ID of the character to retrieve.
     * @return the Character with the specified ID, or null if not found.
     */
    public Character getCharacterById(long id) {
        ArrayList<Character> characters = getAllCharacters();
        for (Character character : characters) {
            if (character.getId() == id) {
                return character;
            }
        }
        return null;
    }

    /**
     * Retrieves a character by its name.
     *
     * @param name the name of the character to retrieve.
     * @return the Character with the specified name, or null if not found.
     */
    public Character getCharacterByName(String name) {
        ArrayList<Character> characters = getAllCharacters();
        for (Character character : characters) {
            if (character.getName().equals(name)) {
                return character;
            }
        }
        return null;
    }

    /**
     * Validates the existence and path of a JSON file.
     *
     * @return true if the file exists and contains valid JSON; false otherwise.
     */
    public boolean validateJson() {
        File file = new File(CHARACTER_FILENAME);
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
