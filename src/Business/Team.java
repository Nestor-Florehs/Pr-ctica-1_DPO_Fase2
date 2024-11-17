package Business;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Integer> charactersIDs = new ArrayList<>();

    public Team(String name, ArrayList<Integer> charactersIDs) {
        this.name = name;
        this.charactersIDs = charactersIDs;
    }

    public String getName() {
        return name;
    }
}
