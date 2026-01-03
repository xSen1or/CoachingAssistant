package gr.huadit.GUI;

import javax.swing.*;
import java.awt.*;

public class AddActivity extends JFrame{

    public AddActivity() {
        displayWindowGUI();
    }

    public void displayWindowGUI() {
        setTitle("Activity Details");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(6, 2, 10, 10));

        // Labels & TextFields
        add(new JLabel("Activity Name:"));
        JTextField activityNameField = new JTextField();
        add(activityNameField);

        add(new JLabel("ID:"));
        JTextField idField = new JTextField();
        add(idField);

        add(new JLabel("Total Distance:"));
        JTextField totalDistanceField = new JTextField();
        add(totalDistanceField);

        add(new JLabel("Average Pace:"));
        JTextField averagePaceField = new JTextField();
        add(averagePaceField);

        add(new JLabel("Average Heart Rate:"));
        JTextField averageHeartRateField = new JTextField();
        add(averageHeartRateField);

        add(new JLabel("Duration:"));
        JTextField durationField = new JTextField();
        add(durationField);
        JButton saveButton = new JButton("Save");
        add(saveButton);
        add(new JLabel());

        setVisible(true);


    }
}
