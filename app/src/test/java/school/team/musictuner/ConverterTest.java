package school.team.musictuner;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConverterTest {
    private class JList<T> {
        public final List<T> val = new ArrayList<>();
        public JList<T> add(T next) {
            val.add(next);
            return this;
        }
    }
    @Test
    public void testConverterTimeline() {
        double momentsTime = 0.25;
        JList<JList<Double>> list = (new JList<JList<Double>>()).add(new JList<Double>().add(50.0).add(75.0)).add(new JList<Double>().add(30.0).add(90.0));
        Sound sound = makeSound(list,10.0,250,1000);
        Converter converter = new Converter();
        Settings settings = new Settings();
        settings.setSensitivity(5.0);
        Timeline timeline = converter.soundTimeline(sound);
        int section = 0;
        for (int i = 0; i < timeline.length(); i++) {
            if ((i+1.0)/timeline.momentsPerSecond()>(section+1)*momentsTime) section++;
            else {
                assertEquals(timeline.getMoment(i).frequencies.size(),list.val.get(section).val.size());
                Iterator<Pitch> iterator = timeline.getMoment(i).frequencies.iterator();
                for (int j = 0; j < timeline.getMoment(i).frequencies.size(); j++) {
                    double freqResult = iterator.next().getFrequency();
                    double freqExpect = list.val.get(section).val.get(j);
                    assertTrue(freqResult>freqExpect-10.0&&freqResult<freqExpect+10.0);
                }
            }
        }
    }
    private Sound makeSound(JList<JList<Double>> signals, double amplitude, int section, double sampleRate) {
        Sound sound = new Sound(sampleRate,section*signals.val.size());
        for (int i = 0; i < sound.length(); i++) for (int j = 0; j < section; j++) {
            int index = (i*section+j);
            for (int k = 0; k < signals.val.get(i).val.size(); k++)sound.setDataAt(index,Math.sin(2*Math.PI*index*signals.val.get(i).val.get(k)/sampleRate));
        }
        return sound;
    }
    @Test
    public void converterPlayedSectionTest() {
        Timeline timeline = new Timeline(2,4);
        Signal signal1 = new Signal();
        Signal signal2 = new Signal();
        Pitch base = new Pitch();
        base.setAmplitude(10);
        base.setFrequency(TuneSet.STANDARD_MIDDLE_C_FREQUENCY);
        Pitch p1 = new Pitch();
        p1.setAmplitude(8);
        p1.setFrequency(base.getFrequency()*1.5);
        Pitch base2 = new Pitch();
        base2.setAmplitude(11);
        base2.setFrequency(TuneSet.STANDARD_MIDDLE_C_FREQUENCY+1.1);
        Pitch p2 = new Pitch();
        p2.setAmplitude(15);
        p2.setFrequency(base2.getFrequency()*2);
        signal1.frequencies.add(base);
        signal1.frequencies.add(p1);
        signal2.frequencies.add(base2);
        signal2.frequencies.add(p2);
        timeline.setMoment(0,signal1);
        timeline.setMoment(1,signal2);
        Converter converter = new Converter();
        Settings settings = new Settings();
        settings.setSensitivity(5.0);
        Tuner tuner = new Tuner();
        converter.setSettings(settings);
        converter.setTuner(tuner);
        PlayedSection out = converter.section(timeline);
        assertTrue(out.beatsPerSecond()==4);
        assertTrue(out.getBeats()==2);
        assertTrue(out.getTuneSet().equals(TuneSet.STANDARD));
        assertTrue(out.getNotes().size()==3);
        Iterator<PlayedNote> iterator = out.getNotes().iterator();
        PlayedNote baseNote = iterator.next();
        PlayedNote note1 = iterator.next();
        PlayedNote note2 = iterator.next();
        assertTrue(baseNote.getPitch().getAmplitude()>7);
        assertTrue(baseNote.getPitch().getAmplitude()<11);
        assertTrue(baseNote.getPitch().getFrequency()>TuneSet.STANDARD_MIDDLE_C_FREQUENCY-1);
        assertTrue(baseNote.getPitch().getFrequency()<TuneSet.STANDARD_MIDDLE_C_FREQUENCY+2);
        assertTrue(note1.getPitch().getAmplitude()>7);
        assertTrue(note1.getPitch().getAmplitude()<11);
        assertTrue(note1.getPitch().getFrequency()>TuneSet.STANDARD_MIDDLE_C_FREQUENCY*1.5-1);
        assertTrue(note1.getPitch().getFrequency()<TuneSet.STANDARD_MIDDLE_C_FREQUENCY*1.5+1);
        assertTrue(note2.getPitch().getAmplitude()>10);
        assertTrue(note2.getPitch().getAmplitude()<20);
        assertTrue(note2.getPitch().getFrequency()>TuneSet.STANDARD_MIDDLE_C_FREQUENCY*2-1);
        assertTrue(note2.getPitch().getFrequency()<TuneSet.STANDARD_MIDDLE_C_FREQUENCY*2+1);
    }
}
