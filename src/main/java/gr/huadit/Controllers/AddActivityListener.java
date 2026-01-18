package gr.huadit.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gr.huadit.Classes.ActivityCard;
import gr.huadit.JSONHandler.JSONFileReader;
import gr.huadit.JSONHandler.JSONFileWriter;

public class AddActivityListener implements ActionListener {
    private final JTextField activityNameField;
    private final JTextField idField;
    private final JTextField totalDistanceField;
    private final JTextField averagePaceField;
    private final JTextField averageHeartRateField;
    private final JTextField durationField;

    // Constructor
    public AddActivityListener(JTextField activityNameField, JTextField idField, JTextField totalDistanceField,
            JTextField averagePaceField, JTextField averageHeartRateField, JTextField durationField) {
        this.activityNameField = activityNameField;
        this.idField = idField;
        this.totalDistanceField = totalDistanceField;
        this.averagePaceField = averagePaceField;
        this.averageHeartRateField = averageHeartRateField;
        this.durationField = durationField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource(); // pressed button 
        JDialog srcFrame = (JDialog) SwingUtilities.getWindowAncestor(src); // source window.

        for (JTextField field : new JTextField[] { activityNameField, idField, totalDistanceField, averagePaceField,
   averageHeartRateField, durationField }) { // loop through the fields and check if any of them are blank. Not checking this could result to a Fatal Error.
            if (field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(srcFrame, "Please enter all fields"); // error pop up message.
                return;
            }
            srcFrame.dispose(); // dispose of the window.
        }


        try {
            long minutes = Long.parseLong(durationField.getText().trim());
            ActivityCard card = new ActivityCard(activityNameField.getText(), idField.getText(), Double.parseDouble(totalDistanceField.getText()), Double.parseDouble(averagePaceField.getText()), Double.parseDouble(averageHeartRateField.getText()), Duration.ofMinutes(minutes));
            JSONFileWriter writer = new JSONFileWriter(card);
            JSONFileReader reader = new JSONFileReader();
            if (reader.contains(card.getActivityName())) {
                JOptionPane.showMessageDialog(srcFrame, "Filename already exists");
                return;
            }
            writer.writeProfileToFile();
            JOptionPane.showMessageDialog(srcFrame, "Activity added successfully!");
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(srcFrame, exc); // error pop up message
        }
    }
}