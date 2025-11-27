package gr.huadit;

import gr.huadit.Helpers.XMLFileReader;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Logger log = new ConsoleLogger();
        XMLFileReader reader = new XMLFileReader();
        if (args.length < 1) {
            log.print("Usage:  java -cp target/CoachingAssistant-1.0-SNAPSHOT.jar gr.huadit.Main <filename> ", LoggerLevel.FATAL);
        }

        String fileName = args[0];

        try {
            log.print( "Reading file!", LoggerLevel.DEBUG);
            Path startingDir = Paths.get(System.getProperty("user.home"));
            String pattern = "*.tcx";
            Find.Finder finder = new Find.Finder(pattern);

            Files.walkFileTree(startingDir, finder);
            finder.done();

            reader.readFile(finder.getPath(), log);
        } catch (Exception e) {
            log.print( "File Not Found!\n" + e.getMessage(), LoggerLevel.ERROR);
            System.exit(1);
        }

    }
}
