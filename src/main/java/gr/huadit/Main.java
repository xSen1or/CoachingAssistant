package gr.huadit;

import gr.huadit.Helpers.XMLMultipleFileReader;
import gr.huadit.Helpers.XMLSingleFileReader;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        /*
        * Creating object for handling the XML Files and logs.
        *
        * */
        Logger log = new ConsoleLogger();
        XMLSingleFileReader reader = new XMLSingleFileReader();
        XMLMultipleFileReader multiReader = new XMLMultipleFileReader();


        if (args.length < 1) { // if the usage of the command is incorrect return a usage message.
            log.print("Usage:  java -cp target/CoachingAssistant-1.0-SNAPSHOT.jar gr.huadit.Main <filename> ", LoggerLevel.FATAL);
            System.exit(1);
        }

        log.print("Number of .tcx File Passed by User: " + args.length, LoggerLevel.DEBUG);
        String pattern = args[0];
        Path startingDir = Paths.get(System.getProperty("user.home"));
        try {
            if (args.length > 1) { // we have more than one file.
                log.print("Multiple Files", LoggerLevel.DEBUG);
                Find.Finder finder = null; // we create the finder with first file tha arg[0]
                String[] filePaths =  new String[args.length]; // creating this inside the if statement because it is useless if there is just one file
                for (int i = 0; i < args.length; i++) { // we start the loop from i = 1 because the first
                    finder = new Find.Finder(args[i]);
                    Files.walkFileTree(startingDir, finder);
                    filePaths[i] = finder.getPath();  // copy the filenames from the arguments to the files[] array.
                    log.print("File Name:: " + args[i], LoggerLevel.DEBUG);

                    multiReader.read(filePaths[i], log); // call the method of the XMLMultipleFileReader
                }
            } else { // we just have one
                // Pattern is the key file. Key file is the file we are looking for in the directory.
                log.print("Single File", LoggerLevel.DEBUG);
                Find.Finder finder = new Find.Finder(pattern);
                Files.walkFileTree(startingDir, finder);
                reader.read(finder.getPath(), log);
            }
        } catch (Exception e) {
            log.print( "File Not Found!\n" + e.getMessage(), LoggerLevel.ERROR);
            System.exit(1);
        }
    }
}
