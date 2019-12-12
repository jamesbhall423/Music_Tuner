package school.team.musictuner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.media.MediaRecorder;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.SortedSet;

/**
* The controller for the initial activity.
* Music Tuner function.
* Works in conjunction with MainDisplay
 */
public class MainController {
    private Timer timer;
    private static final String TAG = "Tuner MainController";
    private static final String SETTINGS = "Settings";
    private MainDisplay mainDisplay;
    private Converter converter;
    private MediaRecorder mRecorder = null;
    private Settings settings;

    public void setDisplay(MainDisplay display) {
        Log.d(TAG,"set display "+display);
        this.mainDisplay=display;
    }
    public MainController() {
        converter=new Converter();
       // settings = new Settings();

       // converter.setSettings(settings);

    }
    public Converter getConverter() {
        return converter;
    }
    /**
    * Load up the Note-Time function activity.
     */

    public synchronized void advancedDisplay(final Context context) {
        Log.d(TAG,"Advanced Display Launch");
        Log.d(TAG,"Main Display "+mainDisplay);
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
        intentLoad.putExtra(SETTINGS, settings);
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
        if (timer!=null) return;
        timer = new Timer(true);
        final Timer run = timer;
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                //Log.v(TAG,"Main Controller Background Thread");
                if (timer==null) run.cancel();
                doRecording();
            }
        },0L,250L);
    }
    /**
    * Ends the thread that listens to audio.
     */
    public synchronized void pause() {
        Log.d(TAG,"Pause method");
        if (timer!=null) timer.cancel();
        timer=null;
        //If mRecorder is not recording, then it is not initialized which is
        //what we are checking here.
        /*if(mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }*/
    }

    private void doRecording() {
        // create Sound from audio Sound(time) 0.5 seconds
        //getSignal
        //if signal has pitches
        //display signal fundamental frequency, note
        Sound sound = new Sound(0.5);
        Signal signal = converter.getSignal(sound, 0, (int) 0.5);
        double frequencies = signal.getFundamentalFrequency();
        if(signal.frequencyThere())
        {
            
        }
    }

    public void loadSettings(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences(Settings.NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("NAME", "EMPTY");
        if(json.equals("EMPTY")) {
            settings = new Settings();
            System.out.println("CALLED!!!!");
        }
        else {
            settings = gson.fromJson(json, Settings.class);
        }

        converter.setSettings(settings);
    }

}
