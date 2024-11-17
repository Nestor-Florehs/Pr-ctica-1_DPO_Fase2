package Presentation;
import Business.*;

public class Controller {
    private Input input;
    private Output output;
    private CharacterManager characterManager;
    private TeamManager teamManager;


    public Controller() {
        input = new Input();
        output = new Output();
        characterManager = new CharacterManager();
        teamManager = new TeamManager();
    }

    public void executeMainOption(OptionStartingMenu option) {
        switch (option) {
            case OptionStartingMenu.LIST_CHARACTERS -> listCharacters();
            case OptionStartingMenu.MANAGE_TEAMS -> manageTeams();
            case OptionStartingMenu.LIST_ITEMS -> listItems();
            case OptionStartingMenu.SIMULATE_COMBAT -> simulateCombat();
            case OptionStartingMenu.EXIT -> System.out.println("We hope to see you again!");
            case OptionStartingMenu.ELSE -> System.out.println("Option not valid!");
        }
    }

    public void executeManageTeams(OptionManageTeam option) {
        switch (option) {
            case OptionManageTeam.CREATE_TEAM -> createTeam();
            case OptionManageTeam.LIST_TEAMS -> listTeams();
            case OptionManageTeam.DELETE_TEAM -> deleteTeam();
            case OptionManageTeam.BACK -> simulateCombat();
            case OptionManageTeam.ELSE -> System.out.println("Option not valid!");
        }
    }

    private void createTeam() {
        System.out.println("Create a new team");
    }

    private void listTeams() {
        int option;

        do {
            output.listMenu(teamManager.listTeams());
            option = input.askInteger("\nChoose an option: ");

            if (option != 0) {
                //output.listTeamAttributes();
                input.pressAnyKeyToContinue();
            }
        } while (option != 0);
    }

    private void deleteTeam() {
        System.out.println("Delete a team");
    }

    private void listCharacters() {
        int option;

        do {
            output.listMenu(characterManager.listCharacters());
            option = input.askInteger("\nChoose an option: ");

            if (option != 0) {
                output.listCharacterAttributes(characterManager.listCharacterAttribute(option), teamManager.listTeamsOfCharacter(option));
                input.pressAnyKeyToContinue();
            }
        } while (option != 0);
    }

    private void manageTeams() {
        int option;
        do {
            output.manageTeamsMenu();
            option = input.askInteger("\nChoose an option: ");
            executeManageTeams(OptionManageTeam.convertIntToEnum(option));
        } while (option != 4);
    }

    private void listItems() {
        System.out.println("Lista de items");
    }

    private void simulateCombat() {
        System.out.println("Simulate combat");
    }

    public void run() {
        int option;
        do {
            output.mainMenu();
            option = input.askInteger("\nChoose an option: ");
            executeMainOption(OptionStartingMenu.convertIntToEnum(option));
        } while (option != 5);
    }

    public static void main(String[] args) {
        new Controller().run();
    }
}