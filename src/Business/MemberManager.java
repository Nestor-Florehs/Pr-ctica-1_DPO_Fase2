package Business;

import Presentation.Input;
import Presentation.Output;

import java.util.ArrayList;

/**
 * Manages the creation and retrieval of members for a team.
 */
public class MemberManager {

    /**
     * Retrieves a list of members by prompting the user for character names or IDs.
     * The user is prompted to input the name or ID for each character, and the corresponding
     * strategy for each member is chosen. If a character does not exist, the user is prompted again.
     *
     * @return a list of members created from the provided character data.
     */
    public ArrayList<Member> getMembers() {
        ArrayList<Member> members = new ArrayList<>();
        CharacterManager characterManager = new CharacterManager();
        Input input = new Input();

        for (int i = 1; i <= 4; i++) {
            String member = input.askString("\nPlease enter name or id for character #" + i + ": ");
            Character character = characterManager.getCharacterByIdOrName(member);
            if (character == null) {
                Output.printPhrase("\nCharacter #" + i + " does not exist");
                i--;
            } else {
                String strategy = input.askStrategy(i);
                Member member1 = new Member(character.getId(), strategy);
                member1.setName(character.getName());
                members.add(member1);
            }
        }
        return members;
    }
}
