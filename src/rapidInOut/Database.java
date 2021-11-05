package rapidInOut;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 * This class manages a collection of files containing records such as
 * Residents, Vehicles, and their respective In/Out Logs.
 *
 * It possesses methods to access/modify these data, import/export data from/to
 * an external file, generate case note to a text file, convert ArrayLists to a
 * 2-D Object array, and lastly a lookup function.
 *
 * References
 * --------------------------------------------------------------------
 * Lanthier, M. (2014). Saving and loading information.
 * http://people.scs.carleton.ca/~lanthier/teaching/COMP1406/Notes/COMP1406_Ch11_FileIO.pdf
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class Database implements java.io.Serializable {

    protected ArrayList<Resident> residentList;
    protected ArrayList<Vehicle> vehicleList;
    protected ArrayList<ResidentLog> residentLogs;
    protected ArrayList<VehicleLog> vehicleLogs;

    /**
     * Default constructor for Database class, prepares the ArrayLists for use.
     *
     * @author Foong : Amos 18044418
     *
     */
    public Database() {
        residentList = new ArrayList<Resident>();
        vehicleList = new ArrayList<Vehicle>();
        residentLogs = new ArrayList<ResidentLog>();
        vehicleLogs = new ArrayList<VehicleLog>();
    }

    // Getter and setter methods for Object's instance data.
    //------------------------------------------------------------------
    /**
     * Accessor method to access Object's instance data.
     *
     * @return An ArrayList containing records of Residents.
     * @author Foong : Amos 18044418
     *
     */
    public ArrayList<Resident> getResidentList() {
        return residentList;
    }

    /**
     * Mutator method to modify the Object's instance data.
     *
     * @param residentList : List containing the records of Residents.
     * @author Foong : Amos 18044418
     *
     */
    public void setResidentList(ArrayList<Resident> residentList) {
        this.residentList = residentList;
    }
    //------------------------------------------------------------------

    /**
     * Accessor method to access Object's instance data.
     *
     * @return An ArrayList containing records of Vehicles.
     * @author Foong : Amos 18044418
     *
     */
    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * Mutator method to modify the Object's instance data.
     *
     * @param vehicleList : List containing the records of Vehicles.
     * @author Foong : Amos 18044418
     *
     */
    public void setVehicleList(ArrayList<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    //------------------------------------------------------------------

    /**
     * Accessor method to access Object's instance data.
     *
     * @return An ArrayList containing records of Residents' In/Out Logs.
     * @author Foong : Amos 18044418
     *
     */
    public ArrayList<ResidentLog> getResidentLogs() {
        return residentLogs;
    }

    /**
     * Mutator method to modify the Object's instance data.
     *
     * @param residentLogs : List containing the records of Resident's In/Out
     * Logs.
     * @author Foong : Amos 18044418
     *
     */
    public void setResidentLogs(ArrayList<ResidentLog> residentLogs) {
        this.residentLogs = residentLogs;
    }
    //------------------------------------------------------------------

    /**
     * Accessor method to access Object's instance data.
     *
     * @return An ArrayList containing records of the Drives' Logs.
     * @author Foong : Amos 18044418
     *
     */
    public ArrayList<VehicleLog> getVehicleLogs() {
        return vehicleLogs;
    }

    /**
     * Mutator method to modify the Object's instance data.
     *
     * @param vehicleLogs : List containing the records of Drives' Logs.
     * @author Foong : Amos 18044418
     *
     */
    public void setVehicleLogs(ArrayList<VehicleLog> vehicleLogs) {
        this.vehicleLogs = vehicleLogs;
    }
    //------------------------------------------------------------------

    // File IO methods for importing and exporting data from/onto .dat files.
    //=======================================================================================================
    /**
     * Method to import the records of Residents from a .dat file. File is
     * located in the Database folder which is in the same directory as the src
     * folder. It also has several error handling methods (try-catch) to handle
     * exceptions when the file is not found or when it has troubles reading the
     * file.
     *
     * @return An ArrayList of Residents.
     * @author Foong : Amos 18044418
     *
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Resident> importResidentList() {
        ArrayList<Resident> temp = new ArrayList<Resident>();

        try {
            // Specifies the file to be read from (under the Database folder in the directory of src).
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(".\\Database\\Residents.dat"));

            // Imports every Resident Object that is in the dat file 
            // and adds it to a local ArrayList of type Resident.  
            temp.addAll((ArrayList<Resident>) in.readObject());

            in.close();
        } catch(ClassNotFoundException e) {
            System.err.println("Error: Object's class does not match.");
            e.printStackTrace();
            // Fail-safe method to ensure program has usable data.
            RapidInOutApplication.initialiseResidentData(this);
        } catch(FileNotFoundException e) {
            System.err.println("Error: Cannot open file for reading.");
            e.printStackTrace();
            // Fail-safe method to ensure program has usable data.
            RapidInOutApplication.initialiseResidentData(this);
        } catch(IOException e) {
            System.err.println("Error: Cannot read from file.");
            e.printStackTrace();
            // Fail-safe method to ensure program has usable data.
            RapidInOutApplication.initialiseResidentData(this);
        }

        return temp;
    }

    /**
     * Method to export the records of Residents to a .dat file. Calls the
     * exportData method to transfer localised data onto a file. This method
     * also specifies the specific path and file name.
     *
     * @author Foong : Amos 18044418
     *
     */
    public void exportResidentList() {
        String fileName = ".\\Database\\Residents.dat";

        // Calls the exportData method passing in file Name and ArrayList.
        this.exportData(fileName, this.getResidentList());
    }
    //=======================================================================================================

    /**
     * Method to import the records of Vehicles from a .dat file. File is
     * located in the Database folder which is in the same directory as the src
     * folder. It also has several error handling methods (try-catch) to handle
     * exceptions when the file is not found or when it has troubles reading the
     * file.
     *
     * @return An ArrayList of Vehicles.
     * @author Foong : Amos 18044418
     *
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Vehicle> importVehicleList() {
        ArrayList<Vehicle> temp = new ArrayList<Vehicle>();

        try {
            // Specifies the file to be read from (under the Database folder in the directory of src).
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(".\\Database\\Vehicles.dat"));

            // Imports every Vehicle Object that is in the dat file  
            // and adds it to a local ArrayList of type Vehicle. 
            temp.addAll((ArrayList<Vehicle>) in.readObject());

            in.close();
        } catch(ClassNotFoundException e) {
            System.err.println("Error: Object's class does not match.");
            e.printStackTrace();
            // Fail-safe method to ensure program has usable data.
            RapidInOutApplication.initialiseVehicleData(this);
        } catch(FileNotFoundException e) {
            System.err.println("Error: Cannot open file for reading.");
            e.printStackTrace();
            // Fail-safe method to ensure program has usable data.
            RapidInOutApplication.initialiseVehicleData(this);
        } catch(IOException e) {
            System.err.println("Error: Cannot read from file.");
            e.printStackTrace();
            // Fail-safe method to ensure program has usable data.
            RapidInOutApplication.initialiseVehicleData(this);
        }

        return temp;
    }

    /**
     * Method to export the records of Vehicles to a .dat file. Calls the
     * exportData method to transfer localised data onto a file. This method
     * also specifies the specific path and file name.
     *
     * @author Foong : Amos 18044418
     *
     */
    public void exportVehicleList() {
        String fileName = ".\\Database\\Vehicles.dat";

        // Calls the exportData method passing in file Name and ArrayList.
        this.exportData(fileName, this.getVehicleList());
    }
    //=======================================================================================================

    /**
     * Method to import the records of Residents' In/Out Logs from a .dat file.
     * File is located in the Backup folder which is under the Database folder.
     * It also has several error handling methods (try-catch) to handle
     * exceptions when the file is not found or when it has troubles reading the
     * file.
     *
     * @param date : The date of the logs.
     * @return An ArrayList of Residents' In/Out Logs.
     * @author Foong : Amos 18044418
     *
     */
    @SuppressWarnings("unchecked")
    public ArrayList<ResidentLog> importResidentLogs(String date) {
        ArrayList<ResidentLog> temp = new ArrayList<ResidentLog>();

        try {
            String fileName = ".\\Database\\Backup\\" + date + " Resident Log Backup.dat";

            // Specifies the file to be read from (selected by the user via JFileChooser).
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));

            // Imports every ResidentLog Object that is in the .dat file  
            // and adds it to a local ArrayList of type ResidentLog. 
            temp.addAll((ArrayList<ResidentLog>) in.readObject());

            in.close();
        } catch(ClassNotFoundException e) {
            System.err.println("Error: Object's class does not match.");
            e.printStackTrace();
        } catch(FileNotFoundException e) {
            System.err.println("Error: Cannot open file for reading.");
            e.printStackTrace();
        } catch(IOException e) {
            System.err.println("Error: Cannot read from file.");
            e.printStackTrace();
        }

        return temp;
    }

    /**
     * Method to export/backup the records of Residents' In/Out Logs to a .dat
     * file. Calls the exportData method to transfer localised data onto a file.
     * This method also specifies the specific path and file name (with a
     * date-stamp).
     *
     * @author Foong : Amos 18044418
     *
     */
    public void backupResidentLogs() {
        // Formats date into specified pattern.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Gets current date.
        LocalDateTime currentDate = LocalDateTime.now();

        // Specifies a file path and creates a filename based on current date.
        String fileName = ".\\Database\\Backup\\";
        fileName += format.format(currentDate) + " Resident Log Backup.dat";

        // Calls the exportData method passing in file Name and ArrayList.
        this.exportData(fileName, this.getResidentLogs());
    }
    //=======================================================================================================

    /**
     * Method to import the records of Vehicle Logs from a .dat file. File is
     * located in the Backup folder which is under the Database folder. It also
     * has several error handling methods (try-catch) to handle exceptions when
     * the file is not found or when it has troubles reading the file.
     *
     * @param date : The date of the logs.
     * @return An ArrayList of Vehicle Logs.
     * @author Foong : Amos 18044418
     *
     */
    @SuppressWarnings("unchecked")
    public ArrayList<VehicleLog> importVehicleLogs(String date) {
        ArrayList<VehicleLog> temp = new ArrayList<VehicleLog>();

        try {
            String fileName = ".\\Database\\Backup\\" + date + " Vehicle Log Backup.dat";

            // Specifies the file to be read from (selected by the user via JFileChooser).
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));

            // Imports every ResidentLog Object that is in the dat file  
            // and adds it to a local ArrayList of type ResidentLog. 
            temp.addAll((ArrayList<VehicleLog>) in.readObject());

            in.close();
        } catch(ClassNotFoundException e) {
            System.err.println("Error: Object's class does not match.");
            e.printStackTrace();
        } catch(FileNotFoundException e) {
            System.err.println("Error: Cannot open file for reading.");
            e.printStackTrace();
        } catch(IOException e) {
            System.err.println("Error: Cannot read from file.");
            e.printStackTrace();
        }

        return temp;
    }

    /**
     * Method to export/backup the records of Vehicle Logs to a .dat file. Calls
     * the exportData method to transfer localised data onto a file. This method
     * also specifies the specific path and file name (with a date-stamp).
     *
     * @author Foong : Amos 18044418
     *
     */
    public void backupVehicleLogs() {
        // Formats date into specified pattern.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Gets current date.
        LocalDateTime currentDate = LocalDateTime.now();

        // Specifies a file path and creates a filename based on current date.
        String fileName = ".\\Database\\Backup\\";
        fileName += format.format(currentDate) + " Vehicle Log Backup.dat";

        // Calls the exportData method passing in file Name and ArrayList.
        this.exportData(fileName, this.getVehicleLogs());
    }
    //=======================================================================================================

    /**
     * Shared method, any method that contains the keyword "export" or "backup"
     * will summon upon this method. This method performs the exportation of
     * data onto an external file. It overwrites the file. It writes the data as
     * binary format and from a whole Object.
     *
     * @param fileName : File path and name that is to be written to.
     * @param list : An ArrayList containing the data to be written.
     * @author Foong : Amos 18044418
     *
     */
    public void exportData(String fileName, ArrayList<?> list) {
        try {
            // Specifies the file to be written to (overwrites it). 
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName, false));

            // Exports the passed in list data onto a dat file.
            out.writeObject(list);

            out.close();
        } catch(FileNotFoundException e) {
            System.err.println("Error: Cannot open file for writing.");
            e.printStackTrace();
        } catch(IOException e) {
            System.err.println("Error: Cannot write to file.");
            e.printStackTrace();
        }
    }
    //=======================================================================================================

    /**
     * Method to generate a human readable text file that comprises a sentence
     * containing the time, name of the person, and destination (called a case
     * note). Text file is also time and date-stamped, has the ability to
     * generate separate files for each day, and log times of entries.
     *
     * @param name : Name of the Resident going out/returning in.
     * @param destination : Place the Resident is departing to.
     * @param inOut : Word describing the Resident's movement.
     * @author Foong : Amos 18044418
     *
     */
    public void generateCaseNote(String name, String destination, String inOut) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");

        // Gets current date & time.
        LocalDateTime now = LocalDateTime.now();

        // Specifies a file path and creates a filename based on current date.
        String fileName = ".\\Database\\Case Notes\\";
        fileName += formatDate.format(now) + " Case Notes.txt";

        try {
            // Appends to a text file, adds each entry to the text file.
            PrintWriter out = new PrintWriter(new FileOutputStream(fileName, true));

            // Outputs a sentence describing the person going in/out, where, and when, to a text file. 
            out.println(formatTime.format(now) + " hrs: " + name + " clearing "
                    + inOut + (inOut.equals("in") ? " from " : " to ") + destination + ".");

            out.close();
        } catch(FileNotFoundException e) {
            System.err.println("Error: Cannot open file for writing.");
            e.printStackTrace();
        }
    }
    //=======================================================================================================

    // Class generaliser/converter to convert data (from 
    // created classes) so it can be displayed onto a table.
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * This method converts an ArrayList into a two-dimensional Object array.
     * The 2-D array is then later used by the TableModel class to organise and
     * display data in designated columns. Each record is placed on the same
     * row.
     *
     * @return A two-dimensional Object array containing information that can be
     * displayed onto a table (JTable).
     * @author Foong : Amos 18044418
     *
     */
    public Object[][] convertResidentList() {
        // Gets the size of the ArrayList.
        int firstDimension = this.residentList.size();
        // Gets the number of object instance data one class has (# of object attributes).
        int secondDimension = this.residentList.getClass().getDeclaredFields().length;

        Object[][] data = new Object[firstDimension][secondDimension];

        // For loop to transform the ArrayList to a 2-D Object array.
        for(int i = 0; i < firstDimension; i++) {
            data[i][0] = this.residentList.get(i).getFullName();
            data[i][1] = this.residentList.get(i).getLevel();
            data[i][2] = this.residentList.get(i).getJob();
            data[i][3] = this.residentList.get(i).isPresent();
        }

        return data;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * This method converts an ArrayList into a two-dimensional Object array.
     * The 2-D array is then later used by the TableModel class to organise and
     * display data in designated columns. Each record is placed on the same
     * row.
     *
     * @return A two-dimensional Object array containing information that can be
     * displayed onto a table (JTable).
     * @author Foong : Amos 18044418
     *
     */
    public Object[][] convertVehicleList() {
        // Gets the size of the ArrayList.
        int firstDimension = this.vehicleList.size();
        // Gets the number of object instance data one class has (# of object attributes).
        int secondDimension = this.vehicleList.getClass().getDeclaredFields().length;

        Object[][] data = new Object[firstDimension][secondDimension];

        // For loop to transform the ArrayList to a 2-D Object array.
        for(int i = 0; i < firstDimension; i++) {
            data[i][0] = this.vehicleList.get(i).getRego();
            data[i][1] = this.vehicleList.get(i).getMake();
            data[i][2] = this.vehicleList.get(i).getModel();
            data[i][3] = this.vehicleList.get(i).getYear();
            data[i][4] = this.vehicleList.get(i).isAvailable();
        }

        return data;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * This method converts an ArrayList into a two-dimensional Object array.
     * The 2-D array is then later used by the TableModel class to organise and
     * display data in designated columns. Each record is placed on the same
     * row.
     *
     * @return A two-dimensional Object array containing information that can be
     * displayed onto a table (JTable).
     * @author Foong : Amos 18044418
     *
     */
    public Object[][] convertResidentLogs() {
        // Gets the size of the ArrayList.
        int firstDimension = this.residentLogs.size();
        // Gets the number of object instance data one class has (# of object attributes).
        int secondDimension = this.residentLogs.getClass().getDeclaredFields().length;

        Object[][] data = new Object[firstDimension][secondDimension];

        // For loop to transform the ArrayList to a 2-D Object array.
        for(int i = 0; i < firstDimension; i++) {
            data[i][0] = this.residentLogs.get(i).getPerson();
            data[i][1] = this.residentLogs.get(i).getDestination();
            data[i][2] = this.residentLogs.get(i).getOutTime();
            data[i][3] = this.residentLogs.get(i).getInTime();
            data[i][4] = this.residentLogs.get(i).isLunchFeedback();
            data[i][5] = this.residentLogs.get(i).isDinnerFeedback();
        }

        return data;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * This method converts an ArrayList into a two-dimensional Object array.
     * The 2-D array is then later used by the TableModel class to organise and
     * display data in designated columns. Each record is placed on the same
     * row.
     *
     * @return A two-dimensional Object array containing information that can be
     * displayed onto a table (JTable).
     * @author Foong : Amos 18044418
     *
     */
    public Object[][] convertVehicleLogs() {
        // Gets the size of the ArrayList.
        int firstDimension = this.vehicleLogs.size();
        // Gets the number of object instance data one class has (# of object attributes).
        int secondDimension = this.vehicleLogs.getClass().getDeclaredFields().length;

        Object[][] data = new Object[firstDimension][secondDimension];

        // For loop to transform the ArrayList to a 2-D Object array.
        for(int i = 0; i < firstDimension; i++) {
            data[i][0] = this.vehicleLogs.get(i).getRego();
            data[i][1] = this.vehicleLogs.get(i).getPerson();
            data[i][2] = this.vehicleLogs.get(i).getDestination();
            data[i][3] = this.vehicleLogs.get(i).getOutTime();
            data[i][4] = this.vehicleLogs.get(i).getInTime();
            data[i][5] = this.vehicleLogs.get(i).isLunchFeedback();
            data[i][6] = this.vehicleLogs.get(i).isDinnerFeedback();
        }

        return data;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // Search methods to look through the database records.
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    /**
     * Method to access the database Residents' records and perform a linear
     * search for a specific person according to their name. If the passed in
     * name (key) matches/partially matches a name in the Resident's ArrayList,
     * it will return the index of the first occurrence of that Resident.
     *
     * @param name : The name to be searching for.
     * @return An integer number describing the index of the record.
     * @author Foong : Amos 18044418
     *
     */
    public int lookupResident(String name) {
        // For loop to perform linear search of the Residents ArraysList.
        for(int i = 0; i < this.getResidentList().size(); i++) {
            // If passed in name matches/partially matches 
            // a resident in database records...
            if(this.getResidentList().get(i).getFullName().contains(name)) {
                // Returns the index of the Resident in the ArraysList.
                return i;
            }
        }

        // Return -1 if not found.
        return -1;
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    /**
     * Method to access the database Resident In/Out Log records and perform a
     * linear search for a specific person according to their name. If the
     * passed in name (key) matches/partially matches a name in the Resident
     * Log's ArrayList, it will return the index of the first occurrence of that
     * Log.
     *
     * @param name : The name to be searching for.
     * @return An integer number describing the index of the record.
     * @author Foong : Amos 18044418
     *
     */
    public int lookupResidentLog(String name) {
        // For loop to perform linear search of the Residents ArraysList.
        for(int i = 0; i < this.getResidentLogs().size(); i++) {
            // If passed in name matches to a resident in database records...
            if(this.getResidentLogs().get(i).getPerson().contains(name)) {
                // Returns the index of the Resident in the ArraysList.
                return i;
            }
        }

        // Return -1 if not found.
        return -1;
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    /**
     * Method to access the database Vehicles' records and perform a linear
     * search for a specific vehicle according to their rego. If the passed in
     * rego (key) matches/partially matches a rego in the Vehicles' ArrayList,
     * it will return the index of the first occurrence of that Vehicle.
     *
     * @param rego : The rego to be searching for.
     * @return An integer number describing the index of the record.
     * @author Foong : Amos 18044418
     *
     */
    public int lookupVehicle(String rego) {
        // For loop to perform linear search of the Vehicles ArraysList.
        for(int i = 0; i < this.getVehicleList().size(); i++) {
            // If passed in rego matches/partially matches
            // to a Vehicle in database records...
            if(this.getVehicleList().get(i).getRego().contains(rego)) {
                // Returns the index of the vehicle in the ArraysList.
                return i;
            }
        }

        // Return -1 if not found.
        return -1;
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    /**
     * Method to access the database Vehicle In/Out Log records and perform a
     * linear search for a specific vehicle according to the driver. If the
     * passed in driver's name (key) matches/partially matches a name in the
     * Vehicle Log ArrayList, it will return the index of the first occurrence
     * of that Log.
     *
     * @param name : The driver's name to be searching for.
     * @return An integer number describing the index of the record.
     * @author Foong : Amos 18044418
     *
     */
    public int lookupVehicleLog(String name) {
        // For loop to perform linear search of the VehicleLogs ArraysList.
        for(int i = 0; i < this.getVehicleLogs().size(); i++) {
            // If passed in name matches/partially matches
            // to a resident in database records...
            if(this.getVehicleLogs().get(i).getPerson().contains(name)) {
                // Returns the index of the Vehicle Log in the ArraysList.
                return i;
            }
        }

        // Return -1 if not found.
        return -1;
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}
