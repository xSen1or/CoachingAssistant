package gr.huadit.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gr.huadit.DTO.TotalFiles;
import gr.huadit.Helpers.XMLCreator;

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
            XMLCreator builder = new XMLCreator();
            builder.createXML(activityNameField.getText(), idField.getText(), totalDistanceField.getText(), averagePaceField.getText(), averageHeartRateField.getText(), durationField.getText(), srcFrame);
            JOptionPane.showMessageDialog(srcFrame, "Activity added to the Selected Files page!");
            TotalFiles.results.add("activity_" + idField.getText() + ".tcx");
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(srcFrame, exc); // error pop up message
        }
    }
}