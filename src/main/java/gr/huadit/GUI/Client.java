package gr.huadit.GUI;

import gr.huadit.ButtonListeners.ProfileButtonListener;

import javax.swing.*;
import java.awt.*;

/*

    This class indicates the Edit User Info & Calorie Method

 */



public class Client extends JDialog {
    // Text fields stored as INSTANCE VARIABLES
    private JTextField nameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField heightField;
    private JTextField weightField;

    public Client(JFrame parent) {
        super(parent, "Client", true); //modal = true
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

    public void displayProfile(String name, int age, String gender, double height, double weight) {
        JFrame frame = new JFrame("User Profile Display");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 44, 52));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(5, 2, 10, 15));

        Font labelFont = new Font("SansSerif", Font.BOLD, 16);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 15);

// Labels
        JLabel nameLabel = createStyledLabel("Name:", labelFont);
        JLabel ageLabel = createStyledLabel("Age:", labelFont);
        JLabel genderLabel = createStyledLabel("Gender:", labelFont);
        JLabel heightLabel = createStyledLabel("Height:", labelFont);
        JLabel weightLabel = createStyledLabel("Weight:", labelFont);

// Fields
        JLabel nameField = createStyledLabel(name, fieldFont);
        JLabel ageField = createStyledLabel(Integer.toString(age), fieldFont);
        JLabel genderField = createStyledLabel(gender, fieldFont);
        JLabel heightField = createStyledLabel(Integer.toString((int) height), fieldFont);
        JLabel weightField = createStyledLabel(Integer.toString((int) weight), fieldFont);


        nameField.setText(name);
        ageField.setText(Integer.toString(age));
        genderField.setText(gender);
        heightField.setText(Integer.toString((int) height));
        weightField.setText(Integer.toString((int) weight));

// Add Components
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(genderLabel);
        panel.add(genderField);
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(weightLabel);
        panel.add(weightField);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static JLabel createStyledLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(198, 198, 198));
        label.setFont(font);
        return label;
    }

    private static JTextField createStyledField(Font font) {
        JTextField field = new JTextField();
        field.setBackground(new Color(60, 63, 72));
        field.setForeground(new Color(230, 230, 230));
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 90)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8))
        );
        field.setFont(font);
        return field;
    }
    public String getNameInput() { return nameField.getText(); }
    public String getAgeInput() { return ageField.getText(); }
    public String getGenderInput() { return genderField.getText(); }
    public String getHeightInput() { return heightField.getText(); }
    public String getWeightInput() { return weightField.getText(); }
}
