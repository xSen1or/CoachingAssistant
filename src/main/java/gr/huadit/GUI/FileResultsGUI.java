package gr.huadit.GUI;

import gr.huadit.Classes.ActivityCard;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Loggers.ConsoleLogger;

import javax.swing.*;
import java.awt.*;


public class FileResultsGUI extends JFrame {
    private final ConsoleLogger log = new ConsoleLogger();

    public void displayGUIWindow(ActivityCard card) {
        log.print("displayGUIWindow on FileResultsGUI triggered", LoggerLevel.INFO);
        JLabel name = new JLabel(card.getActivityName());
        JLabel id = new JLabel(card.getId());
        JLabel totalDistance = new JLabel(card.getTotalDistance() + " km");
        JLabel averagePace = new JLabel(card.getAveragePace() + " min/km");
        JLabel averageHeartRate = new JLabel(card.getAverageHeartRate() + " bpm");
        JLabel duration = new JLabel(card.getDuration().toString());

        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(
                layout.createSequentialGroup()

                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(name)
                        .addComponent(id)
                        .addComponent(totalDistance)
                        .addComponent(averagePace)
                            .addComponent(averageHeartRate)
                            .addComponent(duration)
                    )
        );

        add(mainPanel);
    }
}
