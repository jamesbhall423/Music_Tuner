package school.team.musictuner;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * A musical section formatted in a manner the user would expect. Namely, each note will
 * start on a given beat and end on a given beat.
 *
 * For Example
 * Beat  0   1   2   3   4   5   6
 * Note  C4  E4  G4  A4  F4  D4  G4
 * Note  G3--------> A3------->  C3
 * Note  E3--------> F3------->  G2
 * Note  C3--------> D3------->  E2
 */
public class PlayedSection implements Cloneable, Serializable {
    private static final long SerialVersionUID = 1L;
    private SortedSet<PlayedNote> notes;
    private int beats;
    private double beatsPerSecond;
    private TuneSet tuneSet;

    /**
     * Creates a Played Section
     * @param beats - the total number of beats
     * @param beatsPerSecond
     */
    public PlayedSection(int beats, double beatsPerSecond) {

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
