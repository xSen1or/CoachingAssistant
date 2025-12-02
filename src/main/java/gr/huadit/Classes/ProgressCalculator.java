package gr.huadit.Classes;

public class ProgressCalculator {

    /*
        double h = 120 heart rate
        double w = 75 weight kg
        double a = 30 age
        double t = 30 time in minutes
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

    public double calculatePace(long totalSeconds, double distanceMeters) {
            double distanceKm = distanceMeters / 1000.0;
            return totalSeconds / distanceKm;
    }


    public double calculateCFunction(double[] m, double[] ceff, double w) {
        if (m.length != ceff.length) {
            throw new IllegalArgumentException("Arrays m and ceff must have the same length.");
        }

        double C = 0.0;

        for (int i = 0; i < m.length; i++) {
            C += m[i] * ceff[i] * w;
        }
        return C;
    }
}
