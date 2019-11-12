package school.team.musictuner;

import org.junit.Test;

import static java.lang.Math.abs;

public class testTuneNotesFunction {
    @Test
    public void testForTuneNotesFunction() {
        Signal test = new Signal();
        double old = var(test);
        test.tuneNotes();
        double tuned = var(test);
        assert (tuned < old);
    }
    private double var(Signal signal) {
        return 0.0;
    }
}
