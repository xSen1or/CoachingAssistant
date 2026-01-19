package gr.huadit.Controllers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(button);
        frame.dispose();
    }
}
