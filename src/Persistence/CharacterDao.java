package Persistence;

import Business.Character;
import Business.Member;
import Business.Team;

import java.util.ArrayList;

/**
 * This interface defines the methods required for interacting with character data
 * in a data source, such as a JSON file or database. Implementations of this interface
 * will provide specific mechanisms for retrieving character information.
 */
public interface CharacterDao {

    ArrayList<String> getAllCharactersName();

    Character getCharacter(int index);

    ArrayList<Member> getCharactersOfTeam(Team team);

    Character getCharacterById(long id);

    Character getCharacterByName(String name);

    boolean validateJson();

}