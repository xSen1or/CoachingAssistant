package gr.huadit.ButtonListeners;

import gr.huadit.GUI.SelectFilesGUI;
import gr.huadit.Interfaces.Logger;
import gr.huadit.Loggers.ConsoleLogger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gr.huadit.Find;
public class HomePageButtonListener implements ActionListener {
    private JButton button;
    private Logger logger = new ConsoleLogger();
//    Find.Finder finder = new Find.Finder(pattern);


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        JFrame srcFrame = (JFrame) SwingUtilities.getWindowAncestor(src);
        String CMD = src.getActionCommand();
        if (CMD.equals("SELECT_FILES")) {
            srcFrame.setTitle("Select TCX Files");
            srcFrame.setSize(300, 200);
            srcFrame.setLocationRelativeTo(null);
            srcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            srcFrame.setVisible(true);

// File chooser
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TCX Files", "tcx");
            chooser.setFileFilter(filter);

            int returnVal = chooser.showOpenDialog(srcFrame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " + chooser.getSelectedFile().getPath());
                    Find.Finder finder = new Find.Finder(chooser.getSelectedFile().getPath());

            }
        } else if (CMD.equals("ADD_ACTIVITY")) {
            // TODO
        } else if (CMD.equals("USER_INFO")) {
            // TODO
        } else if (CMD.equals("CALORIE_GOAL")) {
            // TODO
        }
    }
}
