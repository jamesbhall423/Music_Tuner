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
    private static final double SENSITIVITY_FRACTION = 0.2;
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
        int size = 1;
        while (size<off-on) size*=2;
        double[] real = new double[size];
        double[] imag = new double[real.length];
        for (int i = 0; i < off-on; i++) real[i]=sound.getDataAt(i+on);
        double[] fft = fft(real,imag,true);
        double[] fourierAmplitudes = new double[fft.length/2];
        for (int i = 0; i < fourierAmplitudes.length; i++) fourierAmplitudes[i]=size/(off-on-0.0)*Math.sqrt(fft[2*i]*fft[2*i]+fft[2*i+1]*fft[2*i+1]);
        double[] frequencies = actualFrequencies(fourierAmplitudes,sound.samplesPerSecond());
        //System.out.println("length "+frequencies.length);
        //System.out.println("sensitivity "+settings.getSensitivity());
        //for (int i = 0; i < frequencies.length; i++) if (fourierAmplitudes[i]>settings.getSensitivity()) System.out.print(i+" ");
        //System.out.println();
        //System.out.println("offon: "+off+" "+on);
        //System.out.println(fourierAmplitudes[0]+" "+fourierAmplitudes[3]+" "+fourierAmplitudes[5]);
        double[] amplitudes = amplitudes(frequencies,fourierAmplitudes,sound.samplesPerSecond());
        Signal out = new Signal();
        for (int i = 0; i < frequencies.length; i++) out.frequencies.add(new Pitch(frequencies[i],amplitudes[i]));
        return out;
    }

    /**
     * @author Orlando Selenu
     * for methods static double fft and bitInverse
     * obtained at https://stackoverflow.com/questions/3287518/reliable-and-fast-fft-in-java
     */
    /**
     * The Fast Fourier Transform (generic version, with NO optimizations).
     *
     * @param inputReal
     *            an array of length n, the real part
     * @param inputImag
     *            an array of length n, the imaginary part
     * @param DIRECT
     *            TRUE = direct transform, FALSE = inverse transform
     * @return a new array of length 2n
     */
    public static double[] fft(final double[] inputReal, double[] inputImag,
                               boolean DIRECT) {
        // - n is the dimension of the problem
        // - nu is its logarithm in base e
        int n = inputReal.length;

        // If n is a power of 2, then ld is an integer (_without_ decimals)
        double ld = Math.log(n) / Math.log(2.0);

        // Here I check if n is a power of 2. If exist decimals in ld, I quit
        // from the function returning null.
        if (((int) ld) - ld != 0) {
            System.out.println("The number of elements is not a power of 2.");
            return null;
        }

        // Declaration and initialization of the variables
        // ld should be an integer, actually, so I don't lose any information in
        // the cast
        int nu = (int) ld;
        int n2 = n / 2;
        int nu1 = nu - 1;
        double[] xReal = new double[n];
        double[] xImag = new double[n];
        double tReal, tImag, p, arg, c, s;

        // Here I check if I'm going to do the direct transform or the inverse
        // transform.
        double constant;
        if (DIRECT)
            constant = -2 * Math.PI;
        else
            constant = 2 * Math.PI;

        // I don't want to overwrite the input arrays, so here I copy them. This
        // choice adds \Theta(2n) to the complexity.
        for (int i = 0; i < n; i++) {
            xReal[i] = inputReal[i];
            xImag[i] = inputImag[i];
        }

        // First phase - calculation
        int k = 0;
        for (int l = 1; l <= nu; l++) {
            while (k < n) {
                for (int i = 1; i <= n2; i++) {
                    p = bitreverseReference(k >> nu1, nu);
                    // direct FFT or inverse FFT
                    arg = constant * p / n;
                    c = Math.cos(arg);
                    s = Math.sin(arg);
                    tReal = xReal[k + n2] * c + xImag[k + n2] * s;
                    tImag = xImag[k + n2] * c - xReal[k + n2] * s;
                    xReal[k + n2] = xReal[k] - tReal;
                    xImag[k + n2] = xImag[k] - tImag;
                    xReal[k] += tReal;
                    xImag[k] += tImag;
                    k++;
                }
                k += n2;
            }
            k = 0;
            nu1--;
            n2 /= 2;
        }

        // Second phase - recombination
        k = 0;
        int r;
        while (k < n) {
            r = bitreverseReference(k, nu);
            if (r > k) {
                tReal = xReal[k];
                tImag = xImag[k];
                xReal[k] = xReal[r];
                xImag[k] = xImag[r];
                xReal[r] = tReal;
                xImag[r] = tImag;
            }
            k++;
        }

        // Here I have to mix xReal and xImag to have an array (yes, it should
        // be possible to do this stuff in the earlier parts of the code, but
        // it's here to readibility).
        double[] newArray = new double[xReal.length * 2];
        //double radice = 1 / Math.sqrt(n);
        double radice = 2.0/n;
        //System.out.println("fft: "+radice);
        for (int i = 0; i < newArray.length; i += 2) {
            int i2 = i / 2;
            // I used Stephen Wolfram's Mathematica as a reference so I'm going
            // to normalize the output while I'm copying the elements.
            newArray[i] = xReal[i2] * radice;
            newArray[i + 1] = xImag[i2] * radice;
        }
        return newArray;
    }

    /**
     * The reference bitreverse function.
     */
    private static int bitreverseReference(int j, int nu) {
        int j2;
        int j1 = j;
        int k = 0;
        for (int i = 1; i <= nu; i++) {
            j2 = j1 / 2;
            k = 2 * k + j1 - 2 * j2;
            j1 = j2;
        }
        return k;
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
        Signal signal;
        PlayedSection section = new PlayedSection(timeline.length(), timeline.momentsPerSecond());
        for(int i = 0; i < timeline.length(); i++) {
            signal = timeline.getMoment(i);
            Iterator<Pitch> it = signal.frequencies.iterator();
            Iterator<Pitch> lastIt = it;
            while(it.hasNext())
            {
                Pitch pitch = lastIt.next();
                section.getNotes().add(new PlayedNote());

            }
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
    public double[] actualFrequencies(double[] fourierAmplitudes, double samplesPerSecond) {
        //System.out.println("Hello There");
        double wholePeriod = fourierAmplitudes.length/samplesPerSecond; //The amount of time from the start of the period to the end of the period. The inverse of this is the difference between steps in the fourier transform
        double step = 1/wholePeriod; //The difference between values in the fourier transform
        List<Integer> peaks = new ArrayList<>();
        double last = fourierAmplitudes[0];
        boolean rising=false;
        double max = 0.0;
        for (int i = 1; i < fourierAmplitudes.length/2; i++) {
            double next = fourierAmplitudes[i];
            //System.out.println("Hello 3: "+next);
            if (next>last) {
                rising=true;
            }
            else if (rising) {
                peaks.add(i-1);
                //System.out.println("Hello 2");
                double vNext = actualAmplitude(i-1,fourierAmplitudes);
                if (vNext>max) max=vNext;
                rising=false;
            }
            last=next;
        }
        //
        if (max<settings.getSensitivity()) return new double[0];
        double threashold = Math.max(SENSITIVITY_FRACTION*max,settings.getSensitivity());

        List<Double> out = new ArrayList<>();
        //System.out.println("Threashold "+threashold);
        for (int next: peaks) {
            if (actualAmplitude(next,fourierAmplitudes)>threashold) {
                //System.out.println("Peak "+next+" "+actualAmplitude(next,fourierAmplitudes));
                out.add(actualFrequency(next*step,fourierAmplitudes[next-1],fourierAmplitudes[next],fourierAmplitudes[next+1],step));
            }
        }
        double[] ret = new double[out.size()];
        for (int i = 0; i < ret.length; i++) ret[i]=out.get(i);
        return ret;
    }
    private double actualAmplitude(int target, double[] fourierAmplitudes) {
        return actualAmplitude(fourierAmplitudes[target-1],fourierAmplitudes[target],fourierAmplitudes[target+1]);
    }

    /**
     * 
     * @param actualFrequencies
     * @param fourierAmplitudes
     * @param samplesPerSecond
     * @return
     */
    private double[] amplitudes(double[] actualFrequencies, double[] fourierAmplitudes, double samplesPerSecond) {
        double wholePeriod = fourierAmplitudes.length/samplesPerSecond; //The amount of time from the start of the period to the end of the period. The inverse of this is the difference between steps in the fourier transform
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
