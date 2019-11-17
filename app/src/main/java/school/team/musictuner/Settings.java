package school.team.musictuner;

import java.io.Serializable;

/**
* This class represents various settings under the users control.
 */
public class Settings implements Cloneable, Serializable {
    private static final long SerialVersionUID = 1L;
    public static final String NAME = "TunerSettings";
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
    public void setRemoveOvertones(boolean removeOvertones) {
        this.removeOvertones = removeOvertones;
    }
    public boolean getRemoveOvertones() {
        return removeOvertones;
    }
    public TuneSet getTuneSet() {
        return tuneSet;
    }
    public void setTuneSet(TuneSet tuneSet) {
        this.tuneSet = tuneSet;
    }

    /**
     *
     */
    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }
    /**
     * return the hash code for the object.
     * The hash codes of two objects must be the same if they fulfill equals()
     * Otherwise the method returns different hash codes inasmuch as possible.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Must return an independent object.
     * this.equals(this.clone) returns true;
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    /**
     *
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
