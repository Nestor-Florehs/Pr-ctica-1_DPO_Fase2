package Persistence;

import Business.Character;
import Presentation.Input;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public void saveTeams (ArrayList<Team> teams) throws IOException {
        JSONArray teamArray = new JSONArray();

        for (Team team : teams) {
            JSONObject teamObject = new JSONObject();
            teamObject.put("name", team.getName());
            teamObject.put("members", team.getCharactersIDs());

            teamArray.add(teamObject);
        }

        File file = new File(FILE_PATH);

        FileWriter writeTeams = new FileWriter(file);
        writeTeams.write(teamArray.toJSONString());
        writeTeams.flush();
        writeTeams.close();
    }

    public Team getTeamByName(String name) {
        ArrayList<Team> teams = getAllTeams();
        for (Team t : teams) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

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
