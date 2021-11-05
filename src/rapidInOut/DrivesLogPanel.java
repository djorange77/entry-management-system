package rapidInOut;

import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * This class integrates several View Classes and adds Controller functions to
 * them. This is the 3rd-level of the frame. It displays useful information to
 * the user and allows user-input as well. It is interactive and user-friendly
 * and has multiple back-end functionalities such as synchronising logs and
 * preventing potential issues such as blank entries.
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class DrivesLogPanel extends JPanel implements DocumentListener {

    private static TableView drivesTable;
    private JLabel logoLabel;
    private QueryView queryFields;
    private InOutButtonsView inOutButtons;

    /**
     * The default constructor for the Panel. Prepares the GUI components for
     * use. Amalgamates several GUI components together and add Controllers to
     * those components for panel's functionality.
     *
     * @param database : Class that contains all the required data for the
     * program to function.
     * @author Foong : Amos 18044418
     *
     */
    public DrivesLogPanel(Database database) {
        // Allows the programmer to manually 
        // configure component location and sizes.
        this.setLayout(null);

        // Header for the Drives Log table.
        ArrayList<String> drivesLogHeader = new ArrayList<String>(
                Arrays.asList("Rego", "Driver", "Destination", "Time Out", "Time In", "FB: Lunch", "FB: Dinner"));

        // Prepares the TABLE for use, has sorter function disabled.
        drivesTable = new TableView(drivesLogHeader, database.convertVehicleLogs(), false);
        drivesTable.setLocation(2, 5);
        drivesTable.setSize(drivesTable.getPreferredSize());
        drivesTable.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if(!drivesTable.getTable().getSelectionModel().isSelectionEmpty()) {
                    inOutButtons.getInButton().setEnabled(true);
                } else {
                    inOutButtons.getInButton().setEnabled(false);
                }
            }
        });
        this.add(drivesTable);

        // Prepares the COMPANY LOGO for use.
        logoLabel = new JLabel(new ImageIcon(".\\Images\\OdysseyLogo240x100.png"));
        logoLabel.setLocation(562, 10);
        logoLabel.setSize(logoLabel.getPreferredSize());
        this.add(logoLabel);

        // Prepares the QUERY FIELDS for use.
        queryFields = new QueryView(database.getVehicleList());
        queryFields.setLocation(560, 113);
        queryFields.setSize(queryFields.getPreferredSize());
        queryFields.getNameField().getDocument().addDocumentListener(this);
        queryFields.getDestinationField().getDocument().addDocumentListener(this);
        queryFields.getDestinationField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent enterKeyPress) {
                // If both text fields are not empty, enable ENTER KEY to Sign Out.
                if(!queryFields.getNameField().getText().isEmpty()
                        && !queryFields.getDestinationField().getText().isEmpty()) {
                    // Calls the method to sign a vehicle out.
                    signVehicleOut(database);
                }

                // Uncomment this if you would like to test scroll pane without 
                // having to enter any data (allows blank entries) (DEBUG FUNCTION).
//				signVehicleOut(database);
            }
        });
        this.add(queryFields);

        // Prepares the BUTTONS for use.
        inOutButtons = new InOutButtonsView();
        inOutButtons.setLocation(560, 293);
        inOutButtons.setSize(inOutButtons.getPreferredSize());
        inOutButtons.getOutButton().setEnabled(false);
        inOutButtons.getOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent outButtonPress) {
                // Calls the method to sign a vehicle out.
                signVehicleOut(database);
            }
        });
        inOutButtons.getInButton().setEnabled(false);
        inOutButtons.getInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent inButtonPress) {
                // Gets index of item selected and signs the person & vehicle in.
                int index = drivesTable.getTable().getSelectedRow();
                signVehicleIn(database, index);
            }
        });
        this.add(inOutButtons);
    }

    // Getter methods for Object's instance data.
    //-------------------------------------------
    public static TableView getDrivesTable() {
        return drivesTable;
    }
    //-------------------------------------------

    public QueryView getQueryFields() {
        return queryFields;
    }
    //-------------------------------------------

    public InOutButtonsView getInOutButtons() {
        return inOutButtons;
    }
    //-------------------------------------------

    // Methods that relate to the DocumentListener (some are overridden).
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void changedUpdate(DocumentEvent userTyping) {
        setOutButton();
    }

    @Override
    public void insertUpdate(DocumentEvent userTyping) {
        setOutButton();
    }

    @Override
    public void removeUpdate(DocumentEvent userTyping) {
        setOutButton();
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Method that checks the Driver & Destination fields and returns a boolean
     * value describing the state of each fields. The boolean values is then
     * used to assess whether to activate/disable the OUT BUTTON. This method is
     * shared between all the overridden methods of the DocumentListener above.
     *
     * @author Foong : Amos 18044418
     *
     */
    public void setOutButton() {
        // Activates OUT button when both text fields 
        // (driver and destination) are not empty.
        inOutButtons.getOutButton().setEnabled(
                !queryFields.getNameField().getText().isEmpty()
                && !queryFields.getDestinationField().getText().isEmpty());
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // Signing in and out methods and their related functions.
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    /**
     * Method that contains a set of instructions to sign a Vehicle out. It
     * pulls data from the Query Fields and creates a new Object for specific
     * ArrayList, it then resets the fields.
     *
     * Also checks if the person driving is a resident, if they are, it will log
     * it in the Resident's In/Out Log (synchronise logs). It also changes the
     * availability/presence of a vehicle/resident.
     *
     * It also backs up the changes & updates the tables across all tabs.
     *
     * @param database : Class containing all essential data for the program to
     * function.
     * @author Foong : Amos 18044418
     *
     */
    public void signVehicleOut(Database database) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");
        LocalDateTime now = LocalDateTime.now();

        String rego, name, destination, timeOut;
        boolean lunchFB, dinnerFB;

        // Procures/pulls user entered data from the UI and saves it locally.
        rego = this.getQueryFields().getRegoCombo().getSelectedItem().toString().substring(0, 6);
        name = this.getQueryFields().getNameField().getText();
        destination = this.getQueryFields().getDestinationField().getText();
        timeOut = format.format(now);
        lunchFB = this.getQueryFields().getLunchCheckbox().isSelected();
        dinnerFB = this.getQueryFields().getDinnerCheckbox().isSelected();

        // Clears the fields once saved.
        this.getQueryFields().getNameField().setText("");
        this.getQueryFields().getDestinationField().setText("");
        this.getQueryFields().getLunchCheckbox().setSelected(false);
        this.getQueryFields().getDinnerCheckbox().setSelected(false);

        // Adds a new entry to the database records.
        database.getVehicleLogs().add(new VehicleLog(rego, name, destination, timeOut, lunchFB, dinnerFB));

        // Changes the availability of the vehicle to false in the Vehicles Board.
        changeVehicleAvailability(database, rego, false);

        // Backs up the log to .dat file.
        database.backupVehicleLogs();

        // If driver is a resident, add them to the Residents' In/Out Log as well.
        int residentIndex = database.lookupResident(name);
        if(residentIndex != -1) {
            // Adds a Resident log entry.
            database.getResidentLogs().add(new ResidentLog(name, destination, timeOut, lunchFB, dinnerFB));

            // Sets the Resident's in-house status to false.
            database.getResidentList().get(residentIndex).setPresent(false);

            // Generates a case note (outputs to text file).
            database.generateCaseNote(name, destination, "out");

            // Backs up the log to .dat file.
            database.backupResidentLogs();
        }

        // Calls a method to update the table view.
        fireChanges(database);

        // Clears the highlighted part on the table.
        getDrivesTable().getTable().clearSelection();
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    /**
     * Method that contains a set of instructions to sign a Vehicle in. It uses
     * the index passed in from the caller and searches for the specific Object
     * in an ArrayList, if the log has not been signed in yet, it time-stamps
     * (Time In) the log then changes the availability of the vehicle.
     *
     * Also checks if the person returning from a drive is a resident, if they
     * are, it will sign the person in within the Resident's In/Out Log
     * (synchronise logs) as well as changing the person's in house status.
     *
     * It also backs up the changes & updates the tables across all tabs.
     *
     * @param database : Class containing all essential data for the program to
     * function.
     * @param index : Index of the log to be signed in.
     * @author Foong : Amos 18044418
     *
     */
    public void signVehicleIn(Database database, int index) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");
        LocalDateTime now = LocalDateTime.now();

        // Ensures that the log has not been signed in yet.
        if(database.getVehicleLogs().get(index).getInTime().trim().isEmpty()) {
            // Pulls the rego of the vehicle signing in.
            String rego = database.getVehicleLogs().get(index).getRego();

            // Enters the time signed in into the ArrayList.
            database.getVehicleLogs().get(index).setInTime(format.format(now));

            // Changes the availability of the vehicle to true in the Vehicles Board.
            changeVehicleAvailability(database, rego, true);

            // Backs up the Vehicle log to .dat file.
            database.backupVehicleLogs();

            // Performs changes in the Resident In/Out Log (tab 1) if signing in person is a resident.
            int residentLogIndex = database.lookupResidentLog(database.getVehicleLogs().get(index).getPerson());
            if(residentLogIndex != -1) {
                // Checks if resident has signed in within the Resident In/Out Log.
                if(database.getResidentLogs().get(residentLogIndex).getInTime().trim().isEmpty()) {
                    // Updates the time-in for the Resident In/Out Log.
                    database.getResidentLogs().get(residentLogIndex).setInTime(format.format(now));

                    // Generate a case-note for staff to read later. 
                    database.generateCaseNote(database.getVehicleLogs().get(index).getPerson(),
                            database.getVehicleLogs().get(index).getDestination(), "in");

                    // Changes the person's in-house status to true.
                    int residentIndex = database.lookupResident(database.getResidentLogs().get(residentLogIndex).getPerson());
                    if(residentIndex != -1) {
                        database.getResidentList().get(residentIndex).setPresent(true);
                    }

                    // Backs up the Resident log to .dat file.
                    database.backupResidentLogs();
                }
            }
        } else {
            // Outputs error message if user is trying to resign back in.
            JOptionPane.showMessageDialog(null, "Person is already signed in.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Updates the database.
        fireChanges(database);

        // Clears the highlighted part on the table.
        getDrivesTable().getTable().clearSelection();
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    /**
     * Method to change the availability of a Vehicle.
     *
     * @param database : Class containing all essential data for the program to
     * function.
     * @param rego : Rego number of the vehicle to be changed.
     * @param availability : T/F value describing the availability of the
     * Vehicle.
     * @author Foong : Amos 18044418
     *
     */
    public void changeVehicleAvailability(Database database, String rego, boolean availability) {
        // Changes the availability of the vehicle in the Vehicles Board.
        int vehicleIndex = database.lookupVehicle(rego);
        if(vehicleIndex != -1) {
            database.getVehicleList().get(vehicleIndex).setAvailable(availability);
        }
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    /**
     * Method to update the view of all the tables. Instantaneous response. As
     * soon as an entry has been made, the user will be able to view it.
     *
     * @param database : Class containing all essential data for the program to
     * function.
     * @author Foong : Amos 18044418
     *
     */
    public static void fireChanges(Database database) {
        // Updates all the tables' view.
        getDrivesTable().getTableModel().update(database.convertVehicleLogs());
        getDrivesTable().getTable().updateUI();
        getDrivesTable().getScrollPane().updateUI();

        InOutLogPanel.getInOutTable().getTableModel().update(database.convertResidentLogs());
        InOutLogPanel.getInOutTable().getTable().updateUI();
        InOutLogPanel.getInOutTable().getScrollPane().updateUI();

        VehiclesBoardPanel.getVehiclesTable().getTableModel().update(database.convertVehicleList());
        VehiclesBoardPanel.getVehiclesTable().getTable().updateUI();
        VehiclesBoardPanel.getVehiclesTable().getScrollPane().updateUI();

        ResidentsBoardPanel.getResidentsTable().getTableModel().update(database.convertResidentList());
        ResidentsBoardPanel.getResidentsTable().getTable().updateUI();
        ResidentsBoardPanel.getResidentsTable().getScrollPane().updateUI();
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}
