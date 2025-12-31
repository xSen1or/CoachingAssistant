package gr.huadit.GUI;

import gr.huadit.DTO.AppState;

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

        JLabel goalLabel = new JLabel("Daily Target:");
        JTextField goalField = new JTextField();

        JLabel consumedLabel = new JLabel("Consumed:");
        JLabel consumedValue = new JLabel("0");

        JLabel remainingLabel = new JLabel("Remaining:");
        JLabel remainingValue = new JLabel("-");

        JLabel statusLabel = new JLabel("Status:");
        JLabel statusValue = new JLabel("-");

        JButton calculateButton = new JButton("Counter");

        calculateButton.addActionListener(e -> {
            AppState.dailyGoal = Integer.parseInt(goalField.getText());

            int remaining = AppState.dailyGoal - AppState.todayConsumed;
            consumedValue.setText(String.valueOf(AppState.todayConsumed));
            remainingValue.setText(String.valueOf(Math.max(0, remaining)));

            statusValue.setText(remaining <= 0 ? "Succeeded ✅" : "Unsuccessful ❌");
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
