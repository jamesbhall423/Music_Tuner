package school.team.musictuner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.io.IOException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
* The controller for the Note-Time function.
* Works with AdvancedDisplay
 * With the exception of the constructor, setDisplay, and startBackground Thread methods, all methods must be called on background thread.
*
 */
public class AdvancedController {
    private static final String TAG = "Tuner Advanced";
    private boolean running;
    private Sound data; //Save and load
    private List<PlayedSection> sections; //Save and load
    private Converter converter;
    private AdvancedDisplay display;
    private Object recording;

    /**
    * Creates an AdvancedController
     */
    public AdvancedController(Converter converter) {

    }
    /**
    * Sets the display to the given instance
     */
    public synchronized void setDisplay(AdvancedDisplay display) {
        this.display=display;
    }
    /**
    * load sound data that was previously recorded by this program.
     */
    public synchronized void load(String file) {
        Log.i(TAG, "Starting load method in advanceController.");

        try {
            FileInputStream loadFile = new FileInputStream(file);
            ObjectInputStream load = new ObjectInputStream(loadFile);

            data = (Sound) load.readObject();
            sections = (List<PlayedSection>) load.readObject();
            if (sections!=null) display.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    display.displaySections(sections);
                }
            });

            load.close();
        }
        catch(Exception exc) {
            Log.e(TAG,"Error! File doesn't exist or can't be found.");
        }

        Log.i(TAG, "Finished load method.");
    }
    /**
    * save sound data in this instance
     */
    public synchronized void save(String file) {
        Log.i(TAG, "Starting save method in advanceController");

        try {
            FileOutputStream saveFile = new FileOutputStream(file);
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            save.writeObject(data);
            save.writeObject(sections);

            save.close();
        } catch (Exception exe) {
            Log.e(TAG,"Can't save file!");
        }
        Log.i(TAG, "Finished save method.");
    }

    /**
    * read and process the given audio file
     */
    public synchronized void read(String file) {
        try {
            data = new Sound(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
    * Starts a recording of audio input, if no recording is currently in progress.
     * If a recording is in progress, does nothing.
     */
    public synchronized void startRecording() {
        running = true;
        display.showStatus("Recording");
        recording = data.startRecording(1000);
    }
    /**
    * Ends and processes the recording of audio input.
     */
    public synchronized void endRecording() {
        running = false;
        data.endRecording(recording);
        processRecording();
    }

    private void processRecording(){
        display.showStatus("Processing");
        Timeline timeline = converter.soundTimeline(data);
        PlayedSection playedSection = converter.section(timeline);
        sections = new ArrayList<PlayedSection>();
        sections.add(playedSection);
        display.displaySections(sections);
        display.showStatus("Finished");
    }
    /**
    * Ends any listening / reading activities or background threads that may be running -
    * Frees all resources used by this controller.
     */
    public synchronized void destroy() {
        if (running) data.endRecording(recording);
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
    public synchronized void divide(int section, double timeInSection) {
        display.displaySections(sections);
    }
    /**
    * Stretch
    * Forces the given PlayedSection to have the given number of beats,
    *  by varying the number of beats per second.
    *
     */
    public synchronized void setBeats(int section, int beats) {

    }
    /**
     * Load up an activity to set the settings.
     */
    public synchronized void settingsDisplay(final Context context) {
        destroy();
        final Intent intentLoad = new Intent(context, SettingsActivity.class);
        display.runOnUIThread(new Runnable(){ public void run() {context.startActivity(intentLoad);}});
    }
    /**
     * Stretch -
     * Load an activity to train the Tuner to an instrument
     *
     */
    public synchronized void trainingDisplay(final Context context) {
       destroy();
        final Intent intentLoad = new Intent(context, TrainingActivity.class);
        display.runOnUIThread(new Runnable(){ public void run() {context.startActivity(intentLoad);}});
    }

    public synchronized void mainDisplay(final Context context) {
        destroy();
        final Intent intentLoad = new Intent(context, MainActivity.class);
        display.runOnUIThread(new Runnable(){ public void run() {context.startActivity(intentLoad);}});
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
