package rapidInOut;

/**
 * This is a Model Class. It stores information about a Vehicle Log. Information
 * such as Rego, Driver, Destination, Time Out, Time In, Lunch/Dinner Feedback.
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class VehicleLog extends ResidentLog implements java.io.Serializable {

    private String rego;

    /**
     * Default constructor to create a new entry of a Vehicle In/Out Log.
     *
     * @param rego : Rego of the vehicle going out.
     * @param person : Name of the driver going out.
     * @param destination : Place the driving is leaving for.
     * @param outTime : Time of departure.
     * @param lunch : If the driver requires lunch to be saved for them.
     * @param dinner : If the driver requires dinner to be saved for them.
     * @author Foong : Amos 18044418
     *
     */
    public VehicleLog(String rego, String person, String destination, String outTime, boolean lunch, boolean dinner) {
        super(person, destination, outTime, lunch, dinner);
        this.setRego(rego);
    }

    // Getter and setter methods for Object's instance data.
    //------------------------------------------------------
    public String getRego() {
        return rego;
    }

    public void setRego(String rego) {
        this.rego = (rego.isEmpty() ? "UNKNOWN" : rego);
    }
    //------------------------------------------------------

    @Override
    public String toString() {
        return this.getRego() + " " + super.getPerson();
    }
}
