package Persistence;

import Business.Stat;

import java.util.ArrayList;

/**
 * This interface defines the methods required for interacting with stat data
 * in a data source, such as a JSON file or database. Implementations of this interface
 * will provide specific mechanisms for retrieving, adding, and updating stat information.
 */
public interface StatDao {

    void saveStats(ArrayList<Stat> stats);

    void updateStats(int index, Stat newStats);

    Stat getStatsByIndex(int index);

    void addStats(Stat stat, String teamName);

    void deleteStatsByIndex(int index);

}