package gr.huadit.Helpers;

import gr.huadit.Interfaces.Logger;
import gr.huadit.Enums.LoggerLevel;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import gr.huadit.Loggers.ConsoleLogger;
import static gr.huadit.Helpers.XMLSingleFileReader.getNodeValue;
import static gr.huadit.Interfaces.XMLReader.GARMIN_NS;

public record TrackPointResults(double totalDistance, int countBPM, double averageBPM, Duration dur) {


    public static TrackPointResults processTrackPoints(NodeList trackPoints, String[] timings) {
        Logger log = new ConsoleLogger();
        double totalDistance = 0.0;
        int countBPM = 0;
        double sumBPM = 0.0;

        for (int j = 0; j < trackPoints.getLength(); j++) {
            Element tp = (Element) trackPoints.item(j);

            String distanceStr = getNodeValue(tp.getElementsByTagNameNS(GARMIN_NS, "DistanceMeters"), null);
            String timeStr = getNodeValue(tp.getElementsByTagNameNS(GARMIN_NS, "Time"), null);

            Element hrElement = (Element) tp.getElementsByTagNameNS(GARMIN_NS, "HeartRateBpm").item(0);
            String bpmStr = hrElement != null ? getNodeValue(hrElement.getElementsByTagName("Value"), null) : null;

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
        return new TrackPointResults(totalDistance, countBPM, averageBPM, Duration.between(firstDT, lastDT));
    }

}


