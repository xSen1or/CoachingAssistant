package gr.huadit.Interfaces;

import org.w3c.dom.NodeList;

public interface XMLReader {
    String GARMIN_NS = "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2";

    void read(String fileName, Logger logger);

    default void read(String[] files, Logger logger) {
        for (String file : files) {
            read(file, logger);
        }
    }

    static String getNodeValue(NodeList n, Logger logger) {
        return "";
    }

}
