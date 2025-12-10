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

    public void flag() {
        if (args[0].equals("-term")) {
            flag = 1;
            this.getWeight();
            this.getFiles();
        } else if (args[0].equals("-gui")) {
            flag = 0;
            System.out.println("GUI!");
            Starting homePage = new Starting();
            homePage.displayPage();
        } else {
            log.print("Usage:  java -jar CoachingAssistant-1.0-SNAPSHOT.jar -<run-type> [-w weight] <filename> ", LoggerLevel.INFO);
            System.exit(1);
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
        System.out.println(this.index);
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