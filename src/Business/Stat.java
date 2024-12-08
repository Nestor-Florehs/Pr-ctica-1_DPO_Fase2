package Business;

/**
 * Represents the statistics of a team, including games played, games won,
 * KO done, and KO received.
 */
public class Stat {
    private int gamesPlayed;
    private int gamesWon;
    private int KODone;
    private int KOReceived;

    /**
     * Constructs a new Stat object with specified initial values.
     *
     * @param gamesPlayed the number of games played.
     * @param gamesWon the number of games won.
     * @param KODone the number of knockouts performed.
     * @param KOReceived the number of knockouts received.
     */
    public Stat(int gamesPlayed, int gamesWon, int KODone, int KOReceived) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.KODone = KODone;
        this.KOReceived = KOReceived;
    }

    /**
     * Retrieves the total number of games played.
     *
     * @return the number of games played.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Retrieves the total number of games won.
     *
     * @return the number of games won.
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Retrieves the total number of knockouts performed.
     *
     * @return the number of knockouts performed.
     */
    public int getKODone() {
        return KODone;
    }

    /**
     * Retrieves the total number of knockouts received.
     *
     * @return the number of knockouts received.
     */
    public int getKOReceived() {
        return KOReceived;
    }

    /**
     * Increments the number of games won by one.
     */
    public void addGamesWon() {
        this.gamesWon++;
    }

    /**
     * Increments the number of games played by one.
     */
    public void addGamesPlayed() {
        this.gamesPlayed++;
    }

    /**
     * Increments the number of knockouts received by one.
     */
    public void addKOReceived() {
        this.KOReceived++;
    }

    /**
     * Increments the number of knockouts performed by the specified value.
     *
     * @param koDone the number of additional knockouts performed.
     */
    public void addKODone(int koDone) {
        this.KODone += koDone;
    }

    /**
     * Returns a string representation of the statistics.
     *
     * @return a formatted string containing games played, games won,
     *         knockouts performed, and knockouts received.
     */
    public String toString() {
        String str;
        str = "\nGames Played: " + gamesPlayed + "\n";
        str += "Games Won: " + gamesWon + "\n";
        str += "KO Done: " + KODone + "\n";
        str += "KO Received: " + KOReceived + "\n";
        return str;
    }
}