package gr.huadit.JSONHandler;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.huadit.DTO.Profile;

public class JSONFileReader {
    private final String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/java/gr/huadit/Storage";


    public JSONFileReader() {

    }

    public Profile readJSON(String key) {

        File outputFile = new File(STORAGE_DIRECTORY, "fileContainer.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(outputFile);
            String name = jsonNode.get("type").asText();
            String age = jsonNode.get("id").asText();
            String weight = jsonNode.get("distance").asText();
            String height = jsonNode.get("pace").asText();
            String gender = jsonNode.get("Gender").asText();
            return null;
        } catch (IOException e) {
            System.err.println("ERROR AT READING JSON FILE:");
        }
        return null;
    }
}
