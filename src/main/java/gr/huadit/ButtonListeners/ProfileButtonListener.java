package gr.huadit.ButtonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gr.huadit.Classes.Profile;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.GUI.ProfileGUI;
import gr.huadit.JSONHandler.JSONFileReader;
import gr.huadit.JSONHandler.JSONFileWriter;
import gr.huadit.Loggers.ConsoleLogger;


public class ProfileButtonListener implements ActionListener {
    private final ConsoleLogger logger = new ConsoleLogger();
    private final ProfileGUI profileGUI;

    public ProfileButtonListener(ProfileGUI profileGUI) {
        this.profileGUI = profileGUI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        String CMD = src.getActionCommand();
        if (CMD.equals("SAVE")) {
            logger.print("Save Button Pressed", LoggerLevel.INFO);
            Profile prof = new Profile(profileGUI.getNameInput(), Integer.parseInt(profileGUI.getAgeInput()), Double.parseDouble(profileGUI.getWeightInput()), Double.parseDouble(profileGUI.getHeightInput()), profileGUI.getGenderInput());

            try {
                JSONFileWriter writer = new JSONFileWriter(prof);
                writer.writeProfileToFile();
            } catch (Exception exc) {
                logger.print("Error writing to JSON file: " + exc.getMessage(), LoggerLevel.ERROR);
            }
        //    public void displayProfile(String name, String age, String gender, String height, String weight) {
            new ProfileGUI().displayProfile(prof.name(), prof.age(), prof.gender(), prof.height(), prof.weight());

        } else if (CMD.equals("CANCEL")) {
            logger.print("Cancel Button Pressed", LoggerLevel.INFO);
        }
    }
}
