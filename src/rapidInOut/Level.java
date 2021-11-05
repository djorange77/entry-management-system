package rapidInOut;

/**
 * This is an enumeration class that has values that describes the level status
 * that a Resident can hold.
 *
 * @author Foong : Amos 18044418
 *
 */
public enum Level {
    LEVEL_4_REENTRY, LEVEL_4, LEVEL_3, LEVEL_2,
    LEVEL_1, STARTER, STATUS, LIMBO;

    @Override
    public String toString() {
        // Returns a String of the level without the underscores.
        return this.name().replace("_", " ");
    }
}
