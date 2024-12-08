package Presentation;
import Business.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Controller class is responsible for managing the main operations of the simulation program.
 * It interacts with managers to handle user inputs, execute actions, and control the flow of the program.
 * This class serves as the entry point for user interactions and is responsible for controlling the
 * main menu, team management, item listing, and combat simulation.
 */
public class Controller {
    private final Input input;
    private final Output output;
    private final CharacterManager characterManager;
    private final TeamManager teamManager;
    private final StatManager statsManager;
    private final ItemManager itemManager;
    private final MemberManager memberManager;

    static final String INVALID_OPTION_MESSAGE = "Option not valid!";
    static final String CHOOSE_OPTION_MESSAGE = "\nChoose an option: ";
    static final String TEAM_NO_EXIST_MESSAGE = "Team doesn't exist";
    static final String INITIALIZING_TEAMS_MESSAGE = "Initializing teams...";
    static final String STARTING_SIMULATION_MESSAGE = "\nStarting simulation...";
    static final String STRATEGY_BALANCED_MESSAGE = "\t1) Balanced";
    static final String LOOKING_AVAILABLE_TEAMS = "Looking for available teams...";
    static final String WELCOME_MESSAGE = "\nWelcome to Super LS, Bro! Simulator.";
    static final String EXIT_MESSAGE = "\nWe hope to see you again!";
    static final String VERIFYING_FILES_MESSAGE = "\nVerifying local files...";
    static final String STARTING_PROGRAM_MESSAGE = "Starting program...\n";
    static final String ENTER_TEAMS_NAME_MESSAGE = "\nPlease enter the team's name: ";
    static final String TEAM_NOT_DELETED_MESSAGE = "\nThe team has not been deleted";
    static final String COMBAT_READY_MESSAGE = "Combat ready!";
    static final String CHARACTER_NOT_EXIST_MESSAGE = "This character does not exist";
    static final String ITEM_NOT_EXIST_MESSAGE = "This item does not exist";
    static final String TEAM_TO_REMOVE_MESSAGE = "\n\tEnter the name of the team to remove: ";
    static final String ASKING_FOR_REMOVE_TEAM_MESSAGE = "\n\tAre you sure you want to delete this team? ";
    static final String CHOOSE_TEAM_MESSAGE = "Choose team #";

    /**
     * Constructs a new Controller instance, initializing all necessary managers
     * for handling characters, teams, stats, items, and members.
     */
    public Controller() {
        input = new Input();
        output = new Output();
        characterManager = new CharacterManager();
        teamManager = new TeamManager();
        statsManager = new StatManager();
        itemManager = new ItemManager();
        memberManager = new MemberManager();
    }

    /**
     * Executes the selected option from the main menu.
     *
     * @param option the option selected from the main menu.
     * @throws IOException if an input/output error occurs.
     */
    private void executeMainOption(OptionStartingMenu option) throws IOException {
        switch (option) {
            case OptionStartingMenu.LIST_CHARACTERS -> listCharacters();
            case OptionStartingMenu.MANAGE_TEAMS -> manageTeams();
            case OptionStartingMenu.LIST_ITEMS -> listItems();
            case OptionStartingMenu.SIMULATE_COMBAT -> simulateCombat();
            case OptionStartingMenu.EXIT -> Output.printPhrase(EXIT_MESSAGE);
            case OptionStartingMenu.ELSE -> Output.printPhrase(INVALID_OPTION_MESSAGE);
        }
    }

    /**
     * Executes the selected option for managing teams.
     *
     * @param option the option selected from the team management menu.
     * @throws IOException if an input/output error occurs.
     */
    private void executeManageTeams(OptionManageTeam option) throws IOException {
        switch (option) {
            case OptionManageTeam.CREATE_TEAM -> createTeam();
            case OptionManageTeam.LIST_TEAMS -> listTeams();
            case OptionManageTeam.DELETE_TEAM -> deleteTeam();
            case OptionManageTeam.BACK -> {
                break;
            }
            case OptionManageTeam.ELSE -> System.out.println(INVALID_OPTION_MESSAGE);
        }
    }

