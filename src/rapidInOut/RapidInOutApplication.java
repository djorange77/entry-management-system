package rapidInOut;

import java.util.*;

import javax.swing.*;

/**
 * This is the driver-class. Program execution begins here. It calls several
 * data initialisation methods to prepare the essential data that will be used.
 * It also has a JFrame which houses the Tabs and makes the whole program
 * usable.
 *
 * @author Foong : Amos 18044418
 *
 */
public class RapidInOutApplication {

    /**
     * Entry point of the program.
     *
     * @param args : Unused parameter.
     * @author Foong : Amos 18044418
     *
     */
    public static void main(String[] args) {
        Database testData = new Database();

        // Calls the importData method to import data from .dat files.
        importData(testData);

        // Initialises a new JFrame for use.
        JFrame frame = new JFrame("Rapid In Out (RIO)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(833, 515);
        frame.setResizable(false);

        // Initialises the Tabs Component for use.
        TabsView tabs = new TabsView(testData);
        tabs.setLocation(1, 0);
        tabs.setSize(tabs.getPreferredSize());
        frame.getContentPane().add(tabs); 		// Add the Tabs component to the frame.

        // Make the program appear =)
        frame.setVisible(true);
    }

    /**
     * Method to import data from a .dat files and store it locally.
     *
     * @param testImportedData : Data Object containing essential data that
     * program needs to function.
     * @author Foong : Amos 18044418
     *
     */
    public static void importData(Database testImportedData) {
        // Imports data and saves it locally by calling one of the Database methods.
        testImportedData.getResidentList().addAll(testImportedData.importResidentList());

        // Imports data and saves it locally by calling one of the Database methods.
        testImportedData.getVehicleList().addAll(testImportedData.importVehicleList());
    }

    /**
     * Method that adds hard-coded data to the database. This method is intended
     * as a fail-safe method in case the import function fails.
     *
     * @param testData : Database Object containing essential data that the
     * program requires to function.
     * @author Foong : Amos 18044418
     *
     */
    public static void initialiseResidentData(Database testData) {
        // Adds the Residents that the organisation manages to the Residents' List		
        testData.getResidentList().add(new Resident("Amos Foong", Level.LEVEL_4_REENTRY, Job.HOUSE_DRIVER));
        testData.getResidentList().add(new Resident("Darth Vader", Level.LEVEL_4, Job.SUPERVISOR));
        testData.getResidentList().add(new Resident("Richard Hareke", Level.LEVEL_3, Job.COORDINATOR));
        testData.getResidentList().add(new Resident("James Dean", Level.LEVEL_2, Job.RAMROD));
        testData.getResidentList().add(new Resident("David Jones", Level.LEVEL_1, Job.ROUSTABOUT));
        testData.getResidentList().add(new Resident("Hector Barbossa", Level.STARTER, Job.START_STRUCTURE));
        testData.getResidentList().add(new Resident("James Bond", Level.STATUS, Job.SPORT_STRUCTURE));
        testData.getResidentList().add(new Resident("Jane Smith", Level.LIMBO, Job.MAINTENANCE));
        testData.getResidentList().add(new Resident("Ashley Wright", Level.LEVEL_4, Job.HOUSE_DRIVER));
        testData.getResidentList().add(new Resident("Chris Wallace", Level.LEVEL_4_REENTRY, Job.FLOATER));
        testData.getResidentList().add(new Resident("Suresh Govind", Level.LEVEL_3, Job.PNG));
    }

    /**
     * Method that adds hard-coded data to the database. This method is intended
     * as a fail-safe method in case the import function fails.
     *
     * @param testData : Database Object containing essential data that the
     * program requires to function.
     * @author Foong : Amos 18044418
     *
     */
    public static void initialiseVehicleData(Database testData) {
        // Adds a fleet of Vehicles to the Vehicles' List.
        testData.getVehicleList().add(new Vehicle("HDW154", "Hyundai", "i30", "2014"));
        testData.getVehicleList().add(new Vehicle("JLE578", "Hyundai", "iLoad", "2020"));
        testData.getVehicleList().add(new Vehicle("EDM379", "Toyota", "Hiace", "2010"));
        testData.getVehicleList().add(new Vehicle("FKR750", "Toyota", "Hiace", "2011"));
        testData.getVehicleList().add(new Vehicle("GUW468", "Toyota", "Hiace", "2012"));
        testData.getVehicleList().add(new Vehicle("MGM996", "Toyota", "Hiace", "2014"));
    }

    /**
     * This method was intended for console IO, however, as I changed to GUI,
     * this method is redundant. I initially used this method to test my
     * Collections skills.
     *
     * @param scanner : Scanner to grab user input.
     * @param testData : Database object to add user defined Resident to.
     * @author Foong : Amos 18044418
     *
     */
    public static void addResident(Scanner scanner, Database testData) {
        String inputName = "";

        do {
            System.out.println("Name:");
            inputName = scanner.next();

            if(inputName.equals("q")) {
                continue;
            }

            System.out.println("Level:");
            int level = scanner.nextInt();

            System.out.println("Job");
            int job = scanner.nextInt();

            testData.getResidentList().add(new Resident(inputName, Level.values()[level], Job.values()[job]));
        } while(!(inputName.equals("q")));
    }
}
