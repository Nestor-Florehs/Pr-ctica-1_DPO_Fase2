package Presentation;
import Business.CharacterManager;

public class Controller {
    Input input;
    Output output;
    CharacterManager characterManager;

    public Controller() {
        input = new Input();
        output = new Output();
        characterManager = new CharacterManager();
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
            case OptionManageTeam.CREATE_TEAM -> listCharacters();
            case OptionManageTeam.LIST_TEAMS -> manageTeams();
            case OptionManageTeam.DELETE_TEAM -> listItems();
            case OptionManageTeam.BACK -> simulateCombat();
            case OptionManageTeam.ELSE -> System.out.println("Option not valid!");
        }
    }

    private void listCharacters() {
        int option;

        output.listCharactersMenu(characterManager.listCharacters());
        option = input.askInteger("\nChoose an option: ");
    }

    private void manageTeams() {
        System.out.println("Manage teams");
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