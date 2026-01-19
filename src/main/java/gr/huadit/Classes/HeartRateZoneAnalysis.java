package gr.huadit.Classes;

import gr.huadit.Holders.Constants;
import gr.huadit.Holders.CurrentUser;

import java.util.ArrayList;
import java.util.List;

public class HeartRateZoneAnalysis {

    // Initialize attributes.
    private int age;


    // BPM Zones
    private static final double[] zoneMin = {0.50, 0.60, 0.70, 0.80, 0.90};
    private static final double[] zoneMax = {0.60, 0.70, 0.80, 0.90, 1.00};

    private static final double[] ceff = {0.07, 0.10, 0.13, 0.16, 0.20};

    // Constructor
    public HeartRateZoneAnalysis() {
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Get the MHR (obviously)
    public int calculateMHR() {
        return Constants.MHR_CALCULATION_FACTOR - age;
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


    public double getZoneCalories(ActivityCard card, ProgressCalculator prgCal) {
        if (CurrentUser.currentUser == null || card == null || card.getDuration() == null) return 0.0;

        int age = CurrentUser.currentUser.optInt("age", 25);
        double weight = CurrentUser.currentUser.optDouble("weight", 0.0);
        double mhr = 220.0 - age;
        double avgHr = card.getAverageHeartRate();

        if (mhr == 0) return 0.0;

        double ceff = prgCal.getCeff(avgHr, mhr);

        // Duration in minutes
        double minutes = card.getDuration().toSeconds() / 60.0;

        return minutes * ceff * weight;
    }
}
