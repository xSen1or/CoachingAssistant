package gr.huadit.Classes;

import java.util.ArrayList;
import java.util.List;

public class HeartRateZoneAnalysis {

    // Initialize attributes.
    private final int age;
    private final double weight;
    private final List<Integer> bpmValues;

    // BPM Zones
    private static final double[] zoneMin = {0.50, 0.60, 0.70, 0.80, 0.90};
    private static final double[] zoneMax = {0.60, 0.70, 0.80, 0.90, 1.00};

    private static final double[] ceff = {0.07, 0.10, 0.13, 0.16, 0.20};

    // Constructor
    public HeartRateZoneAnalysis(int age, double weight) {
        this.age = age;
        this.weight = weight;
        this.bpmValues = new ArrayList<>();
    }

    // adder
    public void addBpmValue(int bpm) {
        bpmValues.add(bpm);
    }

    // Get the MHR (obviously)
    public int calculateMHR() {
        return 220 - age;
    }

    public int getZone(int heartRate) {
        int mhr = calculateMHR();

        for (int i = 0; i < 5; i++) {
            if (heartRate >= zoneMin[i] * mhr && heartRate < zoneMax[i] * mhr) {
                return i;
            }
        }
        return -1;
    }


    public int[] calculateMinutesPerZone() {
        int[] secondsPerZone = new int[5];

        for (int bpm : bpmValues) {
            int zone = getZone(bpm);
            if (zone != -1) {
                secondsPerZone[zone]++;
            }
        }

        int[] minutesPerZone = new int[5];
        for (int i = 0; i < 5; i++) {
            minutesPerZone[i] = secondsPerZone[i] / 60;
        }

        return minutesPerZone;
    }

    public double calculateCalories(int[] minutesPerZone) {
        double calories = 0;

        for (int i = 0; i < 5; i++) {
            calories += minutesPerZone[i] * ceff[i] * weight;
        }
        return calories;
    }
}
