package gr.huadit.DTO;

import java.time.Duration;




public record Activity(String name, String id, double totalDistance, double averagePace, double averageHeartRate, Duration duration) {
    @Override 
    public String toString() {
        return "Name: " + name + " ID: " + id + " Distance: " + totalDistance + " Average Pace: " + averagePace + " Average Heart Rate: " + averageHeartRate + " Duration: " + duration; 
    }
}