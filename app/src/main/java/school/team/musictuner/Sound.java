package school.team.musictuner;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;

/**
* Represents raw sound data.
* Essentially air pressure values at different points in time.
 */
public class Sound implements Cloneable, Serializable {
    private static final long SerialVersionUID = 1L;
private int length;
private int mSampleRate;
private MediaCodec mediaCodec;
private MediaExtractor mediaExtractor;

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
        mediaExtractor = new MediaExtractor();

        mediaExtractor.setDataSource(file);

        int channel = 0;
        for (int i = 0; i < mediaExtractor.getTrackCount(); i++) {
            MediaFormat format = mediaExtractor.getTrackFormat(i);
            String mime = format.getString(MediaFormat.KEY_MIME);
            if (mime.startsWith("audio/")) {
                mediaExtractor.selectTrack(i);
                Log.d("SOUND_TAG", "format : " + format);
                ByteBuffer csd = format.getByteBuffer("csd-0");

                for (int k = 0; k < csd.capacity(); ++k) {
                    Log.e("SOUND_TAG", "csd : " + csd.array()[k]);
                }
                //mSampleRate = format.getInteger(MediaFormat.KEY_SAMPLE_RATE);
                //channel = format.getInteger(MediaFormat.KEY_CHANNEL_COUNT);
                break;
            }
        }
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
        return mSampleRate;
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

    /**
     *
     */
    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }
    /**
     * return the hash code for the object.
     * The hash codes of two objects must be the same if they fulfill equals()
     * Otherwise the method returns different hash codes inasmuch as possible.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Must return an independent object.
     * this.equals(this.clone) returns true;
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    /**
     *
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
