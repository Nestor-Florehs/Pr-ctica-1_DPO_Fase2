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

    /*
    public static void saveParticipants(ArrayList<Character> characters) throws IOException {
        JSONArray participantsArray = new JSONArray();
        JSONArray ticketsArray = new JSONArray();

        for (Character participant : characters) {
            JSONObject participantObj = new JSONObject();
            participantObj.put("name", participant.getName());
            participantObj.put("birth", participant.getBirth());
            participantObj.put("nationality", participant.getNationality());
            participantObj.put("ticketId", participant.getTicketId());
            participantObj.put("grade", participant.getGrade());
            participantObj.put("field", participant.getField());

            participantsArray.add(participantObj);

            if (participant.getTicket() != null) {
                JSONObject ticketObj = new JSONObject();
                Ticket ticket = participant.getTicket();
                ticketObj.put("hour", ticket.getHour());
                ticketObj.put("table", ticket.getTable());
                ticketObj.put("drinks", ticket.getDrinks());

                // the ticket should be stored in the tickets array at the same index as participant
                ticketsArray.add(ticketObj);
            } else {
                // if no ticket, add a placeholder for index consistency
                ticketsArray.add(false);
            }
        }

        JSONObject participantsJsonObject = new JSONObject();
        participantsJsonObject.put("participants", participantsArray);

        FileWriter writerParticipants = new FileWriter("C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\PartyGRASP\\src\\Database\\Sallefest.json");
        FileWriter writerTickets = new FileWriter("C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\PartyGRASP\\src\\Database\\Tickets.json");

        writerParticipants.write(participantsJsonObject.toJSONString());
        writerTickets.write(ticketsArray.toJSONString());

        writerParticipants.flush();
        writerTickets.flush();
        writerParticipants.close();
        writerTickets.close();
    }
    */

    public static void main(String[] args) {
        ArrayList<Character> characters = new ArrayList<>();
        characters = new CharacterDao().readUsers();
        System.out.println(characters);
    }
}
