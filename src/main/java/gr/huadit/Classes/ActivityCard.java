package gr.huadit.Classes;


import gr.huadit.DTO.Activity;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.GUI.FileResults;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import java.time.Duration;

import static gr.huadit.Helpers.ArgumentHandler.flag;

/*
    Usage: print activity information
    Could convert it to record, but we lose the private attributes
 */
public class  ActivityCard implements gr.huadit.Interfaces.ActivityCard {
    private final String ActivityName;
    private final String  Id;
    private final Logger logger = new ConsoleLogger();

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

        Activity activity = new Activity(this.getActivityName(), this.getId(), this.getTotalDistance(), this.getAverageHeartRate(), this.getDuration());

        logger.print(activity.toString(), LoggerLevel.INFO);

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