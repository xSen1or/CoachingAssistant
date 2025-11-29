package gr.huadit.Helpers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import gr.huadit.Classes.AthleteCard;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Interfaces.XMLReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.time.format.DateTimeParseException;

import gr.huadit.Interfaces.Logger;

public class XMLSingleFileReader implements XMLReader {
    private static final String GARMIN_NS =  "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2";
    ProgressCalculator progressCalculator = new ProgressCalculator();

    // Read Function
    public void read(String fileName, Logger logger) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);

            try {
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new FileInputStream(fileName));
                NodeList activityList = doc.getElementsByTagNameNS(GARMIN_NS, "Activity");
                for (int i = 0; i < activityList.getLength(); i++) {
                    System.out.println(i);
                    Element activityElement = (Element) activityList.item(i);

                    String sport = activityElement.getAttribute("Sport");
                    String Id = doc.getElementsByTagNameNS(GARMIN_NS, "Id").item(0).getTextContent();
                    NodeList trackPoints = activityElement.getElementsByTagNameNS(GARMIN_NS, "Trackpoint");

                    String[] timings = new String[trackPoints.getLength()];
                    TrackPointResults results = TrackPointResults.processTrackPoints(trackPoints, timings);

                    AthleteCard athleteCard = new AthleteCard(sport, Id, results.totalDistance(), progressCalculator.calculatePace(results.dur().toSeconds(), results.totalDistance()), results.averageBPM(), results.dur());
                    athleteCard.printAthleteCard();
                }
            }  catch (DateTimeParseException exc) {
                logger.print("DateTimeParseException " + exc.getMessage(), LoggerLevel.ERROR);
            } catch (Exception e) {
                logger.print("Exc e: " + e.getMessage(), LoggerLevel.ERROR);
            }
        }

    // getNodeValue Function
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