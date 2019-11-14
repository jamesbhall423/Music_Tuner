package school.team.musictuner;

import java.io.Serializable;

public class Timeline implements Cloneable, Serializable {
    private int length, on, off, moment;
    private double momentsPerSecond;
    private Signal signal;

    Timeline(int length, double momentsPerSecond) {
        this.length = length;
        this.momentsPerSecond = momentsPerSecond;
    }
    Timeline(Timeline base, double momentsPerSecond) {
        this.momentsPerSecond = momentsPerSecond;
    }
    Timeline(Timeline base, int on, int off) {
        this.on = on;
        this.off = off;
    }
    Signal getMoment(int moment) {
        return signal;
    }
    void setMoment(int moment, Signal signal) {
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
