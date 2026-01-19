package gr.huadit.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import gr.huadit.Controllers.AddActivityListener;
import gr.huadit.Interfaces.GraphicUserInterface;

public class AddActivity extends JDialog implements GraphicUserInterface {

   public AddActivity(JFrame parent) {
        super(parent, "Add Activity", true);
    }

    public void displayGUIWindow() {
        setTitle("Activity Details");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Activity Name:"), gbc);
        gbc.gridx = 1;
        JTextField activityNameField = new JTextField(15);
        panel.add(activityNameField, gbc);
  
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        JTextField idField = new JTextField(15);
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Total Distance:(km)"), gbc);
        JTextField totalDistanceField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(totalDistanceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Average Pace:(km/h)"), gbc);
        gbc.gridx = 1;
        JTextField averagePaceField = new JTextField(15);
        panel.add(averagePaceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Average Heart Rate:(bp/s)"), gbc);
        gbc.gridx = 1;
        JTextField averageHeartRateField = new JTextField(15);
        panel.add(averageHeartRateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Duration:(minutes)"), gbc);
        gbc.gridx = 1;
        JTextField durationField = new JTextField(15);
        panel.add(durationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new AddActivityListener(activityNameField, idField, totalDistanceField, averagePaceField, averageHeartRateField, durationField));
        panel.add(saveButton, gbc);
        add(panel);
        setVisible(true);
   }
}