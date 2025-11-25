package gr.huadit.Helpers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import gr.huadit.LoggerLevel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileInputStream;
import gr.huadit.Logger;
public class XMLFileReader {
        public void readFile(String fileName, Logger logger) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new FileInputStream(fileName));
                logger.send(LoggerLevel.DEBUG, "Parsed File!");

                NodeList activityList = doc.getElementsByTagName("Activity");
                for (int i = 0; i < activityList.getLength(); i++) {
                    Element activityElement = (Element) activityList.item(i);
                    String sport = activityElement.getAttribute("Sport");
                    System.out.println("Sport: " + sport);
                    NodeList trackPoints = activityElement.getElementsByTagName("Trackpoint");

                    for (int j = 0; j < trackPoints.getLength(); j++) {
                        Element e = (Element) trackPoints.item(j);
                        String distance = getNodeValue(e.getElementsByTagName("DistanceMeters"), logger);
                        System.out.println("Distance: " + distance);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.send(LoggerLevel.ERROR, e.getMessage());
            }
        }
    public static String getNodeValue(NodeList n, Logger logger) {
        if (n == null) {
            n.item(0);
        }
        return n.item(0).getChildNodes().item(0).getNodeValue();
    }
}
