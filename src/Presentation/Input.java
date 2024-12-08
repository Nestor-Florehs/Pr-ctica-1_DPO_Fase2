package Presentation;

import java.util.Scanner;

import static Presentation.Controller.*;

/**
 * The Input class handles all input operations from the user. It provides
 * methods for reading various types of data, such as strings and integers,
 * from the console. It ensures that the input is properly validated and
 * provides a simple interface for gathering user input.
 */
public class Input {
    static private Scanner sc;

    public Input() {
        sc = new Scanner(System.in);
    }

    /**
     * Prompts the user to input an integer and validates the input.
     *
     * @param prompt the message displayed to the user to request input.
     * @return the valid integer entered by the user.
     * If the input is not a valid integer, an error message is displayed and the user is prompted again.
     */
    public int askInteger(String prompt){
        int integer;
        try{
            System.out.print(prompt);
            integer = Integer.parseInt(sc.nextLine());
        }
        catch (Exception e) {
            Output.printPhrase(INVALID_OPTION_MESSAGE);
            return askInteger(prompt);
        }
        return integer;
    }

    /**
     * Prompts the user to input a string and returns the entered value.
     *
     * @param prompt the message displayed to the user to request input. The
     *               prompt is shown on the console.
     * @return the string entered by the user.
     */
    public String askString(String prompt){
        String string;
        System.out.print(prompt);
        string = sc.nextLine();
        return string;
    }

    /**
     * Waits for the user to press the Enter key before proceeding.
     *
     * Displays a prompt message to the user indicating to press any key to
     * continue. The method waits for the user to press Enter.
     */
    public void pressAnyKeyToContinue(){
        Scanner scanner = new Scanner(System.in);
        Output.printPhrase("\n<Press any key to continue...>");
        scanner.nextLine(); // Espera a que el usuario presione Enter
    }

    /**
     * Prompts the user to select a game strategy for a character by index.
     *
     * Displays a message indicating the character's index and the available
     * strategies.
     *
     * @param index the index of the character for whom the strategy is
     *              being selected.
     * @return the name of the selected strategy as a string.
     */
    public String askStrategy(int index){
        Output.printPhrase("Game strategy for character #" + index+ "?");
        Output.printPhrase(STRATEGY_BALANCED_MESSAGE);
        int option = askInteger(CHOOSE_OPTION_MESSAGE);
        if (option == 1) {
            return "balanced";
        } else {
            Output.printPhrase(INVALID_OPTION_MESSAGE);
            return askStrategy(index);
        }
    }
}
