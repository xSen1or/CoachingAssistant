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

    // made this a function just to keep it clean.
    private void addElement(Document doc, Element parent, String name, String value) {
        Element element = doc.createElement(name); // create the new element.
        element.setTextContent(value);
        parent.appendChild(element);
    }

    public void createXML(String activityName, String id, String totalDistance, String averagePace,String averageHeartRate, String duration, JDialog parent) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Root element
            Element root = document.createElement("activity");
            document.appendChild(root);

            // Elements
            addElement(document, root, "id", id);
            addElement(document, root, "name", activityName);
            addElement(document, root, "totalDistanceKm", totalDistance);
            addElement(document, root, "averagePaceKmH", averagePace);
            addElement(document, root, "averageHeartRate", averageHeartRate);
            addElement(document, root, "durationMinutes", duration);

            // Write XML to file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            String fileName = "activity_" + id + ".xml";
            transformer.transform(
                    new DOMSource(document),
                    new StreamResult(new File(fileName)));

            System.out.println("XML created: " + fileName);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent, ex);
        }
    }

}
