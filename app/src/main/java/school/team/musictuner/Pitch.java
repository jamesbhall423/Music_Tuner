package school.team.musictuner;

import java.io.Serializable;

/**
* Represents a frequency in the sound audio.
 */
public class Pitch implements Comparable<Pitch>, Cloneable, Serializable {
    private static final long SerialVersionUID = 1L;

    public Pitch() {

    }
    public Pitch(double frequency, double amplitude) {
        this.frequency=frequency;
        this.amplitude=amplitude;
    }
    private double frequency, amplitude;
    public double getFrequency() {
        return frequency;
    }
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
    public double getAmplitude() {
        return amplitude;
    }
    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }
    /**
    * returns the Note closest in frequency to this pitch.
     */
    public Note getNote(TuneSet tuneSet) {
        return  tuneSet.getNote(frequency);
    }
    /**
    * tunes the frequency to the nearest Note (as in Note class)
     */
    public void tuneNote(TuneSet tuneSet) {
        setFrequency(tuneSet.getNote(getFrequency()).frequency);
    }


    /**
    * compare frequency <, ==, > o.frequency;
     */
    @Override
    public int compareTo(Pitch o) {
        return (int)Math.signum(this.frequency-o.frequency);
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
        return "Pitch freq="+frequency+" amp="+amplitude;
    }
}
