package school.team.musictuner;

public class Settings {
    private double sensitivity;
    private boolean removeOvertones;
    private TuneSet tuneSet;
    public void setSensitivity(double sensitivity) {
        this.sensitivity=sensitivity;
    }
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
