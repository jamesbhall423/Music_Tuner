package school.team.musictuner;

import java.io.IOException;

/**
* Represents raw sound data.
* Essentially air pressure values at different points in time.
 */
public class Sound {
private int length;
    public Sound(double samplesPerSecond, int length) {

    }
    /**
    * Creates a sound by listening to audio for the given number of seconds.
     */
    public Sound(double time) {

    }
    /**
    * Retrieves sound from the given audio file.
     */
    public Sound(String file) throws IOException {

    }
    /**
    * Starts listening to audio input
    * will listen for at most maxTime seconds.
    * returns a key that allows programmer to retrieve the sound.
     */
    public static Object startRecording(double maxTime) {

        return null;
    }
    /**
    * Ends and retrieves the sound recording represented by the key.
     */
    public static Sound endRecording(Object key) {

        return null;
    }
    /**
    * returns the air pressure value at the given sample.
     */
    public double getDataAt(int sample) {
        return 0.0;
    }
    /**
    * sets the air pressure value for the given sample.
    * should only be called when building the sound
     */
    public void setDataAt(int point, double value) {

    }
    public double samplesPerSecond() {
        return 0.0;
    }
    /**
    * return a Sound object that covers the same interval in time, but with a fewer number of samples.
     */
    public Sound getCompressedSound(double samplesPerSecond) {
        return null;
    }

    /**
     *
     * @return the number of samples
     */
    public int length() {
        return length;
    }

    /**
     *
     * @param on
     * @param off
     * @return a Sound object with an interval from on (inclusive) to off (not inclusive)
     */
    public Sound getSound(int on, int off) {
        return null;
    }

}
