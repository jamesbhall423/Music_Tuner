package school.team.musictuner;

import java.util.SortedSet;
import java.util.TreeSet;

import static java.sql.Types.NULL;
/**
* Represent a list of frequencies that are present at a particular point in time.
 */
public class Signal {
    /**
    * returns the fundamental frequency of the pitches
    * i. e. the fundamental frequency of
    * 100 hertz, 200 hertz, 500 hertz
    * is 100 hertz.
    * (The others are multiples.)
     */
    public double getFundamentalFrequency() {
        return 0;
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
    }
}