    /**
     * Prompts the user to create a new team. If the team name is available, it adds the team and initializes stats.
     *
     * @throws IOException if an input/output error occurs.
     */
    private void createTeam() throws IOException {
        String teamName = input.askString(ENTER_TEAMS_NAME_MESSAGE);
        Team t = teamManager.getTeamByName(teamName);
        ArrayList<Member> members;

        if (t == null) {
            members = memberManager.getMembers();
            Team team = new Team(teamName, members);
            teamManager.addTeam(team);
            statsManager.inicialiceStats(teamName);
        } else {
            Output.printPhrase("\nWe are sorry \"" + t.getName() + "\" is taken.");
        }
    }

    /**
     * Lists all available teams. Allows the user to select a team and view its attributes.
     */
    private void listTeams() {
        int option;

        do {
            output.listMenu(teamManager.listTeams());
            option = input.askInteger(CHOOSE_OPTION_MESSAGE);

            if (option != 0) {
                try {
                    Team team = teamManager.getTeamByIndex(option);
                    output.listTeamAttributes(team, characterManager.getCharactersOfTeam(team), statsManager.getStatsByIndex(option - 1));
                    input.pressAnyKeyToContinue();
                } catch (Exception e) {
                    Output.printPhrase(TEAM_NO_EXIST_MESSAGE);
                    input.pressAnyKeyToContinue();
                }
            }
        } while (option != 0);
    }

    /**
     * Prompts the user to delete a team by name. If confirmed, deletes the team and its associated stats.
     *
     * @throws IOException if an input/output error occurs.
     */
    private void deleteTeam() throws IOException {
        String teamName;
        teamName = input.askString(TEAM_TO_REMOVE_MESSAGE);

        if (input.askString(ASKING_FOR_REMOVE_TEAM_MESSAGE).equals("Yes")) {
            Output.printPhrase(teamManager.deleteTeamByName(teamName));
        } else {
            Output.printPhrase(TEAM_NOT_DELETED_MESSAGE);
        }
    }

    /**
     * Lists all available characters and allows the user to select one to view its details.
     */
    private void listCharacters() {
        int option;

        do {
            output.listMenu(characterManager.listCharacters());
            option = input.askInteger(CHOOSE_OPTION_MESSAGE);

            if (option != 0) {
                try {
                    output.listCharacterAttributes(characterManager.listCharacterAttribute(option), teamManager.listTeamsOfCharacter(option));
                    input.pressAnyKeyToContinue();
                } catch (Exception e) {
                    Output.printPhrase(CHARACTER_NOT_EXIST_MESSAGE);
                    input.pressAnyKeyToContinue();
                }
            }
        } while (option != 0);
    }

    /**
     * Allows the user to manage teams by providing a menu of options (create, list, delete, etc.).
     *
     * @throws IOException if an input/output error occurs.
     */
    private void manageTeams() throws IOException {
        int option;
        do {
            output.manageTeamsMenu();
            option = input.askInteger(CHOOSE_OPTION_MESSAGE);
            executeManageTeams(OptionManageTeam.convertIntToEnum(option));
        } while (option != 4);
    }

    /**
     * Lists all available items and allows the user to select one to view its details.
     */
    private void listItems() {
        int option;
        do {
            output.listMenu(itemManager.listItems());
            option = input.askInteger(CHOOSE_OPTION_MESSAGE);

            if (option != 0) {
                try {
                    output.listItemAttributes(itemManager.listItemAttribute(option));
                    input.pressAnyKeyToContinue();
                } catch (Exception e) {
                    Output.printPhrase(ITEM_NOT_EXIST_MESSAGE);
                    input.pressAnyKeyToContinue();
                }
            }
        } while (option != 0);
    }

