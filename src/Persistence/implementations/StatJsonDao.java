package Persistence.implementations;

import Business.Stat;
import Persistence.StatDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

/**
 * This class implements the StatDao interface and provides functionality
 * to interact with stat data stored in a JSON file.
 */
public class StatJsonDao implements StatDao {
    private static final String STAT_FILENAME = "database/stats.json";

    /**
     * Retrieves all stats from a JSON file.
     *
     * @return a list of Stat objects parsed from the JSON file. If the
     *         file does not exist or an error occurs, an empty list is returned.
     */
    private ArrayList<Stat> getAllStats() {
        JSONParser parser = new JSONParser();
        ArrayList<Stat> stats = new ArrayList<>();
        try {
            FileReader reader = new FileReader(STAT_FILENAME);
            JSONArray array = (JSONArray) parser.parse(reader);

            for (Object o : array) {
                JSONObject stat = (JSONObject) o;

                int gamesPlayed = ((Long) stat.get("games_played")).intValue();
                int gamesWon = ((Long) stat.get("games_won")).intValue();
                int koDone = ((Long) stat.get("KO_done")).intValue();
                int koReceived = ((Long) stat.get("KO_received")).intValue();

                Stat s = new Stat(gamesPlayed, gamesWon, koDone, koReceived);
                stats.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stats;
    }

    /**
     * Saves a list of stats to a JSON file.
     *
     * @param stats the list of Stats to save.
     * @throws RuntimeException if an error occurs while saving the stats.
     */
    public void saveStats(ArrayList<Stat> stats) {
        JSONArray statsArray = new JSONArray();

        for (Stat s : stats) {
            JSONObject stat = new JSONObject();
            stat.put("games_played", s.getGamesPlayed());
            stat.put("games_won", s.getGamesWon());
            stat.put("KO_done", s.getKODone());
            stat.put("KO_received", s.getKOReceived());

            statsArray.add(stat);
        }

        File file = new File(STAT_FILENAME);
        try (FileWriter writeTeams = new FileWriter(file)) {
            writeTeams.write(statsArray.toJSONString());
            writeTeams.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStats(int index, Stat newStats) {
        ArrayList<Stat> stats = getAllStats();
        if (index >= 0 && index < stats.size()) {
            stats.set(index, newStats); // Reemplaza la estadística en la posición index con la nueva
            saveStats(stats);
        }
    }

    /**
     * Retrieves stats by their index in the list.
     *
     * @param index the index of the stats (1-based).
     * @return the Stats at the specified index.
     */
    public Stat getStatsByIndex(int index) {
        return getAllStats().get(index);
    }

    /**
     * Adds new stats to the list and saves them to the file.
     *
     * @param stat the Stats to be added.
     * @param teamName the name of the team associated with the stats.
     */
    public void addStats(Stat stat, String teamName) {
       ArrayList<Stat> stats = getAllStats();
       stats.add(stat);
       saveStats(stats);
    }

    /**
     * Validates the existence and path of a JSON file.
     *
     * @return true if the file exists and contains valid JSON; false otherwise.
     */
    public void deleteStatsByIndex(int index) {
        ArrayList<Stat> stats = getAllStats();
        stats.remove(index);
        saveStats(stats);
    }
}
