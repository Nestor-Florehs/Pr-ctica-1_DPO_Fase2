package Business;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Long> charactersIDs = new ArrayList<>();

    public Team(String name, ArrayList<Long> charactersIDs) {
        this.name = name;
        this.charactersIDs = charactersIDs;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Long> getCharactersIDs() {
        return charactersIDs;
    }
}
