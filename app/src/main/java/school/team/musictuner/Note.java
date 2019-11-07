package school.team.musictuner;

public class Note implements Comparable<Note> {

    final double frequency;
    final String name =  "note";
    final TuneSet tuneSet = new TuneSet();

    public Note (double frequency, Tune tune)
    {
        this.frequency = frequency;
    }

    public int compareTo(Note other) {
        return 0;

    }

    public static Note getNote(double frequency, Tune tune) {
        return new Note(frequency, tune);
    }

    boolean isSharp() {
        return false;
    }

    boolean isFlat() {
        return false;
    }

    char halfStep() {
        return 'b';
    } //flat b, or sharp #

    char letter() { //capitalized
return 'C';
    }

    int octave() {
        return 0;
    }

}
