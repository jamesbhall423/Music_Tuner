package school.team.musictuner;

import java.util.SortedSet;
import java.util.TreeSet;

import static java.sql.Types.NULL;

public class Signal {
    public double getFundamentalFrequency() {
        return 0;
    }
    public SortedSet<Pitch> frequencies;
    public int getFundamentalFrequency() {
        return 0;
    }
    public Signal() {
        frequencies = new TreeSet<Pitch>();
    }
    public void tuneNotes() {
    }
}
