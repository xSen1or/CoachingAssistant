package gr.huadit.GUI;

import gr.huadit.ButtonListeners.ProfileButtonListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileGUI extends JFrame {
    private final List<String> filenames;
    public ProfileGUI(List<String> filenames) {
        this.filenames = filenames;
        displayGUIWindow();
    }

    public void displayGUIWindow() {

        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ---- Labels + Text Fields ----
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();

        JLabel genderLabel = new JLabel("Gender:");
        JTextField genderField = new JTextField();

        JLabel heightLabel = new JLabel("Height:");
        JTextField heightField = new JTextField();

        JLabel weightLabel = new JLabel("Weight:");
        JTextField weightField = new JTextField();

        JLabel listLabel = new JLabel("File:");
        List<String> filesGetName = new ArrayList<>();

        for (String str : filenames) {
            String trimmed = str.contains("/") ?
                    str.substring(str.lastIndexOf("/") + 1) : str;
            filesGetName.add(trimmed);
        }

        JList<String> list = new JList<>(filesGetName.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200, 80));

        // ---- Buttons ----
        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("SAVE");
        saveButton.addActionListener(new ProfileButtonListener());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("CANCEL");
        cancelButton.addActionListener(new ProfileButtonListener());

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // ---- Horizontal Layout ----
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel)
                                        .addComponent(ageLabel)
                                        .addComponent(genderLabel)
                                        .addComponent(heightLabel)
                                        .addComponent(weightLabel)
                                        .addComponent(listLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameField)
                                        .addComponent(ageField)
                                        .addComponent(genderField)
                                        .addComponent(heightField)
                                        .addComponent(weightField)
                                        .addComponent(scrollPane)))

                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addComponent(cancelButton))
        );

        // ---- Vertical Layout ----
        layout.setVerticalGroup(
                layout.createSequentialGroup()

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(nameField))

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(ageLabel)
                                .addComponent(ageField))

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(genderLabel)
                                .addComponent(genderField))

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(heightLabel)
                                .addComponent(heightField))

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(weightLabel)
                                .addComponent(weightField))

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(listLabel)
                                .addComponent(scrollPane))

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(saveButton)
                                .addComponent(cancelButton))
        );

        add(mainPanel);

        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
