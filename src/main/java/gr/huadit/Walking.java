package gr.huadit;

public class Walking implements Activity {


    private double time;
    private double longitude;
    private double latitude;
    private double altitude;
    private double heartrate;



    



    public Walking(double time, double longitude, double latitude, double altitude, double heartrate) {
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.heartrate = heartrate;
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
