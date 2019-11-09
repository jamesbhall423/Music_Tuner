package school.team.musictuner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testPitch {

    @Test
    public void testPitchCompareTo() {
        Pitch pitch1 = new Pitch();
        Pitch pitch2 = new Pitch();

        pitch1.setFrequency(10);
        pitch2.setFrequency(10);

        assertEquals(0, pitch1.compareTo(pitch2));
    }
}
