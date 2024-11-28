package Presentation;
import Business.*;
import Business.Character;

import java.io.IOException;
import java.util.ArrayList;

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

    private String askStrategy(int index){
        Output.printPhrase("Game strategy for character #" + index+ "?");
        Output.printPhrase("\t1) Balanced");
        int option = input.askInteger("\nChoose and option: ");
        switch (option) {
            case 1:
                return "balanced";
            default:
                Output.printPhrase("\tOption not valid!");
                return askStrategy(index);
        }
    }

    private ArrayList<Member> getMembers() {
        ArrayList<Member> members = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            String member = input.askString("\nPlease enter name or id for character #" + i + ": ");
            Character character = characterManager.getCharacterByIdOrName(member);
            if (character == null) {
                Output.printPhrase("\nCharacter #" + i + " does not exist");
                i--;
            } else {
                String strategy = askStrategy(i);
                Member member1 = new Member(character.getId(), strategy);
                member1.setName(character.getName());
                members.add(member1);
            }
        }
        return members;
    }

    // TODO, solucionar guardado de las Stats iniciales, guarda en el json el nombre de los equipos mal
    private void createTeam() throws IOException {
        String teamName = input.askString("\nPlease enter the team's name: ");
        Team t = teamManager.getTeamByName(teamName);
        ArrayList<Member> members;

        if (t == null) {
            members = getMembers();
            Team team = new Team(teamName, members);
            teamManager.addTeam(team);
            statsManager.inicialiceStats(teamName);
        } else {
            Output.printPhrase("\nWe are sorry \"" + t.getName() + "\" is taken.");
        }
    }

    private void listTeams() {
        int option;

        do {
            output.listMenu(teamManager.listTeams());
            option = input.askInteger("\nChoose an option: ");

            if (option != 0) {
                try {
                    Team team = teamManager.getTeamByIndex(option);
                    output.listTeamAttributes(team, characterManager.getCharactersOfTeam(team), statsManager.getStatsByIndex(option));
                    input.pressAnyKeyToContinue();
                } catch (Exception e) {
                    output.printPhrase("Team doesn't exist");
                    input.pressAnyKeyToContinue();
                }
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