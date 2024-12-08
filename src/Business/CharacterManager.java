package Business;
import java.util.ArrayList;

import Persistence.CharacterDao;
import Persistence.implementations.CharacterJsonDao;
import Presentation.Input;

/**
 * Manages the operations related to characters, including retrieving
 * character details and characters.
 */
public class CharacterManager {
    private final CharacterDao characterDao;

    /**
     * Constructs a new CharacterManager with an instance of CharacterJsonDao.
     */
    public CharacterManager() {
        this.characterDao = new CharacterJsonDao();
    }

    /**
     * Retrieves a list of all character names.
     *
     * @return an ArrayList of strings containing the names of all characters.
     */
    public ArrayList<String> listCharacters() {
        ArrayList<String> listCharactersName;
        listCharactersName = characterDao.getAllCharactersName();
        return listCharactersName;
    }

    /**
     * Retrieves the attributes of a character by its index.
     *
     * @param index the index of the character to retrieve.
     * @return a Character object containing the attributes of the specified character.
     */
    public Character listCharacterAttribute(int index) {
        Character character;
        character = characterDao.getCharacter(index);
        return character;
    }

    /**
     * Retrieves all members of a specified team and updates their names.
     *
     * @param team the team whose members will be retrieved.
     * @return an ArrayList of Member objects with updated names.
     */
    public ArrayList<Member> getCharactersOfTeam(Team team) {
        ArrayList<Member> members;
        members = characterDao.getCharactersOfTeam(team);
        for (Member member : members) {
            member.setName(characterDao.getCharacterById(member.getId()).getName());
        }
        return members;
    }

    /**
     * Retrieves a character by its ID or name.
     *
     * @param input a string representing the ID or name of the character.
     * @return the Character object matching the given ID or name, or null if not found.
     */
    public Character getCharacterByIdOrName(String input) {
        try {
            Long id = Long.parseLong(input);
            Character character = characterDao.getCharacterById(id);
            return character;
        } catch (Exception e) {
            Character character = characterDao.getCharacterByName(input);
            return character;
        }
    }

    /**
     * Check the validity of the json file.
     *
     * @return True if the character.json exist and False if not exist.
     */
    public boolean validateCharacterJson() {
        return characterDao.validateJson();
    }
}
