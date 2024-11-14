public class Controller {
    InputOutput io;

    public Controller() {
        io = new InputOutput();
    }

    public void executeMainOption(OptionStartingMenu option) {
        switch (option) {
            case LIST_CHARACTERS -> listCharacters();
            case MANAGE_TEAMS -> manageTeams();
            case LIST_ITEMS -> listItems();
            case SIMULATE_COMBAT -> simulateCombat();
            case EXIT -> System.out.println("Exit program");
            case ELSE -> System.out.println("Invalid option!!");
        }
    }

    public void executeManageTeams(OptionMangeTeam option) {
        switch (option) {
            case CREATE_TEAM -> listCharacters();
            case LIST_TEAMS -> manageTeams();
            case DELETE_TEAM -> listItems();
            case BACK -> simulateCombat();
            case ELSE -> System.out.println("Invalid option!!");
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
            io.mainMenu();
            option = io.askInteger("\nChoose an option: ");
            executeMainOption(OptionStartingMenu.convertIntToEnum(option));
        } while (option != 5);
    }

    public static void main(String[] args) {
        new Controller().run();
    }
}
