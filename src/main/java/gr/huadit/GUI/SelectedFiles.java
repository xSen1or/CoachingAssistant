package gr.huadit.GUI;


import gr.huadit.Controllers.*;
import gr.huadit.Holders.TotalFiles;
import gr.huadit.Interfaces.GraphicUserInterface;
import gr.huadit.JSONHandler.JSONFileReader;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.List;

public class SelectedFiles extends JDialog implements GraphicUserInterface {

    public SelectedFiles(JFrame parentFrame) {
        super(parentFrame, "Select File", true);
    }

    public void displayGUIWindow() {
        List<String> filenames = TotalFiles.results;

        setSize(300, 200);
        setLocationRelativeTo(getParent());

        JSONFileReader reader = new JSONFileReader();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (String fileName : filenames) {
            JButton button = new JButton(fileName);
            button.addActionListener(new SelectFilesListener(fileName));
            panel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        setContentPane(scrollPane); // replaces old content
        setVisible(true);
    }

}
