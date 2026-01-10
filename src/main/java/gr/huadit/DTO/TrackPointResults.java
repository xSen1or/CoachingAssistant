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

public record TrackPointResults(
        double totalDistance,
        int countBPM,
        double averageBPM,
        Duration dur
) {

    public static TrackPointResults processTrackPoints(NodeList trackPoints, String[] timings) {

        Logger log = new ConsoleLogger();

        double totalDistance = 0.0;
        int countBPM = 0;
        double sumBPM = 0.0;

        /* ---------------- Trackpoint loop ---------------- */
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
                    sumBPM += Double.parseDouble(bpmStr);
                    countBPM++;
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

        /* ---------------- Time calculation ---------------- */

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        OffsetDateTime firstDT = null;
        OffsetDateTime lastDT  = null;

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

        Duration dur;

        if (firstDT != null && lastDT != null) {
            dur = Duration.between(firstDT, lastDT);
        } else {
            log.print("Duration could not be calculated (missing timestamps)",
                    LoggerLevel.WARNING);
            dur = Duration.ZERO;
        }

        double averageBPM = countBPM > 0 ? sumBPM / countBPM : 0.0;

        return new TrackPointResults(
                totalDistance,
                countBPM,
                averageBPM,
                dur
        );
    }
}
