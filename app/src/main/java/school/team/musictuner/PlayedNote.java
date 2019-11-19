package school.team.musictuner;

import java.io.Serializable;

/**
 * A Note that lasts for a given number of beats
 * See PlayedSection
 */
public class PlayedNote implements Comparable<PlayedNote>, Cloneable, Serializable {
    private static final long SerialVersionUID = 1L;

    private Pitch pitch;
    private int start, end;

    /**
     * Set the frequency and amplitude of the note.
     * @param pitch
     */
    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    /**
     *
     * @return The frequency and amplitude of the note
     */
    public Pitch getPitch() {
        return pitch;
    }

    /**
     *
     * @return the moment or beat when the note begins
     */
    public int start() {
        return 0;
    }

    /**
     *
     * @return the moment or beat when the note ends
     */
    public int end() {
        return 0;
    }

    /**
     *
     * @param start - The beggining of the note
     * @param end - The end of the note
     */
    public void setTimeFrame(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public int compareTo(PlayedNote o) {
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
