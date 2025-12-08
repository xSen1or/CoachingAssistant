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
    private JFrame srcFrame = null;
    private final ProfileGUI profileGUI;

    public ProfileButtonListener(ProfileGUI profileGUI) {
        this.profileGUI = profileGUI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(src);
        String CMD = src.getActionCommand();
        if (CMD.equals("SAVE")) {
            logger.print("Save Button Pressed", LoggerLevel.INFO);
            Profile prof = new Profile(profileGUI.getNameInput(), Integer.parseInt(profileGUI.getAgeInput()), Double.parseDouble(profileGUI.getWeightInput()), Double.parseDouble(profileGUI.getHeightInput()), profileGUI.getGenderInput());
            JSONFileWriter writer = new JSONFileWriter(prof);
            JSONFileReader reader = new JSONFileReader();

            writer.writeProfileToFile();
        } else if (CMD.equals("CANCEL")) {
            logger.print("Cancel Button Pressed", LoggerLevel.INFO);
        }
    }
}
