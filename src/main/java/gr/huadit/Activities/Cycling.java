package gr.huadit.Activities;

import gr.huadit.Interfaces.Activity;

public class Cycling  implements Activity {


    private double time;
    private double longitude;
    private double latitude;
    private double altitude;
    private double heartrate;

    


    public Cycling(double time, double longitude, double latitude, double altitude, double heartrate) {
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.heartrate = heartrate;
    }

    @Override
    public String getSportName() {
        return "Cycling";
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public double getTime() {
        return this.time;
    }

    @Override
    public double getLongitude() {
        return this.longitude;
    
    }

    @Override
    public double getLatitude() {
        return this.latitude;
    }

    @Override
    public double getAltitude() {
        return this.altitude;
    }

    @Override
    public double getHeartRate() {
        return this.heartrate;
    }
    
}
