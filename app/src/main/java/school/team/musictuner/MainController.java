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

    public static void advancedDisplay(Context context) {
        Log.d(TAG,"Advanced Display Launch");
        Intent intentLoad = new Intent(context, AdvancedActivity.class);
        context.startActivity(intentLoad);
    }
    /**
    * Load up an activity to set the settings.
     */
    public static void settingsDisplay(Context context) {
        Log.d(TAG,"Settings Display Launch");
        Intent intentLoad = new Intent(context, SettingsActivity.class);
        context.startActivity(intentLoad);
    }
    /**
    * Stretch -
    * Load an activity to train the Tuner to an instrument
    *
     */
    public static void trainingDisplay(Context context) {
        Log.d(TAG,"Training Display Launch");
        Intent intentLoad = new Intent(context, TrainingActivity.class);
        context.startActivity(intentLoad);
    }
    /**
    * Starts a background thread that listens for audio notes and displays them to MainDisplay
     */
    public void start(){

    }
    /**
    * Ends the thread that listens to audio.
     */
    public void pause() {

    }

}
