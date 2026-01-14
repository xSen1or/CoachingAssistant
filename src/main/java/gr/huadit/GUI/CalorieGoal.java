package gr.huadit.GUI;

import gr.huadit.Holders.AppState;

import javax.swing.*;
import java.awt.*;

public class CalorieGoal extends JDialog {
    JFrame parent;

    public CalorieGoal(JFrame parent) {
        super(parent, "CalorieGoal", true);
        displayWindowGUI();
    }

    public void displayWindowGUI() {
        parent = new JFrame("Στόχος Θερμίδων");
        parent.setSize(350, 250);
        parent.setLocationRelativeTo(null);

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

        // Set the given value as the daily goal in the AppState Holder.
        calculateButton.addActionListener(e -> {
            AppState.dailyGoal = Integer.parseInt(goalField.getText());

            // Calculate the remaining calories.
            int remaining = AppState.dailyGoal - AppState.todayConsumed;
            // Update the values.
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

        parent.add(panel);
        parent.setVisible(true);
    }

    public JFrame getParent() {
        return parent;
    }

}
