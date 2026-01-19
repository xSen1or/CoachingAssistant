package gr.huadit.Interfaces;


public interface XMLReader {
    String GARMIN_NS = "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2";

    void read(String fileName, Logger logger);

    default void read(String[] files, Logger logger) {
        for (String file : files) {
            read(file, logger);
        }
    }
}
