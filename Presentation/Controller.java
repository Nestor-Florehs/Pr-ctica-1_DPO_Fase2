package Presentation;

public class Controller {
    Input input;
    Output output;

    public Controller() {
       input = new Input();
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
        System.out.println("Lista de caracteres");
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
            option =input.askInteger("\nChoose an option: ");
            executeMainOption(OptionStartingMenu.convertIntToEnum(option));
        } while (option != 5);
    }

    public static void main(String[] args) {
        new Controller().run();
    }
}
