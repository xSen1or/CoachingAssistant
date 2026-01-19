package gr.huadit.Classes;

import gr.huadit.Holders.CurrentUser;

public class VO2Assessment {
    public VO2Assessment() {}

    public String getVO2Assessment(double vo2) {
        if (vo2 >= 50) return "Excellent";
        if (vo2 >= 40) return "Good";
        if (vo2 >= 30) return "Average";
        return "Poor";
    }

    public double[] getVO2Calculations(ActivityCard card) {
        // Returns array: [0] -> VO2 Max Value, [1] -> Calories
        if (CurrentUser.currentUser == null || card == null || card.getDuration() == null) return new double[]{0.0, 0.0};

        int age = CurrentUser.currentUser.optInt("age", 25);
        double weight = CurrentUser.currentUser.optDouble("weight", 0.0);
        double rhr = CurrentUser.currentUser.optDouble("restingHeartRate", 70.0); // Default to 70 if missing

        double mhr = 220.0 - age;

        // 1. Calculate VO2 Max
        double vo2Max = 15.3 * (mhr / rhr);

        // 2. Calculate Calories
        double minutes = card.getDuration().toSeconds() / 60.0;
        double vo2Calories = (vo2Max * weight * minutes) / 200.0;

        return new double[]{vo2Max, vo2Calories};
    }
}
