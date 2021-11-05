package rapidInOut;

import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
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
public class InOutLogPanel extends JPanel implements DocumentListener {

    private static TableView inOutTable;
    private JLabel logoLabel;
    private QueryView queryFields;
    private InOutButtonsView inOutButtons;
    private LiveClockView clockLabel;

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
    public InOutLogPanel(Database database) {
        // Allows to manually configure component location and sizes.
        this.setLayout(null);

        // Header for the Resident In/Out Log table.
        ArrayList<String> inOutLogHeader = new ArrayList<String>(
                Arrays.asList("Name", "Destination", "Time Out", "Time In", "FB: Lunch", "FB: Dinner"));

        // Prepares the TABLE for use, has sorter function disabled.
        inOutTable = new TableView(inOutLogHeader, database.convertResidentLogs(), false);
        inOutTable.setLocation(2, 5);
        inOutTable.setSize(inOutTable.getPreferredSize());
        inOutTable.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Enable/disable SIGN IN button depending if an item is highlighted.
                if(!inOutTable.getTable().getSelectionModel().isSelectionEmpty()) {
                    inOutButtons.getInButton().setEnabled(true);
                } else {
                    inOutButtons.getInButton().setEnabled(false);
                }
            }
        });
        this.add(inOutTable);

        // Prepares the COMPANY LOGO for use.
        logoLabel = new JLabel(new ImageIcon(".\\Images\\OdysseyLogo240x100.png"));
        logoLabel.setLocation(562, 10);
        logoLabel.setSize(logoLabel.getPreferredSize());
        this.add(logoLabel);

        // Prepares the QUERY FIELDS for use.
        queryFields = new QueryView();
        queryFields.setLocation(560, 113);
        queryFields.setSize(queryFields.getPreferredSize());
        queryFields.getNameField().getDocument().addDocumentListener(this);
        queryFields.getDestinationField().getDocument().addDocumentListener(this);
        queryFields.getDestinationField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If both text fields are filled, enable the ENTER KEY function...
                if(!queryFields.getNameField().getText().isEmpty()
                        && !queryFields.getDestinationField().getText().isEmpty()) {
                    // Signs resident out when enter key
                    //  is pressed in the destination field.
                    signResidentOut(database);
                }

                // Uncomment this if you would like to test scroll pane without 
                // having to enter any data (allows blank entries) (DEBUG FUNCTION).
