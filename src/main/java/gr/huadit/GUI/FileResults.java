package gr.huadit.GUI;

import gr.huadit.Classes.ActivityCard;
import gr.huadit.Classes.ProgressCalculator;
import gr.huadit.Enums.LoggerLevel;
import gr.huadit.Holders.CalculationType;
import gr.huadit.Holders.CurrentUser;
import gr.huadit.Loggers.ConsoleLogger;

import javax.swing.*;
import java.time.Duration;

public class FileResults extends JDialog {

    private final ConsoleLogger log = new ConsoleLogger();
    ProgressCalculator prgCal = new ProgressCalculator();

    public FileResults(JFrame parent) {
        super(parent, "Activity Results", true);
    }


    private Double getCalculation() {
        String calcType = (CalculationType.calculationType != null) ? CalculationType.calculationType : "Mifflin-St Jeor";

        if (CurrentUser.currentUser == null) return 0.0;

        // Fetch inputs safely
        String gender = CurrentUser.currentUser.optString("gender", "MALE");
        double weight = CurrentUser.currentUser.optDouble("weight", 0.0);
        double height = CurrentUser.currentUser.optDouble("height", 0.0);
        int age = CurrentUser.currentUser.optInt("age", 25);

        return prgCal.calculateBMR(calcType, gender, weight, height, age);
    }



    private double getZoneCalories(ActivityCard card) {
        if (CurrentUser.currentUser == null || card == null || card.getDuration() == null) return 0.0;

        int age = CurrentUser.currentUser.optInt("age", 25);
        double weight = CurrentUser.currentUser.optDouble("weight", 0.0);
        double mhr = 220.0 - age;
        double avgHr = card.getAverageHeartRate();

        if (mhr == 0) return 0.0;

        double ceff = prgCal.getCeff(avgHr, mhr);

        // Duration in minutes
        double minutes = card.getDuration().toSeconds() / 60.0;

        return minutes * ceff * weight;
    }




    private double[] getVO2Calculations(ActivityCard card) {
        // Returns array: [0] -> VO2 Max Value, [1] -> Calories
        if (CurrentUser.currentUser == null || card == null || card.getDuration() == null) return new double[]{0.0, 0.0};

        int age = CurrentUser.currentUser.optInt("age", 25);
        double weight = CurrentUser.currentUser.optDouble("weight", 0.0);
        double rhr = CurrentUser.currentUser.optDouble("restingHeartRate", 70.0); // Default to 70 if missing

        double mhr = 220.0 - age;

        // 1. Calculate VO2 Max
        double vo2Max = 15.3 * (mhr / rhr);

        // 2. Calculate Calories
        double minutes = card.getDuration().toSeconds() / 60.0;
        double vo2Calories = (vo2Max * weight * minutes) / 200.0;

        return new double[]{vo2Max, vo2Calories};
    }

    private String getVO2Assessment(double vo2) {
        if (vo2 >= 50) return "Excellent";
        if (vo2 >= 40) return "Good";
        if (vo2 >= 30) return "Average";
        return "Poor";
    }

    // ==========================================
    // GUI DISPLAY
    // ==========================================
    public void displayGUIWindow(ActivityCard card) {
        SwingUtilities.invokeLater(() -> {
            log.print("displayGUIWindow on FileResultsGUI triggered", LoggerLevel.INFO);

            if (card == null) return;

            setTitle("Activity Results");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            getContentPane().removeAll();

            String formattedDuration = getString(card);

            // ---- RUN CALCULATIONS ----
            double bmrVal = getCalculation(); // Your original calculation
            double zoneCalVal = getZoneCalories(card);
            double[] vo2Results = getVO2Calculations(card);

            // ---- LABELS ----
            JLabel nameLabel = new JLabel("Name:");
            JLabel idLabel = new JLabel("ID:");
            JLabel distanceLabel = new JLabel("Distance:");
            JLabel paceLabel = new JLabel("Avg Pace:");
            JLabel heartRateLabel = new JLabel("Avg HR:");
            JLabel durationLabel = new JLabel("Duration:");

            JLabel bmrLabel = new JLabel("BMR (Daily):");         // 1. Original
            JLabel zoneCalLabel = new JLabel("Zone Method:");     // 2. New
            JLabel vo2CalLabel = new JLabel("VO2 Method:");       // 3. New

            // ---- VALUES ----
            JLabel name = new JLabel(String.valueOf(card.getActivityName()));
            JLabel id = new JLabel(String.valueOf(card.getId()));
            JLabel totalDistance = new JLabel(card.getTotalDistance() + " km");
            JLabel averagePace = new JLabel(card.getAveragePace() + " min/km");
            JLabel averageHeartRate = new JLabel(card.getAverageHeartRate() + " bpm");
            JLabel duration = new JLabel(formattedDuration);

            // 1. ORIGINAL VALUE DISPLAY
            String bmrText = (bmrVal == 0.0) ? "Not Calculated" : String.format("%.0f kcal", bmrVal);
            JLabel bmrValue = new JLabel(bmrText);

            // 2. ZONE CALORIES DISPLAY
            JLabel zoneCalValue = new JLabel(String.format("%.1f kcal", zoneCalVal));

            // 3. VO2 CALORIES DISPLAY (+ Assessment)
            String vo2Text = String.format("%.1f kcal (Rate: %s)", vo2Results[1], getVO2Assessment(vo2Results[0]));
            JLabel vo2CalValue = new JLabel(vo2Text);


            JPanel mainPanel = new JPanel();
            GroupLayout layout = new GroupLayout(mainPanel);
            mainPanel.setLayout(layout);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            // Horizontal Group
            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(nameLabel)
                                    .addComponent(idLabel)
                                    .addComponent(distanceLabel)
                                    .addComponent(paceLabel)
                                    .addComponent(heartRateLabel)
                                    .addComponent(durationLabel)
                                    .addComponent(bmrLabel)     // 1
                                    .addComponent(zoneCalLabel) // 2
                                    .addComponent(vo2CalLabel)  // 3
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(name)
                                    .addComponent(id)
                                    .addComponent(totalDistance)
                                    .addComponent(averagePace)
                                    .addComponent(averageHeartRate)
                                    .addComponent(duration)
                                    .addComponent(bmrValue)     // 1
                                    .addComponent(zoneCalValue) // 2
                                    .addComponent(vo2CalValue)  // 3
                            )
            );

            // Vertical Group
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(nameLabel).addComponent(name))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(idLabel).addComponent(id))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(distanceLabel).addComponent(totalDistance))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(paceLabel).addComponent(averagePace))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(heartRateLabel).addComponent(averageHeartRate))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(durationLabel).addComponent(duration))
                            .addGap(15) // Spacer
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(bmrLabel).addComponent(bmrValue))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(zoneCalLabel).addComponent(zoneCalValue))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(vo2CalLabel).addComponent(vo2CalValue))
            );

            add(mainPanel);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }

    private String getString(ActivityCard card) {
        Duration duration = card.getDuration();
        if (duration == null) return "N/A";
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        if (hours > 0) return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return String.format("%02d:%02d", minutes, seconds);
    }
}