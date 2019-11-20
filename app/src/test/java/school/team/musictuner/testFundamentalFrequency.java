package school.team.musictuner;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class testFundamentalFrequency {
    @Test
    public void testForGettingFundamentalFrequency() {
        Pitch test = new Pitch();

    }
    @Test
    public void testForFundamentalFrequency2() {

        Signal signal = new Signal();

        Pitch pitch1 = new Pitch(97,5);
        Pitch pitch2 = new Pitch(203,10);
        Pitch pitch3 = new Pitch(301,5);
        signal.frequencies.add(pitch1);
        signal.frequencies.add(pitch2);
        signal.frequencies.add(pitch3);

        double fundamental = signal.getFundamentalFrequency();
        assertTrue(fundamental+"",fundamental>=98&&fundamental<101.5);
    }
}
