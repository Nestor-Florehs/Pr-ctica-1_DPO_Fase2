import java.util.ArrayList;
import java.util.Scanner;

public class InputOutput {
    static private Scanner sc;

    public InputOutput() {
        sc = new Scanner(System.in);
    }

    public void mainMenu(){
        System.out.println("\n\t1) List Characters");
        System.out.println("\t2) Manage Teams");
        System.out.println("\t3) List Items");
        System.out.println("\t4) Simulate Combat");
        System.out.println("\n\t5) Exit");
    }

    public void listCharactersMenu(ArrayList<Character> characters){
        int i = 0;
        System.out.println();
        for (Character character : characters) {
            i++;
            System.out.println("\t" + i + ")" + character);
        }
        System.out.println();

        System.out.println("\t0) Back");
    }

    public static int askInteger(String prompt){
        int integer;

        try{
            System.out.print(prompt);
            integer = Integer.parseInt(sc.nextLine());
        }
        catch (Exception e) {
            System.out.println("\tInvalid option!!\n");
            return askInteger(prompt);
        }

        return integer;
    }

    public static String askString(String prompt){
        String string;
        System.out.print(prompt);
        string = sc.nextLine();
        return string;
    }

    public void manageTeamsMenu(){
        System.out.println("\nTeam management.");
        System.out.println("\t1) Create a Team");
        System.out.println("\t2) List Teams");
        System.out.println("\t3) Delete a Team");
        System.out.println("\n\t4) Back");
    }

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

    public void simulateCombatMenu(){

    }

}
