package school.team.musictuner;

import org.junit.Test;

import static java.lang.Math.abs;

public class testTuneNotesFunction {
    @Test
    public void testForTuneNotesFunction() {
        Signal test = new Signal();
        Pitch perfect = new Pitch();
        double old = abs(perfect.getFrequency() - test.getFundamentalFrequency());
        test.tuneNotes();
        double tuned = abs(perfect.getFrequency() - test.getFundamentalFrequency());
        assert (tuned < old);
    }
}
