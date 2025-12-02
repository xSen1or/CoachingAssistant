package gr.huadit;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import gr.huadit.GUI.ProfileGUI;
import gr.huadit.Loggers.ConsoleLogger;
import gr.huadit.Enums.LoggerLevel;

public class Application {
    public static void main(String[] args) {
        ConsoleLogger log = new ConsoleLogger();
        String pattern = "*.tcx";
        Find.Finder finder = new Find.Finder(pattern);
        try {
            Path startingDirectory = Paths.get(System.getProperty("user.home"));
            Files.walkFileTree(startingDirectory, finder);
            finder.done();
            log.print("Files Parsed.", LoggerLevel.INFO);
        } catch (Exception e) {
            log.print("Error: " + e.getMessage(), LoggerLevel.ERROR);
        }
        new ProfileGUI(finder.getFilenames());
    }
}
