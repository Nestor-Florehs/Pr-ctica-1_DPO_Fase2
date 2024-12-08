import Presentation.Controller;

import java.io.IOException;

/**
 * The Main class serves as the entry point for the program.
 * It contains the main method which initializes the Controller class and starts its execution.
 *
 * @throws IOException if an input/output error occurs.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        controller.run();
    }
}