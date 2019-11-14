package school.team.musictuner;

import java.io.Serializable;

public class PlayedNote implements Comparable<PlayedNote>, Cloneable, Serializable {

    private Pitch pitch;
    private int start, end;
    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }
    public Pitch getPitch() {
        return pitch;
    }
    public int start() {
        return 0;
    }
    public int end() {
        return 0;
    }
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
