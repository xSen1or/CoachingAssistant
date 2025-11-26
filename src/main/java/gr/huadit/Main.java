package gr.huadit;

import gr.huadit.Helpers.XMLFileReader;
import gr.huadit.Loggers.ConsoleLogger;
import gr.huadit.LoggerLevel;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Logger log = new ConsoleLogger();
        XMLFileReader reader = new XMLFileReader();
        String fileName = args[0];
        try {
            log.print( "Reading file!", LoggerLevel.DEBUG);
            Path startingDir = Paths.get(System.getProperty("user.home"));
            String pattern = args[0];
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
