package Business;

import Persistence.*;
import Presentation.Output;

public class JsonValidation {

    private static final String CORRECT_FILES_MESSAGE = "Files OK";
    private static final String WRONG_CHARACTER_FILE_MESSAGE = "Error: The characters.json file can’t be accessed.\n";
    private static final String WRONG_ITEM_FILE_MESSAGE = "Error: The item.json file can’t be accessed.\n";

    public void checkJsonValidity() {
        CharacterDao characterDao = new CharacterDao();
        ItemDao itemDao = new ItemDao();

        boolean allValid = true;

        if (!characterDao.validateJson()) {
            Output.printPhrase(WRONG_CHARACTER_FILE_MESSAGE);
            allValid = false;
        }
        if (!itemDao.validateJson()) {
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
