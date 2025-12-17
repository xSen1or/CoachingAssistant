package gr.huadit.Classes;

import gr.huadit.Activities.Cycling;
import gr.huadit.Activities.Running;
import gr.huadit.Activities.Swimming;
import gr.huadit.Activities.Walking;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.GUI.FileResults;
import gr.huadit.Interfaces.Activity;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import java.time.Duration;

import static gr.huadit.Helpers.ArgumentHandler.flag;

/*
    Usage: print activity information

 */



public class ActivityCard {
    private final Logger log = new ConsoleLogger();
    private final String ActivityName;
    private final String  Id;

    private final double TotalDistance;
    private final double AveragePace;
    private final double AverageHeartRate;
    private final Duration duration;
    private String pace;

    public ActivityCard(String activityName, String id, double totalDistance, double averagePace, double averageHeartRate, Duration duration) {
        ActivityName = activityName;
        Id = id;
        TotalDistance = totalDistance;
        AveragePace = averagePace;
        AverageHeartRate = averageHeartRate;
        this.duration = duration;
    }

    public void printAthleteCard() {
        long minutes = (long) (this.AveragePace / 60);
        long seconds = (long) (this.AveragePace % 60);
        String formattedDuration = String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutes() % 60, duration.toSeconds() % 60);

        System.out.println(
                "Activity: " + this.ActivityName + "\n" +
                        "ID: " + this.Id + "\n" +
                        "Total Distance: " + this.TotalDistance + "\n" +
                        "Pace: " + minutes + ":" + seconds + "min/km" + "\n" +
                        "Average BPM: " + this.AverageHeartRate + "\n" +
                        "Duration: " + formattedDuration
        );

        this.pace = minutes + ":" + seconds + "min/km";

        if (flag == 0) { // if a flag is for gui -> run gui
            FileResults fileResultsGUI = new FileResults();
            fileResultsGUI.displayGUIWindow(this);
        }
    }

    public void saveActivity() {
        switch (this.ActivityName) {
            case "Running" -> {
                Activity running = new Running(this.Id, this.duration, this.pace, new ProgressCalculator().caloriesMen(1, 1, 1, 1), this.TotalDistance);
                log.print("OBJECT CREATED: " + running, LoggerLevel.DEBUG);
            }
            case "Swimming" -> {
                Activity swimming = new Swimming(this.Id, this.duration, this.pace, new ProgressCalculator().caloriesMen(1, 1, 1, 1), this.TotalDistance);
                log.print("OBJECT CREATED: " + swimming, LoggerLevel.DEBUG);
            }
            case "Walking" -> {
                Activity walking = new Walking(this.Id, this.duration, this.pace, new ProgressCalculator().caloriesMen(1, 1, 1, 1), this.TotalDistance);
                log.print("OBJECT CREATED: " + walking, LoggerLevel.DEBUG);
            }
            case "Cycling" -> {
                Activity cycling = new Cycling(this.Id, this.duration, this.pace, new ProgressCalculator().caloriesMen(1, 1, 1, 1), this.TotalDistance);
                log.print("OBJECT CREATED: " + cycling, LoggerLevel.DEBUG);
            }
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