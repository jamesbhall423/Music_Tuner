package school.team.musictuner;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("school.team.musictuner", appContext.getPackageName());
        assertEquals(4, 2+2);
    }

    @Test
    public void testPitchCompareTo() {
        Pitch pitch1 = new Pitch();
        Pitch pitch2 = new Pitch();

        pitch1.setFrequency(10);
        pitch2.setFrequency(10);

        assertEquals(0, pitch1.compareTo(pitch2));
    }
}
