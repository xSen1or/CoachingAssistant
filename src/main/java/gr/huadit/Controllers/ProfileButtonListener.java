package gr.huadit.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import gr.huadit.Holders.CalculationType;
import org.json.JSONObject;

import gr.huadit.Holders.CurrentUser;
import gr.huadit.DTO.Profile;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.GUI.Client;
import gr.huadit.GUI.Starting;
import gr.huadit.Loggers.ConsoleLogger;

public class ProfileButtonListener implements ActionListener {

    private final ConsoleLogger logger = new ConsoleLogger();
    private final Client client; // Interface to get GUI inputs
    private JSONObject currentUser;

    public ProfileButtonListener(Client client) {
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource(); // button that has been pressed.
        JDialog srcFrame = (JDialog) SwingUtilities.getWindowAncestor(src); // parent window.
        String CMD = src.getActionCommand(); // get the buttons specific ID.

        if (CMD.equals("SAVE")) { // if the buttons id is "SAVE" do this.
            logger.print("Save Button Pressed", LoggerLevel.INFO); // logging.
            srcFrame.dispose(); // close the parent window.
            
            try {
                // Get all the necessery values. 
                String name = client.getNameInput(); 
                int age = Integer.parseInt(client.getAgeInput()); 
                double weight = Double.parseDouble(client.getWeightInput()); 
                double height = Double.parseDouble(client.getHeightInput()); 
                String gender = client.getGenderInput();
                String calcType = client.getCalculationTypeInput();



                Profile profile = new Profile(name, age, weight, height, gender); // pass the information to the DTO Class 

                // create a new JSBON Object and give it to the holder. 
                currentUser = new JSONObject();
                currentUser.put("name", profile.name());
                currentUser.put("age", profile.age());
                currentUser.put("weight", profile.weight());
                currentUser.put("height", profile.height());
                currentUser.put("gender", profile.gender());

                // Save Calculation type
                CalculationType.calculationType = calcType;

                JOptionPane.showMessageDialog(srcFrame, "Profile created:\n" + currentUser.toString(2)); // pop up message. 
                CurrentUser.currentUser = currentUser; // update the holders value. 
                srcFrame.dispose();
                Starting newPage = new Starting();
                newPage.displayGUIWindow();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(srcFrame, "Please enter valid numbers for age, weight, and height."); // pop up error message
                logger.print("Number format error: " + ex.getMessage(), LoggerLevel.ERROR); // error logging 
            }
        }
    }

    public JSONObject getCurrentUser() {
        return currentUser;
    }
}
