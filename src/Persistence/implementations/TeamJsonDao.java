package Persistence.implementations;

import Business.Member;
import Persistence.TeamDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Business.Team;

/**
 * This class implements the TeamDao interface and provides functionality
 * to interact with team data stored in a JSON file.
 */
public class TeamJsonDao implements TeamDao {
    private static final String TEAM_FILENAME = "database/teams.json";

    /**
     * Retrieves all teams from a JSON file.
     *
     * @return a list of Team objects parsed from the JSON file. If the
     *         file does not exist or an error occurs, an empty list is returned.
     */
    private ArrayList<Team> getAllTeams() {
        JSONParser parser = new JSONParser();
        ArrayList<Team> teams = new ArrayList<>();

        try {
            FileReader reader = new FileReader(TEAM_FILENAME);

            JSONArray array = (JSONArray) parser.parse(reader);

            for (Object o : array) {
                ArrayList<Member> membersList = new ArrayList<>();
                JSONObject team = (JSONObject) o;
                JSONArray members = (JSONArray) team.get("members");

                String name = (String) team.get("name");
                for (Object o2 : members) {
                    JSONObject member = (JSONObject) o2;
                    long id = (long) member.get("id"); // Handle long for large numbers
                    String strategy = (String) member.get("strategy"); // Handle strategy field
                    membersList.add(new Member(id, strategy));
                }
                Team t = new Team(name, membersList);
                teams.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teams;
    }

    /**
     * Saves a list of teams to a JSON file.
     *
     * @param teams the list of teams to be saved.
     * @throws IOException if an error occurs while writing to the file.
     */
    public void saveTeams(ArrayList<Team> teams) throws IOException {
        JSONArray teamArray = new JSONArray();

        for (Team team : teams) {
            JSONObject teamObject = new JSONObject();
            teamObject.put("name", team.getName());

            // Crear un JSONArray para los miembros
            JSONArray membersArray = new JSONArray();
            for (Member member : team.getMembers()) {
                JSONObject memberObject = new JSONObject();
                memberObject.put("id", member.getId());
                memberObject.put("strategy", member.getStrategy());
                membersArray.add(memberObject);
            }

            teamObject.put("members", membersArray); // Añadir los miembros al equipo
            teamArray.add(teamObject);
        }

        // Guardar en el archivo
        File file = new File(TEAM_FILENAME);
        try (FileWriter writeTeams = new FileWriter(file)) {
            writeTeams.write(teamArray.toJSONString());
            writeTeams.flush();
        }
    }

    /**
     * Retrieves a team by its name.
     *
     * @param name the name of the team to retrieve.
     * @return the Team with the specified name, or null if not found.
     */
    public Team getTeamByName(String name) {
        ArrayList<Team> teams = getAllTeams();
        for (Team t : teams) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Deletes a team by its name.
     *
     * @param name the name of the team to delete.
     * @return a message indicating whether the team was successfully removed
     *         or not found.
     * @throws IOException if an error occurs while saving the updated team list.
     */
    public String deleteTeamByName(String name) throws IOException {
        Team t = getTeamByName(name);
        ArrayList<Team> teams = getAllTeams();
        boolean found = false;

        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getName().equals(name)) {
                found = true;
                teams.remove(i);
            }
        }
        saveTeams(teams);

        if (found) {
            return "\n\t(" + name + ")" + " has been removed from the system.";
        } else {
            return "\n\t(" + name + ")" + " has not exist in the system.";
        }
    }

    /**
     * Retrieves the names of all teams.
     *
     * @return a list of team names.
     */
    public ArrayList<String> getAllTeamNames() {
        ArrayList<String> teamNames = new ArrayList<>();
        ArrayList<Team> teams = getAllTeams();
        for (Team team : teams) {
            teamNames.add(team.getName());
        }
        return teamNames;
    }

    /**
     * Retrieves the names of teams that include a specific character.
     *
     * @param id the ID of the character.
     * @return a list of team names containing the character.
     */
    public ArrayList<String> getTeamsOfCharacter(long id) {
        ArrayList<Team> teams = getAllTeams();
        ArrayList<String> teamNames = new ArrayList<>();
        boolean found;

        for (Team team : teams) {
            ArrayList<Long> membersIDs = team.getCharactersIDs();
            found = false;

            for (Long memberID : membersIDs) {
                if (memberID == id && !found) {
                    teamNames.add(team.getName());
                    found = true;
                }
            }
        }

        return teamNames;
    }

    /**
     * Retrieves a team by its index in the list.
     *
     * @param index the index of the team (1-based).
     * @return the Team at the specified index.
     */
    public Team getTeamByIndex(int index) {
        ArrayList<Team> teams = getAllTeams();
        return teams.get(index - 1);
    }

    /**
     * Adds a new team to the list and saves it to the file.
     *
     * @param team the team to be added.
     * @throws IOException if an error occurs while saving the team list.
     */
    public void addTeam(Team team) throws IOException {
        ArrayList<Team> teams = getAllTeams();
        teams.add(team);
        saveTeams(teams);
    }

    /**
     * Validates the existence and path of a JSON file.
     *
     * @return true if the file exists and contains valid JSON; false otherwise.
     */
    public boolean validateJson() {
        File file = new File(TEAM_FILENAME);
        if (!file.exists()) {
            System.err.println("Error: teams.json no existe.");
            return false;
        }
        try (FileReader reader = new FileReader(file)) {
            JSONParser parser = new JSONParser();
            parser.parse(reader);
        } catch (Exception e) {
            System.err.println("Error: teams.json tiene un formato inválido.");
            return false;
        }
        return true;
    }

}
