package gr.huadit.GUI;

import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Find;
import gr.huadit.Loggers.ConsoleLogger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SelectFilesGUI extends JFrame {
    private final ConsoleLogger log = new ConsoleLogger();
    private final String PATTERN = "*.tcx";
    private final String STR_DIR = "user.home";

    public SelectFilesGUI() {
        displayGUIWindow();
    }

    public void displayGUIWindow() {
        Find.Finder finder = new Find.Finder(PATTERN);

        try {
            Path startingDirectory = Paths.get(System.getProperty("user.home"));
            Files.walkFileTree(startingDirectory, finder);
            log.print("Files Parsed.", LoggerLevel.INFO);
        } catch (Exception e) {
            log.print("Error: " + e.getMessage(), LoggerLevel.ERROR);
        }

        List<String> files = finder.getFilenames();

        if (files.isEmpty()) {
            System.out.println("No files found.");
        }

        String[] arrayOfFilenames = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            String fullPath = files.get(i);
            arrayOfFilenames[i] = Path.of(fullPath).getFileName().toString();
            // much safer than substring()
        }


        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new BorderLayout());

// Add list inside scroll pane
        JList<String> list = new JList<>(arrayOfFilenames);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200, 80));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

// Add main panel to the window
        add(mainPanel);



    }

}
