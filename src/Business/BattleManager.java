package Business;

import Presentation.Input;
import Presentation.Output;

import java.util.ArrayList;
import java.util.Random;

public class BattleManager {

    private Battle battle;

    public BattleManager(Battle battle) {
        this.battle = battle;
    }

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

    public void calculateKOs() {
        for (Team team : battle.getTeams()) {
            ArrayList<Member> members = team.getMembers();
            for (Member member : members) {
                if (!member.getKO()) {
                    Random random = new Random();
                    int randomNum = random.nextInt(200) + 1;
                    double knockOut = (double) randomNum / 200;
                    if (knockOut < member.getDamageReceived()) {
                        member.setKO();
                        Output.printPhrase(member.getName() + " flies away! It’s a KO!");
                    }
                }
            }
        }
    }

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

    public void executeBattle() {
        boolean combatEnd = false;
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
