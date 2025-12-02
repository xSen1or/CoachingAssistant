package gr.huadit.JSONHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import gr.huadit.Classes.Profile;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import java.io.File;

/*

    Disclaimer: Οχι αυτος ο κώδικας δεν ειναι ChatGPT, ναι εμεις το σκεφτηκαμε να το βαλουμε.
    Πως λειτουργεί;
    Παίρνει ο constructor ενα αντικείμενο τύπου Προφιλ και μέσω ObjectMapper & ObjectNode
    γράφουμε τα δεδομένα που θέλουμε στο .json αρχείο το οποίο έχει object-like structure

 */


public class JSONFileWriter {
    private final String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/src/main/java/gr/huadit/Storage";
    private final Profile prof;
    private final Logger logger = new ConsoleLogger();

    public JSONFileWriter(Object obj) {
        this.prof = (Profile) obj;
    }

    public void  writeToFile() {
        try {
            System.out.println(1);
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("Name", prof.getName());
            jsonNode.put("Age", prof.getAge());
            jsonNode.put("Gender", prof.getGender());
            jsonNode.put("Weight", prof.getWeight());
            jsonNode.put("Height", prof.getHeight());

            File outputFile = new File(STORAGE_DIRECTORY, "storage.json");
            System.out.println("Writing to file: " + outputFile.getAbsolutePath());

            objectMapper.writeValue(outputFile, jsonNode);

            System.out.println(2);
        } catch (Exception e) {
            logger.print("Couldn't parse data in storage", LoggerLevel.FATAL);
            System.exit(1);
        }
    }
}
