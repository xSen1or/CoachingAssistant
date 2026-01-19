package gr.huadit.Classes;

import gr.huadit.Holders.Constants;
import gr.huadit.Holders.CurrentUser;


public class HeartRateZoneAnalysis {


    // Constructor
    public HeartRateZoneAnalysis() {}



    // Get the calorie zone.
    public double getZoneCalories(ActivityCard card, ProgressCalculator prgCal) {

        // If the user hasn't created a profile prevent crash.
        if (CurrentUser.currentUser == null || card == null || card.getDuration() == null) return 0.0;

        // get the values
        int age = CurrentUser.currentUser.optInt("age", 25);
        double weight = CurrentUser.currentUser.optDouble("weight", 0.0);
        double mhr = Constants.MHR_CALCULATION_FACTOR - age;
        double avgHr = card.getAverageHeartRate();

        if (mhr == 0) return 0.0;

        double ceff = prgCal.getCeff(avgHr, mhr);

        // Duration in minutes
        double minutes = card.getDuration().toSeconds() / 60.0;

        return minutes * ceff * weight;
    }
}
