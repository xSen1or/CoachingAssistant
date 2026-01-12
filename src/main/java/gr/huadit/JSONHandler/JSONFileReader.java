package gr.huadit.JSONHandler;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.huadit.Classes.ActivityCard;
import gr.huadit.DTO.Profile;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Loggers.ConsoleLogger;

import javax.swing.*;

public class JSONFileReader {
    private final String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/java/gr/huadit/Storage";
    ConsoleLogger logger = new ConsoleLogger();

    public JSONFileReader() {

    }
    
    public boolean contains(String key) {
        File container = new File(STORAGE_DIRECTORY, "fileContainer.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(container);
            if (root.isArray()) {
                for (JsonNode node : root) {
                    String name = node.get("activity_name").asText();
                    if (name.equalsIgnoreCase(key)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public ActivityCard readJSON(String key) {
        if (!contains(key)) return null;
        File container = new File(STORAGE_DIRECTORY, "fileContainer.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(container);

            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {

                    // Safe check if the activity_name field actually exists. (not sure if its necessary but I added it anyway)
                    JsonNode JSONName = node.get("activity_name");
                    if (JSONName == null) continue;

                    // if that's not the wanted activity move along.
                    String activityName = node.get("activity_name").asText();
                    if (!activityName.equalsIgnoreCase(key)) continue;

                    // get the rest of the values.
                    String activityId = node.get("activity_id").asText();
                    double totalDistance = node.get("activity_total_distance").asDouble();
                    double averagePace = node.get("activity_average_pace").asDouble();
                    double averageHeartRate = node.get("activity_average_heart_rate").asDouble();
                    String durationStr = node.get("activity_duration").asText();
                    Duration duration = Duration.parse(durationStr);

                    // return the object as an ActivityCard.
                    return new ActivityCard(activityName, activityId, totalDistance, averagePace, averageHeartRate , duration);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An extremely rare error occurred.");
            logger.print(e.getMessage(), LoggerLevel.FATAL);
        }
        return null;
    }
}
