package Business;

import Persistence.TeamDao;
import Persistence.CharacterDao;

import java.io.IOException;
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

        Character character = characterDao.getCharacter(index);
        return teamDao.getTeamsOfCharacter(character.getId());
    }

    public Team getTeamByIndex(int index) {
        Team team;
        TeamDao teamDao = new TeamDao();

        team = teamDao.getTeamByIndex(index);

        return team;
    }

    public String deleteTeamByName(String name) throws IOException {
        TeamDao teamDao = new TeamDao();
        return teamDao.deleteTeamByName(name);
    }
}
