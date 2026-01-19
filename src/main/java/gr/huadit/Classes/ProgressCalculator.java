package gr.huadit.Classes;

public class ProgressCalculator {

    public double caloriesMen(double h, double w, double a, double t) {
        return -55.0969
                + (0.6309 * h)
                + (0.1966 * w)
                + (0.2017 * a)
                * (t / 4.184);
    }

    public double caloriesWomen(double h, double w, double a, double t) {
        return -20.4022
                + (0.4472 * h)
                + (0.1263 * w)
                + (0.074 * a)
                * (t / 4.184);
    }

    public double calculatePace(long totalSeconds, double distanceMeters) {
            double distanceKm = distanceMeters / 1000.0;
            return totalSeconds / distanceKm;
    }

    public double getCeff(double avgHr, double mhr) {
        double percentage = (avgHr / mhr);
        double ceff = 0.0;

        if (percentage >= 0.50 && percentage < 0.60) ceff = 0.07;      // Zone 1
        else if (percentage >= 0.60 && percentage < 0.70) ceff = 0.10; // Zone 2
        else if (percentage >= 0.70 && percentage < 0.80) ceff = 0.13; // Zone 3
        else if (percentage >= 0.80 && percentage < 0.90) ceff = 0.16; // Zone 4
        else if (percentage >= 0.90) ceff = 0.20;                      // Zone 5
        else ceff = 0.05; // Low intensity fallback
        return ceff;
    }

    public double calculateBMR(String method, String gender, double weightKg, double heightCm, int ageYears) {
        double bmr = 0.0;
        boolean isMale = gender.equalsIgnoreCase("MALE");

        if (method.equals("Mifflin-St Jeor")) {
            double baseCalculation = (10 * weightKg) + (6.25 * heightCm) - (5 * ageYears);

            if (isMale) {
                bmr = baseCalculation + 5;
            } else {
                bmr = baseCalculation - 161;
            }

        } else if (method.equals("Harris-Benedict")) {
            if (isMale) {
                bmr = 66.4730 + (13.7516 * weightKg) + (5.0033 * heightCm) - (6.7550 * ageYears);
            } else {
                bmr = 655.0955 + (9.5634 * weightKg) + (1.8496 * heightCm) - (4.6756 * ageYears);
            }
        }

        return bmr;
    }


}
