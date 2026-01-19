package gr.huadit.Helpers;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.GUI.Starting;
import gr.huadit.Interfaces.ArgumentParser;
import gr.huadit.Loggers.ConsoleLogger;


public class ArgumentHandler implements ArgumentParser {
    private final ConsoleLogger log = new ConsoleLogger();
    private final String[] args;
    private int index = 0;
    public static int flag = -1;

    // constructor
    public ArgumentHandler(String[] args) {
        this.args = args;
    }

    // for debugging 
    public void debugIndexOfArguments() {
        for (int i = 0; i < args.length; i++) {
            log.print("Argument " + i + " is: " + args[i], LoggerLevel.DEBUG);
        }
    }

    /*
    if flag = 0 -> terminal mode
    if flag = 1 -> gui mode
     */

    public void usage() {
        log.print("Usage:  java -jar CoachingAssistant-1.0-SNAPSHOT.jar -<run-type> [-w weight] <filename> ", LoggerLevel.INFO);
    }

    public void flag() {
        if (args[0].equals("-term")) {
            // logging 
            log.print("DISCLAIMER: Take notice that the applications response time depends on the storage of the current device.!", LoggerLevel.INFO);
            // passes the correct flag value 
            flag = 1;
            // calls the two functions that retrieve the neccessery data.
            this.getWeight(); //gets the weight argument
            this.getFiles(); //gets the files
        } else if (args[0].equals("-gui")) {
            flag = 0; // flag = 0 indicates that we are in GUI mode
            Starting homePage = new Starting(); // open the starting page
            homePage.displayGUIWindow();
        } else {
            log.print("Usage:  java -jar CoachingAssistant-1.0-SNAPSHOT.jar -<run-type> [-w weight] <filename> ", LoggerLevel.INFO);
            System.exit(1); // in case of missing arguments, throw a help message and terminate the program
        }
    }   

    // a function that returns a message in case the user gave invalid or empty info 
    public boolean isEmpty() {

        if (args.length == 1 && args[0].equals("-term") || args.length < 4  && args[0].equals("-term") && args[1].equals("-w" )){
            log.print("No arguments (or not enough) provided for Terminal Mode.", LoggerLevel.FATAL);
            usage();

            return true;

        }

        return false;

    }

    public void getFiles() {
        // Prevent crash when calling -gui (no arguments to read)
        if (args.length <= index) {
            return;
        }
        if (!args[index].trim().toLowerCase().endsWith(".tcx")) throw new IllegalArgumentException("Invalid file type!");
        PathParser pParser = new PathParser(args, index, log);
        pParser.handleXMLInput();

    }
    
    // get the weight argument.
    public void getWeight() {
        index = 1;
        if (args.length > 2 && args[index].equals("-w")) {
            log.print("Weight Argument Found: " + args[index + 1], LoggerLevel.DEBUG);
            index = 3;
        }
    }
}