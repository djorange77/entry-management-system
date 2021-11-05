package rapidInOut;

/**
 * This is a Model Class. It stores information about a Resident. Information
 * such as Name, Level Status, Job Function, and Presence.
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class Resident implements java.io.Serializable {

    private String fullName;
    private Level level;
    private Job job;
    private boolean present;

    /**
     * 3-parameter constructor for the Resident class.
     *
     * @param fullName : Full name of the resident.
     * @param level : Level status of the resident.
     * @param job : Job function of the resident.
     * @author Foong : Amos 18044418
     *
     */
    public Resident(String fullName, Level level, Job job) {
        this.setFullName(fullName);
        this.setLevel(level);
        this.setJob(job);
        this.setPresent(true);
    }

    /**
     * 1-parameter constructor for the Resident class.
     *
     * @param fullName : Full name of the resident.
     * @author Foong : Amos 18044418
     *
     */
    public Resident(String fullName) {
        this.setFullName(fullName);
        this.setLevel(Level.STARTER);
        this.setJob(Job.FLOATER);
        this.setPresent(true);
    }

    // Getter and setter methods for Object's instance data.
    //------------------------------------------------------------------
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        // Validates fullName then assigns it to Object's instance data.
        this.fullName = (fullName.trim().isEmpty() ? "UNKNOWN" : fullName);
    }
    //------------------------------------------------------------------

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    //------------------------------------------------------------------

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    //------------------------------------------------------------------

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        // Changes the presence of the resident within the house. 
        this.present = present;
    }
    //------------------------------------------------------------------

    @Override
    public String toString() {
        return this.getFullName() + " " + this.getJob();
    }
}
