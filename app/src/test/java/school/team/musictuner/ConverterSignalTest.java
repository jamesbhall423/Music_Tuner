package school.team.musictuner;

import org.junit.Test;

import static org.junit.Assert.*;
public class ConverterSignalTest {
    @Test
    public void convertToSignalTest() {
        Converter converter = new Converter();
        Settings settings = new Settings();
        settings.setSensitivity(1.0);
        converter.setSettings(settings);
        Pitch[] pitches = new Pitch[];
    }
    private void testSignal(Converter converter, Pitch[] input, Pitch[] noise, Sound storage, double[] phaseShift,int on, int off) {
        for (int i = 0; i < input.length; i++) for (int time = 0; time < storage.length(); time++)
            storage.setDataAt(time,storage.getDataAt(time)+Math.sin(2*Math.PI*input[i].getFrequency()*time/storage.samplesPerSecond()+phaseShift[i]));
        for (int i = 0; i < noise.length; i++) for (int time = 0; time < storage.length(); time++)
            storage.setDataAt(time,storage.getDataAt(time)+Math.sin(2*Math.PI*noise[i].getFrequency()*time/storage.samplesPerSecond()+phaseShift[i]));
        Pitch[] output = converter.getSignal(storage,on,off).frequencies.toArray(new Pitch[0]);
        assert (output.length==input.length);
        for (int i = 0; i < input.length; i++) assert (input[i].getFrequency()<output[i].getFrequency()+(storage.samplesPerSecond()/(off-on+0.0)));
        for (int i = 0; i < input.length; i++) assert (input[i].getFrequency()>output[i].getFrequency()-(storage.samplesPerSecond()/(off-on+0.0)));
        for (int i = 0; i < input.length; i++) assert (input[i].getAmplitude()>0.67*output[i].getAmplitude()-1.0);
        for (int i = 0; i < input.length; i++) assert (input[i].getAmplitude()<1.5*output[i].getAmplitude()+1.0);

    }
}
