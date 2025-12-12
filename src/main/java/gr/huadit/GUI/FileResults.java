package gr.huadit.GUI;

import gr.huadit.Classes.ActivityCard;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Loggers.ConsoleLogger;

import javax.swing.*;
import java.time.Duration;

public class FileResults extends JFrame {
    private final ConsoleLogger log = new ConsoleLogger();

    public void displayGUIWindow(ActivityCard card) {
        log.print("displayGUIWindow on FileResultsGUI triggered", LoggerLevel.INFO);

        String formattedDuration = getString(card);

        JLabel durationLabel = new JLabel("Duration:");
        JLabel durationValue = new JLabel(formattedDuration);

        // Labels
        JLabel nameLabel = new JLabel("Name:");
        JLabel idLabel = new JLabel("ID:");
        JLabel distanceLabel = new JLabel("Distance:");
        JLabel paceLabel = new JLabel("Average Pace:");
        JLabel heartRateLabel = new JLabel("Heart Rate:");

        // Values
        JLabel name = new JLabel(card.getActivityName());
        JLabel id = new JLabel(card.getId());
        JLabel totalDistance = new JLabel(card.getTotalDistance() + " km");
        JLabel averagePace = new JLabel(card.getAveragePace() + " min/km");
        JLabel averageHeartRate = new JLabel(card.getAverageHeartRate() + " bpm");
        JLabel duration = new JLabel(formattedDuration);

        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal group: 2 columns
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel)
                                .addComponent(idLabel)
                                .addComponent(distanceLabel)
                                .addComponent(paceLabel)
                                .addComponent(heartRateLabel)
                                .addComponent(durationLabel)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(name)
                                .addComponent(id)
                                .addComponent(totalDistance)
                                .addComponent(averagePace)
                                .addComponent(averageHeartRate)
                                .addComponent(duration)
                        )
        );

        // Vertical group: each row is a parallel group
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(name)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(idLabel)
                                .addComponent(id)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(distanceLabel)
                                .addComponent(totalDistance)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(paceLabel)
                                .addComponent(averagePace)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(heartRateLabel)
                                .addComponent(averageHeartRate)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(durationLabel)
                                .addComponent(duration)
                        )
        );

        add(mainPanel);
        pack(); // Fit the window size
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static String getString(ActivityCard card) {
        Duration duration = card.getDuration(); // your Duration object

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

// Format as HH:MM:SS or MM:SS if hours = 0
        String formattedDuration;
        if (hours > 0) {
            formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            formattedDuration = String.format("%02d:%02d", minutes, seconds);
        }
        return formattedDuration;
    }

}
