package rapidInOut;

/**
 * This is a Model Class. It stores information about a Resident Log.
 * Information such as Person, Destination, Time Out, Time In, Lunch and Dinner
 * Feedback.
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class ResidentLog implements java.io.Serializable {

    private String person;
    private String destination;
    private String outTime;
    private String inTime;
    private boolean lunchFeedback;
    private boolean dinnerFeedback;

    /**
     * Default constructor to create a new entry of a Resident In/Out Log.
     *
     * @param person : Name of the person going out.
     * @param destination : Place the person is leaving for.
     * @param outTime : Time of departure.
     * @param lunch : If the person requires lunch to be saved for them.
     * @param dinner : If the person requires dinner to be saved for them.
     * @author Foong : Amos 18044418
     *
     */
    public ResidentLog(String person, String destination, String outTime, boolean lunch, boolean dinner) {
        this.setPerson(person);
        this.setDestination(destination);
        this.setOutTime(outTime);
        this.setInTime("");
        this.setLunchFeedback(lunch);
        this.setDinnerFeedback(dinner);
    }

    // Getter and setter methods for Object's instance data.
    //--------------------------------------------------------------
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = (person.isEmpty() ? " " : person);
    }
    //--------------------------------------------------------------

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = (destination.isEmpty() ? " " : destination);
    }
    //--------------------------------------------------------------

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = (outTime.isEmpty() ? " " : outTime);
    }
    //--------------------------------------------------------------

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = (inTime.isEmpty() ? " " : inTime);
    }
    //--------------------------------------------------------------

    public boolean isLunchFeedback() {
        return lunchFeedback;
    }

    public void setLunchFeedback(boolean lunchFeedback) {
        this.lunchFeedback = lunchFeedback;
    }
    //--------------------------------------------------------------

    public boolean isDinnerFeedback() {
        return dinnerFeedback;
    }

    public void setDinnerFeedback(boolean dinnerFeedback) {
        this.dinnerFeedback = dinnerFeedback;
    }
    //--------------------------------------------------------------

    @Override
    public String toString() {
        return "Person: " + this.getPerson() + " Time out: " + this.getOutTime();
    }
}
