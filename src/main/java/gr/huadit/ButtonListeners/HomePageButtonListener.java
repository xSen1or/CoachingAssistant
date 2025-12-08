package gr.huadit.ButtonListeners;

import gr.huadit.GUI.AddActivityGUI;
import gr.huadit.GUI.CalorieGoalGUI;
import gr.huadit.GUI.FileResultsGUI;
import gr.huadit.GUI.ProfileGUI;
import gr.huadit.Helpers.XMLSingleFileReader;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HomePageButtonListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        JFrame srcFrame = (JFrame) SwingUtilities.getWindowAncestor(src);
        String CMD = src.getActionCommand();
        Logger log = new ConsoleLogger();


        switch (CMD) {
            case "SELECT_FILES" -> {
                srcFrame.setTitle("Select TCX Files");
                srcFrame.setSize(500, 400);
                srcFrame.setLocationRelativeTo(null);
//                srcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                srcFrame.setVisible(true);

                // File chooser
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TCX Files", "tcx");
                chooser.setFileFilter(filter);

                FileResultsGUI fileResultsGUI = new FileResultsGUI();
                int returnVal = chooser.showOpenDialog(fileResultsGUI);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " + chooser.getSelectedFile().getPath());
                    XMLSingleFileReader singleFileReader = new XMLSingleFileReader();
                    singleFileReader.read(chooser.getSelectedFile().getPath(), log);
                }
            }
            case "ADD_ACTIVITY" -> new AddActivityGUI();
            case "USER_INFO" -> new ProfileGUI();
            case "CALORIE_GOAL" -> new CalorieGoalGUI();
        }
    }
}
