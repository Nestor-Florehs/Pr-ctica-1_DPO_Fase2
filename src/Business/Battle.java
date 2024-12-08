package Business;

import java.util.ArrayList;

/**
 * Represents a battle between teams, where members of each team
 * can attack one another and receive damage.
 */
public class Battle {
    private ArrayList<Team> teams;

    /**
     * Constructs a Battle object with the specified teams.
     *
     * @param teams a list of teams participating in the battle.
     */
    public Battle(ArrayList<Team> teams) {
        this.teams = teams;
    }

    /**
     * Returns a string representation of the battle, including the teams and their members.
     *
     * @return a formatted string representing the teams and their members.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("\n");
        for (int i = 1; i <= teams.size(); i++) {
            str.append("\tTeam #").append(i).append(": ").append(teams.get(i - 1).getName()).append("\n");

            ArrayList<Member> members = teams.get(i - 1).getMembers();

            for (Member member : members) {
                str.append("\t- ").append(member.getName()).append("\n");
                str.append("\t\tWeapon: ").append(member.getWeapon().getName()).append("\n");
                str.append("\t\tArmor: ").append(member.getArmor().getName()).append("\n");
            }
            str.append("\n");
        }

        return str.toString();
    }

    /**
     * Returns the list of teams participating in the battle.
     *
     * @return a list of teams.
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Sets the winning team based on the number of knockouts achieved by its members.
     * A team wins if it has less than 4 knockouts.
     */
    public void setWinningTeam() {
        for (Team team : teams) {
            ArrayList<Member> members = team.getMembers();
            int numOfKOs = 0;
            for (Member member : members) {
                if (member.getKO()) {
                    numOfKOs++;
                }
            }

            if (numOfKOs < 4) {
                team.setWin();
            }
        }
    }

    /**
     * Simulates an attack from the attacker to the defender, applying damage based on the attacker's weapon.
     *
     * @param attacker the member initiating the attack.
     * @param defender the member receiving the attack.
     */
    public void attack(Member attacker, Member defender) {
        double attackDamage = attacker.getAttackDamage();
        if (attacker.getWeapon() == null) {
            System.out.println(attacker.getName() + " ATTACKS " + defender.getName() + " WITHOUT WEAPON FOR " + String.format("%.2f", attackDamage));
        } else {
            System.out.println(attacker.getName() + " ATTACKS " + defender.getName() + " WITH " + attacker.getWeapon().getName() + " FOR " + String.format("%.2f", attackDamage));
        }
        double damageReceive = defender.receiveDamage(attackDamage);
        System.out.println("\t" + defender.getName() + " RECEIVES " + String.format("%.2f", damageReceive) + " DAMAGE.");
    }

    /**
     * Returns the winning team of the battle.
     *
     * @return the team that won the battle, or null if no team has won.
     */
    public Team getWinningTeam() {
        Team winningTeam = null;
        for (Team team : teams) {
            if (team.getWin()) {
                winningTeam = team;
            }
        }
        return winningTeam;
    }
}
