package gr.huadit.Classes;

import gr.huadit.Holders.Constants;
import gr.huadit.Holders.CurrentUser;

public class VO2Assessment {
    public VO2Assessment() {}

    public String getVO2Assessment(double vo2) {
        if (vo2 >= Constants.VO2_ZONE_EXCELLENT) return "Excellent";
        if (vo2 >= Constants.VO2_ZONE_GOOD) return "Good";
        if (vo2 >= Constants.VO2_ZONE_AVERAGE) return "Average";
        return "Poor";
    }

    public double[] getVO2Calculations(ActivityCard card) {
        // Returns array: [0] -> VO2 Max Value, [1] -> Calories
        if (CurrentUser.currentUser == null || card == null || card.getDuration() == null) return new double[]{0.0, 0.0};

        int age = CurrentUser.currentUser.optInt("age");
        double weight = CurrentUser.currentUser.optDouble("weight", 0.0);
        double rhr = CurrentUser.currentUser.optDouble("restingHeartRate", 70.0); // Default to 70 if missing

        double mhr = Constants.MHR_CALCULATION_FACTOR - age;

        // 1. Calculate VO2 Max
        double vo2Max = Constants.VO2_MAX_CALCULATION_FACTOR * (mhr / rhr);

        // 2. Calculate Calories
        double minutes = card.getDuration().toSeconds() / Constants.CONVERT_TO_MINUTES_NUMBER;
        double vo2Calories = (vo2Max * weight * minutes) / Constants.VO2_BURNED_CALORIES_CONVERT;

        return new double[]{vo2Max, vo2Calories};
    }
}
