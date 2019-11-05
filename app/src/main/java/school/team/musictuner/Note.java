package school.team.musictuner;

public class Note implements Comparable<Note> {

    final double frequency;
    final String name;
    final TuneSet tuneSet;

    public Note (double frequency, Tune tune)
    {
        this.frequency = frequency;
    }

    int compareTo(Note other) {

    }

    static Note getNote(double frequency, Tune) {
        return new Note(frequency, Tune);
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
