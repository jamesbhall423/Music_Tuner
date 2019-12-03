package school.team.musictuner;
/**
* Represents the entrance to the app. The main activity will implement this interface.
* Works in conjunction with MainController
 */
public interface MainDisplay {
    /**
    * Displays the given pitch and its deviation from the ideal note.
    * Must be called on UI thread
     */
    public void displayNote(Pitch pitch, Note note);

    public void runOnUiThread(Runnable runnable);

}
