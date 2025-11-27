package gr.huadit.Helpers;

public class ProgressCalculator {

    /*
        double h = 120;   // heart rate
        double w = 75;    // weight kg
        double a = 30;    // age
        double t = 30;    // time in minutes
    */
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

    public String calculatePace(long totalSeconds, double distanceMeters) {
            if (distanceMeters <= 0) return "Pace: N/A";

            double distanceKm = distanceMeters / 1000.0;
            double paceSecondsPerKm = totalSeconds / distanceKm;

            long minutes = (long) (paceSecondsPerKm / 60);
            long seconds = (long) (paceSecondsPerKm % 60);

            return String.format("Pace: %d:%02d min/km", minutes, seconds);
    }
}
