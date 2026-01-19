package gr.huadit.Interfaces;

import java.time.Duration;

public interface ActivityCard {

    void printAthleteCard();

    String getActivityName();

    String getId();

    double getTotalDistance();

    double getAveragePace();

    double getAverageHeartRate();

    Duration getDuration();

}
