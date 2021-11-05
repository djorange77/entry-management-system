package rapidInOut;

import java.awt.*;
import java.time.*;
import java.time.format.*;

import javax.swing.*;

/**
 * This is a View Class that sets up the view for the Ticking Time Clock.
 *
 * References
 * ----------------------------------------------------------------------
 * Dermot. (2019, February 23). Dynamic clock in java. StackOverflow.
 * https://stackoverflow.com/questions/2959718/dynamic-clock-in-java
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class LiveClockView extends JPanel {

    private JLabel clockLabel;

    /**
     * The default constructor for the Panel. Prepares the GUI component for
     * viewing. Initialises the Label with enlarged font for enhanced visibility
     * and program functionality. Ticking controller is added in the
     * InOutLogPanel.
     *
     * @author Foong : Amos 18044418
     *
     */
    public LiveClockView() {
        this.setPreferredSize(new Dimension(245, 35));

        // Essential elements to procure current date and time, then format it as a String.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("E dd/MM/yyyy hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();

        // Prepares the clockLabel for use.
        clockLabel = new JLabel(format.format(now));
        clockLabel.setFont(new Font("Segoe", Font.PLAIN, 18));
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(clockLabel);
    }

    // Getter method for Object's instance data.
    //-------------------------------------------
    public JLabel getClockLabel() {
        return clockLabel;
    }
    //-------------------------------------------
}
