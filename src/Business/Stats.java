package Business;

public class Stats {
    private int gamesPlayed;
    private int gamesWon;
    private int KODone;
    private int KOReceived;

    public Stats(int gamesPlayed, int gamesWon, int KODone, int KOReceived) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.KODone = KODone;
        this.KOReceived = KOReceived;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getKODone() {
        return KODone;
    }

    public int getKOReceived() {
        return KOReceived;
    }

    public void addGamesWon() {
        this.gamesWon++;
    }

    public void addGamesPlayed() {
        this.gamesPlayed++;
    }

    public void addKOReceived() {
        this.KOReceived++;
    }

    public void addKODone(int koDone) {
        this.KODone += koDone;
    }

    public String toString() {
        String str;
        str = "\nGames Played: " + gamesPlayed + "\n";
        str += "Games Won: " + gamesWon + "\n";
        str += "KO Done: " + KODone + "\n";
        str += "KO Received: " + KOReceived + "\n";
        return str;
    }

}


