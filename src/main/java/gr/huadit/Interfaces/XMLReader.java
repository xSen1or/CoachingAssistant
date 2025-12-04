package gr.huadit.Interfaces;

import org.w3c.dom.NodeList;

public interface XMLReader {
    public static final String GARMIN_NS = "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2";

    public void read(String fileName, Logger logger);
    default void read(String[] files, Logger logger) {
        for (String file : files) {
            read(file, logger);
        }
    };
    public static String getNodeValue(NodeList n, Logger logger) {
        return "";
    }

}
