package gr.huadit.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        
        JButton src = (JButton) e.getSource(); // pressed button.
        JFrame srcFrame = (JFrame) SwingUtilities.getWindowAncestor(src); // parent window. 
        String CMD = src.getActionCommand(); // button command id. 
        Logger log = new ConsoleLogger(); // loggger
        SelectedFiles sFiles = new SelectedFiles(srcFrame); // 

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

                // selecting the files using java Build Id JFileChooser. https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html
                JFileChooser chooser = new JFileChooser();
                
                // pass a filter to accept only .tcx files .
                FileNameExtensionFilter filter =new FileNameExtensionFilter("TCX Files", "tcx");

                // enable multiselection. 
                chooser.setMultiSelectionEnabled(true);
                
                // apply the filter.
                chooser.setFileFilter(filter);

                // open the dialog 
                int returnVal = chooser.showOpenDialog(fileResultsGUI);
                File[] selectedFiles;

                // if the option(s) is approved
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    selectedFiles = chooser.getSelectedFiles();
                    // loop through the selected files.
                    for (File file : selectedFiles) {
                        // add the filenames to the holder.
                        TotalFiles.results.add(file.getName());
                        // logging.
                        log.print("You chose to open this file: " + file.getPath(), LoggerLevel.INFO);
                        
                        // read the files data.
                        XMLSingleFileReader singleFileReader = new XMLSingleFileReader();
                        singleFileReader.read(file.getPath(), log);
                    }
                }
            }
            // opens the AddActivity Window.
            case "ADD_ACTIVITY" -> new AddActivity(srcFrame).displayWindowGUI();
            // opens the profile Window. 
            case "USER_INFO" -> new Client(srcFrame).displayGUIWindow();
            // opens the calorie goal Window.
            case "CALORIE_GOAL" -> {
                CalorieGoal cgoal = new CalorieGoal(srcFrame);
                new CalorieInput().show(cgoal.getParent());
            }
            // you guessed it, open the selected files Window.
            case "DISPLAY_SELECTED_FILES" -> {
                sFiles.displayGUIWindow();
            }
        }
    }
}