package gr.huadit.ButtonListeners;

import gr.huadit.Enums.LoggerLevel;
import gr.huadit.GUI.*;
import gr.huadit.Helpers.XMLSingleFileReader;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StartingPageListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        JFrame srcFrame = (JFrame) SwingUtilities.getWindowAncestor(src);
        String CMD = src.getActionCommand();
        Logger log = new ConsoleLogger();


        switch (CMD) {
            case "SELECT_FILES" -> {
                log.print("Select Files Button Pressed", LoggerLevel.INFO);
                srcFrame.dispose();
                srcFrame.setTitle("Select TCX Files");
                srcFrame.setSize(500, 400);
                srcFrame.setLocationRelativeTo(null);
                srcFrame.setVisible(true);
                FileResults fileResultsGUI = new FileResults();

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter =
                new FileNameExtensionFilter("TCX Files", "tcx");

                chooser.setMultiSelectionEnabled(true);
                chooser.setFileFilter(filter);

                int returnVal = chooser.showOpenDialog(fileResultsGUI);
                File[] selectedFiles = new File[0];
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    selectedFiles = chooser.getSelectedFiles();

                    for (File file : selectedFiles) {
                        log.print("You chose to open this file: " + file.getPath(), LoggerLevel.INFO);
                        XMLSingleFileReader singleFileReader = new XMLSingleFileReader();
                        singleFileReader.read(file.getPath(), log);
                    }
                }
            }
            case "ADD_ACTIVITY" -> new AddActivity();
            case "USER_INFO" -> new Client().displayGUIWindow(srcFrame);
            case "CALORIE_GOAL" -> {
                JFrame parent = new CalorieGoal().show();
                new CalorieInput().show(parent);
            }
        }
    }
}