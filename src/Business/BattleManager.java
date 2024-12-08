package Business;

import Presentation.Output;

import java.util.ArrayList;
import java.util.Random;

/**
 * Manages the flow of a battle, including tracking the state of the battle,
 * executing each turn, and managing the actions of the members of each team.
 */
public class BattleManager {

    private Battle battle;

    /**
     * Constructs a BattleManager with a specific battle instance.
     *
     * @param battle the battle to be managed by this manager.
     */
    public BattleManager(Battle battle) {
        this.battle = battle;
    }

    /**
     * Displays the current state of the battle, including each team's name,
     * the status of each member (with or without weapon/armor), and the percentage
     * of damage they have received.
     */
    private void showStateOfBattlePerTurn() {
        int teamIndex = 1;
        for (Team team : battle.getTeams()) {
            Output.printPhrase("\nTeam #" + teamIndex + " - " + team.getName());
            ArrayList<Member> members = team.getMembers();
            for (Member member : members) {
                if (member.getWeapon() == null && member.getArmor() == null) {
                    Output.printPhrase("\t- " + member.getName() + member.getPercentageOfDamage() + " No Weapon" + " - " + "No Armor");
                } else if (member.getWeapon() == null && member.getArmor() != null) {
                    Output.printPhrase("\t- " + member.getName() + member.getPercentageOfDamage() + " No Weapon" + " - " + member.getArmor().getName());
                } else if (member.getWeapon() != null && member.getArmor() == null) {
                    Output.printPhrase("\t- " + member.getName() + member.getPercentageOfDamage() + member.getWeapon().getName() + " - " + "No Armor");
                } else {
                    Output.printPhrase("\t- " + member.getName() + member.getPercentageOfDamage() + member.getWeapon().getName() + " - " + member.getArmor().getName());
                }
            }
            teamIndex++;
        }
    }

    /**
     * Retrieves a random member from a rival team that is not KO'd.
     *
     * @param currentTeam the current team whose rival member is to be selected.
     * @return a random member from a rival team.
     */
    private Member getRandomMemberOfRivalTeam(Team currentTeam) {
        ArrayList<Team> teams = battle.getTeams();

        ArrayList<Member> rivalMembers = new ArrayList<>();
        for (Team team : teams) {
            if (!team.equals(currentTeam)) {
                rivalMembers.addAll(team.getMembers());
            }
        }

        int randomIndex = (int) (Math.random() * rivalMembers.size());
        Member rival = rivalMembers.get(randomIndex);

        if (rival.getKO()) {
            return getRandomMemberOfRivalTeam(currentTeam);
        } else {
            return rival;
        }
    }

    /**
     * Executes one turn of the battle, where each member of the team can either attack, defend,
     * or equip items based on their strategy.
     */
    private void executeTurn() {
        Output.printPhrase("");
        for (Team team : battle.getTeams()) {
            ArrayList<Member> members = team.getMembers();
            for (Member member : members) {
                if (!member.getKO()) {
                    String strategy = member.getStrategy();

                    if (strategy.equals("balanced")) {
                        if (member.getWeapon() == null) {
                            Output.printPhrase(member.getName() + " coge una arma");
                            member.setWeapon(new ItemManager().getRandomWeapon());
                        } else {
                            if (member.getArmor() != null) {
                                if (member.getDamageReceived() > 0.5 && member.getDamageReceived() < 1.0) {
                                    Output.printPhrase(member.getName() + " defence");
                                    member.setDefending();
                                } else {
                                    Member defender = getRandomMemberOfRivalTeam(team);
                                    battle.attack(member, defender);
                                }
                            } else {
                                Member defender = getRandomMemberOfRivalTeam(team);
                                battle.attack(member, defender);
                            }
                        }
                    }
                }
            }
            Output.printPhrase("");
        }
    }

    public void showBreaksItems() {
        System.out.println();
        for (Team team : battle.getTeams()) {
            ArrayList<Member> members = team.getMembers();
            for (Member member : members) {
                if (!member.getKO()) {
                    if (member.getWeapon() != null) {
                        if (member.getWeapon().getDurability() <= 0) {
                            System.out.println("Oh no!" +  member.getName() + "’s " + member.getWeapon().getName() + " breaks!");
                            member.setWeapon(null);
                        }
                    }
                    if (member.getArmor() != null) {
                        if (member.getArmor().getDurability() <= 0) {
                            System.out.println("Oh no!" +  member.getName() + "’s " + member.getArmor().getName() + " breaks!");
                            member.setArmor(null);
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Simulates the calculation of KOs for each team, checking if a member's damage
     * exceeds the threshold for a KO. If a KO occurs, the opposing team gains a KO count.
     */
    public void calculateKOs() {
        ArrayList<Team> teams = battle.getTeams();

        for (int i = 0; i < teams.size(); i++) {
            Team currentTeam = teams.get(i);
            Team opponentTeam = teams.get((i + 1) % teams.size());

            ArrayList<Member> members = currentTeam.getMembers();

            for (Member member : members) {
                if (!member.getKO()) {
                    Random random = new Random();
                    int randomNum = random.nextInt(200) + 1;
                    double knockOut = (double) randomNum / 200;

                    if (knockOut < member.getDamageReceived()) {
                        member.setKO();
                        opponentTeam.addKoDone();

                        Output.printPhrase(member.getName() + " flies away! It’s a KO!");
                    }
                }
            }
        }
    }

    /**
     * Checks if the battle has finished by determining if any team has all its
     * members KO'd. If all members of a team are KO'd, the combat is finished.
     *
     * @return true if the combat is finished, false otherwise.
     */
    public boolean checkCombatFinal() {
        boolean combatFinished = false;

        for (Team team : battle.getTeams()) {
            ArrayList<Member> members = team.getMembers();
            int numOfKOs = 0;
            for (Member member : members) {
                if (member.getKO()) {
                    numOfKOs++;
                }
            }
            if (numOfKOs == 4) {
                combatFinished = true;
            }
        }

        return combatFinished;
    }

    /**
     * Executes the battle, showing the battle state, executing turns, checking for
     * equipment breaks, calculating KOs, and determining the winner at the end.
     */
    public void executeBattle() {
        boolean combatEnd;
        int turn = 1;

        do {
            Output.printPhrase("\n--- ROUND " + turn + "! ---");
            showStateOfBattlePerTurn();
            executeTurn();
            showBreaksItems();
            calculateKOs();

            combatEnd = checkCombatFinal();
            turn++;
        } while (!combatEnd);

        battle.setWinningTeam();
        Team winnerTeam = battle.getWinningTeam();
        Output.printPhrase("\n--- END OF COMBAT ---");
        Output.printPhrase("\n" + winnerTeam.getName() + " wins!");
        showStateOfBattlePerTurn();
    }
}
