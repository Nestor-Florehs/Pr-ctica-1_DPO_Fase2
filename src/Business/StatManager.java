package Business;

import Persistence.StatDao;
import Persistence.implementations.StatJsonDao;

import java.util.ArrayList;

/**
 * Manages the operations related to statistics of teams, including retrieving,
 * initializing, and updating statistics based on the outcome of battles.
 */
public class StatManager {

    private final StatDao statDao;

    /**
     * Constructs a StatManager object, initializing the DAO for stat management.
     */
    public StatManager() {
        this.statDao = new StatJsonDao();
    }

    /**
     * Retrieves the statistics of a team by its index.
     *
     * @param index the index of the team to retrieve statistics for.
     * @return the `Stat` object containing the statistics of the specified team.
     */
    public Stat getStatsByIndex(int index) {
        return statDao.getStatsByIndex(index);
    }

    /**
     * Initializes the statistics for a team by its name, setting all stats to zero.
     *
     * @param teamName the name of the team to initialize stats for.
     */
    public void inicialiceStats(String teamName) {
        Stat stats = new Stat(0, 0, 0, 0);
        statDao.addStats(stats, teamName);
    }

    /**
     * Updates the statistics of all teams based on the outcome of a battle.
     * This includes games played, games won, knockouts done, and knockouts received.
     *
     * @param battle the battle whose results will be used to update team statistics.
     */
    public void updateStatsOfBattle(Battle battle) {
        for (Team team : battle.getTeams()) {
            TeamManager teamManager = new TeamManager();
            System.out.println("\n" + team.getName());
            Stat stats = statDao.getStatsByIndex(teamManager.getIndexOfTeamByName(team.getName()));
            stats.addGamesPlayed();
            if (team.getWin()) {
                stats.addGamesWon();
            }
            int teamKODone = team.getKoDone();
            int teamKOReceived = 0;
            ArrayList<Member> members = team.getMembers();
            for (Member member : members) {
                if (member.getKO()) {
                    stats.addKOReceived();
                    teamKOReceived++;
                }
            }
            stats.addKODone(teamKODone);
            statDao.updateStats(teamManager.getIndexOfTeamByName(team.getName()), stats);
        }
    }
}