package gr.huadit.GUI;

import gr.huadit.ButtonListeners.HomePageButtonListener;

import javax.swing.*;
import java.awt.*;

public class HomePageGUI extends JFrame {
    public HomePageGUI() {
        displayPage();
    }

    public void displayPage() {
        setTitle("Fitness Tracker - Home");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Title label
        JLabel title = new JLabel("Fitness Tracker Home");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // spacing

        // Button 1: Select TCX files and show statistics
        JButton btnSelectFiles = new JButton("Select .tcx Files & Show Stats");
        btnSelectFiles.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnSelectFiles);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // ActionListener
        btnSelectFiles.setActionCommand("SELECT_FILES");
        btnSelectFiles.addActionListener(new HomePageButtonListener());

        // Button 2: Add new activity
        JButton btnAddActivity = new JButton("Add New Activity");
        btnAddActivity.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnAddActivity);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // ActionListener
        btnAddActivity.setActionCommand("ADD_ACTIVITY");
        btnAddActivity.addActionListener(new HomePageButtonListener());

        // Button 3: Enter user info & calorie calculation method
        JButton btnUserInfo = new JButton("Enter User Info & Calorie Method");
        btnUserInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnUserInfo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // ActionListener
        btnUserInfo.setActionCommand("USER_INFO");
        btnUserInfo.addActionListener(new HomePageButtonListener());

        // Button 4: Enter daily calorie goal
        JButton btnCalorieGoal = new JButton("Set Daily Calorie Goal & Track Progress");
        btnCalorieGoal.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnCalorieGoal);

        // ActionListener
        btnCalorieGoal.setActionCommand("CALORIE_GOAL");
        btnCalorieGoal.addActionListener(new HomePageButtonListener());


        add(panel);
        setVisible(true);
    }
}
