package gr.huadit.Helpers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import gr.huadit.LoggerLevel;
import gr.huadit.XMLReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileInputStream;
import gr.huadit.Logger;

public class XMLFileReader implements XMLReader {
    private static final String GARMIN_NS =
            "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2";

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
                    System.out.println("Sport: " + sport);

                    NodeList trackPoints = activityElement.getElementsByTagNameNS(GARMIN_NS, "Trackpoint");

                    double totalDistance = 0;

                    for (int j = 0; j < trackPoints.getLength(); j++) {
                        Element tp = (Element) trackPoints.item(j);

                        String distanceStr = getNodeValue(
                                tp.getElementsByTagNameNS(GARMIN_NS, "DistanceMeters"),
                                logger
                        );

                        if (distanceStr != null) {
                            double distance = Double.parseDouble(distanceStr);
                            if (distance > totalDistance) {    // keep the largest value
                                totalDistance = distance;
                            }
                        }
                    }

                    System.out.println("Total Distance: " + totalDistance + " meters");
                }
            } catch (Exception e) {
                e.printStackTrace();
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
