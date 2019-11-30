package school.team.musictuner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import java.io.IOException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
* The controller for the Note-Time function.
* Works with AdvancedDisplay
*
 */
public class AdvancedController {
    private static final String TAG = "Tuner Advanced";
    private boolean running;
    private Sound data; //Save and load
    private List<PlayedSection> sections; //Save and load
    private Converter converter;
    private AdvancedDisplay display;

    /**
    * Creates an AdvancedController
     */
    public AdvancedController(Converter converter) {

    }
    /**
    * Sets the display to the given instance
     */
    public void setDisplay(AdvancedDisplay display) {
        this.display=display;
    }
    /**
    * load sound data that was previously recorded by this program.
     */
    public void load(String file) {
        Log.i(TAG, "Starting load method in advanceController.");

        Log.i(TAG, "Finished load method.");
    }
    /**
    * save sound data in this instance
     */
    public void save(String file) {
        Log.i(TAG, "Starting save method in advanceController");

        Log.i(TAG, "Finished save method.");
    }

    /**
    * read and process the given audio file
     */
    public void read(String file) {
        try {
            data = new Sound(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
    * Starts a recording of audio input.
     */
    public void startRecording() {
running = true;
    }
    /**
    * Ends and processes the recording of audio input.
     */
    public void endRecording() {
running = false;
    }
    /**
    * Ends any listening / reading activities or background threads that may be running -
    * Frees all resources used by this controller.
     */
    public void destroy() {

    }
    /**
    * Stretch
    * - divides the the given section after the given number of seconds within the section.
    * For instance, if the current List of played sections has 3 Played sections as follows:
    * ABC GC DBC and the note G lasted 0.5 seconds
    *  then calling divide(1 , 0.5)//0 is the first element, 1 the second,
    *  Would result in the following output:
    * ABC G C DBC
    *
     */
    public void divide(int section, double timeInSection) {
        display.displaySections(sections);
    }
    /**
    * Stretch
    * Forces the given PlayedSection to have the given number of beats,
    *  by varying the number of beats per second.
    *
     */
    public void setBeats(int section, int beats) {

    }
    /**
     * Load up an activity to set the settings.
     */
    public static void settingsDisplay(Context context) {
        Intent intentLoad = new Intent(context, SettingsActivity.class);
        context.startActivity(intentLoad);
    }
    /**
     * Stretch -
     * Load an activity to train the Tuner to an instrument
     *
     */
    public static void trainingDisplay(Context context) {
        Intent intentLoad = new Intent(context, TrainingActivity.class);
        context.startActivity(intentLoad);
    }

    public static void mainDisplay(Context context) {
        Intent intentLoad = new Intent(context, MainActivity.class);
        context.startActivity(intentLoad);
    }

    public void startBackgroundThread(Runnable toRun)
    {
        if (!running)
            new Thread(new Runnable() {
            @Override
            public void run() {

                startRecording();
            }
        }).start();
    }

}
