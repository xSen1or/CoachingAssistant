package gr.huadit.Controllers;

// Event Listeners
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gr.huadit.Classes.ActivityCard;
import java.time.Duration;

// JSON Handlers
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.JSONHandler.JSONFileReader;
import gr.huadit.JSONHandler.JSONFileWriter;
import gr.huadit.Loggers.ConsoleLogger;

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

    // Event Listener
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource(); // pressed button 
        JDialog srcFrame = (JDialog) SwingUtilities.getWindowAncestor(src); // source window.

        for (JTextField field : new JTextField[] { activityNameField, idField, totalDistanceField, averagePaceField, averageHeartRateField, durationField }) { // loop through the fields and check if any of them are blank. Not checking this could result to a Fatal Error.
            if (field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(srcFrame, "Please enter all fields"); // error pop up message.
                return;
            }
            srcFrame.dispose(); // dispose the window.
        }


        try {
            // Converting the string input to long
            long minutes = Long.parseLong(durationField.getText().trim());
            // Create the object.
            ActivityCard card = new ActivityCard(activityNameField.getText(), idField.getText(), Double.parseDouble(totalDistanceField.getText()), Double.parseDouble(averagePaceField.getText()), Double.parseDouble(averageHeartRateField.getText()), Duration.ofMinutes(minutes));
            // Pass it to the JSONFileWriter.
            JSONFileWriter writer = new JSONFileWriter(card);
            // Check if the JSON Object already exists.
            JSONFileReader reader = new JSONFileReader();
            if (reader.contains(card.getActivityName())) {
                JOptionPane.showMessageDialog(srcFrame, "Filename already exists");
                return;
            }
            // If it doesn't, write it.
            writer.writeProfileToFile();
            // Pop up window message.
            JOptionPane.showMessageDialog(srcFrame, "Activity added successfully!");
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(srcFrame, "An ultra rare error occurred."); // error pop up message
            ConsoleLogger log = new ConsoleLogger();
            log.print(exc.getMessage(), LoggerLevel.ERROR);
        }
    }
}