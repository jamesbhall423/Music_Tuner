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
}
