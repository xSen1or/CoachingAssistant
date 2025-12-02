package gr.huadit.JSONHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.huadit.Classes.Profile;

import java.io.File;
import java.io.IOException;

public class JSONFileReader {
    private final String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/java/gr/huadit/Storage";


    public JSONFileReader() {

    }

    public Profile readJSON() {
        File outputFile = new File(STORAGE_DIRECTORY, "storage.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(outputFile);
            String name = jsonNode.get("Name").asText();
            int age = jsonNode.get("Age").asInt();
            int weight = jsonNode.get("Weight").asInt();
            int height = jsonNode.get("Height").asInt();
            String gender = jsonNode.get("Gender").asText();
            return new Profile(name, age, weight, height, gender);
        } catch (IOException e) {
            System.err.println("ERROR AT READING JSON FILE:");
        }
        return null;
    }

    public static void main(String[] args) {
        JSONFileReader reader = new JSONFileReader();
        Profile prof = reader.readJSON();
        System.out.println(prof.toString());
    }

}
