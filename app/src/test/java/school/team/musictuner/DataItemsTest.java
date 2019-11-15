package school.team.musictuner;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import static school.team.musictuner.TuneSet.STANDARD;
public class DataItemsTest {
    @Test
    public void testDataItemsEqualsAndHashCode() {
        testEqual(STANDARD.getNote(300),STANDARD.getNote(300).clone());
        testNotEqual(STANDARD.getNote(280),STANDARD.getNote(230));

        Pitch p1 = new Pitch();
        p1.setAmplitude(5.0);
        p1.setFrequency(220);
        Pitch p2 = new Pitch();
        p2.setAmplitude(7.0);
        p2.setFrequency(250);
        testEqual(p1,p1.clone());
        testNotEqual(p1,p2);

        PlayedNote pn1 = new PlayedNote();
        pn1.setPitch(p1);
        PlayedNote pn2 = new PlayedNote();
        pn2.setPitch(p2);
        testEqual(pn1,pn1.clone());
        testNotEqual(pn1,pn2);

        PlayedSection ps1 = new PlayedSection(1,7.0);
        ps1.getNotes().add(pn1);
        PlayedSection ps2 = new PlayedSection(2,4.0);
        ps2.getNotes().add(pn2);
        testEqual(ps1,ps1.clone());
        testNotEqual(ps1,ps2);

        Settings settings1 = new Settings();
        settings1.setSensitivity(13.0);
        Settings settings2 = new Settings();
        settings2.setSensitivity(5.0);
        testEqual(settings1,settings1.clone());
        testNotEqual(settings2,settings1);

        Signal signal1 = new Signal();
        signal1.frequencies.add(p1);
        Signal signal2 = new Signal();
        signal2.frequencies.add((Pitch)p1.clone());
        Signal signal3 = new Signal();
        signal3.frequencies.add(p2);
        testEqual(signal1,signal2);
        testNotEqual(signal2,signal3);

        Sound sound1 = new Sound(10000.0,2);
        sound1.setDataAt(0,2.3);
        sound1.setDataAt(1,-17.0);
        Sound sound2 = new Sound(10000.0,2);
        sound2.setDataAt(0,2.3);
        sound2.setDataAt(1,-17.0);
        Sound sound3 = new Sound(10000.0,2);
        sound3.setDataAt(0,22.3);
        sound3.setDataAt(1,-17.0);
        testEqual(sound1,sound2);
        testNotEqual(sound2,sound3);

        Timeline timeline1 = new Timeline(2,10);
        timeline1.setMoment(0,signal1);
        timeline1.setMoment(1,signal1);
        Timeline timeline2 = new Timeline(2,10);
        timeline2.setMoment(0,signal1);
        timeline2.setMoment(1,signal1);
        Timeline timeline3 = new Timeline(2,10);
        timeline3.setMoment(0,signal3);
        timeline3.setMoment(1,signal1);
    }
    private void testEqual(Object o1, Object o2) {
        assertTrue(o1!=o2&&o1.equals(o2)&&o1.hashCode()==o2.hashCode());
    }
    private void testNotEqual(Object o1, Object o2) {
        assertFalse(o1.equals(o2));
    }
}
