package school.team.musictuner;

import java.io.Serializable;

/**
 * Divides sound input into a list of intervals. Each interval can contain a given list of notes
 * Example
 * Moment 0 1 2 3 4 5 6 7
 * C5                   C
 * B4                 B
 * A4               A
 * G4             G
 * F4           F
 * E4         E
 * D4       D
 * C4     C C C C C C C C
 */
public class Timeline implements Cloneable, Serializable {
    private static final long SerialVersionUID = 1L;
    private int length, on, off, moment;
    private double momentsPerSecond;
    private Signal signal;

    public Timeline(int length, double momentsPerSecond) {
        this.length = length;
        this.momentsPerSecond = momentsPerSecond;
    }

    /**
     * stretch
     * Create a timeline with the same data as base, but a different number of moments per second
     * @param base
     * @param momentsPerSecond
     */
    public Timeline(Timeline base, double momentsPerSecond) {
        this.momentsPerSecond = momentsPerSecond;
    }

    /**
     * Stretch
     * Create a timeline that starts at on and lasts off-on moments.
     * @param base
     * @param on
     * @param off
     */
    public Timeline(Timeline base, int on, int off) {
        this.on = on;
        this.off = off;
    }

    /**
     *
     * @param moment
     * @return the Signal at the given moment
     */
    public Signal getMoment(int moment) {
        return signal;
    }
    public void setMoment(int moment, Signal signal) {
        this.moment = moment;
    }
    public int length() {
       return length;
    }
    public double momentsPerSecond() {
        return momentsPerSecond;
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
