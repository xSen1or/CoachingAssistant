package gr.huadit.Helpers;
import gr.huadit.Find;
import gr.huadit.GUI.HomePageGUI;
import gr.huadit.Loggers.ConsoleLogger;
import gr.huadit.Enums.LoggerLevel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ArgumentHandler {
    private final ConsoleLogger log = new ConsoleLogger();
    private final String[] args;
    private int index = 0;

    public ArgumentHandler(String[] args) {
        this.args = args;
    }

    public void debugIndexOfArguments() {
        for (int i = 0; i < args.length; i++) {
            log.print("Argument " + i + " is: " + args[i], LoggerLevel.DEBUG);
        }
    }

    public void flag() {
        if (args[0].equals("-term")) {
            this.getWeight();
            this.getFiles();
        } else if (args[0].equals("-gui")) {
            System.out.println("GUI!");
            new HomePageGUI();
        } else {
            log.print("Usage:  java -cp target/CoachingAssistant-1.0-SNAPSHOT.jar gr.huadit.Main <run-type> [-w weight] <filename> ", LoggerLevel.INFO);
            System.exit(1);
        }
    }

    public boolean isEmpty() {
        if (args.length == 0) {
            log.print("No arguments provided", LoggerLevel.FATAL);
            log.print("Usage:  java -cp target/CoachingAssistant-1.0-SNAPSHOT.jar gr.huadit.Main <run-type> [-w weight] <filename> ", LoggerLevel.INFO);
            return true;
        }
        return false;
    }

    public void getFiles() {

        // Prevent crash when calling -gui (no arguments to read)
        if (args.length <= index) {
            return;
        }
        System.out.println(this.index);
        XMLSingleFileReader reader = new XMLSingleFileReader();
        XMLMultipleFileReader multiReader = new XMLMultipleFileReader();
        String pattern = args[index];
        Path startingDir = Paths.get(System.getProperty("user.home")); // entering the starting directory
        try {
            if (args.length - index > 1) { // More than one file AFTER the index
                Find.Finder finder; // we create the finder with the first file -> arg[0]
                String[] filePaths =  new String[args.length]; // creating this inside the if statement because it is useless if there is just one file

                for (int i = index; i < args.length; i++) { // we start the loop from i = 1 because the first
                    finder = new Find.Finder(args[i]);
                    Files.walkFileTree(startingDir, finder);
                    filePaths[i] = finder.getPath();  // copy the filenames from the arguments to the files[] array.
                    log.print("File Name:: " + args[i], LoggerLevel.DEBUG);
                    System.out.println(filePaths[i]);
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
    public void getWeight() {
        index = 1;
        if (args.length > 2 && args[index].equals("-w")) {
            log.print("Weight Argument Found: " + args[index + 1], LoggerLevel.DEBUG);
            index = 3;
        }
    }


}