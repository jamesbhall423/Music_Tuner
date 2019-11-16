package school.team.musictuner;

import java.io.Serializable;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.sql.Types.NULL;
/**
* Represent a list of frequencies that are present at a particular point in time.
 */
public class Signal implements Cloneable, Serializable {
    private static final long SerialVersionUID = 1L;
    /**
    * returns the fundamental frequency of the pitches
    * i. e. the fundamental frequency of
    * 100 hertz, 200 hertz, 500 hertz
    * is 100 hertz.
    * (The others are multiples.)
     */
    public double getFundamentalFrequency() {
        Iterator<Pitch> it = frequencies.iterator();
        Pitch current = (Pitch) it;
        return current.getFrequency();
        }
    }
    /**
    * The frequencies, sorted from lowest to highest.
     */
    public final SortedSet<Pitch> frequencies;
    public Signal() {
        frequencies = new TreeSet<Pitch>();
    }
    /**
    * Tune the notes so that they are mostly perfect multiples of the fundamental frequency.
    * i. e.
    * 105 hz, 198 hz, 502 hz, 696 hz ->
    * 100 hz, 200 hz, 500 hz, 700 hz.
     */
    public void tuneNotes() {
        Iterator<Pitch> it = frequencies.iterator();
        double tuner = getFundamentalFrequency();
        while (it.hasNext()) {
            double difference = 0;
            Pitch current = (Pitch) it;
            if (tuner > current.getFrequency()) {
                difference = tuner % current.getFrequency();
            } else {
                difference = current.getFrequency() % tuner;
            }
            if (difference > (tuner / 2)) {
                current.setFrequency(current.getFrequency() + difference);
            } else {
                current.setFrequency(current.getFrequency() - difference);
            }
            frequencies.add(current);
        }
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
