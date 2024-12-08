package Business;

import Persistence.TeamDao;
import Persistence.implementations.StatJsonDao;
import Persistence.implementations.TeamJsonDao;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages the operations related to teams, including retrieving team details,
 * adding new teams, deleting teams, and retrieving teams associated with a character.
 */
public class TeamManager {
    private final TeamDao teamDao;

    /**
     * Constructs a TeamManager object, initializing the DAO for team management.
     */
    public TeamManager() {
        teamDao = new TeamJsonDao();
    }

    /**
     * Retrieves a list of all team names.
     *
     * @return a list of team names as strings.
     */
    public ArrayList<String> listTeams() {
        ArrayList<String> listTeamsName;
        listTeamsName = teamDao.getAllTeamNames();
        return listTeamsName;
    }

    /**
     * Retrieves the teams associated with a character by its index.
     *
     * @param index the index of the character.
     * @return a list of team names associated with the character.
     */
    public ArrayList<String> listTeamsOfCharacter(int index) {
        CharacterManager characterManager = new CharacterManager();
        Character character = characterManager.listCharacterAttribute(index);
        return teamDao.getTeamsOfCharacter(character.getId());
    }

    /**
     * Retrieves the team by its index.
     *
     * @param index the index of the team to retrieve.
     * @return the `Team` object at the specified index.
     */
    public Team getTeamByIndex(int index) {
        Team team;
        team = teamDao.getTeamByIndex(index);
        return team;
    }

    /**
     * Deletes a team by its name and removes associated stats.
     *
     * @param name the name of the team to delete.
     * @return a confirmation message about the deletion.
     * @throws IOException if an I/O error occurs during deletion.
     */
    public String deleteTeamByName(String name) throws IOException {
        StatJsonDao statsDao = new StatJsonDao();
        statsDao.deleteStatsByIndex(getIndexOfTeamByName(name));
        return teamDao.deleteTeamByName(name);
    }

    /**
     * Retrieves a team by its name.
     *
     * @param name the name of the team to retrieve.
     * @return the `Team` object corresponding to the name.
     */
    public Team getTeamByName(String name) {
        return teamDao.getTeamByName(name);
    }

    /**
     * Adds a new team.
     *
     * @param team the `Team` object to be added.
     * @throws IOException if an I/O error occurs during addition.
     */
    public void addTeam(Team team) throws IOException {
        teamDao.addTeam(team);
    }

    /**
     * Retrieves the index of a team by its name.
     *
     * @param name the name of the team.
     * @return the index of the team, or -1 if not found.
     */
    public int getIndexOfTeamByName(String name) {
        ArrayList<String> teams = teamDao.getAllTeamNames();
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }
}