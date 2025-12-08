package gr.huadit.GUI;

import gr.huadit.ButtonListeners.ProfileButtonListener;

import javax.swing.*;

public class ProfileGUI extends JFrame {

    // Text fields stored as INSTANCE VARIABLES
    private JTextField nameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField heightField;
    private JTextField weightField;

    public ProfileGUI() {
        displayGUIWindow();
    }

    public void displayGUIWindow() {

        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ---- Labels + Text Fields ----
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();

        JLabel genderLabel = new JLabel("Gender:");
        genderField = new JTextField();

        JLabel heightLabel = new JLabel("Height: (cm)");
        heightField = new JTextField();

        JLabel weightLabel = new JLabel("Weight: (kg)");
        weightField = new JTextField();

        // ---- Buttons ----
        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("SAVE");
        saveButton.addActionListener(new ProfileButtonListener(this));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("CANCEL");
        cancelButton.addActionListener(new ProfileButtonListener(this));

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

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

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(saveButton)
                                .addComponent(cancelButton))
        );

        // ---- Horizontal Layout ----
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel)
                                .addComponent(ageLabel)
                                .addComponent(genderLabel)
                                .addComponent(heightLabel)
                                .addComponent(weightLabel)
                                .addComponent(saveButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameField)
                                .addComponent(ageField)
                                .addComponent(genderField)
                                .addComponent(heightField)
                                .addComponent(weightField)
                                .addComponent(cancelButton))
        );

        add(mainPanel);

        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // ---- Methods that the ButtonListener will call ----
    public String getNameInput() { return nameField.getText(); }
    public String getAgeInput() { return ageField.getText(); }
    public String getGenderInput() { return genderField.getText(); }
    public String getHeightInput() { return heightField.getText(); }
    public String getWeightInput() { return weightField.getText(); }
}
