package school.team.musictuner;

import java.util.SortedSet;

public class PlayedSection {
    private SortedSet<PlayedNote> notes;
    private int beats;
    private double beatsPerSecond;
    PlayedSection(int beats, double beatsPerSecond) {

    }

    public SortedSet<PlayedNote> getNotes() {
       return notes;
    }

   public int getBeats() {
        return beats;
   }
    public double beatsPerSecond() {
        return beatsPerSecond;
    }

}
