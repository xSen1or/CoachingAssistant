package gr.huadit.DTO;

import gr.huadit.Interfaces.DTO;

import java.time.Duration;

/*
    DTO Class.
*/


public record Activity(String name, String id, double totalDistance, double averageHeartRate, Duration duration) implements DTO {
    @Override 
    public String toString() {
        return "Name: " + name + " ID: " + id + " Distance: " + totalDistance +  " Average Heart Rate: " + averageHeartRate + " Duration: " + duration; 
    }
}