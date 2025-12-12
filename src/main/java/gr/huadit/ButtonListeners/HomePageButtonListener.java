package gr.huadit.ButtonListeners;

import gr.huadit.GUI.AddActivity;
import gr.huadit.GUI.CalorieGoal;
import gr.huadit.GUI.FileResults;
import gr.huadit.GUI.Client;
import gr.huadit.Helpers.XMLSingleFileReader;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

                FileResults fileResultsGUI = new FileResults();

                int returnVal = chooser.showOpenDialog(fileResultsGUI);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " + chooser.getSelectedFile().getPath());
                    XMLSingleFileReader singleFileReader = new XMLSingleFileReader();
                    singleFileReader.read(chooser.getSelectedFile().getPath(), log);
                }
            }
            case "ADD_ACTIVITY" -> new AddActivity();
            case "USER_INFO" -> {
                srcFrame.dispose();
                new Client().displayGUIWindow(srcFrame);
            }
            case "CALORIE_GOAL" -> new CalorieGoal();
        }
    }
}