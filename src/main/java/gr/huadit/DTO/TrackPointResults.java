package gr.huadit.DTO;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import gr.huadit.Enums.LoggerLevel;
import static gr.huadit.Helpers.XMLSingleFileReader.getNodeValue;
import gr.huadit.Interfaces.Logger;
import static gr.huadit.Interfaces.XMLReader.GARMIN_NS;
import gr.huadit.Loggers.ConsoleLogger;

public record TrackPointResults(
        double totalDistance,
        int countBPM,
        double averageBPM,
        Duration dur,
        List<Integer> bpmValues
) {

    public static TrackPointResults processTrackPoints(NodeList trackPoints, String[] timings) {
        // Logger
        Logger log = new ConsoleLogger();

        // Initialize variables and list
        List<Integer> bpmValues = new ArrayList<>();
        double totalDistance = 0.0;
        int countBPM = 0;
        double sumBPM = 0.0;

        // Loop through the trackpoints and calculate the needed information.
        for (int j = 0; j < trackPoints.getLength(); j++) {
            Element tp = (Element) trackPoints.item(j);

            String distanceStr = getNodeValue(
                    tp.getElementsByTagNameNS(GARMIN_NS, "DistanceMeters"), null);

            String timeStr = getNodeValue(
                    tp.getElementsByTagNameNS(GARMIN_NS, "Time"), null);

            Element hrElement = (Element)
                    tp.getElementsByTagNameNS(GARMIN_NS, "HeartRateBpm").item(0);

            String bpmStr = hrElement != null
                    ? getNodeValue(hrElement.getElementsByTagName("Value"), null)
                    : null;

            if (timings != null && timeStr != null && j < timings.length) {
                timings[j] = timeStr;
            }

            if (bpmStr != null && !bpmStr.isEmpty()) {
                try {
                    int bpm = Integer.parseInt(bpmStr);
                    sumBPM += bpm;
                    countBPM++;
                    bpmValues.add(bpm);
                } catch (NumberFormatException ignored) {}
            }
            if (distanceStr != null && !distanceStr.isEmpty()) {
                try {
                    double distance = Double.parseDouble(distanceStr);
                    if (distance > totalDistance) {
                        totalDistance = distance;
                    }
                } catch (NumberFormatException e) {
                    log.print("Ignored invalid distance value", LoggerLevel.WARNING);
                }
            }
        }

        // Formatter for the Duration
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        // Offsets to calculate the Duration
        OffsetDateTime firstDT = null;
        OffsetDateTime lastDT  = null;

        // Pass values to the Offsets
        if (timings != null) {
            for (String t : timings) {
                if (t == null || t.isEmpty()) continue;
                try {
                    OffsetDateTime parsed = OffsetDateTime.parse(t, formatter);
                    if (firstDT == null) {
                        firstDT = parsed;
                    }
                    lastDT = parsed;
                } catch (Exception e) {
                    log.print("Invalid time format: " + t, LoggerLevel.WARNING);
                }
            }
        }

        // Initialize the duration
        Duration dur;

        // Try to calculate the total time between the two offsets or throw an error message and set the duration to ZERO.
        if (firstDT != null) {
            dur = Duration.between(firstDT, lastDT);
        } else {
            log.print("Duration could not be calculated (missing timestamps)", LoggerLevel.ERROR);
            dur = Duration.ZERO;
        }

        double averageBPM = countBPM > 0 ? sumBPM / countBPM : 0.0;

        // Return all the information using the current object.
        return new TrackPointResults(
                totalDistance,
                countBPM,
                averageBPM,
                dur,
                bpmValues
        );
    }
}
