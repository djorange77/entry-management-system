package rapidInOut;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * This is a View Class that amalgamates the JTable and the JScrollPane so that
 * it can be displayed later.
 *
 * References ----------------------------------------------------------- The
 * Java� Tutorials. (n.d.). How to Use Tables. Oracle.
 * https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#modelchange
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class TableView extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private TableModel tableModel;

    /**
     * The default constructor for the Panel. Prepares the GUI component for
     * viewing. Initialises the Table with a Table Model and a Scroll Pane, so
     * it can hold/display more data if need be. This class is shared across all
     * tabs.
     *
     * @param header : Column headers for the table.
     * @param data : 2-D Object array containing the data.
     * @param sorter : Whether to enable/disable the sorting function.
     * @author Foong : Amos 18044418
     *
     */
    public TableView(ArrayList<String> header, Object[][] data, boolean sorter) {
        // Initialise the TableModel.
        tableModel = new TableModel(header, data);

        // Prepares the table component for use.
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(525, 400));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(sorter); 		// Enables/disables sorter for individual columns.

        // Enables scrolling function for the table (scroll bar only appears when needed).
        scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(table);
        add(scrollPane);
        this.setPreferredSize(new Dimension(560, 430));

//		this.setPreferredSize(new Dimension(535, 425));
    }

    // Getter methods for Object's instance data.
    //--------------------------------------------
    public JTable getTable() {
        return this.table;
    }
    //--------------------------------------------

    public TableModel getTableModel() {
        return this.tableModel;
    }
    //--------------------------------------------

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
    //--------------------------------------------
}
