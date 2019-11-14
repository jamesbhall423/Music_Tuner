package school.team.musictuner;

import android.util.Log;

import java.io.*;
/**
 * Provides a list of useful methods converting between different types of sound classes.
 */
public class Converter {
    private Settings settings;
    private Tuner tuner;
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
        return null;
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
        return null;
    }

    /**
     * Constructs a PlayedSection from the given Timeline
     * @param timeline
     * @return
     */
    public PlayedSection section(Timeline timeline) {
        return null;
    }
    public void setTuner(Tuner tuner) {
        this.tuner = tuner;
    }
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
                        Log.e("Author", "Failure to copy object: "+toCopy.toString()+" "+e.getMessage());
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
