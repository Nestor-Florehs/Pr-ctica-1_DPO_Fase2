package Persistence;


import Business.Team;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This interface defines the methods required for interacting with team data
 * in a data source, such as a JSON file or database. Implementations of this interface
 * will provide specific mechanisms for retrieving, adding, and updating team information.
 */
public interface TeamDao {
    void saveTeams(ArrayList<Team> teams) throws IOException;

    Team getTeamByName(String name);

    String deleteTeamByName(String name) throws IOException;

    ArrayList<String> getAllTeamNames();

    ArrayList<String> getTeamsOfCharacter(long id);

    Team getTeamByIndex(int index);

    void addTeam(Team team) throws IOException;

    boolean validateJson();
}