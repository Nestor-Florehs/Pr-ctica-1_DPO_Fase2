package Business;

import Persistence.StatsDao;

public class StatsManager {

    /*
    public Stats getStatsByIndex(int index) {

    }

     */

    public Stats getStatsByIndex (int index) {
        StatsDao dao = new StatsDao();
        return dao.getStatsByIndex(index);
    }
}
