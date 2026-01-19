package gr.huadit.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;

import gr.huadit.Classes.ActivityCard;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Find;
import gr.huadit.Helpers.XMLSingleFileReader;
import gr.huadit.JSONHandler.JSONFileReader;
import gr.huadit.Loggers.ConsoleLogger;

import javax.swing.*;


public class SelectFilesListener implements ActionListener {
    String fn;
    ConsoleLogger log = new ConsoleLogger();
    public SelectFilesListener(String fn) {
        this.fn = fn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JDialog parent = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
        try {


            // if the finder doesn't find the file means that the pressed button indicates to a manually created file. In that case we simply search it in the fileContainer.json and retrieve the object.
            JSONFileReader reader = new JSONFileReader();
            ActivityCard card = reader.readJSON(fn);

            if (card != null) {
                card.printAthleteCard();

            } else {

                Find.Finder finder = new Find.Finder(fn);
                Files.walkFileTree(Paths.get(System.getProperty("user.home")), finder);

                XMLSingleFileReader xmlReader = new XMLSingleFileReader();
                xmlReader.read(finder.getPath(), log);
            }

            parent.dispose();

        } catch (Exception exc) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor((JButton) e.getSource()), "An extremely rare error occurred. Please try again.");
            log.print(exc.getMessage(), LoggerLevel.ERROR);
        }
    }
}
