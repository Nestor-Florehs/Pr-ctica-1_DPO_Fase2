public class Controller {
    InputOutput io;

    public Controller() {
        io = new InputOutput();
    }

    public void executeMainOption(OptionStartingMenu option) {
        switch (option) {
            case LIST_CHARACTERS -> listCharacters();
            case MANAGE_TEAMS -> runManageTeams();
            case LIST_ITEMS -> listItems();
            case SIMULATE_COMBAT -> simulateCombat();
            case EXIT -> System.out.println("Exit program");
            case ELSE -> System.out.println("Invalid option!!");
        }
    }

    public void executeManageTeams(OptionMangeTeam option) {
        switch (option) {
            case CREATE_TEAM -> createTeam();
            case LIST_TEAMS -> listTeam();
            case DELETE_TEAM -> deleteTeam();
            case ELSE -> System.out.println("Invalid option!!");
        }
    }

    private void createTeam() {
        System.out.println("Create team");
    }

    private void listTeam() {
        System.out.println("List team");
    }

    private void deleteTeam() {
        System.out.println("Delete team");
    }

    private void listCharacters() {
        System.out.println("Lista de caracteres");
    }

    private void runManageTeams() {
        int option;
        do {
            io.manageTeamsMenu();
            option = io.askInteger("\nChoose an option: ");
            executeManageTeams(OptionMangeTeam.convertIntToEnum(option));
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
            io.mainMenu();
            option = io.askInteger("\nChoose an option: ");
            executeMainOption(OptionStartingMenu.convertIntToEnum(option));
        } while (option != 5);
    }

    public static void main(String[] args) {
        new Controller().run();
    }
}
