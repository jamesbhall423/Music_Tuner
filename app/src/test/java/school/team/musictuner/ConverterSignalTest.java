package school.team.musictuner;

import org.junit.Test;

import static org.junit.Assert.*;
public class ConverterSignalTest {
    @Test
    public void convertToSignalTest() {
        Converter converter = new Converter();
        Settings settings = new Settings();
        converter.setSettings(settings);

        settings.setSensitivity(1.0);
        Pitch[] pitches = getPitch3(assemblePitch(200,5.0),assemblePitch(400,7.0),assemblePitch(550,8.0));
        Pitch[] noise = getPitch3(assemblePitch(150,0.05),assemblePitch(530,0.27),assemblePitch(320,0.15));
        double[] phaseShift = {0.0,1.0,0.5};
        Sound storage = new Sound(1200,30);
        testSignal(converter,pitches,noise,storage,phaseShift,0,storage.length());


        settings.setSensitivity(5.0);
        pitches = getPitch3(assemblePitch(25,32.0),assemblePitch(42,27.0),assemblePitch(57,18.0));
        noise = getPitch3(assemblePitch(15,0.5),assemblePitch(53,1.8),assemblePitch(32,1.1));
        double[] phaseShift2 ={2.0,1.0,0.5};
        storage = new Sound(120,50);
        testSignal(converter,pitches,noise,storage,phaseShift,10,storage.length()-10);
    }
    private Pitch assemblePitch(double freq, double amp) {
        Pitch out = new Pitch();
        out.setAmplitude(amp);
        out.setFrequency(freq);
        return out;
    }
    private Pitch[] getPitch1(Pitch pitch) {
        Pitch[] out = {pitch};
        return out;
    }
    private Pitch[] getPitch2(Pitch pitch1,Pitch pitch2) {
        Pitch[] out = {pitch1,pitch2};
        return out;
    }
    private Pitch[] getPitch3(Pitch pitch1,Pitch pitch2,Pitch pitch3) {
        Pitch[] out = {pitch1,pitch2,pitch3};
        return out;
    }
    private Pitch[] getPitch4(Pitch pitch1,Pitch pitch2,Pitch pitch3,Pitch pitch4) {
        Pitch[] out = {pitch1,pitch2,pitch3,pitch4};
        return out;
    }
    private Pitch[] getPitch5(Pitch pitch1,Pitch pitch2,Pitch pitch3,Pitch pitch4,Pitch pitch5) {
        Pitch[] out = {pitch1,pitch2,pitch3,pitch4,pitch5};
        return out;
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
