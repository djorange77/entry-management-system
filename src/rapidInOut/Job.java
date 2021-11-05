package rapidInOut;

/**
 * This is an enumeration class that has values that describes the job functions
 * that a Resident can hold.
 *
 * @author Foong : Amos 18044418
 *
 */
public enum Job {
    SUPERVISOR, COORDINATOR, RAMROD, ROUSTABOUT, HOUSE_DRIVER,
    START_STRUCTURE, SPORT_STRUCTURE, MAINTENANCE, PNG, FLOATER;

    @Override
    public String toString() {
        // Replaces the underscores with a space then returns it 
        // to caller. 
        return this.name().replace("_", " ");
    }
}
