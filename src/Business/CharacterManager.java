package Business;
import java.util.ArrayList;
import Persistence.CharacterDao;
import Business.Character;
import Persistence.TeamDao;

public class CharacterManager {

    public ArrayList<String> listCharacters() {
        ArrayList<String> listCharactersName;
        CharacterDao characterDao = new CharacterDao();
        listCharactersName = characterDao.getAllCharactersName();
        return listCharactersName;
    }

    public Character listCharacterAttribute(int index) {
        Character character;
        CharacterDao characterDao = new CharacterDao();
        character = characterDao.getCharacter(index);

        return character;
    }

    public ArrayList<String> listTeams() {
        ArrayList<String> listTeamsName;
        TeamDao teamDao = new TeamDao();
        listTeamsName = teamDao.getAllTeamNames();
        return listTeamsName;
    }
}
