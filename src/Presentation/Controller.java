package Presentation;
import Business.*;

import java.io.IOException;

public class Controller {
    private Input input;
    private Output output;
    private CharacterManager characterManager;
    private TeamManager teamManager;
    private StatsManager statsManager;
    private ItemManager itemManager;

    public Controller() {
        input = new Input();
        output = new Output();
        characterManager = new CharacterManager();
        teamManager = new TeamManager();
        statsManager = new StatsManager();
        itemManager = new ItemManager();
    }

    public void executeMainOption(OptionStartingMenu option) throws IOException {
        switch (option) {
            case OptionStartingMenu.LIST_CHARACTERS -> listCharacters();
            case OptionStartingMenu.MANAGE_TEAMS -> manageTeams();
            case OptionStartingMenu.LIST_ITEMS -> listItems();
            case OptionStartingMenu.SIMULATE_COMBAT -> simulateCombat();
            case OptionStartingMenu.EXIT -> System.out.println("We hope to see you again!");
            case OptionStartingMenu.ELSE -> System.out.println("Option not valid!");
        }
    }

    public void executeManageTeams(OptionManageTeam option) throws IOException {
        switch (option) {
            case OptionManageTeam.CREATE_TEAM -> createTeam();
            case OptionManageTeam.LIST_TEAMS -> listTeams();
            case OptionManageTeam.DELETE_TEAM -> deleteTeam();
            case OptionManageTeam.BACK -> simulateCombat();
            case OptionManageTeam.ELSE -> System.out.println("Option not valid!");
        }
    }

    // TODO, crear la función crear equipo.
    private void createTeam() {
        System.out.println("Create a new team");
    }

    private void listTeams() {
        int option;

        do {
            output.listMenu(teamManager.listTeams());
            option = input.askInteger("\nChoose an option: ");

            if (option != 0) {
                Team team = teamManager.getTeamByIndex(option);
                output.listTeamAttributes(team, characterManager.getCharactersOfTeam(team), statsManager.getStatsByIndex(option));
                input.pressAnyKeyToContinue();
            }
        } while (option != 0);
    }

    private void deleteTeam() throws IOException {
        String teamName;
        teamName = input.askString("\n\tEnter the name of the team to remove: ");

        if (input.askString("\n\tAre you sure you want to delete this team ? ").equals("Yes")) {
            Output.printPhrase(teamManager.deleteTeamByName(teamName));
        } else {
            Output.printPhrase("Team doesn't delete");
        }
    }

    private void listCharacters() {
        int option;

        do {
            output.listMenu(characterManager.listCharacters());
            option = input.askInteger("\nChoose an option: ");

            if (option != 0) {
                try {
                    output.listCharacterAttributes(characterManager.listCharacterAttribute(option), teamManager.listTeamsOfCharacter(option));
                    input.pressAnyKeyToContinue();
                } catch (Exception e) {
                    output.printPhrase("Character doesn't exist");
                    input.pressAnyKeyToContinue();
                }
            }
        } while (option != 0);
    }

    private void manageTeams() throws IOException {
        int option;
        do {
            output.manageTeamsMenu();
            option = input.askInteger("\nChoose an option: ");
            executeManageTeams(OptionManageTeam.convertIntToEnum(option));
        } while (option != 4);
    }

    private void listItems() {
        int option;
        do {
            output.listMenu(itemManager.listItems());
            option = input.askInteger("\nChoose an option: ");

            if (option != 0) {
                try {
                    output.listItemAttributes(itemManager.listItemAttribute(option));
                    input.pressAnyKeyToContinue();
                } catch (Exception e) {
                    output.printPhrase("Input doesn't exist");
                    input.pressAnyKeyToContinue();
                }
            }
        } while (option != 0);

    }

    private void simulateCombat() {
        System.out.println("Simulate combat");
    }

    public void run() throws IOException {
        int option;
        do {
            output.mainMenu();
            option = input.askInteger("\nChoose an option: ");
            executeMainOption(OptionStartingMenu.convertIntToEnum(option));
        } while (option != 5);
    }

    public static void main(String[] args) throws IOException {
        new Controller().run();
    }
}