package gr.huadit.Helpers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import gr.huadit.LoggerLevel;
import gr.huadit.Interfaces.XMLReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.time.format.DateTimeParseException;

import gr.huadit.Interfaces.Logger;

public class XMLFileReader implements XMLReader {
    private static final String GARMIN_NS =  "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2";
    ProgressCalculator progressCalculator = new ProgressCalculator();
    public void readSingleFile(String fileName, Logger logger) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            try {
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new FileInputStream(fileName));
                logger.print( "Parsed File!", LoggerLevel.DEBUG);
                NodeList activityList = doc.getElementsByTagNameNS(GARMIN_NS, "Activity");
                for (int i = 0; i < activityList.getLength(); i++) {
                    Element activityElement = (Element) activityList.item(i);

                    String sport = activityElement.getAttribute("Sport");
                    String Id = doc.getElementsByTagNameNS(GARMIN_NS, "Id").item(0).getTextContent();
                    NodeList trackPoints = activityElement.getElementsByTagNameNS(GARMIN_NS, "Trackpoint");

                    String[] timings = new String[trackPoints.getLength()];
                    TrackPointResults results = TrackPointResults.processTrackPoints(trackPoints, timings);

                    System.out.println("SECONDS: " +results.dur.toSeconds());
                    logger.print("Activity: "+ sport,  LoggerLevel.INFO);
                    logger.print("ID: " + Id,  LoggerLevel.INFO);
                    logger.print("Total Distance: " + results.totalDistance, LoggerLevel.INFO);
                    logger.print(progressCalculator.calculatePace(results.dur.toSeconds(), results.totalDistance), LoggerLevel.INFO);
                    logger.print("Average BPM: " + results.averageBPM, LoggerLevel.INFO);
                    logger.print("Duration: " + results.dur.toHours() + ":" + results.dur.toMinutes() + ":" + results.dur.toSeconds(), LoggerLevel.INFO);
//                    for (int j = 0; j < trackPoints.getLength(); j++) {
//                        Element tp = (Element) trackPoints.item(j);
//
//                        String distanceStr = getNodeValue(
//                                tp.getElementsByTagNameNS(GARMIN_NS, "DistanceMeters"),
//                                logger
//                        );
//                        String bpmStr = getNodeValue(
//                                tp.getElementsByTagNameNS(GARMIN_NS, "AltitudeMeters"),
//                                logger
//                        );
//
//                        String timeStr = getNodeValue(
//                                tp.getElementsByTagNameNS(GARMIN_NS, "Time"), logger
//                        );
//
//                        if (timeStr != null && !timeStr.isEmpty()) {
//                            if (timings.length > j) {
//                                timings[j] = timeStr;
//                            } else {
//                                logger.print("timings array is null or too small for index -> " + j, LoggerLevel.FATAL);
//                                System.exit(1);
//                            }
//                        }
//                        if (bpmStr != null) {
//                            try {
//                                double bpm = Double.parseDouble(bpmStr);
//                                countBPM++;
//                                sumBPM += bpm;
//                            } catch (NumberFormatException exc) {
//                                logger.print("Invalid BPM value at index " + j + ":" + bpmStr, LoggerLevel.ERROR);
//                            }
//                        }
//                        if (distanceStr != null) { // Total Distance
//                            try {
//                                double distance = Double.parseDouble(distanceStr);
//                                if (distance > totalDistance) {
//                                    totalDistance = distance;
//                                }
//                            } catch (NumberFormatException exc) {
//                                logger.print("Invalid distance at index " + j + ":" + distanceStr, LoggerLevel.ERROR);
//                            }
//                        }
//                    }


//                    logger.print("Total Distance: " + totalDistance + " meters", LoggerLevel.DEBUG);
//                    logger.print("Average Heart Beat: " + sumBPM / countBPM, LoggerLevel.DEBUG);
//                    logger.print("Timer: " + timeFormatter(timings, trackPoints.getLength()), LoggerLevel.DEBUG );
                }
            }  catch (DateTimeParseException exc) {
                logger.print("DateTimeParseException " + exc.getMessage(), LoggerLevel.ERROR);
            } catch (Exception e) {
                logger.print("Exc e: " + e.getMessage(), LoggerLevel.ERROR);
            }
        }

    public void readMultipleFiles(String[] fileNames, Logger logger) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            for (int i = 0; i < fileNames.length; i++) {
                Document doc = dBuilder.parse(new FileInputStream(fileNames[i]));

                NodeList activityList = doc.getElementsByTagNameNS(GARMIN_NS, "Activity");
                for (int j = 0; i < activityList.getLength(); j++) {
                    Element activityElement = (Element) activityList.item(j);

                    String sport = activityElement.getAttribute("Sport");
                    String Id = doc.getElementsByTagNameNS(GARMIN_NS, "Id").item(0).getTextContent();
                    logger.print("ID: " + Id,  LoggerLevel.DEBUG);
                    NodeList trackPoints = activityElement.getElementsByTagNameNS(GARMIN_NS, "Trackpoint");


                    double totalDistance = 0;
                    double sumBPM = 0, countBPM = 0;

                    String[] timings = new String[trackPoints.getLength()];

                    for (int k = 0; j < trackPoints.getLength(); k++) {
                        Element tp = (Element) trackPoints.item(k);
                        String distanceStr = getNodeValue(tp.getElementsByTagNameNS(GARMIN_NS, "DistanceMeters"), logger);
                        String bpmStr = getNodeValue(tp.getElementsByTagNameNS(GARMIN_NS, "AltitudeMeters"), logger);
                        String timeStr = getNodeValue(tp.getElementsByTagNameNS(GARMIN_NS, "Time"), logger);
                        if (timeStr != null && !timeStr.isEmpty()) {
                            if (timings.length > j) {
                                timings[j] = timeStr;
                            } else {
                                logger.print("timings array is null or too small for index -> " + j, LoggerLevel.FATAL);
                                System.exit(1);
                            }
                        }
                        if (bpmStr != null) {
                            try {
                                double bpm = Double.parseDouble(bpmStr);
                                countBPM++;
                                sumBPM += bpm;
                            } catch (NumberFormatException exc) {
                                logger.print("Invalid BPM value at index " + j + ":" + bpmStr, LoggerLevel.ERROR);
                            }
                        }
                        if (distanceStr != null) { // Total Distance
                            try {
                                double distance = Double.parseDouble(distanceStr);
                                if (distance > totalDistance) {
                                    totalDistance = distance;
                                }
                            } catch (NumberFormatException exc) {
                                logger.print("Invalid distance at index " + j + ":" + distanceStr, LoggerLevel.ERROR);
                            }
                        }
                    }
                }
            }; // end of for-loop [i]
        } catch (Exception e ) {
            logger.print(e.getMessage(), LoggerLevel.ERROR);
        }

    }

    public static String getNodeValue(NodeList n, Logger logger) {
        if (n == null || n.getLength() == 0) {
            logger.print("NodeList empty!", LoggerLevel.WARNING);
            return null;
        }
        Node node = n.item(0);
        if (node == null) {
            logger.print("Node is null!", LoggerLevel.WARNING);
            return null;
        }
        Node child = node.getFirstChild();
        if (child == null) {
            logger.print("Node has no value!", LoggerLevel.WARNING);
            return null;
        }
        return child.getNodeValue();
    }
}