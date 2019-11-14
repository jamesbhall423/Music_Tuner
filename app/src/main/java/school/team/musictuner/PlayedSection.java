package school.team.musictuner;

import java.io.Serializable;
import java.util.SortedSet;

public class PlayedSection implements Cloneable, Serializable {
    private SortedSet<PlayedNote> notes;
    private int beats;
    private double beatsPerSecond;
    private TuneSet tuneSet;
    PlayedSection(int beats, double beatsPerSecond) {

    }

    public SortedSet<PlayedNote> getNotes() {
       return notes;
    }

   public int getBeats() {
        return beats;
   }
    public double beatsPerSecond() {
        return beatsPerSecond;
    }

    public void setTuneSet(TuneSet tuneSet) {
        this.tuneSet=tuneSet;
    }
    public TuneSet getTuneSet() {
        return tuneSet;
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
