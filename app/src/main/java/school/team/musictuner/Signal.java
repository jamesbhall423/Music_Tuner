package school.team.musictuner;

import static java.sql.Types.NULL;

public class Signal {
    public Pitch pitch;
    public int getFundamentalFrequency() {
        return 0;
    }
    Signal() {
        pitch = new Pitch();
    }
    public void tuneNotes() {
    }
}
