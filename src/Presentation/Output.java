package Presentation;

import java.util.ArrayList;

public class Output {

    public void mainMenu(){
        System.out.println("\n\t1) List Characters");
        System.out.println("\t2) Manage Teams");
        System.out.println("\t3) List Items");
        System.out.println("\t4) Simulate Combat");
        System.out.println("\n\t5) Exit");
    }

    public void listCharactersMenu(ArrayList<String> characters){
        int i = 0;
        System.out.println();
        for (String character : characters) {
            i++;
            System.out.println("\t" + i + ")" + character);
        }
        System.out.println();

        System.out.println("\t0) Back");
    }

    public void listCharacterAttributes(Character character){
        System.out.println(character.toString());
        System.out.println("<Press any key to continue...>");
    }

    public void manageTeamsMenu(){
        System.out.println("\nTeam management.");
        System.out.println("\t1) Create a Team");
        System.out.println("\t2) List Teams");
        System.out.println("\t3) Delete a Team");
        System.out.println("\n\t4) Back");
    }

    public void enterTeamNameToCreate(){
        System.out.println("Please enter the teams’s name: ");
    }

    public void enterTeamMemberName(int memberNumber){
        System.out.println("Please enter name or id for character #" + memberNumber + ":");
    }

    /*
    public void listTeamMemberStrategies(int memberNumber, Strategies strategy){
        int i;
        System.out.println("Game strategy for character #" + memberNumber + "?");
        for(i = 1; i < 2; i++) {
            System.out.println(i + ") " + strategy.name);
        }
    }
     */

    public void teamCreated(boolean created, String teamName){
        if(created){
            System.out.println("Team " + teamName + "has been successfully created!\n");
        }else{
            System.out.println("Team has not been created!\n");
        }
    }

    /*
    public void listAvailableTeams(ArrayList<Team> teams){
        int i = 0;
        System.out.println();
        for (Team team : teams) {
            i++;
            System.out.println("\t" + i + ")" + team);
        }
        System.out.println();

        System.out.println("\t0) Back");
    }
     */

    public void enterTeamNameToRemove(){
        System.out.println("Enter the name of the team to remove: ");
    }

    public void removeTeam(String teamName){
        System.out.println("Are you sure you want to remove \"" + teamName + "\" ? ");
    }

    public void teamRemoved(boolean removed, String teamName){
        if(removed){
            System.out.println("\"" + teamName + "\" has been removed from the system.");
        }
    }

    /*
    public void listItemsMenu(ArrayList<Item> items){
        int i = 0;
        System.out.println();
        for (Item item : items) {
            i++;
            System.out.println("\t" + i + ")" + item);
        }
        System.out.println();

        System.out.println("\t0) Back");
    }

    public void listItemsAttributes(Item item){
        System.out.println(item.toString());
        System.out.println("<Press any key to continue...>");
    }
    */

    public void simulateCombatMenu(){

    }
}