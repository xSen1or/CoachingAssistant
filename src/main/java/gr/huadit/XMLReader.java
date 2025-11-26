package gr.huadit;

import org.w3c.dom.NodeList;

public interface XMLReader {
    public static final String GARMIN_NS =
            "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2";

    public void readFile(String fileName, Logger logger);

    public static String getNodeValue(NodeList n, Logger logger) {
        return null;
    }

}
