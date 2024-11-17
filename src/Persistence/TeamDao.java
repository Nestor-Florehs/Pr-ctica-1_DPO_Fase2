package Persistence;

import Presentation.Input;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import Business.Team;

public class TeamDao {
    private static final String FILE_PATH = "C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\Practica-1-DPO\\src\\Persistence\\Database\\teams.json";

    private ArrayList<Team> getAllTeams() {
        JSONParser parser = new JSONParser();
        ArrayList<Team> teams = new ArrayList<>();

        try {
            // Verifica si el archivo existe antes de intentar leer
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.err.println("El archivo no existe, devolviendo una lista vacía.");
                return teams;
            }

            // Load the JSON file
            FileReader reader = new FileReader(FILE_PATH);

            // Parse the JSON file as an array
            JSONArray array = (JSONArray) parser.parse(reader);

            // Iterate through each JSON object in the array
            for (Object o : array) {
                JSONObject team = (JSONObject) o;

                // Extract fields
                String name = (String) team.get("name");
                ArrayList<Long> membersID = (ArrayList<Long>) team.get("members");

                Team t = new Team(name, membersID);
                teams.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teams;
    }

    public ArrayList<String> getAllTeamNames() {
        ArrayList<String> teamNames = new ArrayList<>();
        ArrayList<Team> teams = getAllTeams();
        for (Team team : teams) {
            teamNames.add(team.getName());
        }
        return teamNames;
    }

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

    public Team getTeamByIndex(int index) {
        ArrayList<Team> teams = getAllTeams();
        return teams.get(index - 1);
    }

}
