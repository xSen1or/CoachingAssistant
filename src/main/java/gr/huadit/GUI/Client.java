package gr.huadit.GUI;

import javax.swing.*;
import gr.huadit.Controllers.CancelButtonListener;
import gr.huadit.Controllers.ProfileButtonListener;
import gr.huadit.Interfaces.GraphicUserInterface;

import java.awt.*;

public class Client extends JDialog implements GraphicUserInterface {
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> calculationTypeComboBox;
    private JTextField heightField;
    private JTextField weightField;

    public Client(JFrame parent) {
        super(parent, "Client", true); // modal = true
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
        String[] genderOptions = {"MALE", "FEMALE"};
        genderComboBox = new JComboBox<>(genderOptions);

        JLabel heightLabel = new JLabel("Height: (cm)");
        heightField = new JTextField();

        JLabel weightLabel = new JLabel("Weight: (kg)");
        weightField = new JTextField();

        JLabel calculationLabel = new JLabel("Calculation Type:");
        String[] methodOptions = {"Mifflin-St Jeor", "Harris-Benedict"};
        calculationTypeComboBox = new JComboBox<>(methodOptions);

        // ---- Buttons ----
        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("SAVE");
        // Ensure this listener exists in your project, or comment it out to test the GUI
        saveButton.addActionListener(new ProfileButtonListener(this));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("CANCEL");
        // Ensure this listener exists in your project, or comment it out to test the GUI
        cancelButton.addActionListener(new CancelButtonListener());

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // ---- Vertical Layout (Rows) ----
        // This determines which components appear on the same Y-axis (Row)
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
                                .addComponent(genderComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(heightLabel)
                                .addComponent(heightField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(weightLabel)
                                .addComponent(weightField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(calculationLabel)       // Added Label here
                                .addComponent(calculationTypeComboBox)) // Added Combo here
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(saveButton)
                                .addComponent(cancelButton))
        );

        // ---- Horizontal Layout (Columns) ----
        // This determines which components are in the Left column vs Right column
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        // Column 1 (Labels + Save Button)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel)
                                .addComponent(ageLabel)
                                .addComponent(genderLabel)
                                .addComponent(heightLabel)
                                .addComponent(weightLabel)
                                .addComponent(calculationLabel) // Correct: Label
                                .addComponent(saveButton))
                        // Column 2 (Fields + Cancel Button)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameField)
                                .addComponent(ageField)
                                .addComponent(genderComboBox)
                                .addComponent(heightField)
                                .addComponent(weightField)
                                .addComponent(calculationTypeComboBox) // Correct: Combo Box
                                .addComponent(cancelButton))
        );

        add(mainPanel);

        // Resize the window slightly to accommodate the new row
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // --- Getters ---

    public String getNameInput() {
        return nameField.getText();
    }

    public String getAgeInput() {
        return ageField.getText();
    }

    public String getGenderInput() {
        Object selected = genderComboBox.getSelectedItem();
        return (selected != null) ? selected.toString() : "MALE";
    }

    public String getCalculationTypeInput() {
        Object selected = calculationTypeComboBox.getSelectedItem();
        return (selected != null) ? selected.toString() : "Mifflin-St Jeor";
    }

    public String getHeightInput() {
        return heightField.getText();
    }

    public String getWeightInput() {
        return weightField.getText();
    }
}