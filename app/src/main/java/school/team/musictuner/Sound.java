package school.team.musictuner;

import android.Manifest;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;

import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioSource;
import android.media.AudioRecord;
import android.media.AudioFormat;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

/**
* Represents raw sound data.
* Essentially air pressure values at different points in time.
 */
public class Sound implements Cloneable, Serializable {
    private static String tag = "Tuner Sound";
    public static int STANDARD_SAMPLE_RATE = 22050;
    public class MicRecord implements Serializable {
        private short[] data;
        public MicRecord(short[] data) {
            this.data=data;
        }
        public double get(int sample) {
            return data[sample];
        }
    }
    public MicRecord audioRecord;
    private static final long SerialVersionUID = 1L;
private int length;
private int mSampleRate;
private MediaCodec mediaCodec;
private MediaExtractor mediaExtractor;

    public Sound(double samplesPerSecond, int length) {
        Log.i(tag, "Samples/s, length constructor start");
        Log.i(tag, "Samples/s, length constructor end");
    }
    /**
    * Creates a sound by listening to audio for the given number of seconds.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public Sound(double time) {
        Log.i(tag, "Only 'time' constructor start");
        short[] buffer = new short[(int)(STANDARD_SAMPLE_RATE*time)];
        Log.d(tag,"AudioRecord.errorBadValue "+AudioRecord.ERROR_BAD_VALUE);
        Log.d(tag,"Audio Record min bytes "+AudioRecord.getMinBufferSize(STANDARD_SAMPLE_RATE,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT));
        AudioRecord record = findAudioRecord();//new AudioRecord(AudioSource.MIC,STANDARD_SAMPLE_RATE,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT,Math.max(2*buffer.length,AudioRecord.getMinBufferSize(STANDARD_SAMPLE_RATE,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT)));
        record.startRecording();
        try {
            Thread.sleep(1000*(int)time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(tag, "Error in 'time' constructor, sleep method");
        }
        record.stop();
        record.read(buffer,0,buffer.length); //I do not believe this retrieves the right amount of time.
        record.release();
        audioRecord = new MicRecord(buffer);
        length = buffer.length;
        mSampleRate = STANDARD_SAMPLE_RATE;
        Log.i(tag, "Only 'time' constructor end");
    }
    private static int[] mSampleRates = new int[] { 44100, 22050, 11025, 8000 };
    private AudioRecord findAudioRecord() {
        for (int rate : mSampleRates) {
            STANDARD_SAMPLE_RATE=rate;
            for (short audioFormat : new short[] { AudioFormat.ENCODING_PCM_16BIT, AudioFormat.ENCODING_PCM_8BIT,  AudioFormat.ENCODING_PCM_FLOAT }) {
                for (short channelConfig : new short[] { AudioFormat.CHANNEL_IN_MONO }) {
                    try {
                        Log.d(tag, "Attempting rate " + rate + "Hz, bits: " + audioFormat + ", channel: "
                                + channelConfig);
                        int bufferSize = AudioRecord.getMinBufferSize(rate, channelConfig, audioFormat);

                        if (bufferSize != AudioRecord.ERROR_BAD_VALUE) {
                            // check if we can instantiate and have a success
                            AudioRecord recorder = new AudioRecord(AudioSource.DEFAULT, rate, channelConfig, audioFormat, bufferSize);

                            if (recorder.getState() == AudioRecord.STATE_INITIALIZED)
                                return recorder;
                        }
                    } catch (Exception e) {
                        Log.d(tag, rate + "Exception, keep trying.",e);
                    }
                }
            }
        }
        return null;
    }
    /**
    * Retrieves sound from the given audio file.
     */
    public Sound(String file) throws IOException {
        Log.i(tag, "File constructor start");
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
        Log.i(tag, "File constructor end");
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
    public static double getDataAt(int sample) {
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
            Log.e(tag, "Cloning Error");
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
