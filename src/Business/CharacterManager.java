package Business;
import java.util.ArrayList;
import Persistence.CharacterDao;

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

    public ArrayList<Member> getCharactersOfTeam(Team team) {
        ArrayList<Member> members;
        CharacterDao characterDao = new CharacterDao();

        members = characterDao.getCharactersOfTeam(team);

        for (Member member : members) {
            member.setName(characterDao.getCharacterById(member.getId()).getName());
        }

        return members;
    }

}
