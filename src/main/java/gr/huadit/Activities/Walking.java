package gr.huadit.Activities;

import gr.huadit.Interfaces.Activity;

import java.time.Duration;

public class Walking implements Activity {

    private final String name = "Walking";
    private final String id;
    private final Duration duration;
    private final String pace;
    private final double calories;
    private final double heartRate;

    public Walking(String id, Duration duration, String pace, double calories, double heartRate) {
        this.id = id;
        this.duration = duration;
        this.pace = pace;
        this.calories = calories;
        this.heartRate = heartRate;
    }

    @Override
    public String getSportName() {
        return this.name;
    }

    @Override
    public String getId() {
        return this.id;
    }



    @Override
    public Duration getDuration() {
        return this.duration;
    }

    @Override
    public String getPace() {
        return this.pace;
    }

    @Override
    public double getCalories() {
        return this.calories;
    }

    @Override
    public double getHeartRate() {
        return this.heartRate;
    }

    @Override
    public String toString() {
        return this.name + " " + this.id + " " + this.duration + " " + this.pace + " " + this.calories + " " + this.heartRate;
    }
}

