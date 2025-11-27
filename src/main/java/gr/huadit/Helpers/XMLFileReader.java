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
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import gr.huadit.Interfaces.Logger;

public class XMLFileReader implements XMLReader {
    private static final String GARMIN_NS =
            "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2";

    public void timeFormatter(String arr[], int lastIndex) {
        // 2015-02-19T09:31:29.000Z
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH);
        LocalDate first = LocalDate.parse(arr[0], outputFormatter);
        LocalDate last =  LocalDate.parse(arr[arr.length - 1], outputFormatter);
        Duration dur =  Duration.between(first, last);

        System.out.println(dur);
    }

    public void readFile(String fileName, Logger logger) {
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
                    System.out.println("Sport: " + sport);
                    logger.print("ID: " + Id,  LoggerLevel.DEBUG);
                    NodeList trackPoints = activityElement.getElementsByTagNameNS(GARMIN_NS, "Trackpoint");


                    double totalDistance = 0;
                    double sumBPM = 0, countBPM = 0;
                    double totalTime = 0;

                    String timings[] = new String[trackPoints.getLength()];

                    for (int j = 0; j < trackPoints.getLength(); j++) {
                        Element tp = (Element) trackPoints.item(j);

                        String distanceStr = getNodeValue(
                                tp.getElementsByTagNameNS(GARMIN_NS, "DistanceMeters"),
                                logger
                        );
                        String bpmStr = getNodeValue(
                                tp.getElementsByTagNameNS(GARMIN_NS, "AltitudeMeters"),
                                logger
                        );

                        String timeStr = getNodeValue(
                                tp.getElementsByTagNameNS(GARMIN_NS, "Time"), logger
                        );

                        if (timeStr != null && !timeStr.isEmpty()) { // Total Time
                            timings[j] = timeStr;
                        }

                        if (bpmStr != null) { // Average Heart Rate
                            double bpm = Double.parseDouble(bpmStr);
                            countBPM++;
                            sumBPM += bpm;
                        }

                        if (distanceStr != null) { // Total Distance
                            double distance = Double.parseDouble(distanceStr);
                            if (distance > totalDistance) {
                                totalDistance = distance;
                            }
                        }
                    }


                    System.out.println("Total Distance: " + totalDistance + " meters");
                    logger.print("Total Distance: " + totalDistance + " meters", LoggerLevel.DEBUG);
                    logger.print("Average Heart Beat: " + sumBPM / countBPM, LoggerLevel.DEBUG);
                    timeFormatter(timings, trackPoints.getLength());
                }
            }  catch (DateTimeParseException exc) {
                logger.print("DateTimeParseException " + exc.getMessage(), LoggerLevel.WARNING);
            } catch (Exception e) {
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
