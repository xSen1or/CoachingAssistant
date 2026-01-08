package gr.huadit.GUI;


import gr.huadit.DTO.TotalFiles;
import gr.huadit.Controllers.*;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SelectedFiles extends JDialog { 

    public SelectedFiles(JFrame parentFrame) {
        super(parentFrame, "Select File", true);
    }

    public void displayGUIWindow() {

        setSize(300, 200);
        setLocationRelativeTo(getParent());

        List<String> filenames = TotalFiles.results;

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
