package gr.huadit.ButtonListeners;

import gr.huadit.DTO.Activity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddActivityListener implements ActionListener {
    private final JTextField activityNameField;
    private final JTextField idField;
    private final JTextField totalDistanceField;
    private final JTextField averagePaceField;
    private final JTextField averageHeartRateField;
    private final JTextField durationField;

    // Constructor
    public AddActivityListener(JTextField activityNameField,
                               JTextField idField,
                               JTextField totalDistanceField,
                               JTextField averagePaceField,
                               JTextField averageHeartRateField,
                               JTextField durationField)
    {
        this.activityNameField = activityNameField;
        this.idField = idField;
        this.totalDistanceField = totalDistanceField;
        this.averagePaceField = averagePaceField;
        this.averageHeartRateField = averageHeartRateField;
        this.durationField = durationField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JTextField field : new JTextField[]{activityNameField, idField, totalDistanceField, averagePaceField, averageHeartRateField, durationField}) {
            if (field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter all fields");
                return;
            }
        }
    }

}
