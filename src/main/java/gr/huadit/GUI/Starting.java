package gr.huadit.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gr.huadit.Controllers.StartingPageListener;
import gr.huadit.DTO.CurrentUser;
// super class


public class Starting extends JFrame {
    public void displayPage() {
        setTitle("Fitness Tracker - Home");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        String username = (CurrentUser.currentUser != null) ? CurrentUser.currentUser.getString("name") : System.getProperty("user.home");
        JLabel helloLabel = new JLabel("Hello, " + username);
        helloLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(helloLabel, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);


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
        btnSelectFiles.setActionCommand("SELECT_FILES"); // CMD -> switch -> ....
        btnSelectFiles.addActionListener(new StartingPageListener());

        // Button 2: Add new activity
        JButton btnAddActivity = new JButton("Add New Activity");
        btnAddActivity.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnAddActivity);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // ActionListener
        btnAddActivity.setActionCommand("ADD_ACTIVITY");
        btnAddActivity.addActionListener(new StartingPageListener());

        // Button 3: Enter user info and calorie calculation method
        JButton btnUserInfo = new JButton("Enter User Info & Calorie Method");
        btnUserInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnUserInfo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // ActionListener
        btnUserInfo.setActionCommand("USER_INFO");
        btnUserInfo.addActionListener(new StartingPageListener());

        // Button 4: Enter daily calorie goal
        JButton btnCalorieGoal = new JButton("Set Daily Calorie Goal & Track Progress");
        btnCalorieGoal.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnCalorieGoal);

        // ActionListener
        btnCalorieGoal.setActionCommand("CALORIE_GOAL");
        btnCalorieGoal.addActionListener(new StartingPageListener());


        // Button 5: Show Selected Files
        JButton showSelectedFiles = new JButton("Show Selected Files");
        showSelectedFiles.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(showSelectedFiles);

        // ActionListener
        showSelectedFiles.setActionCommand("DISPLAY_SELECTED_FILES");
        showSelectedFiles.addActionListener(new StartingPageListener());

        add(panel);
        setVisible(true);
    }

}
