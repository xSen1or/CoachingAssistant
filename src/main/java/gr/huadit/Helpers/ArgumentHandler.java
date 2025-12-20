package gr.huadit.Helpers;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.GUI.Starting;
import gr.huadit.Loggers.ConsoleLogger;


public class ArgumentHandler {
    private final ConsoleLogger log = new ConsoleLogger();
    private final String[] args;
    private int index = 0;
    public static int flag = -1;

    public ArgumentHandler(String[] args) {
        this.args = args;
    }

    public void debugIndexOfArguments() {
        for (int i = 0; i < args.length; i++) {
            log.print("Argument " + i + " is: " + args[i], LoggerLevel.DEBUG);
        }
    }


    /*
    if flag = 0 -> terminal mode
    if flag = 1 -> gui mode
     */

    public void flag() {
        if (args[0].equals("-term")) {
            log.print("DISCLAIMER: Take notice that the applications response time depends on the storage of the current device.!", LoggerLevel.INFO);
            flag = 1;
            this.getWeight(); //gets the weight argument
            this.getFiles(); //gets the files
        } else if (args[0].equals("-gui")) {
            flag = 0; // flag = 0 indicates that we are in GUI mode
            Starting homePage = new Starting(); // open the starting page
            homePage.displayPage();
        } else {
            log.print("Usage:  java -jar CoachingAssistant-1.0-SNAPSHOT.jar -<run-type> [-w weight] <filename> ", LoggerLevel.INFO);
            System.exit(1); // in case of missing arguments, throw a help message and terminate the program
        }
    }   

    public boolean isEmpty() {
        if (args.length == 1 && args[0].equals("-term") || args.length < 4  && args[0].equals("-term") && args[1].equals("-w" )){
            log.print("No arguments (or not enough) provided for Terminal Mode.", LoggerLevel.FATAL);
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
        if (!args[index].trim().toLowerCase().endsWith(".tcx")) throw new IllegalArgumentException("Invalid file type!");
        PathParser pParser = new PathParser(args, index, log);
        pParser.handleXMLInput();

    }
    public void getWeight() {
        index = 1;
        if (args.length > 2 && args[index].equals("-w")) {
            log.print("Weight Argument Found: " + args[index + 1], LoggerLevel.DEBUG);
            index = 3;
        }
    }


}