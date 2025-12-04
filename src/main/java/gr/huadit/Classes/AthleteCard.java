package gr.huadit.Classes;

import java.time.Duration;

public class AthleteCard {
    private final String ActivityName;
    private final String  Id;

    private final double TotalDistance;
    private final double AveragePace;
    private final double AverageHeartRate;
    private final Duration duration;
//    private final ProgressCalculator prgcl =  new ProgressCalculator(); Will be used to calculate the calories etc.

    public AthleteCard(String activityName, String id, double totalDistance, double averagePace, double averageHeartRate, Duration duration) {
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
    }
}