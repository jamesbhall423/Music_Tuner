package school.team.musictuner;

import org.junit.Test;

public class testTuneNotesFunction {
    @Test
    public void testForTuneNotesFunction() {
        Signal test = new Signal();
        int old;
        old = test.getFundamentalFrequency();
        test.tuneNotes();
        int tuned;
        tuned = test.getFundamentalFrequency();
        assert(tuned < old);
    }
}
