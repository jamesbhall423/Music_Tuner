package school.team.musictuner;

public class PlayedNote implements Comparable<PlayedNote> {

    Pitch pitch;
    private int start, end;
    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }
    public Pitch getPitch() {
        return pitch;
    }
    public int start() {
        return 0;
    }
    public int end() {
        return 0;
    }
    void setTimeFrame(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public int compareTo(PlayedNote o) {
        return 0;
    }
}
