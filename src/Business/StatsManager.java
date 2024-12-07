package Business;

import Persistence.StatsDao;
import Persistence.TeamDao;
import Presentation.Input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatsManager {


    public Stats getStatsByIndex (int index) {
        StatsDao dao = new StatsDao();
        return dao.getStatsByIndex(index);
    }

    public void inicialiceStats (String teamName) {
        StatsDao dao = new StatsDao();
        Stats stats = new Stats(0, 0, 0, 0);
        dao.addStats(stats, teamName);
    }

    public void updateStatsOfBattle(Battle battle) {
        for (Team team : battle.getTeams()) {
            StatsDao statsDao = new StatsDao();
            TeamManager teamManager = new TeamManager();
            System.out.println("\n" + team.getName());
            Stats stats = statsDao.getStatsByIndex(teamManager.getIndexOfTeamByName(team.getName()));


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

            statsDao.updateStats(teamManager.getIndexOfTeamByName(team.getName()), stats);
        }
    }

}
