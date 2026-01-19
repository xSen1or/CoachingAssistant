package gr.huadit.Interfaces;

public interface ProgressCalculator {

    double calculatePace(long totalSeconds, double distanceMeters);

    double getCeff(double avgHr, double mhr);

    double calculateBMR(String method, String gender, double weightKg, double heightCm, int ageYears);

}
