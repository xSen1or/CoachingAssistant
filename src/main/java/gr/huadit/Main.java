package gr.huadit;

import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Helpers.ArgumentHandler;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;


 class Main {
    public static void main(String[] args) {
        Logger log = new ConsoleLogger();
        long start = System.nanoTime();
        String os = System.getProperty("os.name");
        log.print("Running on " +  os, LoggerLevel.INFO);

        if (args.length == 0) throw new IllegalArgumentException("No arguments provided");
        ArgumentHandler argumentHandler = new ArgumentHandler(args);
        if (argumentHandler.isEmpty()) System.exit(1);
        argumentHandler.flag();


        long end = System.nanoTime();
        double seconds = (end - start) / 1_000_000_000.0;
        log.print("Execution time: " + seconds + " seconds", LoggerLevel.PERFORMANCE);
    }
}