package gr.huadit.ButtonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gr.huadit.DTO.Profile;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.GUI.Client;
import gr.huadit.DTO.CurrentUser;
import gr.huadit.Loggers.ConsoleLogger;
import org.json.JSONObject;

public class ProfileButtonListener implements ActionListener {

    private final ConsoleLogger logger = new ConsoleLogger();
    private final Client client; // Interface to get GUI inputs
    private JSONObject currentUser;

    public ProfileButtonListener(Client client) {
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        JFrame srcFrame = (JFrame) SwingUtilities.getWindowAncestor(src);
        String CMD = src.getActionCommand();

        if (CMD.equals("SAVE")) {
            logger.print("Save Button Pressed", LoggerLevel.INFO);
            srcFrame.dispose();
            try {
                String name = client.getNameInput();
                int age = Integer.parseInt(client.getAgeInput());
                double weight = Double.parseDouble(client.getWeightInput());
                double height = Double.parseDouble(client.getHeightInput());
                String gender = client.getGenderInput();

                Profile profile = new Profile(name, age, weight, height, gender);

                currentUser = new JSONObject();
                currentUser.put("name", profile.name());
                currentUser.put("age", profile.age());
                currentUser.put("weight", profile.weight());
                currentUser.put("height", profile.height());
                currentUser.put("gender", profile.gender());

                JOptionPane.showMessageDialog(srcFrame, "Profile created:\n" + currentUser.toString(2));
                CurrentUser.currentUser = currentUser;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(srcFrame, "Please enter valid numbers for age, weight, and height.");
                logger.print("Number format error: " + ex.getMessage(), LoggerLevel.ERROR);
            }
        } else if (CMD.equals("CANCEL")) {
            logger.print("Cancel Button Pressed", LoggerLevel.INFO);
        }
    }

    public JSONObject getCurrentUser() {
        return currentUser;
    }
}
