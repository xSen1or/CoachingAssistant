package gr.huadit.Classes;

import java.time.Duration;

public class AthleteCard {
    private String ActivityName;
    private String Id;

    private double TotalDistance;
    private double AveragePace;
    private double AverageHeartRate;
    private Duration duration;
    private ProgressCalculator prgcl =  new ProgressCalculator();

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