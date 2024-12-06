package Business;

import java.util.ArrayList;

public class Battle {
    private ArrayList<Team> teams;

    public Battle(ArrayList<Team> teams) {
        this.teams = teams;
    }


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

    public ArrayList<Team> getTeams() {
        return teams;
    }

    /*
    public Team getWinningTeam () {
        for (Team team : teams) {

        }
    }
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

    public void attack(Member attacker, Member defender) {
        double attackDamage = attacker.getAttackDamage();
        if (attacker.getWeapon() == null) {
            System.out.println(attacker.getName() + " ATTACKS " + defender.getName() + " WITHOUT WEAPON " + " FOR " + attackDamage);
        } else {
            System.out.println(attacker.getName() + " ATTACKS " + defender.getName() + " WITH " + attacker.getWeapon().getName() + " FOR " + attackDamage);
        }
        double damageReceive = defender.receiveDamage(attackDamage);
        System.out.println("\t" + defender.getName() + " RECEIVES " + damageReceive + " DAMAGE.");
    }

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
