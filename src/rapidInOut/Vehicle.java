package rapidInOut;

/**
 * This is a Model Class. It stores information about a Vehicle. Information
 * such as Rego, Make, Model, Year, and Availability.
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class Vehicle implements java.io.Serializable {

    private String rego;
    private String make;
    private String model;
    private String year;
    private boolean available;

    /**
     * 4-parameter constructor for the Vehicle class.
     *
     * @param rego : Rego of the vehicle
     * @param make : Make of the vehicle
     * @param model : Model of the vehicle
     * @param year : Year of the vehicle
     * @author Foong : Amos 18044418
     *
     */
    public Vehicle(String rego, String make, String model, String year) {
        this.setRego(rego);
        this.setMake(make);
        this.setModel(model);
        this.setYear(year);
        this.setAvailable(true);
    }

    /**
     * 1-parameter constructor for the Vehicle class.
     *
     * @param rego : Rego of the vehicle.
     * @author Foong : Amos 18044418
     *
     */
    public Vehicle(String rego) {
        this.setRego(rego);
        this.setMake("");
        this.setModel("");
        this.setYear("");
        this.setAvailable(true);
    }

    // Getter and setter methods for Object's instance data.
    //------------------------------------------------------------------
    public String getRego() {
        return rego;
    }

    public void setRego(String rego) {
        // Validates rego number passed in, capitalises the   
        // entry, then sets it to Object's instance data. 
        this.rego = (rego.trim().isEmpty() ? "UNKNOWN" : rego.toUpperCase());
    }
    //------------------------------------------------------------------

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        // Validates make of Vehicle passed in  
        // then sets it to Object's instance data. 
        this.make = (make.trim().isEmpty() ? "UNKNOWN" : make);
    }
    //------------------------------------------------------------------

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        // Validates model of Vehicle passed in  
        // then sets it to Object's instance data. 
        this.model = (model.trim().isEmpty() ? "UNKNOWN" : model);
    }
    //------------------------------------------------------------------

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        // Validates year of Vehicle passed in  
        // then sets it to Object's instance data. 
        this.year = (year.trim().isEmpty() ? "0000" : year);
    }
    //------------------------------------------------------------------

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    //------------------------------------------------------------------

    @Override
    public String toString() {
        return this.getRego() + " (" + this.getModel() + ")";
    }
}
