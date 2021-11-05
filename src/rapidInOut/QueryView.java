package rapidInOut;

import java.awt.*;
import java.util.*;

import javax.swing.*;

/**
 * This is a View Class that sets up the view for the Querying Fields. It
 * accounts settings for Resident's In/Out Log and Vehicle Log. Choice can be
 * made by selection the different overloaded constructors.
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class QueryView extends JPanel {

    @SuppressWarnings("rawtypes")
    private JComboBox regoCombo;
    private JLabel regoLabel, nameLabel, destinationLabel;
    private JTextField nameField, destinationField;
    private JLabel feedbackLabel;
    private JCheckBox lunchCheckbox, dinnerCheckbox;

    /**
     * Overloaded constructor for the Panel. Prepares the GUI component for
     * viewing. This specific constructor initialises components for the the
     * Vehicle Log with a ComboBox for a speedy Vehicle Selection. Furthermore,
     * it has fields for the driver, destination, and if they require lunch or
     * dinner to be saved for them (feedback).
     *
     * @params vehicleList : Vehicles' rego to be displayed in the ComboBox
     * @author Foong : Amos 18044418
     *
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public QueryView(ArrayList<Vehicle> vehicleList) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(245, 175));

        // Make a border around the outside with the given title.
        this.setBorder(BorderFactory.createTitledBorder("Enter details:"));

        // Prepares the rego label component for use. 
        regoLabel = new JLabel("Rego:");
        regoLabel.setLocation(10, 20);
        regoLabel.setSize(70, 30);
        regoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(regoLabel);

        // Prepares the rego combo-box component for use.
        regoCombo = new JComboBox(vehicleList.toArray());
        regoCombo.setLocation(85, 20);
        regoCombo.setSize(150, 30);
        regoCombo.setSelectedIndex(0);
        this.add(regoCombo);

        // Prepares the name label component for use. 
        nameLabel = new JLabel("Driver:");
        nameLabel.setLocation(10, 60);
        nameLabel.setSize(70, 30);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(nameLabel);

        // Prepares the text field (for name input) component for use. 
        nameField = new JTextField();
        nameField.setLocation(85, 60);
        nameField.setSize(150, 30);
        this.add(nameField);

        // Prepares the destination label component for use.
        destinationLabel = new JLabel("Destination:");
        destinationLabel.setLocation(10, 100);
        destinationLabel.setSize(70, 30);
        destinationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(destinationLabel);

        // Prepares the text field (for destination input) component for use. 
        destinationField = new JTextField();
        destinationField.setLocation(85, 100);
        destinationField.setSize(150, 30);
        this.add(destinationField);

        // Prepares the feedback label component for use.
        feedbackLabel = new JLabel("Feedback:");
        feedbackLabel.setLocation(10, 135);
        feedbackLabel.setSize(70, 30);
        feedbackLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(feedbackLabel);

        // Prepares the check-box (for lunch) component for use.
        lunchCheckbox = new JCheckBox("Lunch", false);
        lunchCheckbox.setLocation(85, 135);
        lunchCheckbox.setSize(70, 30);
        lunchCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lunchCheckbox);

        // Prepares the check-box (for dinner) component for use.
        dinnerCheckbox = new JCheckBox("Dinner", false);
        dinnerCheckbox.setLocation(160, 135);
        dinnerCheckbox.setSize(70, 30);
        dinnerCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(dinnerCheckbox);
    }

    /**
     * Overloaded constructor for the Panel. Prepares the GUI component for
     * viewing. This specific constructor initialises components for the the
     * Resident Log. Queries name, destination, and if they require lunch or
     * dinner to be saved for them (feedback).
     *
     * @author Foong : Amos 18044418
     *
     */
    public QueryView() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(245, 135));

        // Make a border around the outside with the given title
        this.setBorder(BorderFactory.createTitledBorder("Enter details:"));

        // Prepares the name label component for use. 
        nameLabel = new JLabel("Name:");
        nameLabel.setLocation(10, 20);
        nameLabel.setSize(70, 30);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(nameLabel);

        // Prepares the text field (for name input) component for use. 
        nameField = new JTextField();
        nameField.setLocation(85, 20);
        nameField.setSize(150, 30);
        this.add(nameField);

        // Prepares the destination label component for use.
        destinationLabel = new JLabel("Destination:");
        destinationLabel.setLocation(10, 60);
        destinationLabel.setSize(70, 30);
        destinationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(destinationLabel);

        // Prepares the text field (for destination input) component for use. 
        destinationField = new JTextField();
        destinationField.setLocation(85, 60);
        destinationField.setSize(150, 30);
        this.add(destinationField);

        // Prepares the feedback label component for use.
        feedbackLabel = new JLabel("Feedback:");
        feedbackLabel.setLocation(10, 95);
        feedbackLabel.setSize(70, 30);
        feedbackLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(feedbackLabel);

        // Prepares the check-box (for lunch) component for use.
        lunchCheckbox = new JCheckBox("Lunch", false);
        lunchCheckbox.setLocation(85, 95);
        lunchCheckbox.setSize(70, 30);
        lunchCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lunchCheckbox);

        // Prepares the check-box (for dinner) component for use.
        dinnerCheckbox = new JCheckBox("Dinner", false);
        dinnerCheckbox.setLocation(160, 95);
        dinnerCheckbox.setSize(70, 30);
        dinnerCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(dinnerCheckbox);

    }

    // Getter methods for Object's instance data.
    //-------------------------------------------
    @SuppressWarnings("rawtypes")
    public JComboBox getRegoCombo() {
        return regoCombo;
    }
    //-------------------------------------------

    public JTextField getNameField() {
        return nameField;
    }
    //-------------------------------------------

    public JTextField getDestinationField() {
        return destinationField;
    }
    //-------------------------------------------

    public JCheckBox getLunchCheckbox() {
        return lunchCheckbox;
    }
    //-------------------------------------------

    public JCheckBox getDinnerCheckbox() {
        return dinnerCheckbox;
    }
    //-------------------------------------------
}
