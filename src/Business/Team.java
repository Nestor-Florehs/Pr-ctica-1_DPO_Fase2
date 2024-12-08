package Business;

import java.util.ArrayList;

/**
 * Represents a team of members, with attributes such as name,
 * members, win status, and the number of KO.
 */
public class Team {
    private String name;
    private ArrayList<Member> members;
    private boolean win;
    private int koDone;

    /**
     * Constructs a new Team object with the specified name and members.
     *
     * @param name the name of the team.
     * @param members the list of members in the team.
     */
    public Team(String name, ArrayList<Member> members) {
        this.name = name;
        this.members = members;
        this.win = false;
        this.koDone = 0;
    }

    /**
     * Returns the name of the team.
     *
     * @return the name of the team.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a list of the character IDs of the team members.
     *
     * @return an ArrayList of character IDs.
     */
    public ArrayList<Long> getCharactersIDs() {
        ArrayList<Long> charactersIDs = new ArrayList<>();
        for (Member member : members) {
            charactersIDs.add(member.getId());
        }
        return charactersIDs;
    }

    /**
     * Returns the list of members in the team.
     *
     * @return an ArrayList of the team members.
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * Returns a string representation of the team, including its name and members.
     *
     * @return a formatted string representing the team and its members.
     */
    public String toString() {
        return name + " " + members;
    }

    /**
     * Sets the team as the winner.
     */
    public void setWin() {
        win = true;
    }

    /**
     * Returns whether the team has won.
     *
     * @return true if the team has won, false otherwise.
     */
    public boolean getWin() {
        return win;
    }

    /**
     * Increments the number of knockouts performed by the team.
     */
    public void addKoDone() {
        koDone++;
    }

    /**
     * Returns the total number of knockouts performed by the team.
     *
     * @return the number of knockouts performed.
     */
    public int getKoDone() {
        return koDone;
    }
}
