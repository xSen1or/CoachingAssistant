package gr.huadit.GUI;

import javax.swing.*;

public class AddActivity {

    public void displayWindowGUI() {
        JLabel activityNameLabel = new JLabel("Activity Name:");
        JTextField activityNameField = new JTextField();

        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(activityNameLabel)
                                .addComponent(activityNameField)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        )

        );
    }
}
