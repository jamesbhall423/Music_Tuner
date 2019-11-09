package school.team.musictuner;

import java.util.List;
/**
 * A display that represents the note-time function.
 * Works with AdvancedController
 * The showStatus and displaySections methods need to be called on the UI thread.
 */
public interface AdvancedDisplay {
    /**
    * Display the controllers current status:
    *  for instance, whether it is listening for input, reading a file, or processing data.
     */
    void showStatus(String status);

    void runOnUIThread(Runnable runnable);

    /**
    * Displays the given sections (each note) to the user.
     */
    void displaySections(List<PlayedSection> sections);

}
