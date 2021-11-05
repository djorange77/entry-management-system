package rapidInOut;

import java.awt.*;

import javax.swing.*;

/**
 * This class integrates all the Panels together and display them in separate
 * tabs. Allows the user to quickly switch from one function to the other
 * depending on their needs. This tabbed method could reduce the required number
 * of button presses to access different functions and provide instantaneous
 * interaction with the user. This is the second-level of the GUI.
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class TabsView extends JPanel {

    private JTabbedPane tabs;

    /**
     * Default constructor for TabsView. It prepares the tabs for use. It adds
     * all the Panels that will be displays and outputs them to the user.
     *
     * @param database : Contains essential collections of data.
     * @author Foong : Amos 18044418
     *
     */
    public TabsView(Database database) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(815, 475));

        tabs = new JTabbedPane();
        tabs.add("In/Out Log", new InOutLogPanel(database));
        tabs.add("Drives Log", new DrivesLogPanel(database));
        tabs.add("Residents' Board", new ResidentsBoardPanel(database));
        tabs.add("Vehicles' Board", new VehiclesBoardPanel(database));
        tabs.setLocation(0, 0);
        tabs.setSize(815, 475);
        this.add(tabs);
    }
}
