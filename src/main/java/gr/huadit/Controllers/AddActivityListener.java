package gr.huadit.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gr.huadit.DTO.Activity;

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
        JButton src = (JButton) e.getSource();
        JDialog srcFrame = (JDialog) SwingUtilities.getWindowAncestor(src);

        for (JTextField field : new JTextField[] { activityNameField, idField, totalDistanceField, averagePaceField,
                averageHeartRateField, durationField }) {
            if (field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(srcFrame, "Please enter all fields");
                return;
            }
            


            srcFrame.dispose();
        }

        try {
            Activity activity = new Activity(
                    activityNameField.getText().trim(),
                    idField.getText().trim(),
                    Double.parseDouble(totalDistanceField.getText().trim()),
                    Double.parseDouble(averageHeartRateField.getText().trim()),
                    Duration.ofMinutes(Long.parseLong(durationField.getText().trim())));
                    JOptionPane.showMessageDialog(srcFrame, "Activity Added!");
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(srcFrame, exc);
        }
    }
}