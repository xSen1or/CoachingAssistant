package gr.huadit.JSONHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import gr.huadit.DTO.Profile;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import java.io.File;

public class JSONFileWriter {
    private final String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/java/gr/huadit/Storage";
    private Profile prof = null;
    private final Logger logger = new ConsoleLogger();

     public JSONFileWriter(Profile obj) {
        this.prof = obj;
    }

    public void writeProfileToFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            File outputFile = new File(STORAGE_DIRECTORY, "storage.json");
            ArrayNode arrayNode;

            if (outputFile.exists() && outputFile.length() > 0) {
                JsonNode existing = objectMapper.readTree(outputFile);

                if (existing.isArray()) {
                    arrayNode = (ArrayNode) existing;
                } else {
                    arrayNode = objectMapper.createArrayNode();
                    arrayNode.add(existing);
                }
            } else {
                arrayNode = objectMapper.createArrayNode();
            }

            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("Name", prof.name());
            jsonNode.put("Age", prof.age());
            jsonNode.put("Gender", prof.gender());
            jsonNode.put("Weight", prof.weight());
            jsonNode.put("Height", prof.height());

            arrayNode.add(jsonNode);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, arrayNode);

        } catch (Exception e) {
            logger.print("Couldn't parse data in storage", LoggerLevel.FATAL);
            logger.print(e.getMessage(), LoggerLevel.FATAL);
            System.exit(1);
        }
    }
}
