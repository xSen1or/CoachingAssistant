package gr.huadit.GUI;

import gr.huadit.DTO.TotalFiles;

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

        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (String fileName : TotalFiles.results) {
            JButton button = new JButton(fileName);
            button.addActionListener(e -> {
                System.out.println("Selected: " + fileName);
            });
            panel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(panel); // scroll if many files
        add(scrollPane);
        setVisible(true);
    }
}
