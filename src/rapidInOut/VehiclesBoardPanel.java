package rapidInOut;

import java.awt.*;
import java.util.*;

import javax.swing.*;

/**
 * This class is displayed to the user. This is the 3rd-level of the frame. It
 * displays useful information such as all the Vehicles and their respective
 * information to the user. It is interactive and user-friendly. It possesses a
 * sorting function which could sort by Rego, Make, Model, Year or Availability.
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class VehiclesBoardPanel extends JPanel {

    private static TableView vehiclesTable;

    /**
     * The default constructor for the Panel. Prepares the GUI component (Table)
     * for use. Table possess sorting function.
     *
     * @param database : Class that contains all the required data for the
     * program to function.
     * @author Foong : Amos 18044418
     *
     */
    public VehiclesBoardPanel(Database database) {
        super(new GridLayout(1, 0));
        this.setPreferredSize(new Dimension(815, 475));

        // Header for the Vehicles' Board.
        ArrayList<String> vehiclesBoardHeader = new ArrayList<String>(
                Arrays.asList("Rego", "Make", "Model", "Year", "Available"));

        // Prepares the table that is to be displayed, has sorter function enabled.
        vehiclesTable = new TableView(vehiclesBoardHeader, database.convertVehicleList(), true);
        this.add(vehiclesTable);
    }

    // Getter method for Object's instance data.
    //-------------------------------------------
    public static TableView getVehiclesTable() {
        return vehiclesTable;
    }
    //-------------------------------------------
}
