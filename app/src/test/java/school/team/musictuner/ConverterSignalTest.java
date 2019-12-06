package school.team.musictuner;

import android.util.Log;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
public class ConverterSignalTest {
    private static final String TAG = "Tuner ConverterSignal";
    private Random random;
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
    //0<=phaseShift<1
    private void addPitch(double amplitude, double frequency, double samplesPerSecond, double phaseShift, double[] sound) {
        for (int i = 0; i < sound.length; i++) sound[i]+=amplitude*Math.sin(2*Math.PI*(i*frequency/samplesPerSecond+phaseShift));
    }
    private void testTransform(double[] signal, double frequency, double samplesPerSecond) {
        double transform = fourierTransform(signal,samplesPerSecond/frequency);
        System.out.println( "at frequency "+frequency+" transform="+transform);
    }
    private void testSignal(double amplitude, double frequency,double phaseShift,int length, double test) {
        System.out.println("testSignal: amp="+amplitude+" freq="+frequency+" shift="+phaseShift+" length="+length+" test="+test);
        double[] sound = new double[length];
        addPitch(amplitude,frequency,length,phaseShift,sound);
        addWhiteNoise(sound,amplitude);
        resultSignal(sound,test);
    }
    private void testTwoFrequencies(double amp1, double amp2, double freq1, double freq2, double phase1,double phase2, int length, double offset) {
        System.out.println("testTwoFreq: length="+length+" offset="+offset);
        System.out.println("Pitch1: freq="+freq1+" amp="+amp1+" phase="+phase1);
        System.out.println("Pitch2: freq="+freq2+" amp="+amp2+" phase="+phase2);
        double[] sound = new double[length];
        addPitch(amp1,freq1,length,phase1,sound);
        addPitch(amp2,freq2,length,phase2,sound);
        //addWhiteNoise(sound,(amp1+amp2)/2);
        findPeaks(sound,offset);
    }
    private void findPeaks(double[] sound, double offset) {
        double last = fourierTransform(sound,sound.length/(offset));
        boolean rising=false;
        for (double i = offset+2; i < sound.length/2; i++) {
            double next = fourierTransform(sound,sound.length/i);
            if (next>last) {
                rising=true;
            }
            else if (rising) {
                resultSignal(sound,i-1);
                rising=false;
            }
            last=next;
        }
    }
    private void resultSignal(double[] sound, double test) {
        double below = fourierTransform(sound,sound.length/(test-1));
        double equal = fourierTransform(sound,sound.length/(test));
        double above = fourierTransform(sound,sound.length/(test+1));
        double resultFreq = actualFrequency(test,below,equal,above,1);
        System.out.println("ObtainedFreq: "+resultFreq);
        double resultAmp = actualAmplitude(below,equal,above);
        System.out.println("ObtainedAmp: "+resultAmp);
        testTransform(sound,test-2,sound.length);
        testTransform(sound,test-1,sound.length);
        testTransform(sound,test,sound.length);
        testTransform(sound,test+1,sound.length);
        testTransform(sound,test+2,sound.length);
    }
    private void testGroup(double amplitude, double frequency, int length, double test) {
        for (double phaseShift = 0.0; phaseShift<1.0;phaseShift+=0.2) testSignal(amplitude,frequency,phaseShift,length,test);
    }
    private double actualFrequency(double testFreq, double below, double equal, double above, double step) {
        /*if (below/equal<0.8&&above/equal<0.8) {
            below+=equal/6;
            above+=equal/6;
        }
        below=1/below;
        above=1/above;
        equal=1/equal;
        double delta2 = below+above-2*equal;
        double delta = (above-below)/2;
        double relDelta0 = (delta/delta2);
        if (relDelta0>0) relDelta0 = Math.sqrt(2*relDelta0)/2;
        else relDelta0 = -Math.sqrt(-2*relDelta0)/2;*/
        int dir;
        double other;
        if (above>below) {
            other=above;
            dir=1;
        }
        else {
            other=below;
            dir=-1;
        }
        double relDelta0 = dir*other/(equal+other);
        double delta0 = testFreq+relDelta0*step;
        return delta0;
    }
    private double actualAmplitude(double below, double peak, double above) {
        if (below>above) {
            double temp=below;
            below=above;
            above=temp;
        }
        return peak+above-below;
    }
    private void addWhiteNoise(double[] signal, double amplitude) {
        for (int i = 0; i < signal.length; i++) signal[i]+=2*(random.nextDouble()-0.5)*amplitude;
    }
    @Test
    public void testPeaks() {
        random = new Random(2347829876L);
        testGroup(100.0,9.5,1000,10.0);
        testGroup(100.0,12.7,800,13.0);
        testGroup(100.0,9.5,1000,9.5);
        testGroup(100.0,20.3,500,20.4);
        testTwoFrequencies(100.0,80.0,40.0,50.2,0.7,0.3,1000,0.2);
        testTwoFrequencies(120.0,80.0,15.0,20.2,0.5,0.8,1000,0.8);
        testTwoFrequencies(120.0,80.0,12.0,14.8,0.6,0.5,1000,0.3);
        testTwoFrequencies(100.0,100.0,12.0,10.2,0.9,0.2,1000,0.5);
    }
    public static double fourierTransform(double[] signal, double sampleWavelength) {
        //If the signal is a simple harmonic, it will inevitably be a combination of the sin and cos trigonametric functions.
        double phaseSin = 0.0; //One possible phase for the frequency
        double phaseCos = 0.0; //The inverse phase for the frequency
        for (int i = 0; i < signal.length; i++) {
            double angleTraveled = 2*Math.PI*i/sampleWavelength;
            phaseSin += Math.sin(angleTraveled)*signal[i]; //sum correlation with sin phase
            phaseCos += Math.cos(angleTraveled)*signal[i]; //sum correlation with cos phase
        }
        phaseSin/=signal.length; //average correlation with the sin function
        phaseCos/=signal.length; //average correlation with the cos function
        return 2*Math.sqrt(phaseSin*phaseSin+phaseCos*phaseCos); //Perfect match gives value 1/2 the amplitude, hence *2
    }
}
