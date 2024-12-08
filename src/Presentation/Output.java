package Presentation;

import java.util.ArrayList;

import Business.Character;
import Business.Item;
import Business.Member;
import Business.Team;
import Business.Stat;

/**
 * The Output class is responsible for displaying messages and various
 * information to the user. It provides methods for printing menus,
 * character attributes, team details, and other important messages.
 */
public class Output {

    /**
     * Displays the main menu with options for the user.
     */
    public void mainMenu(){
        System.out.println("\n\t1) List Characters");
        System.out.println("\t2) Manage Teams");
        System.out.println("\t3) List Items");
        System.out.println("\t4) Simulate Combat");
        System.out.println("\n\t5) Exit");
    }

    /**
     * Prints a phrase to the console.
     *
     * @param phrase the phrase to be printed.
     */
    public static void printPhrase(String phrase) {
        System.out.println(phrase);
    }

    /**
     * Displays a list of options for the user to choose from.
     *
     * @param strings a list of strings representing the options.
     */
    public void listMenu(ArrayList<String> strings){
        int i = 0;
        System.out.println();
        for (String phrase : strings) {
            i++;
            System.out.println("\t" + i + ")" + phrase);
        }
        System.out.println();

        System.out.println("\t0) Back");
    }

    /**
     * Displays the attributes of a character along with the teams it is part of.
     *
     * @param character the character whose attributes are to be displayed.
     * @param teamsNames a list of team names the character is part of.
     */
    public void listCharacterAttributes(Character character, ArrayList<String> teamsNames){
        System.out.println(character);
        System.out.println("\tTEAMS:");
        if (teamsNames.size() > 0) {
            for (String name : teamsNames){
                System.out.println("\t\t\t" + "-" + name);
            }
        } else {
            System.out.println("\t\t\t" + "-No teams");
        }
    }

    /**
     * Displays the attributes of an item.
     *
     * @param item the item whose attributes are to be displayed.
     */
    public void listItemAttributes(Item item){
        System.out.println(item);
    }

    /**
     * Displays the team management menu.
     */
    public void manageTeamsMenu(){
        System.out.println("\nTeam management.");
        System.out.println("\t1) Create a Team");
        System.out.println("\t2) List Teams");
        System.out.println("\t3) Delete a Team");
        System.out.println("\n\t4) Back");
    }

    /**
     * Displays the details of a team, including its members and stats.
     *
     * @param team the team whose details are to be displayed.
     * @param members a list of members in the team.
     * @param teamStats the stats of the team.
     */
    public void listTeamAttributes(Team team, ArrayList<Member> members, Stat teamStats){
        System.out.println("\nTeam Name: " + team.getName() + "\n");
        int i = 0;

        for (Member member : members){
            i++;
            System.out.println("Character #" + i + ": " + member.getName() + "\t(" + member.getStrategy() + ")");
        }

        System.out.println("\nCombats played:\t\t" + teamStats.getGamesPlayed());
        System.out.println("Combats won:\t\t" + teamStats.getGamesWon());
        if (teamStats.getGamesPlayed() == 0) {
            System.out.println("Win rate:\t\t\tN/A (no games played)");
        } else {
            System.out.println("Win rate:\t\t\t" + ((teamStats.getGamesWon() * 100) / teamStats.getGamesPlayed()) + "%");
        }
        System.out.println("KOs done:\t\t\t" + teamStats.getKODone());
        System.out.println("KOs received:\t\t" + teamStats.getKOReceived());
    }

    /**
     * Displays a list of team names for the user to choose from.
     *
     * @param teamsNames a list of team names.
     */
    public void listTeamsNames(ArrayList<String> teamsNames){
        int i = 0;
        System.out.println();
        for (String teamName : teamsNames) {
            i++;
            System.out.println("\t" + i + ")" + teamName);
        }
        System.out.println();
    }

    /**
     * Prints the logo of the program.
     */
    public void printLogo(){
        System.out.println("   ___                       _     ___    ___         _");
        System.out.println("  / __|_   _ _ __  ___ _ _  | |  /  __|  | _ )_ _ ___| |");
        System.out.println("  \\__ \\ | | | '- \\/ -_) '_| | |__\\__ \\_  | _ \\ '_/ _ \\_|");
        System.out.println("  |___/ \\_,_| .__/\\___|_|   |____|___( ) |___/_| \\___(_)");
        System.out.println("            |_|                      |/");
    }
}