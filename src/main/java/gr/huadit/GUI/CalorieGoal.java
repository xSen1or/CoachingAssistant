package gr.huadit.GUI;

import gr.huadit.Holders.AppState;

import javax.swing.*;
import java.awt.*;

public class CalorieGoal {

    public CalorieGoal() {
    }

    public void show() {
        JFrame frame = new JFrame("Στόχος Θερμίδων");
        frame.setSize(350, 250);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel goalLabel = new JLabel("Ημερήσιος στόχος:");
        JTextField goalField = new JTextField();

        JLabel consumedLabel = new JLabel("Καταναλωθείσες:");
        JLabel consumedValue = new JLabel("0");

        JLabel remainingLabel = new JLabel("Απομένουν:");
        JLabel remainingValue = new JLabel("-");

        JLabel statusLabel = new JLabel("Κατάσταση:");
        JLabel statusValue = new JLabel("-");

        JButton calculateButton = new JButton("Υπολογισμός");

        calculateButton.addActionListener(e -> {
            AppState.dailyGoal = Integer.parseInt(goalField.getText());

            int remaining = AppState.dailyGoal - AppState.todayConsumed;
            consumedValue.setText(String.valueOf(AppState.todayConsumed));
            remainingValue.setText(String.valueOf(Math.max(0, remaining)));

            statusValue.setText(remaining <= 0 ? "Επιτεύχθηκε ✅" : "Δεν επιτεύχθηκε ❌");
        });

        panel.add(goalLabel);
        panel.add(goalField);
        panel.add(consumedLabel);
        panel.add(consumedValue);
        panel.add(remainingLabel);
        panel.add(remainingValue);
        panel.add(statusLabel);
        panel.add(statusValue);
        panel.add(new JLabel());
        panel.add(calculateButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
