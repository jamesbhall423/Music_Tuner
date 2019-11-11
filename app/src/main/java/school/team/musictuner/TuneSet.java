package school.team.musictuner;

/**
 * Represents a tuning scheme.
 */
public class TuneSet {
    public Note getNote(double frequency) {
        return null;
    }

    /**
    * The tune set that represent standard tuning.
     */
    static final TuneSet STANDARD = null;

    /**
    * Returns an array representing all possible tuneSets.
     */
    static TuneSet[] allTuneSets() {
        TuneSet[] out = {STANDARD};
        return out;
    }
    /**
    * Return the name of this tuneSet.
     */
    public String toString() {
        return "";
    }

}
