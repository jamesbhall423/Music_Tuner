package school.team.musictuner;
/**
* This class represents various settings under the users control.
 */
public class Settings {
    private static String NAME = "TunerSettings";
    private double sensitivity;
    private boolean removeOvertones;
    private TuneSet tuneSet;
    /**
    * Sets the threshold for recognizing pitches.
     */
    public void setSensitivity(double sensitivity) {
        this.sensitivity=sensitivity;
    }
    /**
     * Gets the threshold for recognizing pitches.
     */
    public double getSensitivity() {
        return sensitivity;
    }
    void setRemoveOvertones(boolean removeOvertones) {
        this.removeOvertones = removeOvertones;
    }
    boolean getRemoveOvertones() {
        return removeOvertones;
    }
    TuneSet getTuneSet() {
        return tuneSet;
    }
    void setTuneSet(TuneSet tuneSet) {
        this.tuneSet = tuneSet;
    }

}
