package gr.huadit.Helpers;

import gr.huadit.Interfaces.Logger;
import gr.huadit.Interfaces.XMLReader;
import gr.huadit.LoggerLevel;
import org.w3c.dom.NodeList;

public class XMLMultipleFileReader implements XMLReader {
    private XMLSingleFileReader reader = new XMLSingleFileReader();

    @Override
    public void read(String file, Logger log) {
        log.print("Reading File: " + file, LoggerLevel.INFO);
        reader.read(file, log);
    }

    @Override
    public void read(String[] fileName, Logger logger) {
        for (String file : fileName) {
            read(file, logger); // gets the read above cause of the parameters
        }
    }

    public static String getNodeValue(NodeList n, Logger logger) {
        return "";
    }
}
