package gr.huadit.ButtonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import gr.huadit.Enums.LoggerLevel;
import gr.huadit.GUI.AddActivity;
import gr.huadit.GUI.CalorieGoal;
import gr.huadit.GUI.CalorieInput;
import gr.huadit.GUI.Client;
import gr.huadit.GUI.FileResults;
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
            case "ADD_ACTIVITY" -> new AddActivity().displayWindowGUI();
            case "USER_INFO" -> new Client().displayGUIWindow(srcFrame);
            case "CALORIE_GOAL" -> {
                JFrame parent = new CalorieGoal().show();
                new CalorieInput().show(parent);
            }
        }
    }
}