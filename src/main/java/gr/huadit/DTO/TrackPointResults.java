package gr.huadit.DTO;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import gr.huadit.Enums.LoggerLevel;
import static gr.huadit.Helpers.XMLSingleFileReader.getNodeValue;
import gr.huadit.Interfaces.Logger;
import static gr.huadit.Interfaces.XMLReader.GARMIN_NS;
import gr.huadit.Loggers.ConsoleLogger;

/*
    DTO Class
    Runs a loop for each trackpoint.        
    Calculates the: BPM, DURATION, DISTANCE
    And returns it as an object.
 */



public record TrackPointResults(double totalDistance, int countBPM, double averageBPM, Duration dur) {
    public static TrackPointResults processTrackPoints(NodeList trackPoints, String[] timings) {
        Logger log = new ConsoleLogger(); // logger
        
        // initialize variables 
        double totalDistance = 0.0;
        int countBPM = 0;
        double sumBPM = 0.0;


        // loop for length of the trackpoints 
        for (int j = 0; j < trackPoints.getLength(); j++) {
            Element tp = (Element) trackPoints.item(j); // get the item.

            // get the specific node values 
            String distanceStr = getNodeValue(tp.getElementsByTagNameNS(GARMIN_NS, "DistanceMeters"), null);
            String timeStr = getNodeValue(tp.getElementsByTagNameNS(GARMIN_NS, "Time"), null);
            Element hrElement = (Element) tp.getElementsByTagNameNS(GARMIN_NS, "HeartRateBpm").item(0);
            String bpmStr = hrElement != null ? getNodeValue(hrElement.getElementsByTagName("Value"), null) : null;

            // do some checks 
            if (timeStr != null && !timeStr.isEmpty()) {
                if (timings != null && timings.length > j) {
                    timings[j] = timeStr;
                }
            }

            if (bpmStr != null && !bpmStr.isEmpty()) {
                try {
                    double bpm = Double.parseDouble(bpmStr);
                    countBPM++;
                    sumBPM += bpm;
                } catch (NumberFormatException ignored) {
                }
            }

            if (distanceStr != null && !distanceStr.isEmpty()) {
                try {
                    double distance = Double.parseDouble(distanceStr);
                    if (distance > totalDistance) {
                        totalDistance = distance;
                    }
                } catch (NumberFormatException ignored) {
                    // Ignore invalid number silently
                    log.print("Ignored Invalid Number", LoggerLevel.WARNING);
                }
            }
        }

        // create a time formater.
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        OffsetDateTime firstDT = null, lastDT = null;
        try {
            assert timings != null;
            for (String t : timings) {
                if (t != null) {
                    if (firstDT == null) firstDT = OffsetDateTime.parse(t, inputFormatter);
                    lastDT = OffsetDateTime.parse(t, inputFormatter);
                }
            }
        } catch (NullPointerException exc) {
            log.print("Null pointer exception | " + exc.getMessage(), LoggerLevel.WARNING);
        }
        double averageBPM = countBPM > 0 ? sumBPM / countBPM : 0.0;
        assert firstDT != null;
        // return the data.
        return new TrackPointResults(totalDistance, countBPM, averageBPM, Duration.between(firstDT, lastDT));
    }

}


