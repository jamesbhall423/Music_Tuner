package school.team.musictuner;

import android.util.Log;

import org.junit.Test;

import static java.lang.Math.abs;
import static org.junit.Assert.assertTrue;

public class testTuneNotesFunction {
    @Test
    public void testForTuneNotesFunction() {
        Signal test = new Signal();
        Pitch pitch1 = new Pitch();
        pitch1.setAmplitude(10);
        pitch1.setFrequency(95);
        Pitch pitch2 = new Pitch();
        pitch2.setAmplitude(10);
        pitch2.setFrequency(200);
        Pitch pitch3 = new Pitch();
        pitch3.setAmplitude(15);
        pitch3.setFrequency(380);
        test.frequencies.add(pitch1);
        test.frequencies.add(pitch2);
        test.frequencies.add(pitch3);
        //assertTrue("Hello "+test.getFundamentalFrequency(),false);
        testSignal(test);
    }
    public void testSignal(Signal test) {
        double old = var(test);
        test.tuneNotes();
        double tuned = var(test);
        String s = "";
        int i = 0;
        for (Pitch p: test.frequencies) s+=" "+p.getFrequency();
        assertTrue(s+" error "+tuned+" not < "+old,tuned < old);
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
