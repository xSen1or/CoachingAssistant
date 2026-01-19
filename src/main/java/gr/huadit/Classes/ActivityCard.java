package gr.huadit.Classes;


import gr.huadit.GUI.FileResults;
import gr.huadit.Holders.Constants;

import java.time.Duration;

import static gr.huadit.Helpers.ArgumentHandler.flag;

/*
    Usage: print activity information
    Could convert it to record, but we lose the private attributes
 */
public class ActivityCard implements gr.huadit.Interfaces.ActivityCard {
    private final String ActivityName;
    private final String  Id;

    private final double TotalDistance;
    private final double AveragePace;
    private final double AverageHeartRate;
    private final Duration duration;


    // Constructor
    public ActivityCard (String activityName, String id, double totalDistance, double averagePace, double averageHeartRate, Duration duration) {
        ActivityName = activityName;
        Id = id;
        TotalDistance = totalDistance;
        AveragePace = averagePace;
        AverageHeartRate = averageHeartRate;
        this.duration = duration;
    }

    // Call this method to PRINT the .tcx file contents.
    public void printAthleteCard() {
        long minutes = (long) (this.AveragePace / Constants.CONVERT_TO_MINUTES_NUMBER);
        long seconds = (long) (this.AveragePace % Constants.CONVERT_TO_MINUTES_NUMBER);
        String formattedDuration = String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutes() % 60, duration.toSeconds() % 60);

        // Print on the console.
        System.out.println(
                "Activity: " + this.ActivityName + "\n" +
                        "ID: " + this.Id + "\n" +
                        "Total Distance: " + this.TotalDistance + "\n" +
                        "Pace: " + minutes + ":" + seconds + "min/km" + "\n" +
                        "Average BPM: " + this.AverageHeartRate + "\n" +
                        "Duration: " + formattedDuration
        );


        // If the flag is Zero run the file results for GUI.
        if (flag == 0) {
            FileResults fileResultsGUI = new FileResults(null);
            fileResultsGUI.displayGUIWindow(this);
        }
    }


    public String getActivityName() {
        return ActivityName;
    }

    public String getId() {
        return Id;
    }

    public double getTotalDistance() {
        return TotalDistance;
    }

    public double getAveragePace() {
        return AveragePace;
    }

    public double getAverageHeartRate() {
        return AverageHeartRate;
    }

    public Duration getDuration() {
        return duration;
    }


}