package school.team.musictuner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
* The controller for the initial activity.
* Music Tuner function.
* Works in conjunction with MainDisplay
 */
public class MainController {
    private static final String TAG = "Tuner MainController";
    private MainDisplay mainDisplay;
    private Converter converter;

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
        mainDisplay.runOnUIThread(new Runnable(){ public void run() {context.startActivity(intentLoad);}});
    }
    /**
    * Load up an activity to set the settings.
     */
    public synchronized void settingsDisplay(final Context context) {
        Log.d(TAG,"Settings Display Launch");
        pause();
        final Intent intentLoad = new Intent(context, SettingsActivity.class);
        mainDisplay.runOnUIThread(new Runnable(){ public void run() {context.startActivity(intentLoad);}});
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
        mainDisplay.runOnUIThread(new Runnable(){ public void run() {context.startActivity(intentLoad);}});
    }
    /**
    * Starts a background thread (unless there is one already running) that listens for audio notes and displays them to MainDisplay
     * If a background thread is already running, does nothing.
     */
    public synchronized void start(){

    }
    /**
    * Ends the thread that listens to audio.
     */
    public synchronized void pause() {

    }

}
