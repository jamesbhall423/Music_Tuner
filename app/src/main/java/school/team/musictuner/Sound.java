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
    private static int STANDARD_SAMPLE_RATE = 22050;
    private static int STANDARD_CHANNEL = AudioFormat.CHANNEL_IN_MONO;
    private static int STANDARD_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    private static int STANDARD_SOURCE = AudioSource.MIC;
    private static boolean STANDARDS_SET = false;
    private static int MIN_BUFFER_SIZE = 0;
    private static class MicRecord implements Serializable {
        private short[] data;
        private boolean recording = false;
        private AudioRecord record = null;
        private int read = 0;
        public MicRecord() {
            
        }
        public synchronized void startRecording(double maxTime) {
            findAudioRecord2();
            data = new short[(int) (maxTime*STANDARD_SAMPLE_RATE)];
            record = findAudioRecord(maxTime);
            recording=true;
            record.startRecording();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (MicRecord.this) {
                        while (recording&&read<data.length) {
                            int readNext=record.read(data,read,data.length-read);
                            if (read>=0) read+=readNext;
                            else Log.e(tag,"Error when recording: AudioRecorder: "+readNext);
                            try {
                                MicRecord.this.wait(5);
                            } catch (InterruptedException e) {
                                Log.e(tag,e.getMessage());
                            }
                        }
                        endRecording();
                    }
                }
            }).start();
        }
        public synchronized void endRecording() {
            if (recording) {
                recording=false;
                record.stop();
                record.release();
                record=null;
            }
        }
        public double get(int sample) {
            return data[sample];
        }
        public void set(int sample, double value) {
            data[sample]=(short)value;
        }
        public int length() {
            return read;
        }
        public void setLength(int length) {
            data=new short[length];
            read=length;
        }
    }
    private MicRecord audioRecord;
    private static final long SerialVersionUID = 1L;
//private int length;
private int mSampleRate;
//private MediaCodec mediaCodec;
//private MediaExtractor mediaExtractor;

    public Sound(double samplesPerSecond, int length) {
        Log.i(tag, "Samples/s, length constructor start");
        mSampleRate = (int) samplesPerSecond;
        audioRecord = new MicRecord();
        audioRecord.setLength(length);
        Log.i(tag, "Samples/s, length constructor end");
    }
    /**
    * Creates a sound by listening to audio for the given number of seconds.
     */
    //@RequiresApi(api = Build.VERSION_CODES.M)
    public Sound(double time) {
        Log.i(tag, "Only 'time' constructor start");
        audioRecord = new MicRecord();
        audioRecord.startRecording(time);
        try {
            Thread.sleep(1000*(int)time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(tag, "Error in 'time' constructor, sleep method");
        }
        audioRecord.endRecording();
        mSampleRate = STANDARD_SAMPLE_RATE;
        Log.i(tag, "Only 'time' constructor end");
    }
    private void readBuffer(AudioRecord record, short[] buffer) {
        long l = System.nanoTime();
        int sum = 0;
        while (sum<buffer.length) sum+=record.read(buffer,sum,buffer.length);
        double seconds = (System.nanoTime()-l)/1000000000;
        Log.d(tag,"read buffer, length = "+buffer.length+" rate="+record.getSampleRate()+" sec="+seconds);
    }
    private static int[] mSampleRates = new int[] { 44100, 22050, 11025, 8000 };
    //@RequiresApi(api = Build.VERSION_CODES.M)
    private static AudioRecord findAudioRecord(double time) {
        if (!STANDARDS_SET) findAudioRecord2();
        int multiplier = 0;
        switch (STANDARD_ENCODING) {
            case AudioFormat.ENCODING_PCM_8BIT:
                multiplier = 1;
            break;
            case AudioFormat.ENCODING_PCM_16BIT:
                multiplier = 2;
                break;
            case AudioFormat.ENCODING_PCM_FLOAT:
                multiplier = 4;
                break;
        }
        AudioRecord out =  new AudioRecord(STANDARD_SOURCE,STANDARD_SAMPLE_RATE,STANDARD_CHANNEL,STANDARD_ENCODING,Math.max(MIN_BUFFER_SIZE,(int)(time*multiplier*STANDARD_SAMPLE_RATE)));
        //Log.d(tag,"AudioRecord frames = "+out.getBufferSizeInFrames());
        return out;
    }
    private static void findAudioRecord2() {
        if (STANDARDS_SET) return;
        for (int rate : mSampleRates) {
            STANDARD_SAMPLE_RATE=rate;
            for (short audioFormat : new short[] { AudioFormat.ENCODING_PCM_16BIT, AudioFormat.ENCODING_PCM_8BIT,  AudioFormat.ENCODING_PCM_FLOAT }) {
                STANDARD_ENCODING=audioFormat;
                for (short channelConfig : new short[] { AudioFormat.CHANNEL_IN_MONO }) {
                    STANDARD_CHANNEL=channelConfig;
                    try {
                        Log.d(tag, "Attempting rate " + rate + "Hz, bits: " + audioFormat + ", channel: "
                                + channelConfig);
                        int bufferSize = AudioRecord.getMinBufferSize(rate, channelConfig, audioFormat);
                        MIN_BUFFER_SIZE=bufferSize;

                        if (bufferSize != AudioRecord.ERROR_BAD_VALUE) {
                            // check if we can instantiate and have a success
                            AudioRecord recorder = new AudioRecord(STANDARD_SOURCE, rate, channelConfig, audioFormat, bufferSize);

                            if (recorder.getState() == AudioRecord.STATE_INITIALIZED) {
                                STANDARDS_SET = true;
                                recorder.release();
                                return;
                            }

                        }
                    } catch (Exception e) {
                        Log.d(tag, rate + "Exception, keep trying.",e);
                    }
                }
            }
        }
        return;
    }
    /**
    * Retrieves sound from the given audio file.
     */
    public Sound(String file) throws IOException {
        Log.i(tag, "File constructor start");
        Log.e(tag,"File constructor not implemented");
        /*mediaExtractor = new MediaExtractor();

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
        }*/
        Log.i(tag, "File constructor end");
    }
    private Sound() {
        
    }

    /**
    * Starts listening to audio input
    * will listen for at most maxTime seconds.
    * returns a key that allows programmer to retrieve the sound.
     */
    public static Object startRecording(double maxTime) {
        MicRecord record = new MicRecord();
        record.startRecording(maxTime);
        return record;
    }
    /**
    * Ends and retrieves the sound recording represented by the key.
     */
    public static Sound endRecording(Object key) {
        MicRecord record = (MicRecord) key;
        Sound out = new Sound();
        out.mSampleRate=STANDARD_SAMPLE_RATE;
        out.audioRecord=record;
        return out;
    }
    /**
    * returns the air pressure value at the given sample.
     */
    public double getDataAt(int sample) {
        return audioRecord.data[sample];
    }
    /**
    * sets the air pressure value for the given sample.
    * should only be called when building the sound
     */
    public void setDataAt(int point, double value) {
        if (audioRecord!=null) audioRecord.data[point]=(short) value;
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
        return audioRecord.length();
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
