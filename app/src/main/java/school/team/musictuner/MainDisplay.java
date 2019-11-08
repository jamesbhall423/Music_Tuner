package school.team.musictuner;

public interface MainDisplay {
    void displayNote(Pitch note);

    void runOnUIThread(Runnable runnable);

}
