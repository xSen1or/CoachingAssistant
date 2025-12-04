package gr.huadit.Helpers;

import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Find;
import gr.huadit.Interfaces.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathParser {
    private  Find.Finder finder;
    private String[] args;
    private int index;
    private Logger log;

    public PathParser( String[] args, int index, Logger log) {
        this.args = args;
        this.index = index;
        this.log = log;
    }

    public void handleXMLInput() {
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
        log.print("DISCLAIMER: Calorie Calculation is not available in terminal mode cause of missing arguments. Please use the GUI panel to calculate it.", LoggerLevel.INFO);

    }



}
