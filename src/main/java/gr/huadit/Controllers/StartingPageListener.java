package gr.huadit.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import gr.huadit.DTO.TotalFiles;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.GUI.AddActivity;
import gr.huadit.GUI.CalorieGoal;
import gr.huadit.GUI.CalorieInput;
import gr.huadit.GUI.Client;
import gr.huadit.GUI.FileResults;
import gr.huadit.GUI.SelectedFiles;
import gr.huadit.Helpers.XMLSingleFileReader;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

public class StartingPageListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        JFrame srcFrame = (JFrame) SwingUtilities.getWindowAncestor(src);
        String CMD = src.getActionCommand();
        Logger log = new ConsoleLogger();
        List<String> fileNames = new ArrayList<>();
        SelectedFiles sFiles = new SelectedFiles(srcFrame);

        switch (CMD) {
            case "SELECT_FILES" -> {
                // This is where the files are selected so their names will be displayed
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
                File[] selectedFiles;
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    selectedFiles = chooser.getSelectedFiles();

                    for (File file : selectedFiles) {
                        TotalFiles.results.add(file.getName());
                        log.print("You chose to open this file: " + file.getPath(), LoggerLevel.INFO);
                        XMLSingleFileReader singleFileReader = new XMLSingleFileReader();
                        singleFileReader.read(file.getPath(), log);
                    }
                }
            }
            case "ADD_ACTIVITY" -> new AddActivity(srcFrame).displayWindowGUI();
            case "USER_INFO" -> new Client(srcFrame).displayGUIWindow();
            case "CALORIE_GOAL" -> {
                CalorieGoal cgoal = new CalorieGoal(srcFrame);
                new CalorieInput().show(cgoal.getParent());
            }
            case "DISPLAY_SELECTED_FILES" -> {
                sFiles.displayGUIWindow();
            }
        }
    }
}