    /**
     * Executes the battle simulation for a given Battle object. This method starts the combat and
     * prints the results.
     *
     * @param battle the Battle object containing the teams and simulation parameters.
     */
    private void executeBattle(Battle battle) {
        Output.printPhrase(COMBAT_READY_MESSAGE);
        input.pressAnyKeyToContinue();
        BattleManager battleManager = new BattleManager(battle);
        battleManager.executeBattle();
    }

    /**
     * Initializes the battle by assigning weapons and armor to the members of the selected teams.
     * It ensures that the members are properly equipped before starting the combat.
     *
     * @param teams the list of teams selected for the battle.
     */
    private void initializeBattle (ArrayList<Team> teams) {
        Output.printPhrase(INITIALIZING_TEAMS_MESSAGE);

        for (Team team : teams) {
            for (int j = 0; j < 4; j++) {
                Item randomWeapon = itemManager.getRandomWeapon();
                Item randomArmor = itemManager.getRandomArmor();
                Member member = team.getMembers().get(j);

                if (randomArmor == null) {
                    j--;
                }
                if (randomWeapon == null) {
                    j--;
                }

                member.setArmor(randomArmor);
                member.setWeapon(randomWeapon);

                String memberID = String.valueOf(member.getId());
                member.setName(characterManager.getCharacterByIdOrName(memberID).getName());
                member.setDamageReceived(0);
            }
        }

        Battle battle = new Battle(teams);
        System.out.println(battle);
        executeBattle(battle);
        statsManager.updateStatsOfBattle(battle);
    }

    /**
     * Selects two teams for the battle by prompting the user to choose from available teams.
     * It ensures that the selected teams are valid and adds them to the battle setup.
     *
     * @return a list of two teams selected for the battle.
     */
    private ArrayList<Team> selectTeamsForBattle() {
        ArrayList<Team> teams = new ArrayList<>();
        Output.printPhrase(LOOKING_AVAILABLE_TEAMS);

        ArrayList<String> teamsName = teamManager.listTeams();
        output.listTeamsNames(teamsName);

        for (int i = 1; i <= 2; i++) {
            int teamIndex = input.askInteger(CHOOSE_TEAM_MESSAGE + i + ": ");
            try {
                Team team = teamManager.getTeamByIndex(teamIndex);
                teams.add(team);
            } catch (Exception e) {
                Output.printPhrase(TEAM_NO_EXIST_MESSAGE);
                i--;
            }
        }

        return teams;
    }

    /**
     * Starts the combat simulation by selecting teams and initializing the battle. It prints the
     * simulation start message and allows the user to follow the battle's progress.
     */
    private void simulateCombat() {
        Output.printPhrase(STARTING_SIMULATION_MESSAGE);
        ArrayList<Team> teams = selectTeamsForBattle();
        initializeBattle(teams);

        input.pressAnyKeyToContinue();
    }

    /**
     * Initializes the game by printing the logo, verifying the validity of local JSON files,
     * and starting the program.
     */
    private void initializeGame() {
        output.printLogo();
        Output.printPhrase(WELCOME_MESSAGE);
        JsonValidation jsonValidation = new JsonValidation();

        Output.printPhrase(VERIFYING_FILES_MESSAGE);
        jsonValidation.checkJsonValidity();
        Output.printPhrase(STARTING_PROGRAM_MESSAGE);
    }

    /**
     * Runs the program by initializing the game and then continuously prompting the user with the
     * main menu options until they choose to exit.
     *
     * @throws IOException if an input/output error occurs.
     */
    public void run() throws IOException {
        initializeGame();
        int option;
        do {
            output.mainMenu();
            option = input.askInteger(CHOOSE_OPTION_MESSAGE);
            executeMainOption(OptionStartingMenu.convertIntToEnum(option));
        } while (option != 5);
    }
}