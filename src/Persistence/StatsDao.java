package Persistence;

import Business.Stats;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public class StatsDao {
    private static final String FILE_PATH = "C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\Practica-1-DPO\\src\\Persistence\\Database\\stats.json";

    private ArrayList<Stats> getAllStats() {
        JSONParser parser = new JSONParser();
        ArrayList<Stats> stats = new ArrayList<>();


        try {
            FileReader reader = new FileReader(FILE_PATH);
            JSONArray array = (JSONArray) parser.parse(reader);

            for (Object o : array) {
                JSONObject stat = (JSONObject) o;

                int gamesPlayed = ((Long) stat.get("games_played")).intValue();
                int gamesWon = ((Long) stat.get("games_won")).intValue();
                int koDone = ((Long) stat.get("KO_done")).intValue();
                int koReceived = ((Long) stat.get("KO_received")).intValue();

                Stats s = new Stats(gamesPlayed, gamesWon, koDone, koReceived);
                stats.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stats;
    }

    public void saveStats(ArrayList<Stats> stats) {
        JSONArray statsArray = new JSONArray();

        for (Stats s : stats) {
            JSONObject stat = new JSONObject();
            stat.put("games_played", s.getGamesPlayed());
            stat.put("games_won", s.getGamesWon());
            stat.put("KO_done", s.getKODone());
            stat.put("KO_received", s.getKOReceived());

            statsArray.add(stat);
        }

        File file = new File(FILE_PATH);
        try (FileWriter writeTeams = new FileWriter(file)) {
            writeTeams.write(statsArray.toJSONString());
            writeTeams.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStats(int index, Stats newStats) {
        ArrayList<Stats> stats = getAllStats();
        if (index >= 0 && index < stats.size()) {
            stats.set(index, newStats); // Reemplaza la estadística en la posición index con la nueva
            saveStats(stats);
        }
    }

    public Stats getStatsByIndex(int index) {
        return getAllStats().get(index);
    }

    public void addStats(Stats s, String teamName) {
       ArrayList<Stats> stats = getAllStats();
       stats.add(s);
       saveStats(stats);
    }

    public void deleteStatsByIndex(int index) {
        ArrayList<Stats> stats = getAllStats();
        stats.remove(index);
        saveStats(stats);
    }


}
