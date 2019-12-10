package school.team.musictuner;

import android.util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Provides a list of useful methods converting between different types of sound classes.
 */
public class Converter implements Serializable {
    private static long SerialVersionUID = 1L;
    private static final String CONVERTER_TAG = "Tuner Converter";
    private Settings settings;
    private Tuner tuner;
    private static final double SENSITIVITY_FRACTION = 0.1;
    public void setSettings(Settings settings) {
        this.settings=settings;
    }
    public Settings getSettings() {
        return settings;
    }

    /**
     * Constructs a Signal from the given sound
     * @param sound
     * @param on the beginning sample, inclusive
     * @param off the end sample, not inclusive
     * @return
     */
    public Signal getSignal(Sound sound, int on, int off) {
        Signal thisSignal = new Signal();
        List<Double> list = new ArrayList<Double>();
        double samplesPerSecond = sound.samplesPerSecond();
        double[] fourierAmplitude;
        double[] actualFrequencies;

        amplitudes(actualFrequencies, fourierAmplitude, samplesPerSecond);
        actualFrequencies(fourierAmplitude, samplesPerSecond);


        return thisSignal;
    }

    /**
     * Constructs a Signal from the given Sound
     * @param sound
     * @return
     */
    public Signal getSignal(Sound sound) {
        return getSignal(sound,0,sound.length());
    }

    /**
     * Constructs a Timeline from the given sound
     * @param sound
     * @return
     */
    public Timeline soundTimeline(Sound sound) {
        Timeline timeLine = new Timeline(sound.length(), sound.samplesPerSecond());

        return timeLine;
    }

    /**
     * Constructs a PlayedSection from the given Timeline
     * @param timeline
     * @return
     */
    public PlayedSection section(Timeline timeline) {
        Signal signal = new Signal();
        PlayedSection section = new PlayedSection(timeline.length(), timeline.momentsPerSecond());
        for(int i = 0; i < timeline.length(); i++)
             signal = timeline.getMoment(i);
        Iterator<Pitch> it = signal.frequencies.iterator();
        Iterator<Pitch> lastIt = it;
        while(it.hasNext())
        {
         Pitch pitch = lastIt.next();
            section.getNotes().add(new PlayedNote());
        }
        return null;
    }
    public void setTuner(Tuner tuner) {
        this.tuner = tuner;
    }

    /**
     *
     * @param fourierAmplitudes - the amplitude of each sec
     * @return
     */
    private double[] actualFrequencies(double[] fourierAmplitudes, double samplesPerSecond) {
        double wholePeriod = 2*fourierAmplitudes.length/samplesPerSecond; //The amount of time from the start of the period to the end of the period. The inverse of this is the difference between steps in the fourier transform
        double step = 1/wholePeriod; //The difference between values in the fourier transform
        List<Integer> peaks = new ArrayList<>();
        double last = fourierAmplitudes[0];
        boolean rising=false;
        double max = 0.0;
        for (int i = 1; i < fourierAmplitudes.length; i++) {
            double next = fourierAmplitudes[i];
            if (next>last) {
                rising=true;
            }
            else if (rising) {
                peaks.add(i-1);
                max = fourierAmplitudes[i-1];
                rising=false;
            }
            last=next;
        }
        //
        if (max<settings.getSensitivity()) return new double[0];
        double threashold = Math.max(SENSITIVITY_FRACTION*max,settings.getSensitivity());
        List<Double> out = new ArrayList<>();
        for (int next: peaks) {
            if (fourierAmplitudes[next]>threashold) out.add(actualFrequency(next*step,fourierAmplitudes[next-1],fourierAmplitudes[next],fourierAmplitudes[next+1],step));
        }
        double[] ret = new double[out.size()];
        for (int i = 0; i < ret.length; i++) ret[i]=out.get(i);
        return ret;
    }

    /**
     * 
     * @param actualFrequencies
     * @param fourierAmplitudes
     * @param samplesPerSecond
     * @return
     */
    private double[] amplitudes(double[] actualFrequencies, double[] fourierAmplitudes, double samplesPerSecond) {
        double wholePeriod = 2*fourierAmplitudes.length/samplesPerSecond; //The amount of time from the start of the period to the end of the period. The inverse of this is the difference between steps in the fourier transform
        double step = 1/wholePeriod; //The difference between values in the fourier transform
        double[] out = new double[actualFrequencies.length];
        for (int i = 0; i < actualFrequencies.length; i++) {
            int peak = (int) Math.round(actualFrequencies[i]/step);
            if (peak==0) peak++;
            else if (peak==fourierAmplitudes.length-1) peak--;
            out[i]=actualAmplitude(fourierAmplitudes[peak-1],fourierAmplitudes[peak],fourierAmplitudes[peak+1]);
        }
        return out;
    }
    private double actualFrequency(double testFreq, double below, double equal, double above, double step) {
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

    /**
     *
     * @param toCopy
     * @return a deep-copy of toCopy
     */
    public static Object independentObject(final Object toCopy) {
        try {
            final PipedOutputStream out = new PipedOutputStream();
            final PipedInputStream in = new PipedInputStream(out);
            final ObjectInputStream read = new ObjectInputStream(in);
            final ObjectOutputStream write = new ObjectOutputStream(out);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        write.writeObject(toCopy);
                    } catch (IOException e) {
                        Log.e(CONVERTER_TAG, "Failure to copy object: "+toCopy.toString()+" "+e.getMessage());
                        try {
                            write.close();
                        } catch (IOException ex) {
                        }
                    }
                }
            });
            return read.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
