package gr.huadit.GUI;
import gr.huadit.Holders.AppState;

import javax.swing.*;
import java.awt.*;

public class CalorieInput {
    public void show(JFrame parent) {
        JFrame frame = new JFrame("Καταχώρηση Θερμίδων");
        frame.setSize(300, 200);

        int x = parent.getX() + parent.getWidth() + 10; // 10px κενό
        int y = parent.getY();
        frame.setLocation(x, y);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel caloriesLabel = new JLabel("Θερμίδες:");
        JTextField caloriesField = new JTextField();

        JButton addButton = new JButton("Προσθήκη");

        addButton.addActionListener(e -> {
            int calories = Integer.parseInt(caloriesField.getText());
            AppState.todayConsumed += calories;
            caloriesField.setText("");
        });

        panel.add(caloriesLabel);
        panel.add(caloriesField);
        panel.add(new JLabel());
        panel.add(addButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
