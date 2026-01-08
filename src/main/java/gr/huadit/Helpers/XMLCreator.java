package gr.huadit.Helpers;

import java.io.File;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLCreator {

    // Helper to add elements
    private void addElement(Document doc, Element parent, String name, String value) {
        Element element = doc.createElement(name);
        element.setTextContent(value);
        parent.appendChild(element);
    }

    public void createXML(String activityName, String id, String totalDistance, String averagePace,
                          String averageHeartRate, String duration, JDialog parent) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Root element for TCX
            Element root = document.createElement("TrainingCenterDatabase");
            root.setAttribute("xmlns", "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2");
            document.appendChild(root);

            // Activity element
            Element activity = document.createElement("Activity");
            activity.setAttribute("Sport", "Running");
            root.appendChild(activity);

            addElement(document, activity, "Id", id);
            Element lap = document.createElement("Lap");
            lap.setAttribute("StartTime", id); // you could use a timestamp here
            activity.appendChild(lap);

            addElement(document, lap, "TotalTimeSeconds", duration);
            addElement(document, lap, "DistanceMeters", totalDistance);
            addElement(document, lap, "AverageHeartRateBpm", averageHeartRate);
            addElement(document, lap, "AverageSpeed", averagePace);

            // Write to TCX file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            String fileName = "activity_" + id + ".tcx";
            transformer.transform(
                    new DOMSource(document),
                    new StreamResult(new File(fileName)));

            System.out.println("TCX created: " + fileName);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent, ex);
        }
    }
}
