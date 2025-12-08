package gr.huadit.Interfaces;

import java.time.Duration;

public interface Activity {

    String getSportName();
    String getId();
    Duration getDuration();
    String getPace();
    double getCalories();
    double getHeartRate();
}
