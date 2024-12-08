package Business;

import Persistence.implementations.CharacterJsonDao;
import Persistence.implementations.ItemJsonDao;
import Presentation.Output;

/**
 * This class is responsible for validating the character and item JSON files.
 * It checks if the files are accessible and correctly formatted.
 * If any file is invalid, it prints an error message and shuts down the program.
 * If both files are valid, it prints a success message.
 */
public class JsonValidation {

    private static final String CORRECT_FILES_MESSAGE = "Files OK";
    private static final String WRONG_CHARACTER_FILE_MESSAGE = "Error: The characters.json file can’t be accessed.\n";
    private static final String WRONG_ITEM_FILE_MESSAGE = "Error: The item.json file can’t be accessed.\n";

    /**
     * Checks the validity of the character and item JSON files.
     * If either file is invalid, prints an error message and shuts down the program.
     * If both files are valid, prints a success message.
     */
    public void checkJsonValidity() {
        CharacterManager characterManager = new CharacterManager();
        ItemManager itemManager = new ItemManager();

        boolean allValid = true;

        if (!characterManager.validateCharacterJson()) {
            Output.printPhrase(WRONG_CHARACTER_FILE_MESSAGE);
            allValid = false;
        }
        if (!itemManager.validateItemJson()) {
            Output.printPhrase(WRONG_ITEM_FILE_MESSAGE);
            allValid = false;
        }

        if (allValid) {
            System.out.println(CORRECT_FILES_MESSAGE);
        } else {
            Output.printPhrase("Shutting down...");

            System.exit(1);
        }
    }
}
