package school.team.musictuner;

import org.junit.Test;

import static java.lang.Math.abs;

public class testTuneNotesFunction {
    @Test
    public void testForTuneNotesFunction() {
        Signal test = new Signal();
    }
    public void testSignal(Signal test) {
        double old = var(test);
        test.tuneNotes();
        double tuned = var(test);
        assert (tuned < old);
    }
    private double var(Signal signal) {
        double fundamental = signal.getFundamentalFrequency();
        double out = 0.0;
        for (Pitch pitch: signal.frequencies) {
            double mod = pitch.getFrequency() % fundamental;
            out += mod*(fundamental-mod);
        }
        return out;
    }
}
