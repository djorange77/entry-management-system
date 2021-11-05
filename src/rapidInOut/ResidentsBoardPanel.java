package rapidInOut;

import java.awt.*;
import java.util.*;

import javax.swing.*;

/**
 * This class is displayed to the user. This is the 3rd-level of the frame. It
 * displays useful information such as all the Residents and their respective
 * information to the user. It is interactive and user-friendly. It possesses a
 * sorting function which could sort by Name, Level, Job, or Presence.
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class ResidentsBoardPanel extends JPanel {

    private static TableView residentsTable;

    /**
     * The default constructor for the Panel. Prepares the GUI component (Table)
     * for use. Table possess sorting function.
     *
     * @param database : Class that contains all the required data for the
     * program to function.
     * @author Foong : Amos 18044418
     *
     */
    public ResidentsBoardPanel(Database database) {
        super(new GridLayout(1, 0));
        this.setPreferredSize(new Dimension(815, 475));

        // Header for the Residents' Board.
        ArrayList<String> residentsBoardHeader = new ArrayList<String>(
                Arrays.asList("Name", "Level Status", "Job Function", "In House"));

        // Prepares the table that is to be displayed, has sorter function enabled.
        residentsTable = new TableView(residentsBoardHeader, database.convertResidentList(), true);
        this.add(residentsTable);
    }

    // Getter method for Object's instance data.
    //-------------------------------------------
    public static TableView getResidentsTable() {
        return residentsTable;
    }
    //-------------------------------------------
}