//				signVehicleOut(database);
            }
        });
        this.add(queryFields);

        // Prepares the BUTTONS for use.
        inOutButtons = new InOutButtonsView();
        inOutButtons.setLocation(560, 253);
        inOutButtons.setSize(inOutButtons.getPreferredSize());
        inOutButtons.getOutButton().setEnabled(false);
        inOutButtons.getOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Signs the resident out upon button press.
                signResidentOut(database);
            }
        });
        inOutButtons.getInButton().setEnabled(false);
        inOutButtons.getInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Grabs the index of highlighted item and 
                // signs them in upon button press.
                int index = inOutTable.getTable().getSelectedRow();
                signResidentIn(database, index);
            }
        });
        this.add(inOutButtons);

        // Prepares a TICKING CLOCK for use.
        clockLabel = new LiveClockView();
        clockLabel.setLocation(560, 402);
        clockLabel.setSize(clockLabel.getPreferredSize());
        // Makes the clock alive (real-time ticking clock).
        DateTimeFormatter formatClock = DateTimeFormatter.ofPattern("E dd/MM/yyyy hh:mm:ss a");
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            LocalDateTime now = LocalDateTime.now();
            clockLabel.getClockLabel().setText(formatClock.format(now)); // Updates every second.
        });
        timer.setRepeats(true);
        timer.start();
        this.add(clockLabel);
    }

    // Getter methods for Object's instance data.
    //-------------------------------------------
    public static TableView getInOutTable() {
        return inOutTable;
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

    public LiveClockView getClockLabel() {
        return clockLabel;
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
     * Method that checks the Name & Destination fields and returns a boolean
     * value describing the state of each fields. The boolean values is then
     * used to assess whether to activate/disable the OUT BUTTON. This method is
     * shared between all the overridden methods of the DocumentListener above.
     *
     * @author Foong : Amos 18044418
     *
     */
    public void setOutButton() {
        // Activates OUT button when both text fields 
        // (name and destination) are not empty.
        inOutButtons.getOutButton().setEnabled(
                !queryFields.getNameField().getText().isEmpty()
                && !queryFields.getDestinationField().getText().isEmpty());
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // Signing in and out methods and their related functions.
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    /**
     * Method that contains a set of instructions to sign a Resident out. It
     * pulls data from the Query Fields and creates a new Object for specific
     * ArrayList, it then resets the fields.
     *
     * It generates a case note and also changes the presence of a resident.
     *
     * It also backs up the changes & updates the tables across all tabs.
     *
     * @param database : Class containing all essential data for the program to
     * function.
     * @author Foong : Amos 18044418
     *
     */
    public void signResidentOut(Database database) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");
        LocalDateTime now = LocalDateTime.now();

        String name, destination, timeOut;
        boolean lunchFB, dinnerFB;

        // Procures/pulls user entered data from the UI and saves it locally.
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

        // Adds the new entry to the database records.
        database.getResidentLogs().add(new ResidentLog(name, destination, timeOut, lunchFB, dinnerFB));

        // Changes the presence of the resident to false in the Residents Board.
        changeResidentPresence(database, name, false);

        // Generates a case note (outputs to text file).
        database.generateCaseNote(name, destination, "out");

        // Backs up the log to .dat file.
        database.backupResidentLogs();

        // Calls a method to update the table view.
        fireChanges(database);

        // Clears the highlighted part on the table.
        getInOutTable().getTable().clearSelection();
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    /**
     * Method that contains a sequence of instructions to sign a Resident in. It
     * uses the index passed in from the caller and searches for the specific
     * Object in an ArrayList, if the log has not been signed in yet, it
     * time-stamps (Time In) the log then changes the availability of the
     * vehicle.
     *
     * Also checks if the person is returning from a drive, if they are, it will
     * sign the person in within the Vehicle Log (synchronise logs) as well as
     * changing the vehicle's availability.
     *
     * It also backs up the changes & updates the tables across all tabs.
     *
     * @param database : Class containing all essential data for the program to
     * function.
     * @param index : Index of the log to be signed in.
     * @author Foong : Amos 18044418
     *
     */
    public void signResidentIn(Database database, int index) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");
        LocalDateTime now = LocalDateTime.now();

        // Ensures that the log has not been signed in yet.
        if(database.getResidentLogs().get(index).getInTime().trim().isEmpty()) {
            // Extracts the name of the resident signing in.
            String name = database.getResidentLogs().get(index).getPerson();

            // Enters the time signed in into the ArrayList.
            database.getResidentLogs().get(index).setInTime(format.format(now));

            // Changes the presence of the resident to true in the Residents Board.
            changeResidentPresence(database, name, true);

            // Generates a case note (outputs to text file).
            database.generateCaseNote(database.getResidentLogs().get(index).getPerson(),
                    database.getResidentLogs().get(index).getDestination(), "in");

            // Backs up the log to .dat file.
            database.backupResidentLogs();

            // Signs the resident in within the Vehicle Log as well.
            int vehicleLogIndex = database.lookupVehicleLog(name);
            if(vehicleLogIndex != -1) {
                // If Vehicle log has not been signed in yet...
                if(database.getVehicleLogs().get(vehicleLogIndex).getInTime().trim().isEmpty()) {
                    // Signs the Resident in within the Vehicle Log.
                    database.getVehicleLogs().get(vehicleLogIndex).setInTime(format.format(now));

                    // Backs up the Vehicle logs.
                    database.backupVehicleLogs();

                    // Sets the Vehicle status to available.
                    int vehicleIndex = database.lookupVehicle(database.getVehicleLogs().get(vehicleLogIndex).getRego());
                    if(vehicleIndex != -1) {
                        database.getVehicleList().get(vehicleIndex).setAvailable(true);
                    }
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
        getInOutTable().getTable().clearSelection();
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    /**
     * Method to change the in-house status of a Resident.
     *
     * @param database : Class containing all essential data for the program to
     * function.
     * @param name : Name of the person's presence to be changed.
     * @param presence : T/F value describing the in-house status of the
     * Resident.
     * @author Foong : Amos 18044418
     *
     */
    public void changeResidentPresence(Database database, String name, boolean presence) {
        // Changes the presence status of the Resident in the Residents Board.
        int residentIndex = database.lookupResident(name);
        if(residentIndex != -1) {
            database.getResidentList().get(residentIndex).setPresent(presence);
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
        // Updates all the tabs' views.
        getInOutTable().getTableModel().update(database.convertResidentLogs());
        getInOutTable().getTable().updateUI();
        getInOutTable().getScrollPane().updateUI();

        DrivesLogPanel.getDrivesTable().getTableModel().update(database.convertVehicleLogs());
        DrivesLogPanel.getDrivesTable().getTable().updateUI();
        DrivesLogPanel.getDrivesTable().getScrollPane().updateUI();

        VehiclesBoardPanel.getVehiclesTable().getTableModel().update(database.convertVehicleList());
        VehiclesBoardPanel.getVehiclesTable().getTable().updateUI();
        VehiclesBoardPanel.getVehiclesTable().getScrollPane().updateUI();

        ResidentsBoardPanel.getResidentsTable().getTableModel().update(database.convertResidentList());
        ResidentsBoardPanel.getResidentsTable().getTable().updateUI();
        ResidentsBoardPanel.getResidentsTable().getScrollPane().updateUI();
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}
