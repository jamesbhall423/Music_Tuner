package school.team.musictuner;

import java.util.List;

/**
* The controller for the Note-Time function.
* Works with AdvancedDisplay
*
 */
public class AdvancedController {
    private Sound data; //Save and load
    private List<PlayedSection> sections; //Save and load
    private Converter converter; //Save and load - must also save the Settings in SharedPreferences
    /**
    * Creates an AdvancedController
     */
    public AdvancedController(Converter converter) {

    }
    /**
    * Sets the display to the given instance
     */
    public void setDisplay(AdvancedDisplay display) {

    }
    /**
    * load sound data that was previously recorded by this program.
     */
    public void load(String file) {

    }
    /**
    * save sound data in this instance
     */
    public void save(String file) {

    }
    /**
    * read and process the given audio file
     */
    public void read(String file) {

    }
    /**
    * Starts a recording of audio input.
     */
    public void startRecording() {

    }
    /**
    * Ends and processes the recording of audio input.
     */
    public void endRecording() {

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
    public void settingsDisplay() {

    }
    /**
     * Stretch -
     * Load an activity to train the Tuner to an instrument
     *
     */
    public void trainingDisplay() {

    }

}
