package gr.huadit.Interfaces;

import org.w3c.dom.NodeList;

public interface XMLReader {
    public static final String GARMIN_NS =
            "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2";

    public void readSingleFile(String fileName, Logger logger);

    public void readMultipleFiles(String[] fileNames, Logger logger);

    public static String getNodeValue(NodeList n, Logger logger) {
        return "";
    }

}
