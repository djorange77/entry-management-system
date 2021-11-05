package rapidInOut;

import java.util.*;

import javax.swing.table.*;

/**
 * This is a Model class that initialises data to the table that is to be
 * displayed. It implements an abstract class. *I don't really know what this
 * class is and my description may be inaccurate.
 *
 * References ----------------------------------------------------------- The
 * Java� Tutorials. (n.d.). How to Use Tables. Oracle.
 * https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#modelchange
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private Object[][] data;

    /**
     * The default constructor for the TableModel. Prepares the table for use.
     * Receives the data (as a 2-D Object array) to be displayed alongside its
     * respective headers. Used across all four tabs.
     *
     * @param columnNames : Column headers to describe the column of data.
     * @param data : A 2-D Object array consisting all the data to be displayed.
     * @author Foong : Amos 18044418
     *
     */
    public TableModel(ArrayList<String> columnNames, Object[][] data) {
        this.setColumnNames(columnNames);
        this.setData(data);
    }

    // Getter and setter methods for Object's instance data.
    //---------------------------------------------------------
    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(ArrayList<String> columnNames) {
        this.columnNames = columnNames;
    }
    //---------------------------------------------------------

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }
    //---------------------------------------------------------

    // Overridden methods from the Abstract class.
    //############################################
    @Override
    public int getColumnCount() {
        return this.columnNames.size();
    }

    @Override
    public int getRowCount() {
        return this.data.length;
    }
    //############################################

    @Override
    public String getColumnName(int col) {
        return this.columnNames.get(col);
    }
    //############################################

    @Override
    public Object getValueAt(int row, int col) {
        return this.data[row][col];
    }
    //############################################

    /**
     * This method allows boolean values to be displayed as check boxes.
     *
     */
    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    //############################################

    /**
     * Allows other classes to update the data to be displayed by calling the
     * set (mutator) method and passing in the passed in updated 2-D Object
     * array.
     *
     * @param data : Updated data to be displayed.
     * @author Foong : Amos 18044418
     */
    public void update(Object[][] data) {
        this.setData(data);
    }
}
