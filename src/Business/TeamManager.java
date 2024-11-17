package Business;

import Persistence.TeamDao;
import Persistence.CharacterDao;

import java.util.ArrayList;

public class TeamManager {

    public ArrayList<String> listTeams() {
        ArrayList<String> listTeamsName;
        TeamDao teamDao = new TeamDao();
        listTeamsName = teamDao.getAllTeamNames();
        return listTeamsName;
    }

    public ArrayList<String> listTeamsOfCharacter(int index) {
        CharacterDao characterDao = new CharacterDao();
        TeamDao teamDao = new TeamDao();
        ArrayList<String> teamsOfCharacter = new ArrayList<>();

        Character character = characterDao.getCharacter(index);
        return teamDao.getTeamsOfCharacter(character.getId());
    }
}
