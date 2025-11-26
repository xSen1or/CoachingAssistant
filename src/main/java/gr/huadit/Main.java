package gr.huadit;

import gr.huadit.Helpers.XMLFileReader;
//import gr.huadit.Loggers.ConsoleLogger;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        if (filePath == null) throw new  IllegalArgumentException("Please provide a valid file path");
//        Logger log = new ConsoleLogger();
//        XMLFileReader xmlFileReader = new XMLFileReader();
//        xmlFileReader.readFile(filePath, log);
}
}
