package school.team.musictuner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.media.MediaRecorder;
import java.io.*;

/**
* The controller for the initial activity.
* Music Tuner function.
* Works in conjunction with MainDisplay
 */
public class MainController {
    private static final String TAG = "Tuner MainController";
    private MainDisplay mainDisplay;
    private Converter converter;
    private MediaRecorder mRecorder = null;

    public void setDisplay(MainDisplay display) {
        this.mainDisplay=mainDisplay;
    }
    public Converter getConverter() {
        return converter;
    }
    /**
    * Load up the Note-Time function activity.
     */

    public synchronized void advancedDisplay(final Context context) {
        Log.d(TAG,"Advanced Display Launch");
        pause();
        final Intent intentLoad = new Intent(context, AdvancedActivity.class);
        mainDisplay.runOnUiThread(new Runnable(){ public void run() {context.startActivity(intentLoad);}});
    }
    /**
    * Load up an activity to set the settings.
     */
    public synchronized void settingsDisplay(final Context context) {
        Log.d(TAG,"Settings Display Launch");
        pause();
        final Intent intentLoad = new Intent(context, SettingsActivity.class);
        mainDisplay.runOnUiThread(new Runnable(){ public void run() {context.startActivity(intentLoad);}});
    }
    /**
    * Stretch -
    * Load an activity to train the Tuner to an instrument
    *
     */
    public synchronized void trainingDisplay(final Context context) {
        Log.d(TAG,"Training Display Launch");
        pause();
        final Intent intentLoad = new Intent(context, TrainingActivity.class);
        mainDisplay.runOnUiThread(new Runnable(){ public void run() {context.startActivity(intentLoad);}});
    }
    /**
    * Starts a background thread (unless there is one already running) that listens for audio notes and displays them to MainDisplay
     * If a background thread is already running, does nothing.
     */
    public synchronized void start(){
        /*String mFileName = null;

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e("recordError", "prepare() failed. Can't record functionally.");
        }

        mRecorder.start();*/
    }
    /**
    * Ends the thread that listens to audio.
     */
    public synchronized void pause() {
        //If mRecorder is not recording, then it is not initialized which is
        //what we are checking here.
        if(mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
    }
    private void doRecording() {
        // create Sound from audio Sound(time) 0.5 seconds
        //getSignal
        //if signal has pitches
        //display signal fundamental frequency, note
    }

}
