package gr.huadit.ButtonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Loggers.ConsoleLogger;


public class ProfileButtonListener implements ActionListener {
    private JButton button;
    private final ConsoleLogger logger = new ConsoleLogger();

    public void setButton(JButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        String CMD = src.getActionCommand();
        if (CMD.equals("SAVE")) {
            logger.print("Save Button Pressed", LoggerLevel.INFO);
        } else if (CMD.equals("CANCEL")) {
            logger.print("Cancel Button Pressed", LoggerLevel.INFO);
        }
    }
}
