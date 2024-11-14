import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CharacterDao {

    private ArrayList<Character> readUsers () {
        JSONParser parser = new JSONParser();
        ArrayList<Character> characters = new ArrayList<>();

        try {
            // Load the JSON file
            FileReader reader = new FileReader("C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\Practica-1-DPO\\Database\\characters.json");

            // Parse the JSON file as an array
            JSONArray array = (JSONArray) parser.parse(reader);

            // Iterate through each JSON object in the array
            for (Object o : array) {
                JSONObject character = (JSONObject) o;

                // Extract fields
                long id = (long) character.get("id");
                String name = (String) character.get("name");
                long weightLong = (long) character.get("weight");

                int weight = (int) weightLong;

                Character c = new Character(id, name, weight);
                characters.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return characters;
    }

    public static void saveParticipants(ArrayList<Character> characters) throws IOException {
        JSONArray characterArray = new JSONArray();

        for (Character participant : characters) {
            JSONObject characterObject = new JSONObject();
            characterObject.put("name", participant.getName());
            characterObject.put("weight", participant.getWeight());
            characterObject.put("id", participant.getId());


            characterArray.add(characterObject);
        }

        FileWriter writerParticipants = new FileWriter("C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\Practica-1-DPO\\Database\\characters.json");

        writerParticipants.write(JSONArray.toJSONString(characterArray));

        writerParticipants.flush();
        writerParticipants.close();
    }


    public static void main(String[] args) throws IOException {
        ArrayList<Character> characters = new ArrayList<>();
        characters = new CharacterDao().readUsers();
        System.out.println(characters);
        saveParticipants(characters);
    }
}
