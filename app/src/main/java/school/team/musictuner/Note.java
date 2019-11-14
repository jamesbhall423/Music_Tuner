package school.team.musictuner;

import java.io.Serializable;

/**
* Represents a note on a musical scale, such as C# and Eb
 */
public class Note implements Comparable<Note>, Cloneable, Serializable {
    private static final long SerialVersionUID = 1L;
    /**
    * The frequency of the note
     */
    public final double frequency;
    /**
    * The name of the note
     */
    public final String name;
    /**
    * the tuning scheme the note belongs to
     */
    public final TuneSet tuneSet;

    public Note (double frequency, String name, TuneSet tuneSet)
    {
        this.frequency = frequency;
        this.name = name;
        this.tuneSet = tuneSet;
    }

    /**
    * note1.compareTo(note2) = note1.frequency < / = / > note2.frequency
     */
    public int compareTo(Note other) {
        return 0;
    }

    /**
    * returns a note from the given frequency and tuneSet.
     */
    public static Note getNote(double frequency, TuneSet tuneSet) {
        return tuneSet.getNote(frequency);
    }

    public boolean isSharp() {
        return false;
    }

    public boolean isFlat() {
        return false;
    }

    /**
    * return
    * # sharp,
    * b flat,
    * or [blank] natural.
     */
    public char halfStep() {
        return 'b';
    } //flat b, or sharp #

    /**
    * returns the capitalized letter name of the note
     */
    public char letter() { //capitalized
        return 'C';
    }

    /**
    * returns the note's octave - C4 = middle C
     */
    public int octave() {
        return 0;
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
