package gr.huadit.JSONHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import gr.huadit.Classes.ActivityCard;
import gr.huadit.DTO.Profile;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Holders.TotalFiles;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import java.io.File;

public class JSONFileWriter {

    // path
    private final String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/java/gr/huadit/Storage";

    // logger
    private final Logger logger = new ConsoleLogger();

    // object
    private final ActivityCard obj;

    // constructor
    public JSONFileWriter(Object keyObj) {
        obj = (ActivityCard) keyObj;
     }

     // writing function
     public void writeProfileToFile() {
        try {
            // Mapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Initialize variables.
            File outputFile = new File(STORAGE_DIRECTORY, "fileContainer.json");
            ArrayNode arrayNode;


            // Check if the file exists and is not empty.
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


            // Create a new JSON Node.
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("activity_name", obj.getActivityName());
            jsonNode.put("activity_id", obj.getId());
            jsonNode.put("activity_total_distance", obj.getTotalDistance());
            jsonNode.put("activity_average_pace", obj.getAveragePace());
            jsonNode.put("activity_duration", obj.getDuration().toString());
            jsonNode.put("activity_average_heart_rate", obj.getAverageHeartRate());

            TotalFiles.results.add(obj.getActivityName());
            arrayNode.add(jsonNode);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, arrayNode);
            logger.print("JSON OBject Created", LoggerLevel.INFO);
        } catch (Exception e) {
            logger.print("Couldn't parse data in storage", LoggerLevel.FATAL);
            logger.print(e.getMessage(), LoggerLevel.FATAL);
            System.exit(1);
        }
    }
}
